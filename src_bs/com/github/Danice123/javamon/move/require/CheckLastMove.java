package com.github.Danice123.javamon.move.require;

import com.github.Danice123.javamon.battlesystem.BattleFunction;
import com.github.Danice123.javamon.move.Move;
import com.github.Danice123.javamon.pokemon.PokeInstance;

public class CheckLastMove extends Require {
	
	private boolean valid = false;

	public boolean check(BattleFunction iface, PokeInstance user, PokeInstance target, Move move) {
		if (valid) {
			if (target.battleStatus.lastMove == -1) {
				iface.print("The move failed...");
				return false;
			}
			if (target.getPP(target.battleStatus.lastMove) == 0) {
				iface.print("The move failed...");
				return false;
			}
			if (target.battleStatus.flags[4] && target.battleStatus.counters[5] == target.battleStatus.lastMove) {
				iface.print("The move failed...");
				return false;
			}
		}
		return true;
	}

}
