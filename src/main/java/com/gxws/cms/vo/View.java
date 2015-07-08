package com.gxws.cms.vo;

import java.util.List;

public class View<T> {

	private int currentPage = 1;			//��ǰҳ
	private int totalPage = 1;				//��ҳ��
	
	private int recorderPage = 20;			//ÿҳ��¼��
	private long recordTotal;				//�ܼ�¼��
	
	private int index = 10;					//ҳ������
	private int startIndex;					//��ʼҳ��
	private int endIndex;					//��ֹҳ��
	
	private List<?> records;				//��ҳ����
	
	//��ҳ����׼�¼��
	public int getFirstResult() {
		return (this.currentPage-1)*this.recorderPage;
	}
	
	public long getRecordTotal() {
		return recordTotal;
	}
	//�õ���ҳ��
	public void setRecordTotal(long recordTotal) {
		this.recordTotal = recordTotal;
		this.setTotalPage((int)(this.recordTotal%this.recorderPage==0? this.recordTotal/this.recorderPage : this.recordTotal/this.recorderPage+1));
	}
	
	//�õ�ҳ��Ŀ�ʼ�ͽ���
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
		
		//�õ�ҳ��Ŀ�ʼ�ͽ���
		startIndex = currentPage-(index%2==0 ? index/2-1 : index/2);
		endIndex = currentPage+index/2;
		
		//��������
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
