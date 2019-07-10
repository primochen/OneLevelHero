package com.mygdx.enums;

public enum RewardStateEnum {
	not_opened("not_opened"), ing("ing"), cleared("cleared"), always_open("always_open");
	private String rewardState;

	private RewardStateEnum(String rewardState) {
		this.rewardState = rewardState;
	}

	@Override
	public String toString() {
		return rewardState;
	}
}
