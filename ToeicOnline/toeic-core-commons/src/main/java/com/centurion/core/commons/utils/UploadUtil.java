package com.centurion.core.commons.utils;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang.StringUtils;

public class UploadUtil {
	private final int maxMemorySize = 1024 * 1024 * 3; // 3MBl
	private final int maxRequestSize = 1024 * 1024 * 50; // 50 MB

	public Object[] writeOrUpdateFile(HttpServletRequest request, Set<String> titleValue, String path)
			throws FileUploadException, Exception {
		ServletContext context = request.getServletContext();
//		String address = context.getRealPath("image");
		String address = context.getRealPath("/image");
		boolean check = true;
		String fileLocation = null;
		String namefile = null;
		Map<String, String> mapReturnValue = new HashMap<String, String>();// key = pojo.title, value = ...
		// Check that we have a file upload request
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if (!isMultipart) {
			System.out.println("have not enctype multipart/form-data ");
			check = false;
		}
		// Create a factory for disk-based file items
		DiskFileItemFactory factory = new DiskFileItemFactory();

		// Set factory constraints
		factory.setSizeThreshold(maxMemorySize);
		factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

		// Create a new file upload handler
		ServletFileUpload upload = new ServletFileUpload(factory);

		// Set overall request size constraint
		upload.setSizeMax(maxRequestSize);

		List<FileItem> items = upload.parseRequest(request);
		for (FileItem item : items) {
			if (!item.isFormField()) {// Nhân giá trị khác formfield
				String fileName = item.getName();
				if (StringUtils.isNotBlank(fileName)) {
					File uploadedFile = new File(address + File.separator + path + File.separator + fileName);
					fileLocation = address + File.separator + path + File.separator + fileName;
					namefile = fileName;
					boolean isExist = uploadedFile.exists();
					if (isExist) {
						if (uploadedFile.delete()) {
							item.write(uploadedFile);
						} else {
							check = false;
						}

					} else {
						item.write(uploadedFile);
					}
				}

			} else {// Nhân giá trị khác formfield như title, content,...
				if (titleValue != null) {
					String nameField = item.getFieldName();
					String valueField = item.getString();
					if (titleValue.contains(nameField)) {
						mapReturnValue.put(nameField, valueField);
					}

				}
			}
		}
		return new Object[] { check, fileLocation, namefile, mapReturnValue };// check , file location, file name

	}
}
