package com.cafe24.mysite.dto;

public class Paging {
private int allCount;
private int groupCount=5;
private int prevGroupNo;
private int contentsCount=10;
private int allPageCount;
private int allGroupCount;
private int pageNo=1;
private int startPageNo;
private int startPageGroupNo;
public Paging() {
}

public Paging(int allCount) {
	this.allCount=allCount;
	this.allPageCount=((allCount%contentsCount)==0?(allCount/contentsCount):(allCount/contentsCount)+1);
	this.allGroupCount=((allPageCount%groupCount)==0?(allPageCount/groupCount):(allPageCount/groupCount)+1);
}

public int getStartPageGroupNo() {
	return groupCount*prevGroupNo;
}

public void setStartPageGroupNo(int startPageGroupNo) {
	this.startPageGroupNo = startPageGroupNo;
}

public int getStartPageNo() {
	pageNo=(pageNo<1?1:pageNo);
	return (pageNo-1)*contentsCount;
}

public void setStartPageNo(int startPageNo) {
	this.startPageNo = startPageNo;
}	

public int getPageNo() {
	return pageNo;
}

public void setPageNo(int pageNo) {
	
if(pageNo==1) { // > 또는 < 버튼 눌렀을 때 (기본값 1이 들어가는 경우 ) 
	this.pageNo=prevGroupNo*groupCount+1;
}else { 
	this.pageNo = pageNo;
}
}

public int getAllCount() {
	return allCount;
}

public void setAllCount(int allCount) {
	this.allCount = allCount;
}

public int getGroupCount() {
	return groupCount;
}

public void setGroupCount(int groupCount) {
	this.groupCount = groupCount;
}

public int getPrevGroupNo() {
	return prevGroupNo;
}

public void setPrevGroupNo(int prevGroupNo) {
	this.prevGroupNo = prevGroupNo;
}

public int getContentsCount() {
	return contentsCount;
}

public void setContentsCount(int contentsCount) {
	this.contentsCount = contentsCount;
}

public int getAllPageCount() {
	return ((allCount%contentsCount)==0?(allCount/contentsCount):(allCount/contentsCount)+1);
}

public void setAllPageCount(int allPageCount) {
	this.allPageCount = allPageCount;
}

public int getAllGroupCount() {
	return ((allPageCount%groupCount)==0?(allPageCount/groupCount):(allPageCount/groupCount)+1);
}

public void setAllGroupCount(int allGroupCount) {
	this.allGroupCount = allGroupCount;
}

@Override
public String toString() {
	return "Paging [allCount=" + allCount + ", groupCount=" + groupCount + ", prevGroupNo=" + prevGroupNo
			+ ", contentsCount=" + contentsCount + ", allPageCount=" + allPageCount + ", allGroupCount=" + allGroupCount
			+ ", pageNo=" + pageNo + ", startPageNo=" + startPageNo + ", startPageGroupNo=" + startPageGroupNo + "]";
}
 


 
}
 