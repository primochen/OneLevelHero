package com.mygdx.eventTrigger;

import org.springframework.beans.factory.annotation.Autowired;

import com.mygdx.enums.QuestEnum.QuestState;
import com.mygdx.enums.QuestEnum.QuestType;
import com.mygdx.manager.QuestManager;
import com.mygdx.manager.StorySectionManager;
import com.mygdx.model.event.EventParameters;
import com.mygdx.model.event.Quest;

public class QuestGetItemEventTrigger implements EventTrigger {
	@Autowired
	private QuestManager questManager;
	@Autowired
	private StorySectionManager storySectionManager;

	@Override
	public void triggerEvent(EventParameters eventParameter) {
		Quest quest = new Quest();
		quest.setQuestName(eventParameter.getQuest().getQuestName());
		quest.setQuestObjectPath(eventParameter.getQuest().getQuestObjectPath());
		quest.setQuestObjectCount(eventParameter.getQuest().getQuestObjectCount());
		quest.setQuestType(QuestType.get_item);
		quest.setQuestState(QuestState.ing);
		questManager.addQuest(quest);
		storySectionManager.runStorySequence();
	}

}
