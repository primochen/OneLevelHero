package com.mygdx.currentState;

import com.mygdx.enums.BattleStateEnum;
import com.mygdx.enums.CurrentClickStateEnum;
import com.mygdx.enums.FieldTypeEnum;
import com.mygdx.enums.PositionEnum;
import com.mygdx.model.unit.Hero;
import com.mygdx.model.unit.Monster;

public class BattleInfo {
	private Monster currentMonster;
	private BattleStateEnum battleState;
	private CurrentClickStateEnum currentClickState;
	private Hero currentActor;
	private PositionEnum beforePosition;
	private FieldTypeEnum fieldType;

	public Hero getCurrentActor() {
		return currentActor;
	}

	public void setCurrentActor(Hero currentActor) {
		this.currentActor = currentActor;
	}

	public BattleStateEnum getBattleState() {
		return battleState;
	}

	public void setBattleState(BattleStateEnum battleState) {
		this.battleState = battleState;
	}

	public CurrentClickStateEnum getcurrentClickStateEnum() {
		return currentClickState;
	}

	public void setCurrentClickStateEnum(CurrentClickStateEnum currentClickState) {
		this.currentClickState = currentClickState;
	}

	public Monster getCurrentMonster() {
		return currentMonster;
	}

	public void setCurrentMonster(Monster currentMonster) {
		this.currentMonster = currentMonster;
	}

	public PositionEnum getBeforePosition() {
		return beforePosition;
	}

	public void setBeforePosition(PositionEnum beforePosition) {
		this.beforePosition = beforePosition;
	}

	public void setFieldType(FieldTypeEnum fieldType) {
		this.fieldType = fieldType;

	}

	public FieldTypeEnum getFieldType() {
		return fieldType;
	}

}
