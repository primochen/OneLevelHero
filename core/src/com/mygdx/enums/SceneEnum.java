package com.mygdx.enums;

public enum SceneEnum {
	battle("battle_scene"), world_map("world_map_scene");

	private String sceneName;

	SceneEnum(String sceneName) {
		this.sceneName = sceneName;
	}

	@Override
	public String toString() {
		return sceneName;
	}
}
