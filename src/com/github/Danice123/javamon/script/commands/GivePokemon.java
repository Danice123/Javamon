package com.github.Danice123.javamon.script.commands;

import java.util.HashMap;

import com.github.Danice123.javamon.Game;
import com.github.Danice123.javamon.entity.Entity;
import com.github.Danice123.javamon.pokemon.PokeInstance;
import com.github.Danice123.javamon.pokemon.Pokemon;
import com.github.Danice123.javamon.screen.menu.ChatBox;
import com.github.Danice123.javamon.script.ScriptException;

public class GivePokemon extends Command {

	@Override
	public void execute(Game game, HashMap<String, String> strings, Entity target) throws ScriptException {
		PokeInstance poke = new PokeInstance(Pokemon.getPokemon(args[0]), Integer.parseInt(args[1]));
		game.getPlayer().getParty().add(poke);
		
		ChatBox box = new ChatBox(game, game.getWorld(), "RED recieved a " + poke.getPokemon().name);
		game.getPlayer().menuOpen = true;
		synchronized (box) {
			try {
				box.wait();
			} catch (InterruptedException e) {}
		}
		game.getPlayer().menuOpen = false;
	}

}
