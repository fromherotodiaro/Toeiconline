package com.centurion.controller.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.centurion.command.UserCommand;
import com.centurion.core.commons.utils.ExcelPoiUtil;
import com.centurion.core.commons.utils.SessionUtil;
import com.centurion.core.commons.utils.UploadUtil;
import com.centurion.core.dto.RoleDTO;
import com.centurion.core.dto.UserDTO;
import com.centurion.core.dto.UserImportDTO;
import com.centurion.core.web.common.WebConstant;
import com.centurion.core.web.utils.FormUtil;
import com.centurion.core.web.utils.RequestUtil;
import com.centurion.core.web.utils.SingletonServiceUtil;
import com.centurion.core.web.utils.WebCommonUtil;

@WebServlet(urlPatterns = { "/admin-user-list.html", "/ajax-admin-user-edit.html", "/admin-user-import.html",
		"/admin-user-import-validate.html" })
public class UserController extends HttpServlet {
	private final Logger log = Logger.getLogger(this.getClass());
	private final String SHOW_IMPORT_USER = "show_import_user";
	private final String READ_EXCEL = "read_excel";
	private final String VALIDATE_IMPORT = "validate_import";
	private final String LIST_USER_IMPORT = "list_user_import";
	private final String IMPORT_DATA = "import_data";
	ResourceBundle bundle = ResourceBundle.getBundle("ResourcesBundle");

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserCommand command = FormUtil.populate(UserCommand.class, request);
		UserDTO pojo = command.getPojo();

		if (command.getUrlType() != null && command.getUrlType().equals(WebConstant.URL_LIST)) {
			Map<String, Object> mapProperty = new HashMap<String, Object>();
			RequestUtil.initSearchBean(request, command);// phan trang
			Object[] objects = SingletonServiceUtil.getUserServiceInstance().findByProperty(mapProperty,
					command.getSortExpression(), command.getSortDirection(), command.getFirstItem(),
					command.getMaxPageItems());
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
				command.setPojo(SingletonServiceUtil.getUserServiceInstance().findById(pojo.getUserId()));
			}
			command.setRoles(SingletonServiceUtil.getRoleServiceInstance().findAll());
			request.setAttribute(WebConstant.FORM_ITEM, command);
			RequestDispatcher rd = request.getRequestDispatcher("/views/admin/user/edit.jsp");
			rd.forward(request, response);
		} else if (command.getUrlType() != null && command.getUrlType().equals(SHOW_IMPORT_USER)) {

			RequestDispatcher rd = request.getRequestDispatcher("/views/admin/user/importuser.jsp");
			rd.forward(request, response);
		} else if (command.getUrlType() != null && command.getUrlType().equals(VALIDATE_IMPORT)) {
			List<UserImportDTO> userImportDTOs = (List<UserImportDTO>) SessionUtil.getInstance().getValue(request,
					LIST_USER_IMPORT);
			command.setUserImportDTOS(returnListUserImport(command, userImportDTOs, request));
			command.setTotalItems(userImportDTOs.size());
			request.setAttribute(WebConstant.LIST_ITEMS, command);
			RequestDispatcher rd = request.getRequestDispatcher("/views/admin/user/importuser.jsp");
			rd.forward(request, response);
		}
	}

	private List<UserImportDTO> returnListUserImport(UserCommand command, List<UserImportDTO> userImportDTOs,
			HttpServletRequest request) {
		RequestUtil.initSearchBean(request, command);

		command.setTotalItems(userImportDTOs.size());
		int fromIndex = command.getFirstItem();
		if (fromIndex > command.getTotalItems()) {
			fromIndex = 0;
			command.setFirstItem(0);
		}
		int toIndex = command.getFirstItem() + command.getMaxPageItems();
		if (userImportDTOs.size() > 0) {
			if (toIndex > userImportDTOs.size()) {
				toIndex = userImportDTOs.size();
			}
		}
		return userImportDTOs.subList(fromIndex, toIndex);
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

		UploadUtil uploadUtil = new UploadUtil();
		Set<String> value = new HashSet<String>();
		value.add("urlType");
		Object[] objects = uploadUtil.writeOrUpdateFile(request, value, "excel");
		try {
			UserCommand command = FormUtil.populate(UserCommand.class, request);
			UserDTO pojo = command.getPojo();
			if (command.getUrlType() != null && command.getUrlType().equals(WebConstant.URL_EDIT)) {
				if (command.getCrudaction() != null && command.getCrudaction().equals(WebConstant.INSERT_UPDATE)) {
					RoleDTO roleDTO = new RoleDTO();
					roleDTO.setRoleId(command.getRoleId());
					pojo.setRoleDTO(roleDTO);
					if (pojo != null && pojo.getUserId() != null) {
//						update
						SingletonServiceUtil.getUserServiceInstance().updateUser(pojo);
						request.setAttribute(WebConstant.MESSAGE_RESPONSE, WebConstant.REDIRECT_UPDATE);
					} else {
//						 save
						SingletonServiceUtil.getUserServiceInstance().saveUser(pojo);
						request.setAttribute(WebConstant.MESSAGE_RESPONSE, WebConstant.REDIRECT_INSERT);
					}
				}
				RequestDispatcher rd = request.getRequestDispatcher("/views/admin/user/edit.jsp");
				rd.forward(request, response);
			}
			if (objects != null) {
				String urlType = null;
				Map<String, String> mapvalue = (Map<String, String>) objects[3];
				for (Map.Entry<String, String> item : mapvalue.entrySet()) {
					if (item.getKey().equals("urlType")) {
						urlType = item.getValue();
					}
				}
				if (urlType != null && urlType.equals(READ_EXCEL)) {
					String filelocation = objects[1].toString();
					String fileName = objects[2].toString();
					List<UserImportDTO> excelValue = returnValueFormExcel(fileName, filelocation);
					validateData(excelValue);
					SessionUtil.getInstance().putValue(request, LIST_USER_IMPORT, excelValue);
					response.sendRedirect("admin-user-import-validate.html?urlType=validate_import");
				}
			}
			if (command.getUrlType() != null && command.getUrlType().equals(IMPORT_DATA)) {
				List<UserImportDTO> userImportDTOs = (List<UserImportDTO>) SessionUtil.getInstance().getValue(request,
						LIST_USER_IMPORT);
				SingletonServiceUtil.getUserServiceInstance().saveUserImport(userImportDTOs);
				SessionUtil.getInstance().romove(request, LIST_USER_IMPORT);
				response.sendRedirect("admin-user-list.html?urlType=url_list");
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			request.setAttribute(WebConstant.MESSAGE_RESPONSE, WebConstant.REDIRECT_ERROR);
		}
	}

	private void validateData(List<UserImportDTO> excelValue) {
		Set<String> stringSet = new HashSet<String>();
		for (UserImportDTO item : excelValue) {
			validateRequireField(item);
			validateDuplicate(item, stringSet);
		}
		SingletonServiceUtil.getUserServiceInstance().validateImportUser(excelValue);

	}

	private void validateDuplicate(UserImportDTO item, Set<String> stringSet) {
		String message = item.getError();

		if (!stringSet.contains(item.getUserName())) {
			stringSet.add(item.getUserName());
		} else {
			if (item.isValid()) {
				message += "<br/>";
				message += bundle.getString("label.username.duplicate");
			}
		}
		if (StringUtils.isNotBlank(message)) {
			item.setValid(false);
			item.setError(message);
		}
	}

	private void validateRequireField(UserImportDTO item) {
		String message = "";
		if (StringUtils.isBlank(item.getUserName())) {
			message += "<br/>";
			message += bundle.getString("label.username.notempty");
		}
		if (StringUtils.isBlank(item.getPassword())) {
			message += "<br/>";
			message += bundle.getString("label.password.notempty");
		}
		if (StringUtils.isBlank(item.getRoleName())) {
			message += "<br/>";
			message += bundle.getString("label.rolename.notempty");
		}
		if (StringUtils.isNotBlank(message)) {
			item.setValid(false);
		}
		item.setError(message);
	}

	public List<UserImportDTO> returnValueFormExcel(String fileName, String filelocation) throws IOException {
		Workbook workbook = ExcelPoiUtil.getWorkBook(fileName, filelocation);
		Sheet sheet = workbook.getSheetAt(0);
		List<UserImportDTO> excelValue = new ArrayList<UserImportDTO>();
		for (int i = 1; i <= sheet.getLastRowNum(); i++) {
			Row row = sheet.getRow(i);
			UserImportDTO userImportDTO = readDataFormExcel(row);
			excelValue.add(userImportDTO);
		}
		return excelValue;
	}

	private UserImportDTO readDataFormExcel(Row row) {
		UserImportDTO userImportDTO = new UserImportDTO();
		userImportDTO.setUserName(ExcelPoiUtil.getCellValue(row.getCell(0)));
		userImportDTO.setPassword(ExcelPoiUtil.getCellValue(row.getCell(1)));
		userImportDTO.setFullName(ExcelPoiUtil.getCellValue(row.getCell(2)));
		userImportDTO.setRoleName(ExcelPoiUtil.getCellValue(row.getCell(3)));

		return userImportDTO;
	}
}