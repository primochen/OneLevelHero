package com.mygdx.manager;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.assets.TextureAssets;
import com.mygdx.enums.TextureEnum;
//import com.sun.istack.internal.NotNull;

/**
 * 穈��� ����� Texture諝� ������ � ��� ����� ���� 諢�� StaticAssets��� �諴刷萼 ��爰��� �篣�� ���穈� ���. �����
 * 篞賈�_���狩_諈����� 麆資����
 * http://cien.woobi.co.kr/dokuwiki/doku.php?id=%EC%A0%9C%EC%9E%91%ED%99%9C%EB%
 * 8F%99:%EA%B7%B8%EB%9E%98%ED%94%BD_%EC%9E%91%EC%97%85%EB%AC%BC_%EB%AA%85%EB%AA
 * %85%EA%B7%9C%EC%B9%99
 * 
 * @author Velmont
 * 
 */
public class TextureManager {
	@Autowired
	private AssetsManager assetsManager;
	@Autowired
	private TextureAssets textureAssets;
	private boolean count;
	private String[] preName = { "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" };
	// 1. ����穇� 賱���� ���
	// 2. �� (諯賈收 諢��)
	// 3. 諈拗����(諯賈收 諢��)

	public boolean checkPreName(String nowName, String preName) {
		boolean check;
		if (nowName == preName) {
			check = true;
		} else {
			check = false;
			if (preName != "") {
				if (assetsManager.isLoaded(textureAssets.getTexturePath(preName))) {
					assetsManager.unload(textureAssets.getTexturePath(preName));
					assetsManager.finishLoading();
				}
			}
		}
		return check;
	}

	//@NotNull
	public Texture getTexture(String texturePath, String defaultPath) {
		if (textureAssets.getTexturePath(texturePath) != null) {
			assetsManager.load(textureAssets.getTexturePath(texturePath), Texture.class);
			assetsManager.finishLoading();
			return assetsManager.get(textureAssets.getTexturePath(texturePath));
		} else {
			return getTexture(defaultPath);
		}
	}

	public Texture getTexture(String texturePath) {
		return getTexture(texturePath, TextureEnum.bust + "_default_01");
	}

	public Texture getMonsterTexture(String monsterPath) {
		if (textureAssets.getTexturePath(TextureEnum.monster + "_" + monsterPath) != null) {
			preName[14] = TextureEnum.monster + "_" + monsterPath;
			assetsManager.load(textureAssets.getTexturePath(preName[14]), Texture.class);
			assetsManager.finishLoading();
			return assetsManager.get(textureAssets.getTexturePath(preName[14]), Texture.class);
		} else {
			Gdx.app.log("TextureManager", TextureEnum.monster.toString() + "_" + monsterPath + "穈� ����");
			return getTexture(preName[14]);
		}
	}

	public Texture getBustTexture(String facePath) {
		return getTexture(facePath);
	}

	public Texture getBustTexture(String facePath, String faceNumber) {
		return getTexture(TextureEnum.bust + "_" + facePath + "_" + faceNumber);
	}

	public Texture getStatusTexture(String facePath) {
		return getTexture(TextureEnum.status + "_" + facePath);
	}

	public Texture getCharacterBodyTexture(String facePath) {
		if (textureAssets.getTexturePath(TextureEnum.npc + "_" + facePath) != null) {
			count = false;
			if (count) {
				if (!checkPreName(TextureEnum.npc + "_" + facePath, preName[5])) {
					preName[5] = TextureEnum.npc + "_" + facePath;
					assetsManager.load(textureAssets.getTexturePath(preName[5]), Texture.class);
					assetsManager.finishLoading();
				} else {
					preName[5] = TextureEnum.npc + "_" + facePath;
				}
			} else {
				preName[5] = TextureEnum.npc + "_" + facePath;
				assetsManager.load(textureAssets.getTexturePath(preName[5]), Texture.class);
				assetsManager.finishLoading();
			}
			return assetsManager.get(textureAssets.getTexturePath(preName[5]), Texture.class);
		} else
			Gdx.app.log("TextureManager", "NPC_" + facePath + "is Null");
		return getTexture(preName[5]);
	}

	public Texture getFaceTexture(String facePath) {
		return getTexture(TextureEnum.face + "_" + facePath);
	}

	public Texture getMonsterBattleTexture(String facePath) {
		if (textureAssets.getTexturePath(TextureEnum.monster + "_" + facePath) != null) {
			if (!checkPreName(TextureEnum.monster + "_" + facePath, preName[7])) {
				preName[7] = TextureEnum.monster + "_" + facePath;
				assetsManager.load(textureAssets.getTexturePath(preName[7]), Texture.class);
				assetsManager.finishLoading();
			} else {
				Gdx.app.log("TextureManager", "monster_" + facePath + "is null");
				preName[7] = TextureEnum.monster + "_" + facePath;
			}
			return assetsManager.get(textureAssets.getTexturePath(preName[7]), Texture.class);
		} else
			return getTexture(preName[7]);
	}

	public Texture getMonsterBodyTexture(String facePath) {
		if (textureAssets.getTexturePath(TextureEnum.monster + "_" + facePath) != null) {
			if (!checkPreName(TextureEnum.monster + "_" + facePath, preName[8])) {
				preName[8] = TextureEnum.monster + "_" + facePath;
				assetsManager.load(textureAssets.getTexturePath(preName[8]), Texture.class);
				assetsManager.finishLoading();
			} else {
				Gdx.app.log("TextureManager", "monster_" + facePath + "is null");
				preName[8] = TextureEnum.monster + "_" + facePath;
			}
			return assetsManager.get(textureAssets.getTexturePath(preName[8]), Texture.class);
		} else
			return getTexture(preName[8]);
	}

	public Texture getItemTexture(String itemPath) {
		return getTexture(TextureEnum.item + "_" + "one_hand_sword");
	}

	public Texture getBackgroundTexture(String backgroundName) {
		if (textureAssets.getTexturePath(TextureEnum.background + "_" + backgroundName) != null) {
			if (!checkPreName(TextureEnum.background + "_" + backgroundName, preName[10])) {
				preName[10] = TextureEnum.background + "_" + backgroundName;
				assetsManager.load(textureAssets.getTexturePath(preName[10]), Texture.class);
				assetsManager.finishLoading();
			} else {
				preName[10] = TextureEnum.background + "_" + backgroundName;
			}
			return assetsManager.get(textureAssets.getTexturePath(preName[10]), Texture.class);
		} else {
			Gdx.app.log("TextureManager", "bg_" + backgroundName + "is null");
			return getTexture(preName[10], TextureEnum.background + "_" + "prog_team_01");
		}
	}

	public Texture getBackgroundTexture(String backgroundPath, TextureEnum textureEnum) {
		if (textureAssets.getTexturePath(TextureEnum.background + "_" + backgroundPath + "_" + textureEnum) != null) {
			if (!checkPreName(TextureEnum.background + "_" + backgroundPath + "_" + textureEnum, preName[12])) {
				preName[12] = TextureEnum.background + "_" + backgroundPath + "_" + textureEnum;
				assetsManager.load(textureAssets.getTexturePath(preName[12]), Texture.class);
				assetsManager.finishLoading();
			} else {
				preName[12] = TextureEnum.background + "_" + backgroundPath + "_" + textureEnum;
			}
			return assetsManager.get(textureAssets.getTexturePath(preName[12]), Texture.class);
		} else {
			Gdx.app.log("TextureManager", "background_" + backgroundPath + "_" + textureEnum.toString() + "is null");
			return getTexture(preName[12]);
		}
	}

	public Texture getFaceImage(String facePath) {
		return getTexture(TextureEnum.face + "_" + facePath);
	}

	public Texture getSmallBattleImage(String facePath) {
		return getTexture(TextureEnum.battle + "_" + facePath + "_" + TextureEnum.small_image);
	}

	public Texture getBigBattleImage(String facePath) {
		return getTexture(TextureEnum.battle + "_" + facePath + "_" + TextureEnum.big_image,
				TextureEnum.battle + "_default_" + TextureEnum.big_image);
	}

	public Texture getGameObjectTexture(String objectPath) {
		if (textureAssets.getTexturePath(TextureEnum.game_object + "_" + objectPath) != null) {
			preName[11] = TextureEnum.game_object + "_" + objectPath;
			assetsManager.load(textureAssets.getTexturePath(preName[11]), Texture.class);
			assetsManager.finishLoading();
			return assetsManager.get(textureAssets.getTexturePath(preName[11]), Texture.class);
		} else {
			Gdx.app.log("TextureManager", "object_" + objectPath + " is null");
			return getTexture(objectPath, TextureEnum.bust + "_default_01");
		}
	}

	public Texture getMinimapTexture(String floorPath) {
		if (textureAssets.getTexturePath(floorPath + "_" + TextureEnum.minimap) != null) {
			preName[13] = floorPath + "_" + TextureEnum.minimap;
			assetsManager.load(textureAssets.getTexturePath(preName[13]), Texture.class);
			assetsManager.finishLoading();
			return assetsManager.get(textureAssets.getTexturePath(preName[13]), Texture.class);
		} else {
			Gdx.app.log("TextureManager", floorPath + "_" + TextureEnum.minimap + "is null");
			return getTexture(floorPath, TextureEnum.bust + "_default_01");
		}
	}

}
