package com.mygdx.enums;

public enum SkillTypeEnum {
	tech("tech"), magic("magic");

	private String code;

	SkillTypeEnum(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return code;
	}
}
