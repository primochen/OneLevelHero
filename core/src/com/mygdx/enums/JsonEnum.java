package com.mygdx.enums;

public enum JsonEnum {
	hero_json("hero_json"), village_json("village_json"), npc_json("npc_json"), fork_json("fork_json"), worldmap_json(
			"worldmap_json"), credit_list("credit_list"), skin("skin"), dungeon_json("dungeon_json"), atlas_ui_path(
			"atlas_ui_path"), background_file_path("background_file_path"), character_file_path("character_file_path"), weapon_json(
			"weapon_json"), accessory_json("accessory_json"), shield_json("shield_json"), clothes_json("clothes_json"), consumables_json(
			"consumables_json"), etc_item_json("etc_item_json"), game_object_json("game_object_json"), json_file_path(
			"json_file_path"), monster_json("monster_json"), null_json("null_json"), monster_file_path(
			"monster_file_path"), music_file_path("music_file_path"), world_node_music_list("world_node_music_list"), battle_music_list(
			"battle_music_list"), event_music_list("event_music_list"), moving_music_list("moving_music_list"), animation_sheet_file_path(
			"animation_sheet_file_path"), skill_json("skill_json"), story_section_json("story_section_json"), battle_ui_file_path(
			"battle_ui_file_path"), ui_constants("ui_constants"), scene_constants_json("scene_constants_json"), monster_field_json(
			"monster_field_json"), buff_json("buff_json"), dungeon_entrance_json("dungeon_entrance_json"), sound_effect_file_path(
			"sound_effect_file_path"), sound_efect_list("sound_effect_list"), main_story_json("main_story_json");

	private String jsonName;

	JsonEnum(String jsonName) {
		this.jsonName = jsonName;
	}

	@Override
	public String toString() {
		return jsonName;
	}

	public static JsonEnum findJsonEnum(String jsonName) {
		for (JsonEnum jsonEnum : JsonEnum.values()) {
			if (jsonEnum.toString().equals(jsonName))
				return jsonEnum;
		}
		return JsonEnum.null_json;
	}
}
