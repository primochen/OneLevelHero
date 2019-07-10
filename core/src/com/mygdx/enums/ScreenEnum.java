package com.mygdx.enums;

public enum ScreenEnum {
	building("building"), dungeon("dungeon"), dungeon_entrance("dungeon_entrance"), game_object("game_object"), greeting(
			"greeting"), log("log"), loading_bar("loading_bar"), menu("menu"), option("option"), village("village"), world_map(
			"world_map"), credit("credit"), extra("extra"), save("save"), bonus_point("bonus_point"), load("load"), chat_event(
			"event"), field("field"), ending("ending"), cg("cg"), bgm("bgm"), collection("collection"), status("status"), battle(
			"battle"), encounter("encounter"), inventory("inventory"), fork("fork"), game_over("game_over"), character_change(
			"character_change"), choice_option("choice_option"), game_clear("game_clear");

	private String code;

	ScreenEnum(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return code;
	}

	public static ScreenEnum findScreenEnum(String code) {
		for (ScreenEnum screenEnum : ScreenEnum.values()) {
			if (screenEnum.toString().equals(code))
				return screenEnum;
		}
		return null;
	}
}
