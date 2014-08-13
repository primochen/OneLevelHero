package com.mygdx.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.mygdx.game.OneLevelHero;
import com.mygdx.inventory.Inventory;
import com.mygdx.inventory.InventoryActor;
import com.mygdx.resource.Assets;
import com.mygdx.resource.GameUi;
import com.mygdx.stage.VillageStage;

public class VillageScreen implements Screen {

	OneLevelHero game;
	Image background;
	SpriteBatch batch;
	String villageName;

	VillageStage villageStage1;
	VillageStage villageStage2;
	Stage uiStage;
	public static Stage inventoryStage;
	InventoryActor inventoryActor;
	int key = 2;

	boolean state = true;

	public VillageScreen(OneLevelHero game) {
		this.game = game;
	}

	public VillageScreen(OneLevelHero game, String villagename) {
		this.game = game;
		this.villageName = villagename;
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		villageStage1.draw();
		uiStage.draw();
		if (Gdx.input.isKeyPressed(Input.Keys.ANY_KEY)) {
			inventoryActor.setVisible(true);
		}

		inventoryStage.act(delta);
		inventoryStage.draw();

	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		villageStage1 = new VillageStage(villageName + "-0", game);

		// 배경 스테이지와 유아이 스테이지는 따로 관리해 준다.
		uiStage = new Stage();

		// 인벤토리 스테이지
		inventoryStage = new Stage();

		// 여러 스테이지에 인풋 프로세서를 동시에 할당한다
		InputMultiplexer multiplexer = new InputMultiplexer();
		// 만약 버튼이 겹칠 경우 인덱스가 먼저인 쪽(숫자가 작은 쪽)에 우선권이 간다 무조건 유아이가 위에 있어야 하므로 유아이에
		// 우선권을 준다.

		multiplexer.addProcessor(0, inventoryStage);
		multiplexer.addProcessor(1, uiStage);
		multiplexer.addProcessor(2, villageStage1);
		// 멀티 플렉서에 인풋 프로세서를 할당하게 되면 멀티 플렉서 안에 든 모든 스테이지의 인풋을 처리할 수 있다.
		Gdx.input.setInputProcessor(multiplexer);

		Assets.menuScreenButtonLoad();
		Assets.loadSize(villageStage1);
		GameUi UI = new GameUi(game);

		OrthographicCamera cam = new OrthographicCamera(Assets.realWidth, Assets.realHeight / 2);
		cam.translate(100, 300);
		cam.position.set(Assets.realWidth / 2, Assets.realHeight / 2, 0);
		villageStage1.getViewport().setCamera(cam);

		Gdx.app.log("LoadLauncher - getAttack()", String.valueOf(game.loadLauncher.unit.status.getAttack()));
		Gdx.app.log("CurrentManager - getVersion()", String.valueOf(game.currentManager.getVersion()));

		villageStage1.addListener(new InputListener() {

			Vector3 last_touch_down = new Vector3();
			InputEvent event;

			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				this.event = event;
				return true;
			}

			@Override
			public void touchDragged(InputEvent event, float x, float y, int pointer) {
				moveCamera((int) x, (int) y);
				System.out.println((int) x);
			}

			private void moveCamera(int touch_x, int touch_y) {
				Vector3 new_position = getNewCameraPosition(touch_x, touch_y);

				if (!cameraOutOfLimit(new_position))
					villageStage1.getCamera().translate(new_position.sub(villageStage1.getCamera().position));

				last_touch_down.set(touch_x, touch_y, 0);
			}

			private Vector3 getNewCameraPosition(int x, int y) {
				Vector3 new_position = last_touch_down;
				new_position.sub(x, y, 0);
				new_position.y = -new_position.y;
				new_position.add(villageStage1.getCamera().position);

				return new_position;
			}

			private boolean cameraOutOfLimit(Vector3 position) {
				int x_left_limit = (int) Assets.realWidth / 2;
				int x_right_limit = (int) Assets.realWidth / 2;
				int y_bottom_limit = (int) Assets.realHeight / 5;
				int y_top_limit = (int) (Assets.realHeight / 4) * 7;

				if (position.x < x_left_limit || position.x > x_right_limit)
					return true;
				else if (position.y < y_bottom_limit || position.y > y_top_limit)
					return true;
				else
					return false;
			}

		});
		// vs1.getCamera().translate(100, 100, 0);
		// vs1.getCamera().update();

		uiStage.addActor(UI);
		// vs2.addActor(UI);

		//인벤토리 세팅
		Skin skin = Assets.skins;
		DragAndDrop dragAndDrop = new DragAndDrop();
		inventoryActor = new InventoryActor(new Inventory(), dragAndDrop, skin);
		inventoryStage.addActor(inventoryActor);
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		Gdx.input.setInputProcessor(null);
		dispose();
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		villageStage1.dispose();
		villageStage2.dispose();
	}

}
