package com.mygdx.enums;

public enum BattleMessages {
	meet_message("我遇到了！"), player_win_message("我擊敗了怪物！"), start_battle_message("開始戰鬥！");

	private String code;
	BattleMessages(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return this.code;
	}
}
