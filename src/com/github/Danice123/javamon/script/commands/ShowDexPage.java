package com.github.Danice123.javamon.script.commands;

import java.util.HashMap;

import com.github.Danice123.javamon.Game;
import com.github.Danice123.javamon.entity.Entity;
import com.github.Danice123.javamon.pokemon.Pokemon;
import com.github.Danice123.javamon.screen.menu.PokedexPage;
import com.github.Danice123.javamon.script.ScriptException;

public class ShowDexPage extends Command {

	@Override
	public void execute(Game game, HashMap<String, String> strings, Entity target) throws ScriptException {
		if (game.getPlayer().menuOpen) {
			System.out.println("What?");
		} else {
			PokedexPage p = PokedexPage.newMenu(game, game.getWorld(), Pokemon.getPokemon(parseString(args[0], strings)));
			game.getPlayer().menuOpen = true;
			synchronized (p) {
				try {
					p.wait();
				} catch (InterruptedException e) {}
			}
			game.getPlayer().menuOpen = false;
		}
	}

}
