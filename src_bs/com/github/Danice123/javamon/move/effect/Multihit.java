package com.github.Danice123.javamon.move.effect;

import com.github.Danice123.javamon.battlesystem.BattleFunction;
import com.github.Danice123.javamon.move.Move;
import com.github.Danice123.javamon.pokemon.PokeInstance;

public class Multihit extends Effect {
	
	private Damage attack;
	private int min;
	private int max;

	public void use(BattleFunction iface, PokeInstance user, PokeInstance target, Move move) {
		int times = iface.getRandom().nextInt(max - min) + min;
		for (int i = 0; i < times; i++) {
			attack.use(iface, user, target, move);
		}
	}

}
