package com.github.Danice123.javamon.move.effect;

import com.github.Danice123.javamon.battlesystem.BattleFunction;
import com.github.Danice123.javamon.move.Move;
import com.github.Danice123.javamon.pokemon.PokeInstance;
import com.github.Danice123.javamon.pokemon.Stat;

public class StatEffect extends Effect {
	
	private Target target;
	private Stat stat;
	private int levels;

	public void use(BattleFunction iface, PokeInstance user, PokeInstance target, Move move) {
		if (this.target == Target.target) {
			if (target.battleStatus.flags[8] && levels < 0)
				return;
			target.battleStatus.modify(stat, levels);
			if (levels > 0) {
				iface.print(target.getName() + "'s " + stat.name() + " has been raised!");
			} else {
				iface.print(target.getName() + "'s " + stat.name() + " has been lowered...");
			}
			
		} else {
			if (user.battleStatus.flags[8] && levels < 0)
				return;
			user.battleStatus.modify(stat, levels);
			if (levels > 0) {
				iface.print(user.getName() + " raised it's " + stat.name());
			} else {
				iface.print(user.getName() + "'s lowered it's " + stat.name());
			}
		}
	}
}
