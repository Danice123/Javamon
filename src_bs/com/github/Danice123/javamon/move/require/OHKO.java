package com.github.Danice123.javamon.move.require;

import com.github.Danice123.javamon.battlesystem.BattleFunction;
import com.github.Danice123.javamon.move.Move;
import com.github.Danice123.javamon.pokemon.PokeInstance;

public class OHKO extends Require {

	public boolean check(BattleFunction iface, PokeInstance user, PokeInstance target, Move move) {
		if (user.getLevel() < target.getLevel()) {
			iface.print("The attack failed...");
			return true;
		}
		int chance = 30 + user.getLevel() - target.getLevel();
		if (chance <= iface.getRandom().nextInt(100)) {
			return true;
		}
		iface.print("The attack missed...");
		return false;
	}

}
