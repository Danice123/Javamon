package com.github.Danice123.javamon.move.effect;

import com.github.Danice123.javamon.battlesystem.BattleFunction;
import com.github.Danice123.javamon.move.Move;
import com.github.Danice123.javamon.pokemon.PokeInstance;

public class Heal extends Effect {
	
	private int percent;

	public void use(BattleFunction iface, PokeInstance user,
			PokeInstance target, Move move) {
		user.changeHealth(user.getHealth() * (percent / 100));
		iface.print(user.getName() + " has been healed!");
	}

}
