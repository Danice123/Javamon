package com.github.Danice123.javamon.move.require;

import com.github.Danice123.javamon.battlesystem.BattleFunction;
import com.github.Danice123.javamon.move.Move;
import com.github.Danice123.javamon.pokemon.PokeInstance;

public class CheckSelf extends Require {
	
	private boolean isCharging;

	public boolean check(BattleFunction iface, PokeInstance user, PokeInstance target, Move move) {
		if (isCharging && !user.battleStatus.flags[9]) {
			return false;
		}
		return true;
	}

}
