package com.mygdx.manager;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.Gdx;
import com.mygdx.enums.PositionEnum;
import com.mygdx.enums.PositionEnum.EventPosition;
import com.mygdx.enums.PositionEnum.LocatePosition;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.enums.WorldNodeEnum;
import com.mygdx.factory.ScreenFactory;

public class MovingManager {
	@Autowired
	private ScreenFactory screenFactory;
	@Autowired
	private PositionManager positionManager;
	@Autowired
	private EventManager eventManager;
	@Autowired
	private BattleManager battleManager;

	public void goToNode(String Node) {
		positionManager.setCurrentNodePath(Node);
		WorldNodeEnum.NodeType nodeType = positionManager.getCurrentNodeType();
		goCurrentNode(nodeType);
	}

	public void goCurrentLocatePosition() {
		WorldNodeEnum.NodeType nodeType = positionManager.getCurrentNodeType();
		if (positionManager.isInWorldMap()) {
			positionManager.setInWorldMap(false);
		}
		if (positionManager.getCurrentEventPositionType() != PositionEnum.EventPosition.none) {
			goBeforeEventPosition();
		} else {
			Gdx.app.log("MovingManager", "go" + positionManager.getCurrentLocatePositionType());
			switch (positionManager.getCurrentLocatePositionType()) {
				case NODE :
					goCurrentNode(nodeType);
					break;
				case SUB_NODE :
					goCurrentSubNode(nodeType);
					break;
				case FIELD :
					screenFactory.show(ScreenEnum.field);
					break;
				case DUNGEON :
					screenFactory.show(ScreenEnum.dungeon);
				default :
					Gdx.app.log("MovingManager", "NodeType정보 오류");
					break;
			}
		}
	}
	private void goBeforeEventPosition() {
		switch (positionManager.getCurrentEventPositionType()) {
			case game_object :
				screenFactory.show(ScreenEnum.game_object);
				break;
			case battle :
				positionManager.setCurrentEventPositionType(PositionEnum.EventPosition.none);
				goCurrentLocatePosition();
				break;
			case npc :
				screenFactory.show(ScreenEnum.greeting);
				break;
			case story :
				positionManager.setCurrentEventPositionType(PositionEnum.EventPosition.none);
				goCurrentLocatePosition();
				break;
			case world_map :
				positionManager.setCurrentEventPositionType(EventPosition.none);
				screenFactory.show(ScreenEnum.status);
				break;
			case log :
				positionManager.setCurrentEventPositionType(EventPosition.none);
				goCurrentLocatePosition();
				break;
			default :
				Gdx.app.log("MovingManager", "EventPosition 정보 오류");
				break;
		}
	}

	public void goPreviousPosition() {
		switch (positionManager.getCurrentLocatePositionType()) {
			case SUB_NODE :
				positionManager.setCurrentLocatePositionType(LocatePosition.NODE);
				screenFactory.show(ScreenEnum.findScreenEnum(positionManager.getCurrentNodeType().toString()));
				break;
			default :
				Gdx.app.log("MovingManager", "PositionEnum정보 오류");
		}
	}

	public void goCurrentNode(WorldNodeEnum.NodeType nodeType) {
		switch (nodeType) {
			case village :
				screenFactory.show(ScreenEnum.village);
				return;
			case dungeon_entrance :
				screenFactory.show(ScreenEnum.dungeon_entrance);
				return;
			case fork :
				screenFactory.show(ScreenEnum.fork);
				return;
		}
	}

	private void goCurrentSubNode(WorldNodeEnum.NodeType nodeType) {
		switch (nodeType) {
			case village :
				screenFactory.show(ScreenEnum.building);
				return;
			case dungeon_entrance :
				screenFactory.show(ScreenEnum.dungeon);
				return;
			case fork :
				screenFactory.show(ScreenEnum.field); // FIXME
				return;
		}
	}
}
