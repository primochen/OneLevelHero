package com.mygdx.enums;

public enum EventTypeEnum {
	start_battle("start_battle"), always_open_game_object_event("always_open_game_object_event"), close_game_object_event(
			"close_game_object_event"), chat("chat"), choice_option("choice_option"), select_event("select_event"), credit(
			"credit"), move_field("move_field"), move_node("move_node"), move_dungeon_room("move_dungeon_room"), next_section(
			"next_section"), battle_command("battle_command"), play_music("play_music"), save("save"), end_battle(
			"end_battle"), pass_time("pass_time"), collect_event("collect_event"), move_node_before_absolute_time(
			"move_node_before_section_time"), move_node_after_section_time("move_node_after_section_time"), move_sub_node(
			"move_sub_node"), move_sub_node_before_absolute_time("move_sub_node_before_absolute_time"), move_sub_node_after_absolute_time(
			"move_sub_node_after_absolute_time"), move_sub_node_by_time("move_sub_node_by_time"), rest_in_node(
			"rest_in_node"), game_over("game_over"), leave_party("leave_party"), set_time("set_time"), get_buff(
			"get_buff"), join_party("join_party"), move_sub_node_after_day_from("move_sub_node_after_day_from"), move_sub_node_by_time_nextday(
			"move_sub_node_by_time_nextday"), move_sub_node_by_time_from("move_sub_node_by_time_from"), click_arrow(
			"click_arrow"), move_node_by_time("move_node_by_time"), game_clear("game_clear"), get_exp("get_exp"), get_item(
			"get_item"), open_npc_event("open_npc_event"), set_npc_target_time("set_npc_target_time"), close_npc_event(
			"close_npc_event"), quest_get_item("quest_get_item"), select_chat("select_chat"), quit_party("quit_party"), stop_go_sub_node(
			"stop_go_sub_node"), go_sub_node("go_sub_node"), move_dungeon_room_in_target_time(
			"move_dungeon_room_in_target_time"), move_sub_node_in_target_time("move_sub_node_in_target_time"), move_dungeon_room_after_absolute_time(
			"move_dungeon_room_after_absolute_time"), move_node_in_target_time("move_node_in_target_time"), move_dungeon_room_before_absolute_time(
			"move_dungeon_room_before_absolute_time"), always_open_npc_event("always_open_npc_event"), move_sub_node_before_section_time(
			"move_sub_node_before_section_time"), heal_all_hero("heal_all_hero"), set_game_object_target_time(
			"set_game_object_target_time"), open_game_object_event("open_game_object_event"), quest_hunt_monster(
			"quest_hunt_monster"), check_quest("check_quest"), rest_in_fork("rest_in_fork");
	private String code;

	EventTypeEnum(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return code;
	}

	public static EventTypeEnum findEventTypeEnum(String code) {
		for (EventTypeEnum eventTypeEnum : EventTypeEnum.values())
			if (eventTypeEnum.toString().equals(code))
				return eventTypeEnum;
		return null;
	}
}
