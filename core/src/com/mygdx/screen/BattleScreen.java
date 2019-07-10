package com.mygdx.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.enums.StageEnum;

public class BattleScreen extends BaseScreen {
	private Stage gameUiStage, battleInfoMessageStage, characterUiStage, monsterStage, battleStage, skillStage,
			itemStage, battleCommandStage;
	public static boolean showSkillStage = false;
	public static boolean showItemStage = false;
	public static boolean showBattleInfoMessage = false;

	public BattleScreen() {
	}

	@Override
	public void render(float delta) {
		super.render(delta);
		setInputProcessor();
		monsterStage.draw();
		characterUiStage.draw();
		battleStage.draw();
		battleCommandStage.draw();
		gameUiStage.draw();
		if (showBattleInfoMessage) {
			battleInfoMessageStage.draw();
		}
		if (showSkillStage) {
			skillStage.draw();

		}
		if (showItemStage) {
			itemStage.draw();
		}
		monsterStage.act(delta);
		characterUiStage.act(delta);
		gameUiStage.act();
		battleStage.act(delta);
		battleCommandStage.act(delta);
		battleInfoMessageStage.act();
		skillStage.act();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void show() {
		gameUiStage = stageFactory.makeStage(StageEnum.game_ui);
		characterUiStage = stageFactory.makeStage(StageEnum.character_ui);
		monsterStage = stageFactory.makeStage(StageEnum.monster);
		battleCommandStage = stageFactory.makeStage(StageEnum.battle_command);
		battleStage = stageFactory.makeStage(StageEnum.battle);
		battleInfoMessageStage = stageFactory.makeStage(StageEnum.battle_info_message);

		skillStage = stageFactory.makeStage(StageEnum.skill);
		loadPopupStage = stageFactory.makeStage(StageEnum.load_popup);
		itemStage = stageFactory.makeStage(StageEnum.item);
		setInputProcessor();
	}

	private void setInputProcessor() {
		InputMultiplexer multiplexer = new InputMultiplexer();
		int i = 0;
		if (showSkillStage) {
			multiplexer.addProcessor(i++, skillStage);
		} else if (showItemStage) {
			multiplexer.addProcessor(i++, itemStage);
		} else {
			if (showBattleInfoMessage) {
				multiplexer.addProcessor(0, battleInfoMessageStage);
			} else {
				multiplexer.addProcessor(i++, gameUiStage);
				multiplexer.addProcessor(i++, characterUiStage);
				multiplexer.addProcessor(i++, monsterStage);
				multiplexer.addProcessor(i++, battleCommandStage);
				multiplexer.addProcessor(i++, battleStage);
				multiplexer.addProcessor(i++, skillStage);
			}
		}

		if (showLoadStage) {
			multiplexer.addProcessor(0, loadPopupStage);
		}
		Gdx.input.setInputProcessor(multiplexer);
	}
}
