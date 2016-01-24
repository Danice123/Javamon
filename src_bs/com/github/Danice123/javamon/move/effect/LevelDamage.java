package com.github.Danice123.javamon.move.effect;

import com.github.Danice123.javamon.battlesystem.BattleFunction;
import com.github.Danice123.javamon.move.Move;
import com.github.Danice123.javamon.pokemon.PokeInstance;

public class LevelDamage extends Effect {
	
	private int percent;
	
	private boolean random = false;
	private int max;
	private int min;

	public void use(BattleFunction iface, PokeInstance user, PokeInstance target, Move move) {
		if (random) {
			percent = (iface.getRandom().nextInt(max - min) + min) * 10;
		}
		target.changeHealth((int) -(user.getLevel()/ (100.0 / percent)));
	}

}
