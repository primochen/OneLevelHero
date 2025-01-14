package com.mygdx.listener;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.currentState.CurrentInfo;
import com.mygdx.enums.EventTypeEnum;
import com.mygdx.enums.PositionEnum.LocatePosition;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.factory.ScreenFactory;
import com.mygdx.manager.MovingManager;
import com.mygdx.manager.PositionManager;
import com.mygdx.manager.SoundManager;
import com.mygdx.manager.StorySectionManager;
import com.mygdx.manager.TimeManager;
import com.mygdx.screen.StatusScreen;

public class BackButtonListener extends ClickListener {
	@Autowired
	private StorySectionManager storySectionManager;
	@Autowired
	private PositionManager positionManager;
	@Autowired
	private MovingManager movingManager;
	@Autowired
	private ScreenFactory screenFactory;
	@Autowired
	private TimeManager timeManager;
	@Autowired
	private SoundManager soundManager;
	private int adminCount;

	@Override
	public void clicked(InputEvent event, float x, float y) {
		soundManager.playClickSound();
		if (!positionManager.isInWorldMap()) {
			if (positionManager.getCurrentLocatePositionType().equals(LocatePosition.SUB_NODE)) {
				movingManager.goPreviousPosition();
				timeManager.plusMinute(15);
				storySectionManager.triggerNextSectionEvent(EventTypeEnum.move_node,
						positionManager.getCurrentNodePath());
			} else {
				setAdminCount(getAdminCount() + 1);
				if (getAdminCount() > 4) {
					CurrentInfo.changeAdminMode();
					setAdminCount(0);
				}
			}

		} else {
			if (StatusScreen.isClickedWorldMap()) {
				StatusScreen.setClickedWorldMap(false);
				positionManager.setInWorldMap(false);
				screenFactory.show(ScreenEnum.status);
			} else {
				movingManager.goCurrentLocatePosition();
			}
		}
	}

	public int getAdminCount() {
		return adminCount;
	}

	public void setAdminCount(int adminCount) {
		this.adminCount = adminCount;
	}
}