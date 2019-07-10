package com.mygdx.enums;

public enum TextureEnum {
	background("bg"), game_object("object"), face("face"), bust("bust"), npc("npc"), monster("monster"), normal("01"), attack_cutting(
			"attack_cutting"), attack_cutting2("attack_cutting2"), battle("battle"), status("status"), item("item"), big_image(
			"big"), small_image("small"), minimap("minimap");
	// FIXME 애니메이션 시트를 여기에 같이 두는게 적절한가?

	private String textureNumber;

	TextureEnum(String textureNumber) {
		this.setTextureNumber(textureNumber);
	}

	public String getTextureNumber() {
		return textureNumber;
	}

	public void setTextureNumber(String textureNumber) {
		this.textureNumber = textureNumber;
	}

	@Override
	public String toString() {
		return textureNumber;
	}
}
