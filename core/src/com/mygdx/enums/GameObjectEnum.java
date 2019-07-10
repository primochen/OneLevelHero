package com.mygdx.enums;

public enum GameObjectEnum {
	pressed("pressed"), normal("normal"), function("function");

	private String code;

	private GameObjectEnum(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return code;
	}
}
