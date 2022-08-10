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

import com.centurion.command.UserCommand;
import com.centurion.core.dto.UserDTO;
import com.centurion.core.service.RoleService;
import com.centurion.core.service.UserService;
import com.centurion.core.service.impl.RoleServiceImpl;
import com.centurion.core.service.impl.UserServiceImpl;
import com.centurion.core.web.common.WebConstant;
import com.centurion.core.web.utils.FormUtil;
import com.centurion.core.web.utils.WebCommonUtil;

@WebServlet(urlPatterns = { "/admin-user-list.html", "/ajax-admin-user-edit.html" })
public class UserController extends HttpServlet {
	UserService userService = new UserServiceImpl();
	RoleService roleService = new RoleServiceImpl();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserCommand command = FormUtil.populate(UserCommand.class, request);
		UserDTO pojo = command.getPojo();
		ResourceBundle bundle = ResourceBundle.getBundle("ApplicationResources");
		if (command.getUrlType() != null && command.getUrlType().equals(WebConstant.URL_LIST)) {
			Map<String, Object> mapProperty = new HashMap<String, Object>();
			Object[] objects = userService.findByProperty(mapProperty, command.getSortExpression(),
					command.getSortDirection(), command.getFirstItem(), command.getMaxPageItems());
			command.setListResult((List<UserDTO>) objects[1]);
			command.setTotalItems(Integer.parseInt(objects[0].toString()));
			request.setAttribute(WebConstant.LIST_ITEMS, command);
			if (command.getCrudaction() != null && command.getCrudaction().equals(WebConstant.REDIRECT_INSERT)) {
				Map<String, String> mapMessage = new HashMap<String, String>();
				mapMessage.put(WebConstant.REDIRECT_INSERT, bundle.getString("label.user.message.add.success"));
				WebCommonUtil.addRedirectMessage(request, command.getCrudaction(), mapMessage);
			}
			RequestDispatcher rd = request.getRequestDispatcher("/views/admin/user/list.jsp");
			rd.forward(request, response);
		} else if (command.getUrlType() != null && command.getUrlType().equals(WebConstant.URL_EDIT)) {
			if (pojo != null && pojo.getUserId() != null) {
				command.setPojo(userService.findById(pojo.getUserId()));
			}
			command.setRoles(roleService.findAll());
			request.setAttribute(WebConstant.FORM_ITEM, command);
			RequestDispatcher rd = request.getRequestDispatcher("/views/admin/user/edit.jsp");
			rd.forward(request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserCommand command = FormUtil.populate(UserCommand.class, request);
		if (command.getUrlType().equals(WebConstant.URL_EDIT)) {
			if (command.getCrudaction() != null && command.getCrudaction().equals(WebConstant.INSERT_UPDATE)) {
				request.setAttribute(WebConstant.MESSAGE_RESPONSE, "insert_success");
			}
		}
		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/user/edit.jsp");
		rd.forward(request, response);

	}
}