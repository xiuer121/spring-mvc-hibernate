package com.gxws.cms.po;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne; 
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the column_tb database table.
 * 
 */
@Entity
@Table(name = "column_tb") 
public class ColumnTb implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	@Column(name = "managers_tb_id")
	private String managersTbId;

	private String name;

	private String noid;

	@Column(name = "panel_style")
	private String panelStyle;

	@Lob
	private String sort;

	@Column(name = "status_activate")
	private String statusActivate;

	@Column(name = "status_del")
	private String statusDel;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "time_create")
	private Date timeCreate;

	private String type;

	@ManyToOne
	@JoinColumn(name = "parent_id")
	private ColumnTb columnTb;

	@OneToMany(mappedBy = "columnTb")
	private List<ColumnTb> columnTbs;

	public ColumnTb() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getManagersTbId() {
		return this.managersTbId;
	}

	public void setManagersTbId(String managersTbId) {
		this.managersTbId = managersTbId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNoid() {
		return this.noid;
	}

	public void setNoid(String noid) {
		this.noid = noid;
	}

	public String getPanelStyle() {
		return this.panelStyle;
	}

	public void setPanelStyle(String panelStyle) {
		this.panelStyle = panelStyle;
	}

	public String getSort() {
		return this.sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getStatusActivate() {
		return this.statusActivate;
	}

	public void setStatusActivate(String statusActivate) {
		this.statusActivate = statusActivate;
	}

	public String getStatusDel() {
		return this.statusDel;
	}

	public void setStatusDel(String statusDel) {
		this.statusDel = statusDel;
	}

	public Date getTimeCreate() {
		return this.timeCreate;
	}

	public void setTimeCreate(Date timeCreate) {
		this.timeCreate = timeCreate;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public ColumnTb getColumnTb() {
		return this.columnTb;
	}

	public void setColumnTb(ColumnTb columnTb) {
		this.columnTb = columnTb;
	}

	public List<ColumnTb> getColumnTbs() {
		return this.columnTbs;
	}

	public void setColumnTbs(List<ColumnTb> columnTbs) {
		this.columnTbs = columnTbs;
	}

	public ColumnTb addColumnTb(ColumnTb columnTb) {
		getColumnTbs().add(columnTb);
		columnTb.setColumnTb(this);

		return columnTb;
	}

	public ColumnTb removeColumnTb(ColumnTb columnTb) {
		getColumnTbs().remove(columnTb);
		columnTb.setColumnTb(null);

		return columnTb;
	}

}