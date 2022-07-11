package com.centurion.controller.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.centurion.command.UserCommand;
import com.centurion.core.dto.UserDTO;
import com.centurion.core.service.UserService;
import com.centurion.core.service.impl.UserServiceImpl;
import com.centurion.core.web.common.WebConstant;
import com.centurion.core.web.utils.FormUtil;

@WebServlet("/login.html")
public class LoginController extends HttpServlet {
	private final Logger log = Logger.getLogger(this.getClass());

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		RequestDispatcher rd = req.getRequestDispatcher("/views/web/login.jsp");
		rd.forward(req, resp);

	}

	@Override

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UserCommand command = FormUtil.populate(UserCommand.class, req);
		UserDTO pojo = command.getPojo();
		UserService userService = new UserServiceImpl();
		try {
			if (userService.isUserExist(pojo) != null) {
				if (userService.findRoleByUser(pojo) != null && userService.findRoleByUser(pojo).getRoleDTO() != null) {
					if (userService.findRoleByUser(pojo).getRoleDTO().getName().equals(WebConstant.ROLE_ADMIN)) {
						req.setAttribute(WebConstant.ALERT, WebConstant.TYPE_SUCCESS);
						req.setAttribute(WebConstant.MESSAGE_RESPONSE, "Bạn là admin");
					} else if (userService.findRoleByUser(pojo).getRoleDTO().getName().equals(WebConstant.ROLE_USER)) {
						req.setAttribute(WebConstant.ALERT, WebConstant.TYPE_SUCCESS);
						req.setAttribute(WebConstant.MESSAGE_RESPONSE, "Bạn là User");
					}
				}
			}
		} catch (NullPointerException e) {
			log.error(e.getMessage(), e);
			req.setAttribute(WebConstant.ALERT, WebConstant.TYPE_ERROR);
			req.setAttribute(WebConstant.MESSAGE_RESPONSE, "Tên hoặc mật khẩu sai");
		}
		RequestDispatcher rd = req.getRequestDispatcher("/views/web/login.jsp");
		rd.forward(req, resp);
	}

}
