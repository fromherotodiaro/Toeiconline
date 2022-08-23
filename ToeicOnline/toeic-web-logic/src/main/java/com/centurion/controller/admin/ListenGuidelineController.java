package com.centurion.controller.admin;

import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.exception.ConstraintViolationException;

import com.centurion.command.ListenGuidelineCommand;
import com.centurion.core.commons.utils.UploadUtil;
import com.centurion.core.dto.ListenGuidelineDTO;
import com.centurion.core.web.common.WebConstant;
import com.centurion.core.web.utils.FormUtil;
import com.centurion.core.web.utils.RequestUtil;
import com.centurion.core.web.utils.SingletonServiceUtil;
import com.centurion.core.web.utils.WebCommonUtil;

//@WebServlet("/admin-guideline-listen-list.html")
@WebServlet(urlPatterns = { "/admin-guideline-listen-list.html", "/admin-guideline-listen-edit.html" })
public class ListenGuidelineController extends HttpServlet {

	private final Logger log = Logger.getLogger(this.getClass());

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		ListenGuidelineCommand command = FormUtil.populate(ListenGuidelineCommand.class, req);
		ListenGuidelineDTO pojo = command.getPojo();
		ResourceBundle resourceBundle = ResourceBundle.getBundle("ApplicationResources");
		if (command.getUrlType() != null && command.getUrlType().equals(WebConstant.URL_LIST)) {
			executeSearchListenGuideline(req, command);
			if (command.getCrudaction() != null) {
				Map<String, String> mapMessage = buidMapRedirectMessage(resourceBundle);
				WebCommonUtil.addRedirectMessage(req, command.getCrudaction(), mapMessage);
			}
			req.setAttribute(WebConstant.LIST_ITEMS, command);
			RequestDispatcher rd = req.getRequestDispatcher("/views/admin/listenguideline/list.jsp");
			rd.forward(req, resp);
		} else if (command.getUrlType() != null && command.getUrlType().equals(WebConstant.URL_EDIT)) {
			if (command.getPojo() != null && command.getPojo().getListenGuidelineId() != null) {
				command.setPojo(SingletonServiceUtil.getListenGuidelineServiceInstance()
						.findByListenGuidelineId("listenGuidelineId", command.getPojo().getListenGuidelineId()));
			}
			req.setAttribute(WebConstant.FORM_ITEM, command);
			RequestDispatcher rd = req.getRequestDispatcher("/views/admin/listenguideline/edit.jsp");
			rd.forward(req, resp);
		}
	}

	private Map<String, String> buidMapRedirectMessage(ResourceBundle resourceBundle) {
		Map<String, String> mapMessage = new HashMap<String, String>();
		mapMessage.put(WebConstant.REDIRECT_INSERT, resourceBundle.getString("label.listenguideline.add.success"));
		mapMessage.put(WebConstant.REDIRECT_UPDATE, resourceBundle.getString("label.listenguideline.update.success"));
		mapMessage.put(WebConstant.REDIRECT_DELETE, resourceBundle.getString("label.listenguideline.delete.success"));
		mapMessage.put(WebConstant.REDIRECT_ERROR, resourceBundle.getString("label.message.error"));
		return mapMessage;
	}

	private void executeSearchListenGuideline(HttpServletRequest req, ListenGuidelineCommand command) {
		Map<String, Object> properties = buildMapProperties(command);
		RequestUtil.initSearchBear(req, command);
		Object[] objects = SingletonServiceUtil.getListenGuidelineServiceInstance().findListenGuidelineByProperties(
				properties, command.getSortDirection(), command.getSortDirection(), command.getFirstItem(),
				command.getMaxPageItems());
		command.setListResult((List<ListenGuidelineDTO>) objects[1]);
		command.setTotalItems(Integer.parseInt(objects[0].toString()));
	}

	private Map<String, Object> buildMapProperties(ListenGuidelineCommand command) {
		Map<String, Object> properties = new HashMap<String, Object>();

		if (StringUtils.isNotBlank(command.getPojo().getTitle())) {
			properties.put("title", command.getPojo().getTitle());
		}
		return properties;
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ListenGuidelineCommand command = new ListenGuidelineCommand();
		ResourceBundle bundle = ResourceBundle.getBundle("ApplicationResources");
		UploadUtil uploadUtil = new UploadUtil();
		Set<String> valueTitle = buildSetValueListenGuideline();
		Object[] objects = uploadUtil.writeOrUpdateFile(req, valueTitle, WebConstant.LISTENGUIDELINE);
		boolean checkStatusUploadImage = (Boolean) objects[0];
		if (!checkStatusUploadImage) {
			resp.sendRedirect("/admin-guideline-listen-list.html?urlType=url_list&&crudaction=redirect_error");
		} else {
			ListenGuidelineDTO dto = command.getPojo();
			if (StringUtils.isNotBlank(objects[2].toString())) {
				dto.setImage(objects[2].toString());
			}
			Map<String, String> mapValue = (Map<String, String>) objects[3];
			dto = returnValueOfDTO(dto, mapValue);
			if (dto != null) {
				if (dto.getListenGuidelineId() != null) {
					// update
					ListenGuidelineDTO listenGuidelineDTO = SingletonServiceUtil.getListenGuidelineServiceInstance()
							.findByListenGuidelineId("listenGuidelineId", dto.getListenGuidelineId());
					if (dto.getImage() == null) {
						dto.setImage(listenGuidelineDTO.getImage());
					}
					dto.setCreatedDate(listenGuidelineDTO.getCreatedDate());
					ListenGuidelineDTO result = SingletonServiceUtil.getListenGuidelineServiceInstance()
							.updateListenGuideline(dto);
					if (result != null) {
						resp.sendRedirect(
								"admin-guideline-listen-list.html?urlType=url_list&crudaction=redirect_update");
					} else {
						resp.sendRedirect(
								"admin-guideline-listen-list.html?urlType=url_list&crudaction=redirect_error");
					}
				} else {
					try {
						SingletonServiceUtil.getListenGuidelineServiceInstance().saveListenGuideline(dto);
						resp.sendRedirect(
								"admin-guideline-listen-list.html?urlType=url_list&crudaction=redirect_insert");
					} catch (ConstraintViolationException e) {
						log.error(e.getMessage(), e);
						resp.sendRedirect(
								"admin-guideline-listen-list.html?urlType=url_list&crudaction=redirect_error");
					} catch (SQLIntegrityConstraintViolationException e) {
						log.error(e.getMessage(), e);
						resp.sendRedirect(
								"admin-guideline-listen-list.html?urlType=url_list&crudaction=redirect_error");
					} catch (Exception e) {
						log.error(e.getMessage(), e);
						resp.sendRedirect(
								"admin-guideline-listen-list.html?urlType=url_list&crudaction=redirect_error");
					}
				}
			}
		}

	}

	private ListenGuidelineDTO returnValueOfDTO(ListenGuidelineDTO dto, Map<String, String> mapValue) {
		for (Map.Entry<String, String> item : mapValue.entrySet()) {
			if (item.getKey().equals("pojo.title")) {
				dto.setTitle(item.getValue());
			} else if (item.getKey().equals("pojo.content")) {
				dto.setContent(item.getValue());
			} else if (item.getKey().equals("pojo.listenGuidelineId")) {
				dto.setListenGuidelineId(Integer.parseInt(item.getValue().toString()));
			}
		}
		return dto;
	}

	private Set<String> buildSetValueListenGuideline() {
		Set<String> returnValue = new HashSet<String>();
		returnValue.add("pojo.title");
		returnValue.add("pojo.content");
		returnValue.add("pojo.listenGuidelineId");
		return returnValue;

	}

}
