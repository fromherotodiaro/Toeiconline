package com.centurion.controller.admin;

import java.io.IOException;
import java.util.ResourceBundle;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.centurion.command.UserCommand;
import com.centurion.core.dto.CheckLogin;
import com.centurion.core.dto.UserDTO;
import com.centurion.core.web.common.WebConstant;
import com.centurion.core.web.utils.FormUtil;
import com.centurion.core.web.utils.SingletonServiceUtil;

@WebServlet("/login.html")
public class LoginController extends HttpServlet {
	private final Logger log = Logger.getLogger(this.getClass());
	ResourceBundle bundle = ResourceBundle.getBundle("ResourcesBundle");

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		RequestDispatcher rd = req.getRequestDispatcher("/views/web/login.jsp");
		rd.forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserCommand command = FormUtil.populate(UserCommand.class, request);
		UserDTO pojo = command.getPojo();
		if (pojo != null) {
			CheckLogin login = SingletonServiceUtil.getUserServiceInstance().checkLogin(pojo.getName(),
					pojo.getPassword());
			if (login.isUserExist()) {
				if (login.getRoleName().equals(WebConstant.ROLE_ADMIN)) {
					response.sendRedirect("admin-home.html");
				} else if (login.getRoleName().equals(WebConstant.ROLE_USER)) {
					response.sendRedirect("home.html");
				}
			} else {
				request.setAttribute(WebConstant.ALERT, WebConstant.TYPE_ERROR);
				request.setAttribute(WebConstant.MESSAGE_RESPONSE, bundle.getString("label.name.password.wrong"));
				RequestDispatcher rd = request.getRequestDispatcher("/views/web/login.jsp");
				rd.forward(request, response);
			}
		}
	}

}
