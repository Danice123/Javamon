package com.github.Danice123.javamon.move.effect;

import java.util.ArrayList;

import com.github.Danice123.javamon.battlesystem.BattleFunction;
import com.github.Danice123.javamon.move.Move;
import com.github.Danice123.javamon.pokemon.PokeInstance;

public class Chance extends Effect {

	private int chance;
	private ArrayList<Effect> effect;
	
	public void use(BattleFunction iface, PokeInstance user, PokeInstance target, Move move) {
		if (iface.getRandom().nextInt(100) < chance) {
			for (int i = 0; i < effect.size(); i++) {
				effect.get(i).use(iface, user, target, move);
			}
		}
	}

}
