package com.ict.ajax.exam;

public class KMA_VO {
	// xml 문서에서 원하는 Element(요소)를 객체화 시키기 위해 정의하는 클래스
	// 원하는 요소를 변수로 만든다.
	private String local, ta, desc, icon;

	public KMA_VO() {
		// TODO Auto-generated constructor stub
	}

	public KMA_VO(String local, String ta, String desc, String icon) {
		this.local = local;
		this.ta = ta;
		this.desc = desc;
		this.icon = icon;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public String getTa() {
		return ta;
	}

	public void setTa(String ta) {
		this.ta = ta;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

}
