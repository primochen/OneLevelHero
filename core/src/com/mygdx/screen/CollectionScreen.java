package com.mygdx.screen;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.assets.UiComponentAssets;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.listener.SimpleTouchListener;

public class CollectionScreen extends BaseScreen {
	@Autowired
	private UiComponentAssets uiComponentAssets;
	private Stage stage;
	private TextButton endingButton;
	private TextButton cgButton;
	private TextButton bgmButton;
	private TextButton backButton;

	public CollectionScreen() {
	}

	@Override
	public void render(float delta) {
		super.render(delta);

		stage.draw();
	}

	@Override
	public void show() {
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		Table table = new Table(uiComponentAssets.getSkin());

		endingButton = new TextButton("엔딩", uiComponentAssets.getSkin());
		cgButton = new TextButton("CG", uiComponentAssets.getSkin());
		bgmButton = new TextButton("BGM", uiComponentAssets.getSkin());
		backButton = new TextButton("Back", uiComponentAssets.getSkin());

		endingButton.addListener(new SimpleTouchListener() {
			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				screenFactory.push(ScreenEnum.ending);
			}
		});
		cgButton.addListener(new SimpleTouchListener() {
			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				screenFactory.push(ScreenEnum.cg);
			}
		});
		bgmButton.addListener(new SimpleTouchListener() {
			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				screenFactory.push(ScreenEnum.bgm);
			}
		});
		backButton.addListener(new SimpleTouchListener() {
			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				screenFactory.pop();
			}
		});

		table.setFillParent(true);
		table.add(endingButton).expand().width(240).height(240).top().left();
		table.add(cgButton).width(240).height(240).top().right();
		table.row();
		table.add(bgmButton).width(240).height(240).bottom().left();
		table.add(backButton).bottom();
		table.row();

		stage.addActor(table);
	}
}
