package com.mygdx.enums;

public enum EventStateEnum {
	not_opened("not_opened"), opened("opened"), closed("closed"), cleared("cleared"), always_open("always_open"), ing(
			"ing");
	private String eventStateString;

	EventStateEnum(String eventStateString) {
		this.eventStateString = eventStateString;
	}

	@Override
	public String toString() {
		return eventStateString;
	}

	public static EventStateEnum findEventStateEnum(String stringName) {
		for (EventStateEnum eventStateEnum : EventStateEnum.values())
			if (eventStateEnum.toString().equals(stringName))
				return eventStateEnum;
		return null;
	}
}