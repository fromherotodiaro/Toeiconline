package com.centurion.controller.admin;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.centurion.command.UserCommand;
import com.centurion.core.dto.UserDTO;
import com.centurion.core.service.UserService;
import com.centurion.core.service.impl.UserServiceImpl;
import com.centurion.core.web.common.WebConstant;
import com.centurion.core.web.utils.FormUtil;

@WebServlet(urlPatterns = { "/admin-user-list.html" })
public class UserController extends HttpServlet {
	UserService userService = new UserServiceImpl();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserCommand command = FormUtil.populate(UserCommand.class, request);
		UserDTO pojo = command.getPojo();
		if (command.getUrlType().equals(WebConstant.URL_LIST)) {
			Map<String, Object> mapProperty = new HashMap<String, Object>();
			Object[] objects = userService.findByProperty(mapProperty, command.getSortExpression(),
					command.getSortDirection(), command.getFirstItem(), command.getMaxPageItems());
			command.setListResult((List<UserDTO>) objects[1]);
			command.setTotalItems(Integer.parseInt(objects[0].toString()));
			request.setAttribute(WebConstant.LIST_ITEMS, command);
			RequestDispatcher rd = request.getRequestDispatcher("/views/admin/user/list.jsp");
			rd.forward(request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}
}