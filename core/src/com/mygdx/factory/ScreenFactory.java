package com.mygdx.factory;

import java.util.Stack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.IntMap;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.screen.BGMScreen;
import com.mygdx.screen.BattleScreen;
import com.mygdx.screen.BonusPointScreen;
import com.mygdx.screen.BuildingScreen;
import com.mygdx.screen.CGScreen;
import com.mygdx.screen.CharacterChangeScreen;
import com.mygdx.screen.ChatEventScreen;
import com.mygdx.screen.ChoiceOptionScreen;
import com.mygdx.screen.CollectionScreen;
import com.mygdx.screen.CreditScreen;
import com.mygdx.screen.DungeonEntranceScreen;
import com.mygdx.screen.DungeonScreen;
import com.mygdx.screen.EncounterScreen;
import com.mygdx.screen.EndingScreen;
import com.mygdx.screen.FieldScreen;
import com.mygdx.screen.ForkScreen;
import com.mygdx.screen.GameClearScreen;
import com.mygdx.screen.GameObjectScreen;
import com.mygdx.screen.GameOverScreen;
import com.mygdx.screen.GreetingScreen;
import com.mygdx.screen.InventoryScreen;
import com.mygdx.screen.LoadingBarScreen;
import com.mygdx.screen.LogScreen;
import com.mygdx.screen.MenuScreen;
import com.mygdx.screen.SaveScreen;
import com.mygdx.screen.StatusScreen;
import com.mygdx.screen.VillageScreen;
import com.mygdx.screen.WorldMapScreen;

public class ScreenFactory {
	@Autowired
	private transient ApplicationContext context;
	private transient Game game;

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		Gdx.app.debug("ScreenFactory", "screenFactory.setGame(game)");
		this.game = game;
	}

	private IntMap<Screen> screens;
	private Stack<Screen> screenstack;

	public ScreenFactory() {
		screens = new IntMap<Screen>();
		screenstack = new Stack<Screen>();
	}

	private Screen getScreenInstance(ScreenEnum screenEnum) {
		Gdx.app.log("ScreenFactory", String.valueOf(screenEnum) + "Screen 호출");
		switch (screenEnum) {
		case battle:
			return context.getBean(BattleScreen.class);
		case bgm:
			return context.getBean(BGMScreen.class);
		case bonus_point:
			return context.getBean(BonusPointScreen.class);
		case building:
			return context.getBean(BuildingScreen.class);
		case cg:
			return context.getBean(CGScreen.class);
		case character_change:
			return context.getBean(CharacterChangeScreen.class);
		case chat_event:
			return context.getBean(ChatEventScreen.class);
		case choice_option:
			return context.getBean(ChoiceOptionScreen.class);
		case collection:
			return context.getBean(CollectionScreen.class);
		case credit:
			return context.getBean(CreditScreen.class);
		case dungeon:
			return context.getBean(DungeonScreen.class);
		case dungeon_entrance:
			return context.getBean(DungeonEntranceScreen.class);
		case encounter:
			return context.getBean(EncounterScreen.class);
		case ending:
			return context.getBean(EndingScreen.class);
		case game_clear:
			return context.getBean(GameClearScreen.class);
		case game_object:
			return context.getBean(GameObjectScreen.class);
		case game_over:
			return context.getBean(GameOverScreen.class);
		case greeting:
			return context.getBean(GreetingScreen.class);
		case inventory:
			return context.getBean(InventoryScreen.class);
		case loading_bar:
			return context.getBean(LoadingBarScreen.class);
		case log:
			return context.getBean(LogScreen.class);
		case menu:
			return context.getBean(MenuScreen.class);
		case field:
			return context.getBean(FieldScreen.class);
		case fork:
			return context.getBean(ForkScreen.class);
		case save:
			return context.getBean(SaveScreen.class);
		case status:
			return context.getBean(StatusScreen.class);
		case village:
			return context.getBean(VillageScreen.class);
		case world_map:
			return context.getBean(WorldMapScreen.class);
		default:
			return context.getBean(VillageScreen.class); // FIXME
		}
	}

	public void show(ScreenEnum screenEnum) {
		Gdx.app.debug("ScreenFactory", "show(" + String.valueOf(screenEnum) + ")");
		if (game == null) {
			Gdx.app.debug("ScreenFactory", "game is null");
			return;
		}
		if (!screens.containsKey(screenEnum.ordinal()))
			screens.put(screenEnum.ordinal(), getScreenInstance(screenEnum));
		game.setScreen(screens.get(screenEnum.ordinal()));
	}

	public void dispose(ScreenEnum screen) {
		if (!screens.containsKey(screen.ordinal()))
			return;
		screens.remove(screen.ordinal()).dispose();
	}

	public void dispose() {
		for (com.badlogic.gdx.Screen screen : screens.values())
			screen.dispose();
		screens.clear();
	}

	public void push(ScreenEnum screenEnum) {
		game.setScreen(screenstack.push(getScreenInstance(screenEnum)));
	}

	public void pop() {
		screenstack.pop().dispose();
		game.setScreen(screenstack.peek());
	}

	public void popAndPush(ScreenEnum screenEnum) {
		screenstack.pop().dispose();
		game.setScreen(screenstack.push(getScreenInstance(screenEnum)));
	}

	public void popAllAndPush(ScreenEnum screenEnum) {
		while (!screenstack.isEmpty())
			screenstack.pop().dispose();
		game.setScreen(screenstack.push(getScreenInstance(screenEnum)));
	}
}
