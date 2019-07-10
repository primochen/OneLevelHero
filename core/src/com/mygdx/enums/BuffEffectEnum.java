package com.mygdx.enums;

public enum BuffEffectEnum {
	block_action("block_action"), decrease_attack("decrease_attack"), decrease_magic_attack(
			"decrease_magic_attack"), decrease_hp_iterative("decrease_hp_iterative"), increase_defense(
					"increase_defense"), decrease_defense("decrease_defense"), increase_aggro(
							"increase_aggro"), overload("overload"), overwork("overwork"), decrease_speed(
									"decrease_speed"), stink("stink"), fly_action("fly_action"), shock("shock"), weak(
											"weak"), bless("bless"), increase_fire_resistance(
													"increase_fire_resistance"), increase_water_resistance(
															"increase_water_resistance"), increase_electric_resistance(
																	"increase_electric_resistance"), decline(
																			"decline"), charm("charm"), DEFAULT(
																					"default");

	private String buffEffect;

	BuffEffectEnum(String buffEffectString) {
		this.buffEffect = buffEffectString;
	}

	@Override
	public String toString() {
		return buffEffect;
	}

	public static BuffEffectEnum findBuffEffectEnum(String stringName) {
		for (BuffEffectEnum buffEffectEnum : BuffEffectEnum.values())
			if (buffEffectEnum.toString().equals(stringName))
				return buffEffectEnum;
		return null;
	}
}
