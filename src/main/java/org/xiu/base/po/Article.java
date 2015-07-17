package org.xiu.base.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "column_tb")
public class Article implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8975681559356495908L;
	private String title;
	private Integer id;

	@Id
	@Column(name = "id", unique = true, nullable = false, length = 32)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
