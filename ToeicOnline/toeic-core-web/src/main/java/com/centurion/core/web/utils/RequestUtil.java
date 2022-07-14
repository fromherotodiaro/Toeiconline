package com.centurion.core.web.utils;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;

import com.centurion.core.web.command.AbstractCommand;

public class RequestUtil {

	public static void initSearchBear(HttpServletRequest req, AbstractCommand bean) {

		if (bean != null) {

			String sortExpression = req.getParameter(
					new ParamEncoder(bean.getTableId()).encodeParameterName(TableTagParameters.PARAMETER_SORT));
			String sortDirection = req.getParameter(
					new ParamEncoder(bean.getTableId()).encodeParameterName(TableTagParameters.PARAMETER_ORDER));
			String pageStr = req.getParameter(
					new ParamEncoder(bean.getTableId()).encodeParameterName(TableTagParameters.PARAMETER_PAGE));

			Integer page = 1;

			if (StringUtils.isNotBlank(pageStr)) {
//				Checks if a String is not empty (""), not null and not whitespace only.
//				 StringUtils.isNotBlank(null)      = false
//				 StringUtils.isNotBlank("")        = false
//				 StringUtils.isNotBlank(" ")       = false
//				 StringUtils.isNotBlank("bob")     = true
//				 StringUtils.isNotBlank("  bob  ") = true
				try {
					page = Integer.valueOf(pageStr);
				} catch (Exception e) {
					// TODO: handle exception
				}

			}
			bean.setPage(page);
			bean.setSortExpression(sortExpression);
			bean.setSortDirection(sortDirection);
			bean.setFirstItem((bean.getPage() - 1) * bean.getMaxPageItems());

		}

	}

}
