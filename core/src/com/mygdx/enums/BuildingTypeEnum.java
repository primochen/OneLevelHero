package com.mygdx.enums;

public enum BuildingTypeEnum {
	inn("inn"), house("house");
	private String buildingTypeString;

	BuildingTypeEnum(String buildingType) {
		this.buildingTypeString = buildingType;
	}

	@Override
	public String toString() {
		return buildingTypeString;
	}
}
