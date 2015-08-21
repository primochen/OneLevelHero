package com.mygdx.manager;

import org.springframework.beans.factory.annotation.Autowired;

import com.mygdx.assets.WorldMapAssets;
import com.mygdx.currentState.FieldInfo;
import com.mygdx.enums.FieldTypeEnum;
import com.mygdx.model.surroundings.NodeConnection;
import com.mygdx.model.surroundings.WorldNode;

public class FieldManager {
	@Autowired
	private WorldMapAssets worldMapAssets;
	@Autowired
	private EncounterManager encounterManager;
	@Autowired
	private PositionManager positionManager;
	@Autowired
	private MovingManager movingManager;
	@Autowired
	private FieldInfo fieldInfo;

	public FieldTypeEnum getFieldType() {
		return fieldInfo.getFieldList().get(getFieldNumber());
	}

	public String getDestinationNode() {
		return fieldInfo.getDestinationNode();
	}

	public int getLeftFieldLength() {
		return getFieldLength() - getFieldNumber();
	}

	public int getFieldNumber() {
		return fieldInfo.getFieldNumber();
	}

	public boolean isInField() {
		return fieldInfo.isInField();
	}

	public int getFieldLength() {
		return fieldInfo.getFieldList().size();
	}

	public void startMovingField(String destinationNode) {
		WorldNode worldNodeInfo = worldMapAssets.getWorldNodeInfo(positionManager.getCurrentNodeName());
		String startNode = positionManager.getCurrentNodeName();
		NodeConnection conn = worldNodeInfo.getNodeConnection().get(destinationNode);
		fieldInfo.setFieldInfo(startNode, destinationNode, conn);
	}

	public void goForwardField() {
		if (!willBeArrived()) {
			increaseFieldNumber();
			moveField();
		} else {
			positionManager.setCurrentNodeName(fieldInfo.getDestinationNode());
			fieldInfo.setInField(false);
		}
	}

	public void goBackwardField() {
		if (!willComeBack()) {
			decreaseFieldNumber();
			moveField();
		} else {
			positionManager.setCurrentNodeName(fieldInfo.getStartNode());
			fieldInfo.setInField(false);
		}
	}

	private void setFieldNumber(int fieldNumber) {
		fieldInfo.setFieldNumber(fieldNumber);
	}

	private void decreaseFieldNumber() {
		setFieldNumber(getFieldNumber() - 1);
	}

	private void increaseFieldNumber() {
		setFieldNumber(fieldInfo.getFieldNumber() + 1);
	}

	private void moveField() {
		encounterManager.encountEnemy();
	}

	private boolean willBeArrived() {
		return (getFieldNumber() >= getFieldLength() - 1) ? true : false;
	}

	private boolean willComeBack() {
		return (getFieldNumber() == 0) ? true : false;
	}

	public String getArrowName() {
		return fieldInfo.getArrowName();
	}

	public void goInField() {
		moveField();
	}

}
