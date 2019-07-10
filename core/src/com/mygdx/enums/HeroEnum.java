package com.mygdx.enums;

public enum HeroEnum {
	yongsa("yongsa"), parath("parath");
	String heroName;

	private HeroEnum(String heroName) {
		this.heroName = heroName;
	}

	@Override
	public String toString() {
		return heroName;
	}
}
