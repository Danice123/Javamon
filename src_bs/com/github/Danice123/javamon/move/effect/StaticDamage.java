package com.github.Danice123.javamon.move.effect;

import com.github.Danice123.javamon.battlesystem.BattleFunction;
import com.github.Danice123.javamon.move.Move;
import com.github.Danice123.javamon.pokemon.PokeInstance;

public class StaticDamage extends Effect {
	
	private int damage;
	private int percent;
	private boolean pDamage = false;

	public void use(BattleFunction iface, PokeInstance user, PokeInstance target, Move move) {
		if (pDamage) {
			target.changeHealth((int) -(target.getHealth() / (100.0 / percent)));
		} else {
			target.changeHealth(-damage);
		}
		
	}

}
