package com.mygdx.currentState;

import java.util.List;

import com.mygdx.enums.SaveVersion;
import com.mygdx.model.unit.Hero;

public class SaveInfo {
	private SaveVersion saveVersion;
	private List<Hero> partyList;
	private String storyName;
	private String gameTime;
	private String saveTime;
	private String savePlace;

	public String getStoryName() {
		return storyName;
	}

	public void setStoryName(String storyName) {
		this.storyName = storyName;
	}

	public String getGameTime() {
		return gameTime;
	}

	public void setGameTime(String gameTime) {
		this.gameTime = gameTime;
	}

	public String getSaveTime() {
		return saveTime;
	}

	public void setSaveTime(String saveTime) {
		this.saveTime = saveTime;
	}

	public String getSavePlace() {
		return savePlace;
	}

	public void setSavePlace(String savePlace) {
		this.savePlace = savePlace;
	}

	public SaveVersion getSaveVersion() {
		return saveVersion;
	}

	public void setSaveVersion(SaveVersion saveVersion) {
		this.saveVersion = saveVersion;
	}

	public List<Hero> getPartyList() {
		return partyList;
	}

	public void setPartyList(List<Hero> list) {
		this.partyList = list;
	}
}
