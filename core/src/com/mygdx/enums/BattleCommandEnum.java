package com.mygdx.enums;

public enum BattleCommandEnum {
	no_command("no_command", 0), general_attack("general_attack", 30), use_magic("use_magic", 0), use_skill(
			"use_skill", 0), use_item("use_item", 30), defend("defend", 20), run_away("run_away", 0), wait("wait", 200);

	private String code;
	private int costGauge;

	BattleCommandEnum(String code, int costGauge) {
		this.code = code;
		this.costGauge = costGauge;
	}

	@Override
	public String toString() {
		return this.code;
	}

	public int getCostGauge() {
		return costGauge;
	}
}
