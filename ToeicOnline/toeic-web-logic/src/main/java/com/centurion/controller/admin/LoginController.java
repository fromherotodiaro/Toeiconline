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
import com.centurion.core.web.common.WebConstant;
import com.centurion.core.web.utils.FormUtil;
import com.centurion.core.web.utils.SingletonServiceUtil;

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
//		UserService userService = new UserServiceImpl();
		try {
			if (SingletonServiceUtil.getUserServiceInstance().isUserExist(pojo) != null) {
				if (SingletonServiceUtil.getUserServiceInstance().findRoleByUser(pojo) != null
						&& SingletonServiceUtil.getUserServiceInstance().findRoleByUser(pojo).getRoleDTO() != null) {
					if (SingletonServiceUtil.getUserServiceInstance().findRoleByUser(pojo).getRoleDTO().getName()
							.equals(WebConstant.ROLE_ADMIN)) {
						resp.sendRedirect("admin-home.html");
					} else if (SingletonServiceUtil.getUserServiceInstance().findRoleByUser(pojo).getRoleDTO().getName()
							.equals(WebConstant.ROLE_USER)) {

						resp.sendRedirect("home.html");
					}
				}
			}
		} catch (NullPointerException e) {
			log.error(e.getMessage(), e);
			req.setAttribute(WebConstant.ALERT, WebConstant.TYPE_ERROR);
			req.setAttribute(WebConstant.MESSAGE_RESPONSE, "Tên hoặc mật khẩu sai");
			RequestDispatcher rd = req.getRequestDispatcher("/views/web/login.jsp");
			rd.forward(req, resp);
		}

	}

}
