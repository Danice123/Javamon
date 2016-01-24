package com.github.Danice123.javamon.script.commands;

import java.util.Arrays;
import java.util.HashMap;

import com.github.Danice123.javamon.Game;
import com.github.Danice123.javamon.entity.Entity;
import com.github.Danice123.javamon.screen.menu.ChatBox;
import com.github.Danice123.javamon.script.ScriptException;

public class Display extends Command {

	@Override
	public void execute(Game game, HashMap<String, String> strings, Entity target) throws ScriptException {
		try {
			ChatBox box = null;
			if (game.getPlayer().menuOpen) {
				System.out.println("Menu Open Already");
			} else {
				if (args.length > 1)
					box = new ChatBox(game, game.getWorld(), parseString(strings.get(args[0]), strings), Arrays.copyOfRange(args, 1, args.length));
				else
					box = new ChatBox(game, game.getWorld(), parseString(strings.get(args[0]), strings));
				game.getPlayer().menuOpen = true;
			}
			synchronized (box) {
				try {
					box.wait();
				} catch (InterruptedException e) {}
			}
			game.getPlayer().menuOpen = false;
		} catch (NullPointerException e) {
			throw new ScriptException(ScriptException.BAD_STRING);
		}
	}

}
