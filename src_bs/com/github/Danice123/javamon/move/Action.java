package com.github.Danice123.javamon.move;

import java.util.ArrayList;

import com.github.Danice123.javamon.battlesystem.BattleFunction;
import com.github.Danice123.javamon.move.effect.Effect;
import com.github.Danice123.javamon.move.require.Require;
import com.github.Danice123.javamon.pokemon.PokeInstance;

public class Action {
	
	private ArrayList<Require> require;
	private ArrayList<Effect> effect;
	
	public Action() {
		effect = new ArrayList<Effect>();
		require = new ArrayList<Require>();
	}
	
	public void add(Effect e) {
		effect.add(e);
	}
	
	public void use(BattleFunction iface, PokeInstance user, PokeInstance target, Move move) {
		//check requirements
		for (int i = 0; i < require.size(); i++) {
			if(!require.get(i).check(iface, user, target, move))
				return;
		}
		
		//check if missed
		if (move.getCanMiss() && missCalc(iface, user, target, move))
			iface.print(user.getName() + " missed!");
		
		//do attack(s)
		for (int i = 0; i < effect.size(); i++) {
			effect.get(i).use(iface, user, target, move);
		}
	}
	
	public static boolean missCalc(BattleFunction iface, PokeInstance user, PokeInstance target, Move move) {
		if (target.battleStatus.flags[10] || target.battleStatus.flags[11])
			return false;
		int chance = move.getAccuracy() * user.battleStatus.getAccuracy() * target.battleStatus.getEvasion();
		if (iface.getRandom().nextInt(100) <= chance)
			return false;
		return true;
	}
}
