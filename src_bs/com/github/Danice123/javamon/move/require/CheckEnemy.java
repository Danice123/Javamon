package com.github.Danice123.javamon.move.require;

import com.github.Danice123.javamon.battlesystem.BattleFunction;
import com.github.Danice123.javamon.move.Move;
import com.github.Danice123.javamon.pokemon.PokeInstance;
import com.github.Danice123.javamon.pokemon.Status;

public class CheckEnemy extends Require {
	
	private boolean disabled = false;
	private boolean isAsleep = false;

	public boolean check(BattleFunction iface, PokeInstance user, PokeInstance target, Move move) {
		if (disabled && target.battleStatus.flags[4]) {
			iface.print("The move failed...");
			return false;
		}
		if (isAsleep && target.status != Status.Sleep) {
			iface.print("The move failed...");
			return false;
		}
		return true;
	}
}
