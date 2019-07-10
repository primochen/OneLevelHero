package com.mygdx.enums;

public enum BattleMessages {
	meet_message("와 조우했다!"), player_win_message("몬스터를 물리쳤다!"), start_battle_message("전투 시작!");

	private String code;
	BattleMessages(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return this.code;
	}
}
