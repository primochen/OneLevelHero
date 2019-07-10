package com.mygdx.enums;

public enum SkillEffectEnum {
	attack("attack"), duplicated_attack("duplicated_attack"), add_state("add_state"), remove_state(
			"remove_state"), conditional_attack("conditional_attack"), add_self_state("add_self_state"), change_gauge(
					"change_gauge"), multi_effect("multi_effect"), heal("heal"), all_heal("all_heal"), casting(
							"casting");

	private String skillEffectType;

	SkillEffectEnum(String skillEffectType) {
		this.skillEffectType = skillEffectType;

	}

	@Override
	public String toString() {
		return skillEffectType;
	}

	public static SkillEffectEnum findSkillEffectEnum(String stringName) {
		for (SkillEffectEnum skillEffectEnum : SkillEffectEnum.values())
			if (skillEffectEnum.toString().equals(stringName))
				return skillEffectEnum;
		return null;
	}
}
