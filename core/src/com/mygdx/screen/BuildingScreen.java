package com.mygdx.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.enums.MusicEnum;
import com.mygdx.enums.StageEnum;

public class BuildingScreen extends BaseScreen {
	public static boolean isInSave;
	public static boolean isClickPopup;
	private Stage buildingStage;
	private Stage gameUiStage;
	private Stage saveStage;
	private Stage loadStage;
	private Stage gameObjectPopupStage;

	@Override
	public void render(float delta) {
		super.render(delta);
		setInputProcessor();
		buildingStage.draw();
		gameUiStage.draw();
		gameUiStage.act(delta);
		if (isInSave) {
			saveStage.draw();
		}
		if (showLoadStage) {
			loadStage.draw();
		}
		if (isClickPopup) {
			gameObjectPopupStage.draw();
		}
	}

	@Override
	public void show() {
		gameObjectPopupStage = stageFactory.makeStage(StageEnum.building_rest_popup);
		buildingStage = stageFactory.makeStage(StageEnum.building);
		gameUiStage = stageFactory.makeStage(StageEnum.game_ui);
		saveStage = stageFactory.makeStage(StageEnum.save);
		loadStage = stageFactory.makeStage(StageEnum.load_popup);
		setInputProcessor();
		musicManager.setMusicAndPlay(MusicEnum.world_node_music);
	}

	private void setInputProcessor() {
		InputMultiplexer multiplexer = new InputMultiplexer();
		int i = 0;
		if (isInSave) {
			multiplexer.addProcessor(i++, saveStage);
			multiplexer.addProcessor(i++, gameUiStage);
			multiplexer.addProcessor(i++, buildingStage);
		} else {
			multiplexer.addProcessor(i++, gameUiStage);
			multiplexer.addProcessor(i++, buildingStage);
			multiplexer.addProcessor(i++, saveStage);
		}
		if (showLoadStage) {
			multiplexer.addProcessor(0, loadStage);
		}
		if (isClickPopup) {
			multiplexer.addProcessor(0, gameObjectPopupStage);
		}
		Gdx.input.setInputProcessor(multiplexer);

	}
}
