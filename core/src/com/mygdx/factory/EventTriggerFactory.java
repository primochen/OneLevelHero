package com.mygdx.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.badlogic.gdx.Gdx;
import com.mygdx.enums.EventTypeEnum;
import com.mygdx.eventTrigger.AlwaysOpenGameObjectEventTrigger;
import com.mygdx.eventTrigger.AlwaysOpenNpcEventTrigger;
import com.mygdx.eventTrigger.ChatEventTrigger;
import com.mygdx.eventTrigger.CheckQuestEventTrigger;
import com.mygdx.eventTrigger.ChoiceOptionEventTrigger;
import com.mygdx.eventTrigger.CloseGameObjectEventTrigger;
import com.mygdx.eventTrigger.CloseNpcEventTrigger;
import com.mygdx.eventTrigger.EndBattleEventTrigger;
import com.mygdx.eventTrigger.EventTrigger;
import com.mygdx.eventTrigger.GameClearEventTrigger;
import com.mygdx.eventTrigger.GameOverEventTrigger;
import com.mygdx.eventTrigger.GetBuffEventTrigger;
import com.mygdx.eventTrigger.GetItemEventTrigger;
import com.mygdx.eventTrigger.GoSubNodeEventTrigger;
import com.mygdx.eventTrigger.JoinPartyEventTrigger;
import com.mygdx.eventTrigger.MoveDungeonRoomEventTrigger;
import com.mygdx.eventTrigger.MoveNodeEventTrigger;
import com.mygdx.eventTrigger.MoveSubNodeEventTrigger;
import com.mygdx.eventTrigger.NextSectionEventTrigger;
import com.mygdx.eventTrigger.NoEventTrigger;
import com.mygdx.eventTrigger.OpenGameObjectEventTrigger;
import com.mygdx.eventTrigger.OpenNpcEventTrigger;
import com.mygdx.eventTrigger.PassTimeEventTrigger;
import com.mygdx.eventTrigger.PlayMusicEventTrigger;
import com.mygdx.eventTrigger.QuestGetItemEventTrigger;
import com.mygdx.eventTrigger.QuestHuntMonsterEventTrigger;
import com.mygdx.eventTrigger.QuitPartyEventTrigger;
import com.mygdx.eventTrigger.RestInForkEventTrigger;
import com.mygdx.eventTrigger.RestInNodeEventTrigger;
import com.mygdx.eventTrigger.SetGameObjectTargetTimeEventTrigger;
import com.mygdx.eventTrigger.SetNpcTargetTimeEventTrigger;
import com.mygdx.eventTrigger.StartBattleEventTrigger;

public class EventTriggerFactory {
	private static final EventTrigger NO_EVENT = null;
	@Autowired
	private ApplicationContext context;

	public EventTrigger getEventTrigger(EventTypeEnum eventType) {
		switch (eventType) {
			case always_open_game_object_event :
				return context.getBean(AlwaysOpenGameObjectEventTrigger.class);
			case always_open_npc_event :
				return context.getBean(AlwaysOpenNpcEventTrigger.class);
			case end_battle :
				return context.getBean(EndBattleEventTrigger.class);
			case start_battle :
				return context.getBean(StartBattleEventTrigger.class);
			case chat :
				return context.getBean(ChatEventTrigger.class);
			case check_quest :
				return context.getBean(CheckQuestEventTrigger.class);
			case choice_option :
				return context.getBean(ChoiceOptionEventTrigger.class);
			case close_game_object_event :
				return context.getBean(CloseGameObjectEventTrigger.class);
			case close_npc_event :
				return context.getBean(CloseNpcEventTrigger.class);
			case game_clear :
				return context.getBean(GameClearEventTrigger.class);
			case game_over :
				return context.getBean(GameOverEventTrigger.class);
			case get_buff :
				return context.getBean(GetBuffEventTrigger.class);
			case get_item :
				return context.getBean(GetItemEventTrigger.class);
			case go_sub_node :
				return context.getBean(GoSubNodeEventTrigger.class);
			case join_party :
				return context.getBean(JoinPartyEventTrigger.class);
			case move_dungeon_room :
				return context.getBean(MoveDungeonRoomEventTrigger.class);
			case move_node :
				return context.getBean(MoveNodeEventTrigger.class);
			case move_sub_node :
				return context.getBean(MoveSubNodeEventTrigger.class);
			case next_section :
				return context.getBean(NextSectionEventTrigger.class);
			case open_game_object_event :
				return context.getBean(OpenGameObjectEventTrigger.class);
			case open_npc_event :
				return context.getBean(OpenNpcEventTrigger.class);
			case pass_time :
				return context.getBean(PassTimeEventTrigger.class);
			case play_music :
				return context.getBean(PlayMusicEventTrigger.class);
			case rest_in_node :
				return context.getBean(RestInNodeEventTrigger.class);
			case rest_in_fork :
				return context.getBean(RestInForkEventTrigger.class);
			case set_npc_target_time :
				return context.getBean(SetNpcTargetTimeEventTrigger.class);
			case set_game_object_target_time :
				return context.getBean(SetGameObjectTargetTimeEventTrigger.class);
			case quest_get_item :
				return context.getBean(QuestGetItemEventTrigger.class);
			case quest_hunt_monster :
				return context.getBean(QuestHuntMonsterEventTrigger.class);
			case quit_party :
				return context.getBean(QuitPartyEventTrigger.class);
			default :
				Gdx.app.log("EventTriggerFactory", "EventType정보 오류 - " + eventType);
				return context.getBean(NoEventTrigger.class);
		}
	}
}
