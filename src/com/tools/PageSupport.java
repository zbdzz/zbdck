package com.tools;

public class PageSupport {
private int currentPageNo=1;
private int PageSize=0;
private int totalCount=0;
private int totalPageCount=1;
public int getTotalPageCount() {
	return totalPageCount;
}
public void setTotalPageCount(int totalPageCount) {
	this.totalPageCount = totalPageCount;
}
public int getCurrentPageNo() {
	return currentPageNo;
}
public void setCurrentPageNo(int currentPageNo) {
	if (currentPageNo>0) {
		this.currentPageNo = currentPageNo;
	}
	
}

public int getPageSize() {
	return PageSize;
}

public void setPageSize(int pageSize) {
	if(pageSize > 0){
	PageSize = pageSize;
	}
}

public int getTotalCount() {
 
	return totalCount;
}

public void setTotalCount(int totalCount) {
	if(totalCount > 0){
	this.totalCount = totalCount;
	this.setTotalPageCountByRs();
	} 
}
public void setTotalPageCountByRs(){
	if(this.totalCount % this.PageSize == 0){
		this.totalPageCount = this.totalCount / this.PageSize;
	}else if(this.totalCount % this.PageSize > 0){
		this.totalPageCount = this.totalCount / this.PageSize + 1;
	}else{
		this.totalPageCount = 0;
	}
}
}
