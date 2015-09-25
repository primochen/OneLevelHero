package com.mygdx.nextSectionChecker;

import org.springframework.beans.factory.annotation.Autowired;

import com.mygdx.manager.TimeManager;
import com.mygdx.model.event.EventParameters;
import com.mygdx.model.location.TargetTime;

public class MoveDungeonRoomInTargetTimeChecker implements NextSectionChecker {
	@Autowired
	private TimeManager timeManager;
	@Override
	public boolean checkNextEvent(EventParameters eventParameter, String... args) {
		if (args != null) {
			if (args.length == 3) {
				String subNodeName = eventParameter.getLocation().getSubNodeName();
				String floorName = eventParameter.getLocation().getFloorName();
				String roomLabel = eventParameter.getLocation().getRoomLabel();
				if (ArgumentChecker.checkIsSame(subNodeName, args[0])) {
					if (ArgumentChecker.checkIsSame(floorName, args[1])) {
						if (ArgumentChecker.checkIsSame(roomLabel, args[2])) {
							int startHour = eventParameter.getTime().getStartHour();
							int endHour = eventParameter.getTime().getEndHour();
							TargetTime timeInfo = new TargetTime(startHour, endHour);
							int currentMinute = timeManager.getDayMinute();
							if (ArgumentChecker.checkIsInTargetTime(timeInfo, currentMinute))
								return true;
						}
					}
				}
			}
		}
		return false;
	}
}
