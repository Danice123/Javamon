package com.github.Danice123.javamon.screen.menu;

import com.github.Danice123.javamon.Display;
import com.github.Danice123.javamon.Game;
import com.github.Danice123.javamon.screen.Screen;

public class ChatBox extends Screen {
	
	private String[] text;
	private int index = 0;
	
	private boolean drawChoice = false;
	private boolean choice = false;
	
	private String variable;
	private int cIndex = 0;
	
	public ChatBox(Game game, Screen parent, String text) {
		super(game, parent);
		renderBehind = true;
		if (text.contains("/n")){
			this.text = text.split("/n");
		} else {
			this.text = new String[] {text};
		}
	}
	
	public ChatBox(Game game, Screen parent, String text, String[] args) {
		this(game, parent, text);
		if (args[0].equals("yes/no")) {
			choice = true;
			variable = args[1];
		}
	}
	
	@Override
	protected void init() {
		
	}
	
	@Override
	public void render2(float delta) {
		batch.begin();
		Display.border.drawBox(batch, 0, 0, Display.WIDTH, 50 * Display.scale);
		Display.font.drawWrapped(batch, text[index], Display.border.WIDTH + 2, 50 * Display.scale - Display.border.HEIGHT, 
				Display.WIDTH - 2* (Display.border.WIDTH + 2));
		if (drawChoice) {
			Display.border.drawBox(batch, Display.WIDTH - 50 * Display.scale, 50 * Display.scale, 
					50 * Display.scale, 50 * Display.scale);
			Display.font.draw(batch, "Yes", Display.WIDTH - 50 * Display.scale + Display.border.WIDTH + 7 * Display.scale, 
					100 * Display.scale - Display.border.HEIGHT - 5 * Display.scale);
			Display.font.draw(batch, "No", Display.WIDTH - 50 * Display.scale + Display.border.WIDTH + 9 * Display.scale, 
					100 * Display.scale - Display.border.HEIGHT - 20 * Display.scale);
			
			batch.draw(Display.arrow.rightArrow, Display.WIDTH - 50 * Display.scale + Display.border.WIDTH - 3 * Display.scale, 
					100 * Display.scale - Display.border.HEIGHT - 13 * Display.scale - cIndex * 16 * Display.scale, 
					Display.arrow.rightArrow.getRegionWidth() * Display.scale, 
					Display.arrow.rightArrow.getRegionHeight() * Display.scale);
		}
		batch.end();
	}
	
	@Override
	public void tick() {
		
	}

	@Override
	protected void handleKey(Key key) {
		if (key == Key.accept || key == Key.deny) {
			if (!(index < text.length - 1)) {
				if (choice && !drawChoice) {
					drawChoice = true;
				} else {
					if (choice)
						if (cIndex == 1)
							getGame().getPlayer().setFlag(variable, false);
						else
							getGame().getPlayer().setFlag(variable, true);
					disposeMe = true;
				}
			} else
				index++;
		}
		if (key == Key.up) {
			if (cIndex != 0)
				cIndex--;
		}
		if (key == Key.down) {
			if (cIndex != 1)
				cIndex++;
		}
	}
}
