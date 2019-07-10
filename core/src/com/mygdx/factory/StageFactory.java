package com.mygdx.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.enums.StageEnum;
import com.mygdx.stage.BattleCommandStage;
import com.mygdx.stage.BattleInfoMessageStage;
import com.mygdx.stage.BattleStage;
import com.mygdx.stage.BuildingRestPopupStage;
import com.mygdx.stage.BuildingStage;
import com.mygdx.stage.CharacterChangeStage;
import com.mygdx.stage.CharacterUiStage;
import com.mygdx.stage.ChatEventStage;
import com.mygdx.stage.ChoiceEventStage;
import com.mygdx.stage.ChoiceOptionStage;
import com.mygdx.stage.CreditStage;
import com.mygdx.stage.DungeonEntranceRestPopupStage;
import com.mygdx.stage.DungeonEntranceStage;
import com.mygdx.stage.DungeonMinimapStage;
import com.mygdx.stage.DungeonStage;
import com.mygdx.stage.EncounterStage;
import com.mygdx.stage.FieldStage;
import com.mygdx.stage.ForkRestPopupStage;
import com.mygdx.stage.ForkStage;
import com.mygdx.stage.GameClearStage;
import com.mygdx.stage.GameObjectStage;
import com.mygdx.stage.GameOverStage;
import com.mygdx.stage.GameUiStage;
import com.mygdx.stage.GreetingStage;
import com.mygdx.stage.InventoryStage;
import com.mygdx.stage.ItemStage;
import com.mygdx.stage.LoadPopupStage;
import com.mygdx.stage.LoadingBarStage;
import com.mygdx.stage.MenuStage;
import com.mygdx.stage.MonsterStage;
import com.mygdx.stage.SavePopupStage;
import com.mygdx.stage.SkillStage;
import com.mygdx.stage.StatusStage;
import com.mygdx.stage.VillageStage;
import com.mygdx.stage.WorldMapStage;

public class StageFactory {
	@Autowired
	private ApplicationContext context;

	public Stage makeStage(StageEnum stageEnum) {
		switch (stageEnum) {
			case battle :
				return context.getBean(BattleStage.class).makeStage();
			case battle_command :
				return context.getBean(BattleCommandStage.class).makeStage();
			case battle_info_message :
				return context.getBean(BattleInfoMessageStage.class).makeStage();
			case building :
				return context.getBean(BuildingStage.class).makeStage();
			case building_rest_popup :
				return context.getBean(BuildingRestPopupStage.class).makeStage();
			case character_change :
				return context.getBean(CharacterChangeStage.class).makeStage();
			case character_ui :
				return context.getBean(CharacterUiStage.class).makeStage();
			case chat_event :
				return context.getBean(ChatEventStage.class).makeStage();
			case choice_event :
				return context.getBean(ChoiceEventStage.class).makeStage();
			case choice_option :
				return context.getBean(ChoiceOptionStage.class).makeStage();
			case credit :
				return context.getBean(CreditStage.class).makeStage();
			case dungeon :
				return context.getBean(DungeonStage.class).makeStage();
			case dungeon_entrance :
				return context.getBean(DungeonEntranceStage.class).makeStage();
			case dungeon_entrance_rest_popup :
				return context.getBean(DungeonEntranceRestPopupStage.class).makeStage();
			case dungeon_minimap :
				return context.getBean(DungeonMinimapStage.class).makeStage();
			case encounter :
				return context.getBean(EncounterStage.class).makeStage();
			case fork :
				return context.getBean(ForkStage.class).makeStage();
			case fork_rest_popup :
				return context.getBean(ForkRestPopupStage.class).makeStage();
			case game_clear :
				return context.getBean(GameClearStage.class).makeStage();
			case game_ui :
				return context.getBean(GameUiStage.class).makeStage();
			case game_over :
				return context.getBean(GameOverStage.class).makeStage();
			case game_object :
				return context.getBean(GameObjectStage.class).makeStage();
			case greeting :
				return context.getBean(GreetingStage.class).makeStage();
			case loading_bar :
				return context.getBean(LoadingBarStage.class).makeStage();
			case load_popup :
				return context.getBean(LoadPopupStage.class).makeStage();
			case menu :
				return context.getBean(MenuStage.class).makeStage();
			case monster :
				return context.getBean(MonsterStage.class).makeStage();
			case field :
				return context.getBean(FieldStage.class).makeStage();
			case inventory :
				return context.getBean(InventoryStage.class).makeStage();
			case save :
				return context.getBean(SavePopupStage.class).makeStage();
			case skill :
				return context.getBean(SkillStage.class).makeStage();
			case status :
				return context.getBean(StatusStage.class).makeStage();
			case village :
				return context.getBean(VillageStage.class).makeStage();
			case world_map :
				return context.getBean(WorldMapStage.class).makeStage();
			case item :
				return context.getBean(ItemStage.class).makeStage();
			default :
				Gdx.app.debug("StageFactory", "StageEnum 주입 에러");
				return context.getBean(VillageStage.class).makeStage(); // FIXME
		}
	}
}
