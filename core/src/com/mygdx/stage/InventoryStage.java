package com.mygdx.stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.mygdx.assets.AtlasUiAssets;
import com.mygdx.assets.ConstantsAssets;
import com.mygdx.assets.StaticAssets;
import com.mygdx.assets.UiComponentAssets;
import com.mygdx.enums.ItemEnum;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.factory.ListenerFactory;
import com.mygdx.factory.ScreenFactory;
import com.mygdx.listener.SimpleTouchListener;
import com.mygdx.manager.AssetsManager;
import com.mygdx.manager.BagManager;
import com.mygdx.manager.BattleManager;
import com.mygdx.manager.MovingManager;
import com.mygdx.manager.PartyManager;
import com.mygdx.manager.StorySectionManager;
import com.mygdx.manager.TextureManager;
import com.mygdx.model.item.Equipment;
import com.mygdx.model.unit.Hero;
import com.mygdx.popup.DropPopup;
import com.mygdx.popup.EquipPopup;
import com.mygdx.popup.UnEquipPopup;
import com.uwsoft.editor.renderer.actor.CompositeItem;
import com.uwsoft.editor.renderer.actor.ImageItem;
import com.uwsoft.editor.renderer.actor.LabelItem;

public class InventoryStage extends BaseOverlapStage {
	public static final String SCENE_NAME = "inventory_scene";
	public final String CHARACTER_STATUS_IMAGE = "character_status_image";
	public final String CHARACTER_IMAGE = "normal";
	public final String STATUS_LABEL_NAME = "status_label";
	private final String INVENTORY_ITEM_IMAGE = "inventory_item_image";
	private final String INVENTORY_ITEM_LABEL = "inventory_item_label";
	private final String INVENTORY_ACTIVATE_LINE = "inventory_activate_line";
	private final String INVENTORY_LIGHT_BUTTON = "inventory_light_button";
	private final String DEFAULT_VISIBILTY = "Default";
	private final String PRESSED_VISIBILTY = "pressed";
	private final String NORMAL_VISIBILTY = "normal";
	private final String PROPERTY_LABEL = "property_label";
	private final int CHACRACTER_STATUS_SIZE = 4;
	private final int NUMBER_OF_EQUIPMENT = 4;
	private final int ITEM_SLOT_SIZE = 6;
	private List<CompositeItem> equipButtonList, unEquipButtonList, dropButtonList;
	private ItemEnum.Inventory inventoryState;

	@Autowired
	private AtlasUiAssets atlasUiAssets;
	@Autowired
	private BattleManager battleManager;
	@Autowired
	private StorySectionManager storySectionManager;
	@Autowired
	private UiComponentAssets uiComponentAssets;
	@Autowired
	private ConstantsAssets constantsAssets;
	@Autowired
	private BagManager bagManager;
	@Autowired
	private ScreenFactory screenFactory;
	@Autowired
	private MovingManager movingManager;
	@Autowired
	private ListenerFactory listenerFactory;
	@Autowired
	private AssetsManager assetsManager;
	@Autowired
	private transient TextureManager textureManager;
	private Map<String, Array<String>> sceneConstants;
	private Map<Integer, Equipment> itemInfo;
	private Hero currentSelectedHero;
	private int pageNumber;
	private Camera cam;

	public Stage makeStage() {
		assetsManager.initScene(SCENE_NAME);
		initSceneLoader(assetsManager.rm);
		sceneLoader.loadScene(SCENE_NAME);
		addActor(sceneLoader.getRoot());

		sceneConstants = constantsAssets.getSceneConstants(SCENE_NAME);
		currentSelectedHero = partyManager.getCurrentSelectedHero();

		setBackground(sceneConstants);
		showVoidEquipButton(sceneConstants);
		showVoidItemDescription(sceneConstants);

		showEquipmentScene(sceneConstants, bagManager, pageNumber);
		setActivateLine(sceneConstants);
		addEquipButtonListener();

		setPageButton(sceneConstants);
		setPageLight(sceneConstants, pageNumber);

		setCharacterSubImage(partyManager, sceneConstants);

		setTabButton(screenFactory, movingManager);
		setSubTabButton(ItemEnum.Inventory.EQUIPMENT);

		setCamera();
		return this;
	}

	public void act(float delta) {
		showEquipmentScene(sceneConstants, bagManager, pageNumber);
		setSubTabButton(ItemEnum.Inventory.EQUIPMENT);
		setPageLight(sceneConstants, pageNumber);
		setCharacterSubImage(partyManager, sceneConstants);
	}

	private void showVoidEquipButton(Map<String, Array<String>> sceneConstants) {
		equipButtonList = new ArrayList<>(6);
		unEquipButtonList = new ArrayList<>(6);
		dropButtonList = new ArrayList<>(6);
		for (int i = 0; i < ITEM_SLOT_SIZE; i++) {
			CompositeItem equipButton = sceneLoader.getRoot()
					.getCompositeById(sceneConstants.get("equip_button").get(i));
			equipButton.setVisible(false);
			equipButtonList.add(equipButton);
			CompositeItem unEquipButton = sceneLoader.getRoot()
					.getCompositeById(sceneConstants.get("unequip_button").get(i));
			unEquipButton.setVisible(false);
			unEquipButtonList.add(unEquipButton);
			CompositeItem dropButton = sceneLoader.getRoot().getCompositeById(sceneConstants.get("drop_button").get(i));
			dropButton.setVisible(false);
			dropButtonList.add(dropButton);
		}
	}

	private void showVoidItemDescription(Map<String, Array<String>> sceneConstants) {
		LabelItem nameLabel = sceneLoader.getRoot().getLabelById("name_label");
		nameLabel.setText("");
		LabelItem propertyLabel1 = sceneLoader.getRoot().getLabelById("property_label01");
		propertyLabel1.setText("");
		LabelItem propertyLabel2 = sceneLoader.getRoot().getLabelById("property_label02");
		propertyLabel2.setText("");
		Array<String> propertyLabelList = sceneConstants.get(PROPERTY_LABEL);
		for (int i = 0; i < 2; i++) {
			LabelItem propertyLabel = sceneLoader.getRoot().getLabelById(propertyLabelList.get(i));
			propertyLabel.setText("");
			LabelItem statsLabel = sceneLoader.getRoot().getLabelById(propertyLabelList.get(i + 2));
			statsLabel.setText("");
			CompositeItem upDownArrow = sceneLoader.getRoot().getCompositeById(propertyLabelList.get(i + 4));
			upDownArrow.setLayerVisibilty(NORMAL_VISIBILTY, false);
			upDownArrow.setLayerVisibilty(PRESSED_VISIBILTY, false);
			upDownArrow.setLayerVisibilty(DEFAULT_VISIBILTY, false);
		}
		LabelItem descriptionLabel = sceneLoader.getRoot().getLabelById("description_label");
		descriptionLabel.setText("");
	}

	private void setItemDescription(Map<String, Array<String>> sceneConstants, int index) {
		if (itemInfo.get(index) == null) {
			showVoidItemDescription(sceneConstants);
			return;
		}
		if (itemInfo.get(index).getItemPath().equals("empty_item")) {
			showVoidItemDescription(sceneConstants);
			return;
		}
		LabelItem labelItem = sceneLoader.getRoot().getLabelById("name_label");
		labelItem.setText(itemInfo.get(index).getName());
		LabelItem nameLabel = sceneLoader.getRoot().getLabelById("name_label");
		nameLabel.setText(itemInfo.get(index).getName());
		setLabelStyle(nameLabel);

		Array<String> propertyLabelList = sceneConstants.get(PROPERTY_LABEL);
		for (int j = 0; j < itemInfo.get(index).getEffectStatusList().size(); j++) {
			LabelItem propertyLabel = sceneLoader.getRoot().getLabelById(propertyLabelList.get(j));
			String effectStatusName = itemInfo.get(index).getEffectStatusList().get(j);
			propertyLabel.setText(itemInfo.get(index).getEffectStatus().getStatusMarkByName(effectStatusName));
			setLabelStyle(propertyLabel);
			LabelItem statsLabel = sceneLoader.getRoot().getLabelById(propertyLabelList.get(j + 2));
			int effectStatusNumber = itemInfo.get(index).getEffectStatus().getStatusByName(effectStatusName);
			statsLabel.setText(String.valueOf(effectStatusNumber));
			setLabelStyle(statsLabel);

			CompositeItem upDownArrow = sceneLoader.getRoot().getCompositeById(propertyLabelList.get(j + 4));
			if (effectStatusNumber > 0) {
				upDownArrow.setLayerVisibilty(NORMAL_VISIBILTY, true);
				upDownArrow.setLayerVisibilty(PRESSED_VISIBILTY, false);
				upDownArrow.setLayerVisibilty(DEFAULT_VISIBILTY, false);
			} else {
				upDownArrow.setLayerVisibilty(NORMAL_VISIBILTY, false);
				upDownArrow.setLayerVisibilty(PRESSED_VISIBILTY, true);
				upDownArrow.setLayerVisibilty(DEFAULT_VISIBILTY, false);
			}

			LabelItem descriptionLabel = sceneLoader.getRoot().getLabelById("description_label");
			descriptionLabel.setText(itemInfo.get(index).getDescription());
			descriptionLabel.setWrap(true);
			setLabelStyle(descriptionLabel);

			Table labelTable = new Table();
			// FIXME
			labelTable.add(descriptionLabel).width(600).left().bottom().padLeft(2750).padBottom(600);
			addActor(labelTable);
		}
	}

	private void setLabelStyle(LabelItem labelItem) {
		labelItem.setStyle(new LabelStyle(uiComponentAssets.getFont(), Color.WHITE));
		labelItem.setFontScale(1.0f);
		labelItem.setVisible(true);
		labelItem.setTouchable(Touchable.disabled);
	}

	private void setBackground(Map<String, Array<String>> sceneConstants) {
		CompositeItem background = sceneLoader.getRoot().getCompositeById(sceneConstants.get("background").get(0));
		background.setTouchable(Touchable.enabled);
	}

	public void setCompositeItemVisibilty(CompositeItem compositeItem, String visibilty) {
		if (visibilty == PRESSED_VISIBILTY) {
			compositeItem.setLayerVisibilty(PRESSED_VISIBILTY, true);
			compositeItem.setLayerVisibilty(DEFAULT_VISIBILTY, false);
		} else {
			compositeItem.setLayerVisibilty(PRESSED_VISIBILTY, false);
			compositeItem.setLayerVisibilty(DEFAULT_VISIBILTY, true);
		}
	}

	private void setPageButton(final Map<String, Array<String>> sceneConstants) {
		final CompositeItem rightButton = sceneLoader.getRoot().getCompositeById("right_button");
		final CompositeItem leftButton = sceneLoader.getRoot().getCompositeById("left_button");
		setCompositeItemVisibilty(rightButton, DEFAULT_VISIBILTY);
		setCompositeItemVisibilty(leftButton, DEFAULT_VISIBILTY);
		leftButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				setCompositeItemVisibilty(leftButton, PRESSED_VISIBILTY);
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				if (pageNumber > 0) {
					setPageNumber(getPageNumber() - 1);
					showVoidItemDescription(sceneConstants);
					showVoidEquipButton(sceneConstants);
					showEquipmentScene(sceneConstants, bagManager, pageNumber);
					setActivateLine(sceneConstants);
					addEquipButtonListener();
				}
				setCompositeItemVisibilty(leftButton, DEFAULT_VISIBILTY);
			}
		});
		rightButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				setCompositeItemVisibilty(rightButton, PRESSED_VISIBILTY);
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				if (pageNumber < 3) {
					setPageNumber(getPageNumber() + 1);
					showVoidItemDescription(sceneConstants);
					showVoidEquipButton(sceneConstants);
					showEquipmentScene(sceneConstants, bagManager, pageNumber);
					setActivateLine(sceneConstants);
					addEquipButtonListener();
				}
				setCompositeItemVisibilty(rightButton, DEFAULT_VISIBILTY);
			}
		});
	}

	private void setPageLight(Map<String, Array<String>> sceneConstants, int pageNumber) {
		Array<String> inventoryLightButtonList = sceneConstants.get(INVENTORY_LIGHT_BUTTON);
		for (int i = 0; i < 4; i++) {
			CompositeItem pageLight = sceneLoader.getRoot().getCompositeById(inventoryLightButtonList.get(i));
			if (i == pageNumber) {
				setCompositeItemVisibilty(pageLight, PRESSED_VISIBILTY);
			} else {
				setCompositeItemVisibilty(pageLight, DEFAULT_VISIBILTY);
			}
		}
	}

	private void showEquipmentScene(Map<String, Array<String>> sceneConstant, BagManager bagManager, int pageNumber) {
		itemInfo = new HashMap<>();
		for (int i = pageNumber * ITEM_SLOT_SIZE; i < (pageNumber + 1) * ITEM_SLOT_SIZE; i++) {
			setEquippedItemList(sceneConstants, bagManager, i);
			setBagItemList(sceneConstants, bagManager, i);
		}
	}

	private void setSubTabButton(ItemEnum.Inventory inventoryState) {
		CompositeItem equipTag = sceneLoader.getRoot().getCompositeById("equip_tag");
		CompositeItem consumablesTag = sceneLoader.getRoot().getCompositeById("use_tag");
		CompositeItem etcItemTag = sceneLoader.getRoot().getCompositeById("etc_tag");
		switch (inventoryState) {
		case EQUIPMENT:
			setCompositeItemVisibilty(equipTag, PRESSED_VISIBILTY);
			setCompositeItemVisibilty(consumablesTag, DEFAULT_VISIBILTY);
			setCompositeItemVisibilty(etcItemTag, DEFAULT_VISIBILTY);
			break;
		case CONSUMABLES:
			setCompositeItemVisibilty(equipTag, DEFAULT_VISIBILTY);
			setCompositeItemVisibilty(consumablesTag, PRESSED_VISIBILTY);
			setCompositeItemVisibilty(etcItemTag, DEFAULT_VISIBILTY);
			break;
		case ETC_ITEM:
			setCompositeItemVisibilty(equipTag, DEFAULT_VISIBILTY);
			setCompositeItemVisibilty(consumablesTag, DEFAULT_VISIBILTY);
			setCompositeItemVisibilty(etcItemTag, PRESSED_VISIBILTY);
			break;
		}
	}

	private void setActivateLine(final Map<String, Array<String>> sceneConstants) {
		for (int i = 0; i < ITEM_SLOT_SIZE; i++) {
			final int focusIndex = i;
			final Array<String> activateLineList = sceneConstants.get(INVENTORY_ACTIVATE_LINE);
			final CompositeItem activateLineItem = sceneLoader.getRoot().getCompositeById(activateLineList.get(i));
			activateLineItem.setLayerVisibilty(PRESSED_VISIBILTY, false);
			activateLineItem.setLayerVisibilty(DEFAULT_VISIBILTY, true);
			activateLineItem.setTouchable(Touchable.enabled);
			activateLineItem.addListener(new ClickListener() {
				@Override
				public void clicked(InputEvent event, float x, float y) {
					for (int i = 0; i < ITEM_SLOT_SIZE; i++) {
						if (itemInfo.get(i) != null) {
							final CompositeItem activateLineItem = sceneLoader.getRoot()
									.getCompositeById(activateLineList.get(i));
							if (i == focusIndex) {
								activateLineItem.setLayerVisibilty(PRESSED_VISIBILTY, true);
								setEquipButton(i, uiComponentAssets, atlasUiAssets, listenerFactory);
								setItemDescription(sceneConstants, i);
							} else {
								activateLineItem.setLayerVisibilty(PRESSED_VISIBILTY, false);
								setVoidEquipButton(i, itemInfo, uiComponentAssets, atlasUiAssets, listenerFactory);
							}
						} else {
							final CompositeItem activateLineItem = sceneLoader.getRoot()
									.getCompositeById(activateLineList.get(i));
							activateLineItem.setLayerVisibilty(PRESSED_VISIBILTY, false);
							setVoidEquipButton(i, itemInfo, uiComponentAssets, atlasUiAssets, listenerFactory);
						}
					}
				}

			});
		}
	}

	private void setEquipButton(final int index, final UiComponentAssets uiComponentAssets,
			final AtlasUiAssets atlasUiAssets, final ListenerFactory listenerFactory) {
		setCompositeItemVisibilty(equipButtonList.get(index), DEFAULT_VISIBILTY);
		setCompositeItemVisibilty(unEquipButtonList.get(index), DEFAULT_VISIBILTY);
		setCompositeItemVisibilty(dropButtonList.get(index), DEFAULT_VISIBILTY);

		if (isFirstPage()) {
			if (index < 4) {
				if (itemInfo.get(index).getItemPath().equals("empty_item")) {
					equipButtonList.get(index).setVisible(false);
					equipButtonList.get(index).setTouchable(Touchable.disabled);
					unEquipButtonList.get(index).setVisible(false);
					unEquipButtonList.get(index).setTouchable(Touchable.disabled);
					dropButtonList.get(index).setVisible(false);
					dropButtonList.get(index).setTouchable(Touchable.disabled);
				} else {
					equipButtonList.get(index).setVisible(false);
					equipButtonList.get(index).setTouchable(Touchable.disabled);
					unEquipButtonList.get(index).setVisible(true);
					unEquipButtonList.get(index).setTouchable(Touchable.enabled);
					dropButtonList.get(index).setVisible(false);
					dropButtonList.get(index).setTouchable(Touchable.disabled);
				}
			} else {
				equipButtonList.get(index).setVisible(true);
				equipButtonList.get(index).setTouchable(Touchable.enabled);
				unEquipButtonList.get(index).setVisible(false);
				unEquipButtonList.get(index).setTouchable(Touchable.disabled);
				dropButtonList.get(index).setVisible(true);
				dropButtonList.get(index).setTouchable(Touchable.enabled);
			}
		} else {
			equipButtonList.get(index).setVisible(true);
			equipButtonList.get(index).setTouchable(Touchable.enabled);
			unEquipButtonList.get(index).setVisible(false);
			unEquipButtonList.get(index).setTouchable(Touchable.disabled);
			dropButtonList.get(index).setVisible(true);
			dropButtonList.get(index).setTouchable(Touchable.enabled);
		}
	}

	private void setVoidEquipButton(int index, Map<Integer, Equipment> itemInfo, UiComponentAssets uiComponentAssets,
			AtlasUiAssets atlasUiAssets, ListenerFactory listenerFactory) {
		equipButtonList.get(index).setVisible(false);
		equipButtonList.get(index).setTouchable(Touchable.disabled);
		unEquipButtonList.get(index).setVisible(false);
		unEquipButtonList.get(index).setTouchable(Touchable.disabled);
		dropButtonList.get(index).setVisible(false);
		dropButtonList.get(index).setTouchable(Touchable.disabled);
	}

	private void addEquipButtonListener() {
		for (int i = 0; i < ITEM_SLOT_SIZE; i++) {
			final int index = i;
			equipButtonList.get(i).clearListeners();
			unEquipButtonList.get(i).clearListeners();
			dropButtonList.get(i).clearListeners();
			equipButtonList.get(i).addListener(new InputListener() {
				@Override
				public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
					setCompositeItemVisibilty(equipButtonList.get(index), PRESSED_VISIBILTY);
					return true;
				}

				@Override
				public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
					EquipPopup equipPopup = new EquipPopup();
					equipPopup.setAtlasUiAssets(atlasUiAssets);
					equipPopup.setUiComponentAssets(uiComponentAssets);
					equipPopup.setListenerFactory(listenerFactory);
					equipPopup.setEquipment(itemInfo.get(index));
					equipPopup.setCurrentSelectedHero(currentSelectedHero);
					equipPopup.setConstantsAssets(constantsAssets);
					equipPopup.initialize();
					addActor(equipPopup);
					equipPopup.setVisible(true);
					setCompositeItemVisibilty(equipButtonList.get(index), DEFAULT_VISIBILTY);
					showVoidEquipButton(sceneConstants);
					setVoidItemImageAndLabel();
					showEquipmentScene(sceneConstants, bagManager, pageNumber);
					setActivateLine(sceneConstants);
					addEquipButtonListener();
				}
			});
			unEquipButtonList.get(i).addListener(new InputListener() {
				@Override
				public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
					setCompositeItemVisibilty(unEquipButtonList.get(index), PRESSED_VISIBILTY);
					return true;
				}

				@Override
				public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
					UnEquipPopup unEquipPopup = new UnEquipPopup();
					unEquipPopup.setAtlasUiAssets(atlasUiAssets);
					unEquipPopup.setUiComponentAssets(uiComponentAssets);
					unEquipPopup.setListenerFactory(listenerFactory);
					unEquipPopup.setIndex(index);
					unEquipPopup.setCurrentSelectedHero(currentSelectedHero);
					unEquipPopup.setConstantsAssets(constantsAssets);
					unEquipPopup.initialize();
					addActor(unEquipPopup);
					unEquipPopup.setVisible(true);
					setCompositeItemVisibilty(unEquipButtonList.get(index), DEFAULT_VISIBILTY);
					showVoidEquipButton(sceneConstants);
					setVoidItemImageAndLabel();
					showEquipmentScene(sceneConstants, bagManager, pageNumber);
					setActivateLine(sceneConstants);
					addEquipButtonListener();
				}
			});

			dropButtonList.get(i).addListener(new InputListener() {
				@Override
				public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
					setCompositeItemVisibilty(dropButtonList.get(index), PRESSED_VISIBILTY);
					return true;
				}

				@Override
				public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
					DropPopup dropPopup = new DropPopup();
					dropPopup.setAtlasUiAssets(atlasUiAssets);
					dropPopup.setUiComponentAssets(uiComponentAssets);
					dropPopup.setListenerFactory(listenerFactory);
					dropPopup.setBagManager(bagManager);
					dropPopup.setEquipment(itemInfo.get(index));
					dropPopup.setConstantsAssets(constantsAssets);
					dropPopup.initialize();
					addActor(dropPopup);
					dropPopup.setVisible(true);
					setCompositeItemVisibilty(dropButtonList.get(index), DEFAULT_VISIBILTY);
					showVoidEquipButton(sceneConstants);
					setVoidItemImageAndLabel();
					showEquipmentScene(sceneConstants, bagManager, pageNumber);
					setActivateLine(sceneConstants);
					addEquipButtonListener();
				}
			});
		}
	}

	private boolean isFirstPage() {
		if (pageNumber == 0) {
			return true;
		} else {
			return false;
		}
	}

	private void setCharacterSubImage(PartyManager partyManager, Map<String, Array<String>> sceneConstants) {
		Array<String> characterStatusList = sceneConstants.get(CHARACTER_STATUS_IMAGE);
		for (int i = 0; i < CHACRACTER_STATUS_SIZE; i++) {
			CompositeItem compositeItem = sceneLoader.getRoot().getCompositeById(characterStatusList.get(i));
			ImageItem characterStatusImage = compositeItem.getImageById(CHARACTER_IMAGE);
			if (partyManager.getPartyList().size() > i) {
				final Hero imageHero = partyManager.getPartyList().get(i);
				characterStatusImage.setDrawable(new TextureRegionDrawable(
						new TextureRegion(textureManager.getStatusTexture(imageHero.getFacePath()))));
				characterStatusImage.setTouchable(Touchable.enabled);
				characterStatusImage.addListener(new InputListener() {
					@Override
					public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
						setCurrentSelectedHero(imageHero);
						return true;
					}

					@Override
					public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
					}
				});
				ImageItem highLightImage = compositeItem.getImageById(PRESSED_VISIBILTY);
				if (imageHero == currentSelectedHero) {
					highLightImage.setVisible(true);
				} else {
					highLightImage.setVisible(false);
				}
			} else {
				compositeItem.setVisible(false);
			}
		}
	}

	private void setEquippedItemList(Map<String, Array<String>> sceneConstants, BagManager bagManager, int index) {
		Array<String> inventoryItemImageList = sceneConstants.get(INVENTORY_ITEM_IMAGE);
		Array<String> inventoryLabelList = sceneConstants.get(INVENTORY_ITEM_LABEL);
		Array<String> inventoryList = currentSelectedHero.getInventory().getInventoryList();
		ImageItem equipPartsLabel = sceneLoader.getRoot().getImageById("character_equip_parts");
		if (isFirstPage()) {
			if (index != 4 && index != 5) {
				itemInfo.put(index, currentSelectedHero.getInventory()
						.getEquipment(ItemEnum.findItemByType(inventoryList.get(index))));
				ImageItem imageItem = sceneLoader.getRoot().getImageById(inventoryItemImageList.get(index));
				String itemPath = currentSelectedHero.getInventory()
						.getEquipment(ItemEnum.findItemByType(inventoryList.get(index))).getItemPath();
				imageItem.setDrawable(
						(new TextureRegionDrawable(new TextureRegion(textureManager.getItemTexture(itemPath)))));
				imageItem.setVisible(true);
				imageItem.setTouchable(Touchable.disabled);
				LabelItem labelItem = sceneLoader.getRoot().getLabelById(inventoryLabelList.get(index));
				labelItem.setText(currentSelectedHero.getInventory()
						.getEquipment(ItemEnum.findItemByType(inventoryList.get(index))).getName());
				labelItem.setStyle(new LabelStyle(uiComponentAssets.getFont(), Color.WHITE));
				labelItem.setFontScale(1.0f);
				labelItem.setVisible(true);
				labelItem.setTouchable(Touchable.disabled);
				equipPartsLabel.setVisible(true);
			} else {
				setVoidItemImageAndLabel(index);
			}
		} else {
			equipPartsLabel.setVisible(false);
			setVoidItemImageAndLabel(index);
		}
	}

	private void setBagItemList(Map<String, Array<String>> sceneConstants, BagManager bagManager, int index) {
		if (isFirstPage()) {
			if (index == 4 || index == 5) {
				if (bagManager.getEquipmentList().size() > index - NUMBER_OF_EQUIPMENT) {
					setBagItemImageAndLabel(bagManager, index);
				} else {
					itemInfo.put(index, null);
					setVoidItemImageAndLabel(index);
				}
			}
		} else {
			if (bagManager.getEquipmentList().size() > index - NUMBER_OF_EQUIPMENT) {
				setBagItemImageAndLabel(bagManager, index);
			} else {
				itemInfo.put(index, null);
				setVoidItemImageAndLabel(index);
			}
		}
	}

	private void setVoidItemImageAndLabel() {
		for (int i = 0; i < ITEM_SLOT_SIZE; i++) {
			setVoidItemImageAndLabel(i);
		}
	}

	private void setVoidItemImageAndLabel(int index) {
		Array<String> inventoryItemImageList = sceneConstants.get(INVENTORY_ITEM_IMAGE);
		Array<String> inventoryLabelList = sceneConstants.get(INVENTORY_ITEM_LABEL);
		int dividedIndex;
		if (isFirstPage()) {
			dividedIndex = index;
		} else {
			dividedIndex = index % (pageNumber * ITEM_SLOT_SIZE);
		}
		ImageItem inventoryItemImage = sceneLoader.getRoot().getImageById(inventoryItemImageList.get(dividedIndex));
		inventoryItemImage.setVisible(false);
		LabelItem labelItem = sceneLoader.getRoot().getLabelById(inventoryLabelList.get(dividedIndex));
		labelItem.setVisible(false);
	}

	private void setBagItemImageAndLabel(BagManager bagManager, int index) {
		Array<String> inventoryItemImageList = sceneConstants.get(INVENTORY_ITEM_IMAGE);
		Array<String> inventoryLabelList = sceneConstants.get(INVENTORY_ITEM_LABEL);
		int dividedIndex;
		if (isFirstPage()) {
			dividedIndex = index;
		} else {
			dividedIndex = index % (pageNumber * ITEM_SLOT_SIZE);
		}
		if (bagManager.getEquipmentList().size() > index - NUMBER_OF_EQUIPMENT) {
			itemInfo.put(dividedIndex, bagManager.getEquipmentList().get(index - NUMBER_OF_EQUIPMENT));
			ImageItem inventoryItemImage = sceneLoader.getRoot().getImageById(inventoryItemImageList.get(dividedIndex));
			String itemPath = bagManager.getEquipmentList().get(index - NUMBER_OF_EQUIPMENT).getItemPath();
			inventoryItemImage.setDrawable(
					(new TextureRegionDrawable(new TextureRegion(textureManager.getItemTexture(itemPath)))));
			inventoryItemImage.setVisible(true);
			LabelItem labelItem = sceneLoader.getRoot().getLabelById(inventoryLabelList.get(dividedIndex));
			labelItem.setText(bagManager.getEquipmentList().get(index - NUMBER_OF_EQUIPMENT).getName());
			labelItem.setStyle(new LabelStyle(uiComponentAssets.getFont(), Color.WHITE));
			labelItem.setFontScale(1.0f);
			labelItem.setVisible(true);
			labelItem.setTouchable(Touchable.disabled);
		} else {
			setVoidItemImageAndLabel(dividedIndex);
		}
	}

	private void setTabButton(final ScreenFactory screenFactory, final MovingManager movingManager) {
		CompositeItem inventoryButton = sceneLoader.getRoot().getCompositeById("inventory_tab");
		inventoryButton.addListener(new SimpleTouchListener() {
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				screenFactory.show(ScreenEnum.inventory);
			}
		});

		CompositeItem backButton = sceneLoader.getRoot().getCompositeById("back_tab");
		backButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				if (battleManager.isInBattle()) {
					screenFactory.show(ScreenEnum.battle);
				} else {
					movingManager.goCurrentLocatePosition();
				}
			}
		});

		CompositeItem statusButton = sceneLoader.getRoot().getCompositeById("status_tab");
		statusButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				screenFactory.show(ScreenEnum.status);
			}
		});
	}

	private void setCamera() {
		cam = new OrthographicCamera(StaticAssets.BASE_WINDOW_WIDTH, StaticAssets.BASE_WINDOW_HEIGHT);
		cam.position.set(cam.viewportWidth / 2f, cam.viewportHeight / 2f, 0);
		getViewport().setCamera(cam);
	}

	public ItemEnum.Inventory getInventoryState() {
		return inventoryState;
	}

	public void setInventoryState(ItemEnum.Inventory inventoryState) {
		this.inventoryState = inventoryState;
	}

	public void setCurrentSelectedHero(Hero currentSelectedHero) {
		this.currentSelectedHero = currentSelectedHero;
		partyManager.setCurrentSelectedHero(currentSelectedHero);
	}

	public Hero getCurrentSelectedHero() {
		return currentSelectedHero;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
}
