package com.mygdx.listener;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.enums.EventTypeEnum;
import com.mygdx.manager.DungeonManager;
import com.mygdx.manager.PositionManager;
import com.mygdx.manager.SoundManager;
import com.mygdx.manager.StorySectionManager;

public class LeaveDungeonButtonListener extends ClickListener {
	@Autowired
	private DungeonManager dungeonManager;
	@Autowired
	private StorySectionManager storySectionManager;
	@Autowired
	private PositionManager positionManager;
	@Autowired
	private SoundManager soundManager;

	@Override
	public void clicked(InputEvent event, float x, float y) {
		soundManager.playClickSound();
		dungeonManager.leaveDungeon();
		storySectionManager.triggerNextSectionEvent(EventTypeEnum.move_node, positionManager.getCurrentNodePath());
		Gdx.app.log("LeaveDungeonListener", "LeaveDungeon");
	}
}
