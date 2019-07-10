package com.mygdx.enums;

public enum BuffTypeEnum {
	buff("buff"), debuff("debuff");
	private String buffType;

	BuffTypeEnum(String buffType) {
		this.buffType = buffType;
	}

	@Override
	public String toString() {
		return buffType;
	}
}
