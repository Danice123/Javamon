package com.github.Danice123;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.github.Danice123.javamon.Display;

public class DesktopGame {

	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "Javamon";
		cfg.useGL20 = true;
		cfg.width = 240 * 4;
		cfg.height = 160 * 4;
		//cfg.width = 1080;
		//cfg.height = 720;
		cfg.resizable = false;
		
		new LwjglApplication(new Display(cfg.width, cfg.height), cfg);
	}
}
