package com.centurion.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.centurion.core.commons.constant.CoreConstant;

public class DisplayImage extends HttpServlet {

	private final String imagesBase = "/" + CoreConstant.FOLDER_UPLOAD;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String imageUrl = request.getRequestURI();
		System.out.println("imageurl" + imageUrl.indexOf("/repository/"));
		String relativeImagePath = imageUrl.substring(imageUrl.indexOf("/repository/") + "/repository/".length());

		ServletOutputStream outStream;
		outStream = response.getOutputStream();
		FileInputStream fin = new FileInputStream(imagesBase + File.separator + relativeImagePath);
		BufferedInputStream bin = new BufferedInputStream(fin);
		BufferedOutputStream bout = new BufferedOutputStream(outStream);
		int ch = 0;
		while ((ch = bin.read()) != -1)
			bout.write(ch);
		bin.close();
		fin.close();
		bout.close();
		outStream.close();
	}
}
