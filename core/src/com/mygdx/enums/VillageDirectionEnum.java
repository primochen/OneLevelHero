package com.mygdx.enums;

public enum VillageDirectionEnum {
	up_down("up_down"), left_right("left_right"), center("center"), up("up"), down("down"), left("left"), right("right");
	private String code;

	private VillageDirectionEnum(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return code;
	}
}
