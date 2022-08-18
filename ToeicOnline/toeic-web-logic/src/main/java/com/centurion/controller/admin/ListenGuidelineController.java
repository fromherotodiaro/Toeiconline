package com.centurion.controller.admin;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.centurion.command.ListenGuidelineCommand;
import com.centurion.core.commons.utils.UploadUtil;
import com.centurion.core.dto.ListenGuidelineDTO;
import com.centurion.core.web.common.WebConstant;
import com.centurion.core.web.utils.FormUtil;
import com.centurion.core.web.utils.RequestUtil;
import com.centurion.core.web.utils.SingletonServiceUtil;

//@WebServlet("/admin-guideline-listen-list.html")
@WebServlet(urlPatterns = { "/admin-guideline-listen-list.html", "/admin-guideline-listen-edit.html" })
public class ListenGuidelineController extends HttpServlet {

	private final Logger log = Logger.getLogger(this.getClass());

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		ListenGuidelineCommand command = FormUtil.populate(ListenGuidelineCommand.class, req);
		ListenGuidelineDTO pojo = command.getPojo();
		ResourceBundle resourceBundle = ResourceBundle.getBundle("ApplicationResources");
//		HttpSession session = req.getSession();

//		req.setAttribute(WebConstant.ALERT, WebConstant.TYPE_SUCCESS);
//		req.setAttribute(WebConstant.MESSAGE_RESPONSE, resourceBundle.getString("label.guideline.listen.add.success"));
//		if (session != null) {
//			req.setAttribute(WebConstant.ALERT, session.getAttribute(WebConstant.ALERT));
//			req.setAttribute(WebConstant.MESSAGE_RESPONSE, session.getAttribute(WebConstant.MESSAGE_RESPONSE));
//		}

		if (command.getUrlType() != null && command.getUrlType().equals(WebConstant.URL_LIST)) {
			executeSearchListenGuideline(req, command);
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
//		session.removeAttribute(WebConstant.ALERT);
//		session.removeAttribute(WebConstant.MESSAGE_RESPONSE);
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
		HttpSession session = req.getSession();
//		Set<String> valueTitle = buildSetValueListenGuideline();

//		try {
//			Object[] objects = uploadUtil.writeOrUpdateFile(req, valueTitle, WebConstant.LISTENGUIDELINE);
//			Map<String, String> mapValue = (Map<String, String>) objects[3];
//			command = returnValueListenGuidelineCommand(valueTitle, command, mapValue);
//
//			session.setAttribute(WebConstant.ALERT, WebConstant.TYPE_SUCCESS);
//			session.setAttribute(WebConstant.MESSAGE_RESPONSE, bundle.getString("label.guideline.listen.add.success"));
//		} catch (FileUploadException e) {
//			log.error(e.getMessage(), e);
//			session.setAttribute(WebConstant.ALERT, WebConstant.TYPE_ERROR);
//			session.setAttribute(WebConstant.MESSAGE_RESPONSE, bundle.getString("label.error"));
//		} catch (Exception e) {
//			log.error(e.getMessage(), e);
//			session.setAttribute(WebConstant.ALERT, WebConstant.TYPE_ERROR);
//			session.setAttribute(WebConstant.MESSAGE_RESPONSE, bundle.getString("label.error"));
//		}
		resp.sendRedirect("admin-guideline-listen-list.html?urlType=url_list");
	}

//	private ListenGuideLineCommand returnValueListenGuidelineCommand(Set<String> valueTitle,
//			ListenGuideLineCommand command, Map<String, String> mapValue) {
//		for (String item : valueTitle) {
//			if (mapValue.containsKey(item)) {
//				if (item.equals("pojo.title")) {
//					command.getPojo().setTitle(mapValue.get(item));
//				} else if (item.equals("pojo.content")) {
//					command.getPojo().setContent(mapValue.get(item));
//				}
//			}
//		}
//		return command;
//	}

//	private Set<String> buildSetValueListenGuideline() {
//		Set<String> returnValue = new HashSet<String>();
//		returnValue.add("pojo.title");
//		returnValue.add("pojo.content");
//		return returnValue;
//
//	}

}
