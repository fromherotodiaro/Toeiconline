package com.centurion.controller.admin;

import java.io.IOException;
import java.util.ResourceBundle;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.centurion.command.ListenGuideLineCommand;
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

//		command.setMaxPageItems(2);
//		RequestUtil.initSearchBear(req, command);
//		Object[] objects = guidelineService.findListenGuidelineByProperties(null, null, command.getSortDirection(),
//				command.getSortDirection(), command.getFirstItem(), command.getMaxPageItems());
//		command.setListResult((List<ListenGuidelineDTO>) objects[1]);
//		command.setTotalItems(Integer.parseInt(objects[0].toString()));
//		req.setAttribute(WebConstant.ALERT, WebConstant.TYPE_SUCCESS);
//		req.setAttribute(WebConstant.MESSAGE_RESPONSE, resourceBundle.getString("label.guideline.listen.add.success"));

		req.setAttribute(WebConstant.LIST_ITEMS, command);
		if (command.getUrlType() != null && command.getUrlType().equals(WebConstant.URL_LIST)) {
			RequestDispatcher rd = req.getRequestDispatcher("/views/admin/listenguideline/list.jsp");
			rd.forward(req, resp);
		} else if (command.getUrlType() != null && command.getUrlType().equals(WebConstant.URL_EDIT)) {
			RequestDispatcher rd = req.getRequestDispatcher("/views/admin/listenguideline/edit.jsp");
			rd.forward(req, resp);
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}

}
