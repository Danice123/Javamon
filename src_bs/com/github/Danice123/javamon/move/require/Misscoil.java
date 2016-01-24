package com.github.Danice123.javamon.move.require;

import com.github.Danice123.javamon.battlesystem.BattleFunction;
import com.github.Danice123.javamon.move.Action;
import com.github.Danice123.javamon.move.Move;
import com.github.Danice123.javamon.pokemon.PokeInstance;

public class Misscoil extends Require {
	
	private int recoil;

	public boolean check(BattleFunction iface, PokeInstance user, PokeInstance target, Move move) {
		if (Action.missCalc(iface, user, target, move)) {
			iface.print(user.getName() + " missed!");
			iface.print(user.getName() + " continued on and hurt itself!");
			
			int damage = (int) (user.getHealth() / (100.0 / recoil));
			user.changeHealth(-damage);
			return false;
		}
		return true;
	}
}
