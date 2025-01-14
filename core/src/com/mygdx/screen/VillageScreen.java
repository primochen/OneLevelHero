package com.mygdx.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.enums.MusicEnum;
import com.mygdx.enums.StageEnum;

/**
 * VillageStage와 GameUiStage를 addActor()해서 보여주는 Screen 마을의 경우 multiplexer를 이용하여
 * 2개의 화면을 교차로 보여준다.
 * 
 * @author Velmont
 * 
 */
public class VillageScreen extends BaseScreen {
	private Stage villageStage;
	private Stage gameUiStage;
	private Stage characterUiStage;

	VillageScreen() {

	}

	@Override
	public void render(float delta) {
		super.render(delta);
		setInputProcessor();

		villageStage.act();
		characterUiStage.act(delta);
		gameUiStage.act(delta);
		villageStage.draw();
		characterUiStage.draw();
		gameUiStage.draw();
		if (showLoadStage) {
			loadPopupStage.draw();
		}
		// 카메라를 지속적으로 업데이트 해준다.
		villageStage.getViewport().getCamera().update();
	}

	@Override
	public void show() {
		villageStage = stageFactory.makeStage(StageEnum.village);
		characterUiStage = stageFactory.makeStage(StageEnum.character_ui);
		gameUiStage = stageFactory.makeStage(StageEnum.game_ui);
		loadPopupStage = stageFactory.makeStage(StageEnum.load_popup);
		// 여러 스테이지에 인풋 프로세서를 동시에 할 당한다

		musicManager.setMusicAndPlay(MusicEnum.world_node_music);
		setInputProcessor();
	}

	private void setInputProcessor() {
		InputMultiplexer multiplexer = new InputMultiplexer();
		int i = 0;
		// 만약 버튼이 겹칠 경우 인덱스가 먼저인 쪽(숫자가 작은 쪽)에 우선권이 간다 무조건 유아이가 위에 있어야 하므로 유아이에
		// 우선권을 준다.
		if (showLoadStage) {
			multiplexer.addProcessor(i++, loadPopupStage);
		} else {
			multiplexer.addProcessor(i++, gameUiStage);
			multiplexer.addProcessor(i++, villageStage);
			multiplexer.addProcessor(i++, characterUiStage);
		}
		// 멀티 플렉서에 인풋 프로세서를 할당하게 되면 멀티 플렉서 안에 든 모든 스테이지의 인풋을 처리할 수 있다.
		Gdx.input.setInputProcessor(multiplexer);
	}

}
