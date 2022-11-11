package com.api.rest.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Detail_info {
         
	private int in_key;
	private String cust_nm;
	private String jumin_no;
	private String indate;
	private String total_txt;
	private Person person;
	
	public Detail_info() {
		in_key = -1;
		cust_nm = "";
		jumin_no = "";
		indate = "";
		total_txt = "";
	}	
	
	public Detail_info(int in_key, String cust_nm, String jumin_no, String indate,
			String total_txt) {
		super();
		this.in_key = in_key;
		this.cust_nm = cust_nm;
		this.jumin_no = jumin_no;
		this.indate = indate;
		this.total_txt = total_txt;
	}
	
	public int getIn_key() {
		return in_key;
	}
	public void setIn_key(int in_key) {
		this.in_key = in_key;
	}
	public String getCust_nm() {
		return cust_nm;
	}
	public void setCust_nm(String cust_nm) {
		this.cust_nm = cust_nm;
	}
	public String getJumin_no() {
		return jumin_no;
	}
	public void setJumin_no(String jumin_no) {
		this.jumin_no = jumin_no;
	}
	public String getIndate() {
		return indate;
	}
	public void setIndate(String indate) {
		this.indate = indate;
	}
	public String getTotal_txt() {
		return total_txt;
	}
	public void setTotal_txt(String total_txt) {
		this.total_txt = total_txt;
	}	
	
	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	@Override
	public String toString() {
		return "Detail_info [in_key=" + in_key + ", cust_nm=" + cust_nm
				+ ", jumin_no=" + jumin_no + ", indate=" + indate
				+ ", total_txt=" + total_txt + "]";
	}
	
}
