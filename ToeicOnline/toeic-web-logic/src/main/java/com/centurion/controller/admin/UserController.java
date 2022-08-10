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

import org.apache.log4j.Logger;

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
	private final Logger log = Logger.getLogger(this.getClass());
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
			if (command.getCrudaction() != null) {
				Map<String, String> mapMessage = buildMapRedirectMessage(bundle);
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

	private Map<String, String> buildMapRedirectMessage(ResourceBundle bundle) {
		Map<String, String> mapMessage = new HashMap<String, String>();
		mapMessage.put(WebConstant.REDIRECT_INSERT, bundle.getString("label.user.message.add.success"));
		mapMessage.put(WebConstant.REDIRECT_UPDATE, bundle.getString("label.user.message.update.success"));
		mapMessage.put(WebConstant.REDIRECT_DELETE, bundle.getString("label.user.message.delete.success"));
		mapMessage.put(WebConstant.REDIRECT_ERROR, bundle.getString("label.message.error"));
		return mapMessage;
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			UserCommand command = FormUtil.populate(UserCommand.class, request);
			UserDTO pojo = command.getPojo();
			if (command.getUrlType().equals(WebConstant.URL_EDIT)) {
				if (command.getCrudaction() != null && command.getCrudaction().equals(WebConstant.INSERT_UPDATE)) {
					if (pojo != null && pojo.getUserId() != null) {
//						update
						userService.updateUser(pojo);
						request.setAttribute(WebConstant.MESSAGE_RESPONSE, WebConstant.REDIRECT_UPDATE);
					} else {
//						 save
						userService.saveUser(pojo);
						request.setAttribute(WebConstant.MESSAGE_RESPONSE, WebConstant.REDIRECT_INSERT);
					}
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			request.setAttribute(WebConstant.MESSAGE_RESPONSE, WebConstant.REDIRECT_ERROR);
		}

		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/user/edit.jsp");
		rd.forward(request, response);

	}
}