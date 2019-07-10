package com.mygdx.enums;

public enum WorldNodeEnum {
	blackwood("blackwood"), blackwood_forest("blackwood_forest"), cobweb(
			"cobweb");

	private String nodeName;

	WorldNodeEnum(String nodeName) {
		this.nodeName = nodeName;
	}

	@Override
	public String toString() {
		return nodeName;
	}

	public static WorldNodeEnum findWorldNodeEnum(String jsonName) {
		for (WorldNodeEnum worldNodeEnum : WorldNodeEnum.values())
			if (worldNodeEnum.toString().equals(jsonName))
				return worldNodeEnum;

		return null;
	}

	public enum NodeType {
		dungeon_entrance("dungeon_entrance"), village("village"), fork("fork");
		NodeType(String code) {
			this.code = code;
		}

		private String code;

		@Override
		public String toString() {
			return code;
		}
	}
}