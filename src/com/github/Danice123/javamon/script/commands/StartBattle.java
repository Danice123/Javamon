package com.github.Danice123.javamon.script.commands;

import java.util.HashMap;

import com.github.Danice123.javamon.Game;
import com.github.Danice123.javamon.battlesystem.Battlesystem;
import com.github.Danice123.javamon.battlesystem.Trainer;
import com.github.Danice123.javamon.entity.Entity;
import com.github.Danice123.javamon.screen.menu.gen1.Battle;
import com.github.Danice123.javamon.script.ScriptException;

public class StartBattle extends Command {

	@Override
	public void execute(Game game, HashMap<String, String> strings, Entity target) throws ScriptException {
		Trainer trainer;
		if (args[0].toLowerCase().equals("t")) {
			try {
				trainer = (Trainer) target;
			} catch (ClassCastException e) {
				throw new ScriptException(ScriptException.BAD_TARGET);
			}
		} else {
			Entity e = game.getWorld().getEntity(args[0]);
			if (e != null) {
				try {
					trainer = (Trainer) e;
				} catch (ClassCastException error) {
					throw new ScriptException(ScriptException.BAD_TARGET);
				}
			} else {
				throw new ScriptException(ScriptException.BAD_ARGS);
			}
		}
		
		System.out.println("Started battle");
		Battlesystem bs = new Battlesystem(game.getPlayer(), trainer);
		Battle b = new Battle(game, game.getWorld(), bs);
		game.getPlayer().menuOpen = true;
		bs.init(b);
		new Thread(bs).start();
		
		synchronized (bs) {
			try {
				bs.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
