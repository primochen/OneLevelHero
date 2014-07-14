/*
 * Texture 생성자 안에 파일의 경로를 적을때 Gdx.files.internal 안붙이고
 * 그냥 파일 이름을 String으로 써주면 됨
 */
package com.mygdx.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.mygdx.game.OneLevelHero;
import com.mygdx.resource.Assets;
import com.mygdx.resource.Pos;
import com.mygdx.resource.Scene;
import com.mygdx.resource.Scripts;

public class PrologueScreen implements Screen{
	OneLevelHero game;
	Stage stage;
	SpriteBatch batch;
	Texture img;
	Image[] image;
	Scripts script;
	Label textlabel;
	TextureRegion region;
	Texture texture;
	Scene scene;
	
	public PrologueScreen(OneLevelHero game) {
		this.game = game;
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		//Gdx.app.log("Prologue Message", "Prologue");
		
		batch.begin();
		scene.show(); // 배경 출력
		batch.end();

		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		stage = new Stage();
		batch = new SpriteBatch();
		Table table = new Table();
		table.setFillParent(true);
		scene = new Scene(table, batch);
		scene.load("Prologue-scene1");
		scene.print();
		
		Gdx.input.setInputProcessor(stage);

/*		texture = new Texture(Gdx.files.internal("prologue/scene1.png"));
		new Texture(Gdx.files.internal("prologue/scene2.jpg"));
		new Texture(Gdx.files.internal("prologue/scene3.jpg"));
		new Texture(Gdx.files.internal("prologue/scene4.jpg"));*/

		/*stage.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				//scene.print();
				texture = new Texture("prologue/scene2.jpg");
				textlabel.setText(script.ScriptGetter("Prologue-scene2"));
				game.setScreen(new GameScreen(game));
				return true;
			}
		});*/
		
		/*image[0].addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				stage.addActor(image[1]);
				textlabel.setText(script.ScriptGetter("Prologue-scene2"));
				stage.addActor(textlabel);

				return true;
			}
		});
		image[1].addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				stage.addActor(image[2]);
				textlabel.setText(script.ScriptGetter("Prologue-scene3"));
				stage.addActor(textlabel);
				return true;
			}
		});
		image[2].addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				stage.addActor(image[3]);
				textlabel.setText(script.ScriptGetter("Prologue-scene4"));
				stage.addActor(textlabel);
				return true;
			}
		});
		image[3].addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				game.setScreen(new GameScreen(game));
				return true;
			}
		});*/
		
		stage.addActor(table);
	}

	@Override
	public void hide() {
		
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void resume() {
		
	}

	@Override
	public void dispose() {
		
	}

}
