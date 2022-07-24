package com.centurion.controller.admin;

import java.io.IOException;
import java.util.ResourceBundle;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileUploadException;

import com.centurion.command.ListenGuideLineCommand;
import com.centurion.core.commons.utils.UploadUtil;
import com.centurion.core.web.common.WebConstant;
import com.centurion.core.web.utils.FormUtil;

//@WebServlet("/admin-guideline-listen-list.html")
@WebServlet(urlPatterns = { "/admin-guideline-listen-list.html", "/admin-guideline-listen-edit.html" })
public class ListenGuidelineController extends HttpServlet {

//	private ListenGuidelineService guidelineService = new ListenGuidelineServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		ListenGuideLineCommand command = FormUtil.populate(ListenGuideLineCommand.class, req);
		ResourceBundle resourceBundle = ResourceBundle.getBundle("ApplicationResources");
		HttpSession session = req.getSession();

//		command.setMaxPageItems(2);
//		RequestUtil.initSearchBear(req, command);
//		Object[] objects = guidelineService.findListenGuidelineByProperties(null, null, command.getSortDirection(),
//				command.getSortDirection(), command.getFirstItem(), command.getMaxPageItems());
//		command.setListResult((List<ListenGuidelineDTO>) objects[1]);
//		command.setTotalItems(Integer.parseInt(objects[0].toString()));
//		req.setAttribute(WebConstant.ALERT, WebConstant.TYPE_SUCCESS);
//		req.setAttribute(WebConstant.MESSAGE_RESPONSE, resourceBundle.getString("label.guideline.listen.add.success"));
		if (session != null) {
			req.setAttribute(WebConstant.ALERT, session.getAttribute(WebConstant.ALERT));
			req.setAttribute(WebConstant.MESSAGE_RESPONSE, session.getAttribute(WebConstant.MESSAGE_RESPONSE));
		}

		req.setAttribute(WebConstant.LIST_ITEMS, command);
		if (command.getUrlType() != null && command.getUrlType().equals(WebConstant.URL_LIST)) {
			RequestDispatcher rd = req.getRequestDispatcher("/views/admin/listenguideline/list.jsp");
			rd.forward(req, resp);
		} else if (command.getUrlType() != null && command.getUrlType().equals(WebConstant.URL_EDIT)) {
			RequestDispatcher rd = req.getRequestDispatcher("/views/admin/listenguideline/edit.jsp");
			rd.forward(req, resp);
		}
		session.removeAttribute(WebConstant.ALERT);
		session.removeAttribute(WebConstant.MESSAGE_RESPONSE);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ResourceBundle bundle = ResourceBundle.getBundle("ApplicationResources");
		UploadUtil uploadUtil = new UploadUtil();
		HttpSession session = req.getSession();
		try {
			uploadUtil.writeOrUpdateFile(req);
			/*
			 * request.setAttribute(WebConstant.ALERT, WebConstant.TYPE_SUCCESS);
			 * request.setAttribute(WebConstant.MESSAGE_RESPONSE,
			 * bundle.getString("label.guideline.listen.add.success"));
			 */
			session.setAttribute(WebConstant.ALERT, WebConstant.TYPE_SUCCESS);
			session.setAttribute(WebConstant.MESSAGE_RESPONSE, bundle.getString("label.guideline.listen.add.success"));
		} catch (FileUploadException e) {
			req.setAttribute(WebConstant.ALERT, WebConstant.TYPE_ERROR);
			req.setAttribute(WebConstant.MESSAGE_RESPONSE, bundle.getString("label.error"));
		} catch (Exception e) {
			req.setAttribute(WebConstant.ALERT, WebConstant.TYPE_ERROR);
			req.setAttribute(WebConstant.MESSAGE_RESPONSE, bundle.getString("label.error"));
		}
		resp.sendRedirect("admin-guideline-listen-edit.html?urlType=url_list");
	}

}
