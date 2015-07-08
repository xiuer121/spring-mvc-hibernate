package com.gxws.cms.vo;

import java.util.List;

public class View<T> {

	private int currentPage = 1;			//当前页
	private int totalPage = 1;				//总页数
	
	private int recorderPage = 20;			//每页记录数
	private long recordTotal;				//总记录数
	
	private int index = 10;					//页码数量
	private int startIndex;					//开始页码
	private int endIndex;					//终止页码
	
	private List<?> records;				//分页数据
	
	//分页获得首记录数
	public int getFirstResult() {
		return (this.currentPage-1)*this.recorderPage;
	}
	
	public long getRecordTotal() {
		return recordTotal;
	}
	//得到总页数
	public void setRecordTotal(long recordTotal) {
		this.recordTotal = recordTotal;
		this.setTotalPage((int)(this.recordTotal%this.recorderPage==0? this.recordTotal/this.recorderPage : this.recordTotal/this.recorderPage+1));
	}
	
	//得到页码的开始和结束
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
		
		//得到页码的开始和结束
		startIndex = currentPage-(index%2==0 ? index/2-1 : index/2);
		endIndex = currentPage+index/2;
		
		//修正数据
		if(startIndex<1){
			startIndex = 1;
			if(totalPage>=index) endIndex = index;
			else endIndex = totalPage;
		}
		if(endIndex>totalPage){
			endIndex = totalPage;
			if((endIndex-index)>0) startIndex = endIndex-index+1;
			else startIndex = 1;
		}
	}

	public int getRecorderPage() {
		return recorderPage;
	}
	public void setRecorderPage(int recorderPage) {
		this.recorderPage = recorderPage;
	}

	public int getCurrentPage() {
		return this.currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}

	public int getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public int getEndIndex() {
		return endIndex;
	}
	public void setEndIndex(int endIndex) {
		this.endIndex = endIndex;
	}

	public List<?> getRecords() {
		return records;
	}
	public void setRecords(List<?> records) {
		this.records = records;
	}
	
	
	
}
