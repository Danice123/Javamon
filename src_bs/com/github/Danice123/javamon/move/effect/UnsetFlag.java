package com.github.Danice123.javamon.move.effect;

import com.github.Danice123.javamon.battlesystem.BattleFunction;
import com.github.Danice123.javamon.move.Move;
import com.github.Danice123.javamon.pokemon.PokeInstance;

public class UnsetFlag extends Effect {
	
	private Target target;
	private int flag;
	private String text;

	public void use(BattleFunction iface, PokeInstance user, PokeInstance target, Move move) {
		PokeInstance p;
		if (this.target == Target.target) {
			p = target;
		} else {
			p = user;
		}
		
		p.battleStatus.flags[flag] = false;
		if (text != null) {
			text = text.replace("$target", p.getName());
			if (p.battleStatus.lastMove != -1)
				text = text.replace("$lastMove", p.getMove(p.battleStatus.lastMove).getName());
			iface.print(text);
		}
	}
}
