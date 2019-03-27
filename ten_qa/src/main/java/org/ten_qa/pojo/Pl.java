package org.ten_qa.pojo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "tb_pl")
@IdClass(PlPoJo.class)
public class Pl implements Serializable {

	@Id
	private String problemid;

	@Id
	private String lableid;

	public String getLableid() {
		return lableid;
	}

	public void setLableid(String lableid) {
		this.lableid = lableid;
	}

	public String getProblemid() {
		return problemid;
	}

	public void setProblemid(String problemid) {
		this.problemid = problemid;
	}
}