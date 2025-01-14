package com.mygdx.assets;

import java.util.Map;

import com.mygdx.enums.JsonEnum;
import com.mygdx.model.event.GameObject;
import com.mygdx.model.event.NPC;
import com.mygdx.model.event.StorySection;
import com.mygdx.util.JsonParser;

public class EventAssets implements JsonAssetsInitializable {
	private Map<String, NPC> npcMap;
	private Map<String, NPC> mainStoryMap;
	private Map<Integer, StorySection> storySectionMap;
	private Map<String, GameObject> gameObjectMap;

	@Override
	public void set(Map<String, String> jsonStringMap) {
		npcMap = JsonParser.parseMap(NPC.class, jsonStringMap.get(String.valueOf(JsonEnum.npc_json)));
		storySectionMap = JsonParser.parseMap(Integer.class, StorySection.class,
				jsonStringMap.get(String.valueOf(JsonEnum.story_section_json)));
		mainStoryMap = JsonParser.parseMap(NPC.class, jsonStringMap.get(String.valueOf(JsonEnum.main_story_json)));
		gameObjectMap = JsonParser.parseMap(GameObject.class,
				jsonStringMap.get(String.valueOf(JsonEnum.game_object_json)));
	}

	public StorySection getStorySection(int storySectionNumber) {
		return storySectionMap.get(String.valueOf(storySectionNumber));
	}

	public GameObject getGameObject(String gameObjectString) {
		return gameObjectMap.get(gameObjectString);
	}

	public Map<String, NPC> getNpcMap() {
		return npcMap;
	}

	public void setNpcMap(Map<String, NPC> npcMap) {
		this.npcMap = npcMap;
	}

	public Map<String, GameObject> getGameObjectMap() {
		return gameObjectMap;
	}

	public Map<Integer, StorySection> getStorySectionMap() {
		return storySectionMap;
	}

	public void setStorySectionMap(Map<Integer, StorySection> storySectionMap) {
		this.storySectionMap = storySectionMap;
	}

	public Map<String, NPC> getMainStoryMap() {
		return mainStoryMap;
	}

	public void setMainStoryMap(Map<String, NPC> mainStoryMap) {
		this.mainStoryMap = mainStoryMap;
	}

}
