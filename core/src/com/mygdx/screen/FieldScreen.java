package com.mygdx.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.enums.MusicEnum;
import com.mygdx.enums.StageEnum;

public class FieldScreen extends BaseScreen {

	private Stage gameUiStage, fieldStage, characterUiStage;

	@Override
	public void render(float delta) {
		super.render(delta);
		setInputProcessor();
		fieldStage.draw();
		fieldStage.act();
		characterUiStage.draw();
		characterUiStage.act(delta);
		if (showLoadStage) {
			loadPopupStage.draw();
		}
		gameUiStage.draw();
		gameUiStage.act();
	}

	@Override
	public void show() {
		characterUiStage = stageFactory.makeStage(StageEnum.character_ui);
		gameUiStage = stageFactory.makeStage(StageEnum.game_ui);
		fieldStage = stageFactory.makeStage(StageEnum.field);
		loadPopupStage = stageFactory.makeStage(StageEnum.load_popup);
		setInputProcessor();
		musicManager.setMusicAndPlay(MusicEnum.moving_music);
	}

	private void setInputProcessor() {
		InputMultiplexer multiplexer = new InputMultiplexer();
		if (showLoadStage) {
			multiplexer.addProcessor(0, loadPopupStage);
		} else {
			multiplexer.addProcessor(0, gameUiStage);
			multiplexer.addProcessor(1, characterUiStage);
			multiplexer.addProcessor(2, fieldStage);
		}
		Gdx.input.setInputProcessor(multiplexer);
	}
}
