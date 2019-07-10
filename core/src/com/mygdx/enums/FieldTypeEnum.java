package com.mygdx.enums;

public enum FieldTypeEnum {
	crystallized_valley("crystallized_valley"), mountain_titania("mountain_titania"), range_of_a_mountain_titania(
			"range_of_a_mountain_titania"), mountain_titania_dungeon("mountain_titania_dungeon"), blackwood_forest_dungeon(
			"blackwood_forest_dungeon"), cave_of_succubus("cave_of_succubus"), golden_lake("golden_lake"), cave_of_cape_of_tempest(
			"cave_of_cape_of_tempest"), ancient_library_b1("ancient_library_b1"), ancient_library_b2(
			"ancient_library_b2"), ancient_library_b3("ancient_library_b3"), temple_of_moerae_b12(
			"temple_of_moerae_b12"), devil_castle_1f("devil_castle_1f"), devil_castle_2f("devil_castle_2f"), devil_castle_3f(
			"devil_castle_3f"), devil_castle_4f("devil_castle_4f"), blackwood_mountain("blackwood_mountain"), kadilla_mountain(
			"kadilla_mountain"), elven_forest("elven_forest"), blackwood_forest("blackwood_forest"), bridge("bridge"), grassland(
			"grassland"), beach("beach"), sea("sea"), dungeon("dungeon");
	private String fieldType;
	private FieldTypeEnum(String fieldType) {
		this.fieldType = fieldType;
	}

	public String toString() {
		return fieldType;
	}
}