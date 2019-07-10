package com.mygdx.enums;

public enum ItemEnum {
	right_handgrip("right_handgrip"), left_handgrip("left_handgrip"), handgrip("handgrip"), clothes(
			"clothes"), accessory("accessory"), consumables("consumables"), etc_item("etc_item");
	private String code;

	ItemEnum(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return code;
	}

	public static ItemEnum findItemByType(String stringName) {
		for (ItemEnum itemType : ItemEnum.values()) {
			if (itemType.toString().equals(stringName)) {
				return itemType;
			}
		}
		return null;
	}

	public enum Inventory {
		EQUIPMENT, CONSUMABLES, ETC_ITEM;
	}

	public enum HandgripState {
		ZERO_ZERO, ZERO_ONE, ONE_ZERO, ONE_ONE, TWO_ZERO, ZERO_TWO;
	}
}
