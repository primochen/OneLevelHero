package com.mygdx.manager;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.Gdx;
import com.mygdx.assets.EventAssets;
import com.mygdx.currentState.StorySectionInfo;
import com.mygdx.enums.EventElementEnum;
import com.mygdx.enums.EventStateEnum;
import com.mygdx.enums.EventTypeEnum;
import com.mygdx.enums.PositionEnum.EventPosition;
import com.mygdx.factory.NextSectionCheckerFactory;
import com.mygdx.model.event.EventPacket;
import com.mygdx.model.event.StorySection;
import com.mygdx.model.event.StorySectionPacket;
import com.mygdx.nextSectionChecker.NextSectionChecker;

public class StorySectionManager {
	@Autowired
	private EventManager eventManager;
	@Autowired
	private MovingManager movingManager;
	@Autowired
	private EventAssets eventAssets;
	@Autowired
	private StorySectionInfo storySectionInfo;
	@Autowired
	private PositionManager positionManager;
	@Autowired
	private NextSectionCheckerFactory nextSectionCheckerFactory;
	@Autowired
	private TimeManager timeManager;
	private Queue<EventPacket> eventSequenceQueue = new LinkedList<>();

	public void setNewStorySectionAndPlay(int storyNumber) {
		setNewStorySection(storyNumber);
		setNewStorySectionNumber(storyNumber);
		storySectionInfo.setStoryStartTime(timeManager);
		Gdx.app.log("StorySectionManager", "현재 분기번호 [" + storyNumber
				+ "] 가동중-------------------------------------------------------------------------------");
		insertStorySequence();
		insertConditionalNpcEvents();
		insertConditionalGameObjectEvents();
		runStorySequence();
	}

	private void insertConditionalGameObjectEvents() {
		if (storySectionInfo.getCurrentStorySection() == null) {
			return;
		}
		List<EventPacket> conditionalGameObjectEvent = storySectionInfo.getCurrentStorySection()
				.getConditionalGameObjectEvents();
		if (conditionalGameObjectEvent != null) {
			for (EventPacket eventPacket : conditionalGameObjectEvent) {
				eventManager.setGameObjectEventState(eventPacket, EventStateEnum.opened);
			}
		}
	}

	private void setNewStorySectionNumber(int storyNumber) {
		storySectionInfo.setCurrentSectionNumber(storyNumber);
	}

	public int getCurrentStorySectionNumber() {
		return storySectionInfo.getCurrentSectionNumber();
	}

	private void insertConditionalNpcEvents() {
		if (storySectionInfo.getCurrentStorySection() == null) {
			return;
		}
		List<EventPacket> conditionalEvent = storySectionInfo.getCurrentStorySection().getConditionalNpcEvents();
		if (conditionalEvent != null) {
			for (EventPacket eventPacket : conditionalEvent) {
				eventManager.setNpcEventState(eventPacket, EventStateEnum.opened);
			}
		}
	}

	public boolean triggerNextSectionEvent(EventTypeEnum eventType, String... args) {
		if (getNextSections() != null) {
			for (StorySectionPacket nextStorySectionPacket : getNextSections()) {
				if (eventType.equals(nextStorySectionPacket.getEventType())) {
					NextSectionChecker nextSectionChecker = nextSectionCheckerFactory.getNextSectionChecker(eventType);
					if (nextSectionChecker.checkNextEvent(nextStorySectionPacket.getEventParameter(), args)) {
						setNewStorySectionAndPlay(nextStorySectionPacket.getNextSectionNumber());
						return true;
					}
				}
			}
		}
		return false;
	}

	public void insertStorySequence() {
		Gdx.app.log("StorySectionManager", "insertStorySequence");
		if (storySectionInfo.getCurrentStorySection() == null) {
			Gdx.app.log("StorySectionManager", "해당 분기의 sequencialEvents가 없습니다");
			return;
		}
		List<EventPacket> sequencialEvent = storySectionInfo.getCurrentStorySection().getSequencialEvents();
		for (EventPacket eventPacket : sequencialEvent) {
			eventSequenceQueue.add(eventPacket);
		}
	}

	public void runStorySequence() {
		Gdx.app.log("StorySectionManager", "runStorySequence");
		if (!eventSequenceQueue.isEmpty()) {
			EventPacket polledEventPacket = eventSequenceQueue.poll();
			positionManager.setCurrentEventPositionType(EventPosition.story);
			eventManager.triggerEvent(EventElementEnum.story, polledEventPacket);
		} else {
			goCurrentPosition();
		}
	}

	public void setNewStorySection(int storyNumber) {
		storySectionInfo.setCurrentStorySection(eventAssets.getStorySection(storyNumber));
	}

	public List<StorySectionPacket> getNextSections() {
		return storySectionInfo.getCurrentStorySection().getNextSections();
	}

	private void goCurrentPosition() {
		movingManager.goCurrentLocatePosition();
	}

	public void setCurrentStorySection(StorySection storySection) {
		storySectionInfo.setCurrentStorySection(storySection);
	}

	public StorySection getCurrentStorySection() {
		return storySectionInfo.getCurrentStorySection();
	}

	public Queue<EventPacket> getSequenceQueue() {
		return eventSequenceQueue;
	}

	public void setSequenceQueue(Queue<EventPacket> sequenceQueue) {
		this.eventSequenceQueue = sequenceQueue;
	}

}
