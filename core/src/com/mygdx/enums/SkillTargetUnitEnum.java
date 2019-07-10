package com.mygdx.enums;

public enum SkillTargetUnitEnum {
	monster("monster"), self("self"), all("all"), one("one"), random("random");

	private String skillTargetType;

	SkillTargetUnitEnum(String skillTargetType) {
		this.skillTargetType = skillTargetType;
	}

	@Override
	public String toString() {
		return skillTargetType;
	}

	public static SkillTargetUnitEnum findSkillTargetEnum(String stringName) {
		for (SkillTargetUnitEnum skillTargetEnum : SkillTargetUnitEnum.values())
			if (skillTargetEnum.toString().equals(stringName))
				return skillTargetEnum;
		return null;
	}
}
