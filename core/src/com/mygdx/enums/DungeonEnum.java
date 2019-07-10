package com.mygdx.enums;

public class DungeonEnum {
	public enum Type {
		gate("gate"), elite("elite"), object("object"), boss("boss"), up_stair("up_stair"), down_stair("down_stair"), normal(
				"normal");
		private String code;

		private Type(String code) {
			this.code = code;
		}

		@Override
		public String toString() {
			return code;
		}
	}

	public enum Direction {
		forward("forward"), backward("backward");
		private String code;

		private Direction(String code) {
			this.code = code;
		}

		@Override
		public String toString() {
			return code;
		}
	}

	public enum ForwardAngle {
		left("left"), right("right"), top_left("top_left"), bottom_left("bottom_left"), top_right("top_right"), bottom(
				"bottom"), top("top"), bottom_right("bottom_right");
		private String code;

		private ForwardAngle(String code) {
			this.code = code;
		}

		@Override
		public String toString() {
			return code;
		}
	}
}
