package com.centurion.core.web.command;

import java.util.List;

public class AbstractCommand<T> {
	protected T pojo;
	private String crudaction;// chuoi cac lenh ("insert, update, delete")
	private int maxPageItems = 5;// Tổng số item trên 1 trang
	private int firstItem = 0;// Vị trí bắt đầu row item vd: 1-19,20-39
	private int totalItems = 0;// Tổng số item
	private String sortExpression;
	private String sortDirection;
	private String[] checkList;// check box list
	private List<T> listResult; // Tương ứng name trong displaytag
	private String tableId = "tableList";// chứa id do displaytag table sinh ra
	private String messageResponse;
	private int page = 1;
	private String urlType;

	public String getUrlType() {
		return urlType;
	}

	public void setUrlType(String urlType) {
		this.urlType = urlType;
	}

	public T getPojo() {
		return pojo;
	}

	public void setPojo(T pojo) {
		this.pojo = pojo;
	}

	public String getCrudaction() {
		return crudaction;
	}

	public void setCrudaction(String crudaction) {
		this.crudaction = crudaction;
	}

	public int getMaxPageItems() {
		return maxPageItems;
	}

	public void setMaxPageItems(int maxPageItems) {
		this.maxPageItems = maxPageItems;
	}

	public int getFirstItem() {
		return firstItem;
	}

	public void setFirstItem(int firstItem) {
		this.firstItem = firstItem;
	}

	public int getTotalItems() {
		return totalItems;
	}

	public void setTotalItems(int totalItems) {
		this.totalItems = totalItems;
	}

	public String getSortExpression() {
		return sortExpression;
	}

	public void setSortExpression(String sortExpression) {
		this.sortExpression = sortExpression;
	}

	public String getSortDirection() {
		return sortDirection;
	}

	public void setSortDirection(String sortDirection) {
		this.sortDirection = sortDirection;
	}

	public String[] getCheckList() {
		return checkList;
	}

	public void setCheckList(String[] checkList) {
		this.checkList = checkList;
	}

	public List<T> getListResult() {
		return listResult;
	}

	public void setListResult(List<T> listResult) {
		this.listResult = listResult;
	}

	public String getTableId() {
		return tableId;
	}

	public void setTableId(String tableId) {
		this.tableId = tableId;
	}

	public String getMessageResponse() {
		return messageResponse;
	}

	public void setMessageResponse(String messageResponse) {
		this.messageResponse = messageResponse;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

}
