package com.mygdx.game.desktop;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.logging.FileHandler;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.mygdx.game.OneLevelHero;

public class DesktopLauncher {

	public static void main(String[] arg) {

		// FreeTypeFontGenerator generator = new FreeTypeFontGenerator();

		// FileHandle f = Gdx.files.internal("freetypefonts/NotoSansCJKtc-Medium.otf");

		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		// 나중에 수정 요망
		config.title = "One Level Hero test 測試";
		config.width = 1280;
		config.height = 720;
		config.resizable = false;
		new LwjglApplication(new OneLevelHero(), config);
	}
}
