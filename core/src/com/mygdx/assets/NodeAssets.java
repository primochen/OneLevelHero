package com.mygdx.assets;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.mygdx.enums.FieldTypeEnum;
import com.mygdx.enums.JsonEnum;
import com.mygdx.enums.WorldNodeEnum;
import com.mygdx.model.location.Building;
import com.mygdx.model.location.Dungeon;
import com.mygdx.model.location.DungeonEntrance;
import com.mygdx.model.location.Fork;
import com.mygdx.model.location.MonsterField;
import com.mygdx.model.location.SubNode;
import com.mygdx.model.location.Village;
import com.mygdx.util.JsonParser;

public class NodeAssets implements JsonAssetsInitializable {
	public Map<String, Village> villageMap;
	public Map<String, Dungeon> dungeonMap;
	public Map<FieldTypeEnum, ArrayList<String>> monsterFieldMap;
	public Map<String, Fork> forkMap;
	public Map<String, DungeonEntrance> dungeonEntranceMap;

	public void set(Map<String, String> jsonStringMap) {
		villageMap = JsonParser.parseMap(Village.class, jsonStringMap.get(String.valueOf(JsonEnum.village_json)));

		dungeonMap = JsonParser.parseMap(Dungeon.class, jsonStringMap.get(String.valueOf(JsonEnum.dungeon_json)));
		dungeonEntranceMap = JsonParser.parseMap(DungeonEntrance.class,
				jsonStringMap.get(String.valueOf(JsonEnum.dungeon_entrance_json)));
		forkMap = JsonParser.parseMap(Fork.class, jsonStringMap.get(String.valueOf(JsonEnum.fork_json)));

		ArrayList<MonsterField> monsterFieldList = JsonParser.parseList(MonsterField.class,
				jsonStringMap.get(String.valueOf(JsonEnum.monster_field_json)));
		monsterFieldMap = new HashMap<FieldTypeEnum, ArrayList<String>>();
		for (MonsterField monsterField : monsterFieldList) {
			monsterFieldMap.put(monsterField.getFieldType(), monsterField.getFieldMonsterList());
		}
	}

	public SubNode getSubNodeInfo(WorldNodeEnum.NodeType nodeType, String nodeName, String subNodeName) {
		switch (nodeType) {
			case village :
				return getBuildingByPath(nodeName, subNodeName);
			case dungeon_entrance :
				return getDungeonByPath(subNodeName);
			default :
				Gdx.app.log("NodeAssets", "nodeType or subNodeType정보 오류 ");
				return getBuildingByPath("blackwood", "blackwood_inn");
		}
	}

	public Building getBuildingByPath(String nodeName, String buildingPath) {
		return villageMap.get(nodeName).getBuilding().get(buildingPath);
	}

	public Map<String, DungeonEntrance> getDungeonEntranceMap() {
		return dungeonEntranceMap;
	}

	public DungeonEntrance getDungeonEntranceByPath(String dungeonEntrancePath) {
		return dungeonEntranceMap.get(dungeonEntrancePath);
	}

	public Map<String, Village> getVillageMap() {
		return villageMap;
	}

	public Village getVillageByPath(String villageString) {
		return villageMap.get(villageString);
	}

	public Dungeon getDungeonByPath(String dungeonString) {
		return dungeonMap.get(dungeonString);
	}

	public ArrayList<String> getMonsterFieldListByFieldType(FieldTypeEnum fieldType) {
		return monsterFieldMap.get(fieldType);
	}

	public Map<String, Fork> getForkMap() {
		return forkMap;
	}

	public Fork getForkByName(String forkString) {
		return forkMap.get(forkString);
	}
}
