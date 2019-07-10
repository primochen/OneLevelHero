package com.mygdx.enums;

public enum PlusStatusEnum {
	plus("plus"), minus("minus");
	private String code;

	PlusStatusEnum(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return code;
	}
}
