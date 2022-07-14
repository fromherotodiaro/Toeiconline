package com.centurion.controller.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.centurion.command.ListenGuideLineCommand;
import com.centurion.core.dto.ListenGuidelineDTO;
import com.centurion.core.web.common.WebConstant;

@WebServlet("/admin-guideline-listen-list.html")
public class ListenGuidelineController extends HttpServlet {

	/**
	 *
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		ListenGuideLineCommand command = new ListenGuideLineCommand();
		List<ListenGuidelineDTO> listGuidelineDTOs = new ArrayList<ListenGuidelineDTO>();
		ListenGuidelineDTO dto1 = new ListenGuidelineDTO();
		dto1.setTittle("Bai huong dan nghe 1");
		dto1.setContent("Noi dung bai huong dan nghe 1");
		listGuidelineDTOs.add(dto1);
		ListenGuidelineDTO dto2 = new ListenGuidelineDTO();
		dto2.setTittle("Bai huong dan nghe 2");
		dto2.setContent("Noi dung bai huong dan nghe 2");
		listGuidelineDTOs.add(dto2);
		ListenGuidelineDTO dto3 = new ListenGuidelineDTO();
		dto3.setTittle("Bai huong dan nghe 3");
		dto3.setContent("Noi dung bai huong dan nghe 3");
		listGuidelineDTOs.add(dto3);
		ListenGuidelineDTO dto4 = new ListenGuidelineDTO();
		dto4.setTittle("Bai huong dan nghe 4");
		dto4.setContent("Noi dung bai huong dan nghe 4");
		listGuidelineDTOs.add(dto4);

		command.setListResult(listGuidelineDTOs);
		command.setMaxPageItems(2);
		command.setTotalItems(listGuidelineDTOs.size());

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
