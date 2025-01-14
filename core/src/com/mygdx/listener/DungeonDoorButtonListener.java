package com.mygdx.listener;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.enums.EventTypeEnum;
import com.mygdx.manager.DungeonManager;
import com.mygdx.manager.PositionManager;
import com.mygdx.manager.SoundManager;
import com.mygdx.manager.StorySectionManager;
import com.mygdx.manager.TimeManager;

public class DungeonDoorButtonListener extends ClickListener {
	@Autowired
	private DungeonManager dungeonManager;
	@Autowired
	private StorySectionManager storySectionManager;
	@Autowired
	private PositionManager positionManager;
	@Autowired
	private TimeManager timeManager;
	@Autowired
	private SoundManager soundManager;

	private int index;
	@Override
	public void clicked(InputEvent event, float x, float y) {
		soundManager.playClickSound();
		soundManager.setSoundByPathAndPlay("notice_moving");
		dungeonManager.moveRoom(index);
		String subNodePath = positionManager.getCurrentSubNodePath();
		String floorPath = dungeonManager.getDungeonInfo().getCurrentFloor().getFloorPath();
		String roomLabel = dungeonManager.getDungeonInfo().getCurrentRoom().getRoomLabel();
		timeManager.plusMinute(10);
		storySectionManager.triggerNextSectionEvent(EventTypeEnum.move_dungeon_room, subNodePath, floorPath, roomLabel);
		storySectionManager.triggerNextSectionEvent(EventTypeEnum.move_dungeon_room_in_target_time, subNodePath,
				floorPath, roomLabel);
		storySectionManager.triggerNextSectionEvent(EventTypeEnum.move_dungeon_room_before_absolute_time, subNodePath,
				floorPath, roomLabel);
		storySectionManager.triggerNextSectionEvent(EventTypeEnum.move_dungeon_room_after_absolute_time, subNodePath,
				floorPath, roomLabel);

	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
}
