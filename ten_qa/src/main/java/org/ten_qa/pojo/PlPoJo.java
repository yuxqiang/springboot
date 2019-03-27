package org.ten_qa.pojo;

import java.io.Serializable;

public class PlPoJo implements Serializable {

	public String getProblemid() {
		return problemid;
	}

	public void setProblemid(String problemid) {
		this.problemid = problemid;
	}

	public String getLableid() {
		return lableid;
	}

	public void setLableid(String lableid) {
		this.lableid = lableid;
	}

	private String problemid;

	private String lableid;
}
