package com.mygdx.listener;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.enums.GameObjectEnum;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.factory.ScreenFactory;
import com.mygdx.manager.EventManager;
import com.mygdx.manager.SoundManager;
import com.mygdx.model.event.GameObject;

public class GameObjectButtonListener extends ClickListener {
	@Autowired
	private EventManager eventManager;
	@Autowired
	private ScreenFactory screenFactory;
	@Autowired
	private SoundManager soundManager;
	private GameObject pressedGameObject;

	@Override
	public void clicked(InputEvent event, float x, float y) {
		soundManager.playClickSound();
		if (pressedGameObject.getObjectType().equals(GameObjectEnum.normal)) {
			pressedGameObject.setObjectType(GameObjectEnum.pressed);
		}
		eventManager.setCurrentGameObject(pressedGameObject);
		screenFactory.show(ScreenEnum.game_object);
	}

	public void setGameObject(GameObject gameObject) {
		this.pressedGameObject = gameObject;
	}
}