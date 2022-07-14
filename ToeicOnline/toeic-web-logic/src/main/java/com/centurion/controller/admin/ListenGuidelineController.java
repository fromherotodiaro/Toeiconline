package com.centurion.controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.centurion.command.ListenGuideLineCommand;
import com.centurion.core.dto.ListenGuidelineDTO;
import com.centurion.core.service.ListenGuidelineService;
import com.centurion.core.service.impl.ListenGuidelineServiceImpl;
import com.centurion.core.web.common.WebConstant;
import com.centurion.core.web.utils.RequestUtil;

@WebServlet("/admin-guideline-listen-list.html")
public class ListenGuidelineController extends HttpServlet {

	private ListenGuidelineService guidelineService = new ListenGuidelineServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		ListenGuideLineCommand command = new ListenGuideLineCommand();

		command.setMaxPageItems(2);
		RequestUtil.initSearchBear(req, command);

		Object[] objects = guidelineService.findListenGuidelineByProperties(null, null, command.getSortDirection(),
				command.getSortDirection(), command.getFirstItem(), command.getMaxPageItems());
		command.setListResult((List<ListenGuidelineDTO>) objects[1]);
		command.setTotalItems(Integer.parseInt(objects[0].toString()));
		req.setAttribute(WebConstant.LIST_ITEMS, command);
		RequestDispatcher rd = req.getRequestDispatcher("/views/admin/listenguideline/list.jsp");
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}

}
