package com.mygdx.enums;

public enum SkillTargetEnum {
	MONSTER("monster"), SELF("self"), ALL("all"), ONE("one");

	private String skillTargetType;

	SkillTargetEnum(String skillTargetType) {
		this.skillTargetType = skillTargetType;
	}

	@Override
	public String toString() {
		return skillTargetType;
	}
}
