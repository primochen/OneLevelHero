package com.mygdx.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.badlogic.gdx.Gdx;
import com.mygdx.enums.EventTypeEnum;
import com.mygdx.nextSectionChecker.BattleCommandChecker;
import com.mygdx.nextSectionChecker.BattleEndChecker;
import com.mygdx.nextSectionChecker.ChoiceOptionChecker;
import com.mygdx.nextSectionChecker.CollectEventChecker;
import com.mygdx.nextSectionChecker.DoNothingChecker;
import com.mygdx.nextSectionChecker.MoveDungeonRoomAfterAbsoluteTimeChecker;
import com.mygdx.nextSectionChecker.MoveDungeonRoomBeforeAbsoluteTimeChecker;
import com.mygdx.nextSectionChecker.MoveDungeonRoomChecker;
import com.mygdx.nextSectionChecker.MoveDungeonRoomInTargetTimeChecker;
import com.mygdx.nextSectionChecker.MoveFieldChecker;
import com.mygdx.nextSectionChecker.MoveNodeChecker;
import com.mygdx.nextSectionChecker.MoveSubNodeAfterAbsoluteTimeChecker;
import com.mygdx.nextSectionChecker.MoveSubNodeBeforeAbsoluteTimeChecker;
import com.mygdx.nextSectionChecker.MoveSubNodeChecker;
import com.mygdx.nextSectionChecker.NextSectionChecker;

public class NextSectionCheckerFactory {
	@Autowired
	private ApplicationContext context;

	public NextSectionChecker getNextSectionChecker(EventTypeEnum eventType) {
		switch (eventType) {
			case battle_command :
				return context.getBean(BattleCommandChecker.class);
			case end_battle :
				return context.getBean(BattleEndChecker.class);
			case choice_option :
				return context.getBean(ChoiceOptionChecker.class);
			case collect_event :
				return context.getBean(CollectEventChecker.class);
			case move_dungeon_room :
				return context.getBean(MoveDungeonRoomChecker.class);
			case move_dungeon_room_after_absolute_time :
				return context.getBean(MoveDungeonRoomAfterAbsoluteTimeChecker.class);
			case move_dungeon_room_before_absolute_time :
				return context.getBean(MoveDungeonRoomBeforeAbsoluteTimeChecker.class);
			case move_dungeon_room_in_target_time :
				return context.getBean(MoveDungeonRoomInTargetTimeChecker.class);
			case move_field :
				return context.getBean(MoveFieldChecker.class);
			case move_node :
				return context.getBean(MoveNodeChecker.class);
			case move_sub_node :
				return context.getBean(MoveSubNodeChecker.class);
			case move_sub_node_after_absolute_time :
				return context.getBean(MoveSubNodeAfterAbsoluteTimeChecker.class);
			case move_sub_node_before_absolute_time :
				return context.getBean(MoveSubNodeBeforeAbsoluteTimeChecker.class);
			default :
				Gdx.app.log("NextSectionCheckerFactory", "eventType 정보 오류" + eventType);
				return context.getBean(DoNothingChecker.class);
		}
	}
}
