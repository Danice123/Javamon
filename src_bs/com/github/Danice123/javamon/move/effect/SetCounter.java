package com.github.Danice123.javamon.move.effect;

import com.github.Danice123.javamon.battlesystem.BattleFunction;
import com.github.Danice123.javamon.move.Move;
import com.github.Danice123.javamon.pokemon.PokeInstance;

public class SetCounter extends Effect {
	
	private Target target;
	private int counter;
	private String text;
	
	private type type;
	private int n;
	
	private int max;
	private int min;

	public void use(BattleFunction iface, PokeInstance user, PokeInstance target, Move move) {
		PokeInstance p;
		if (this.target == Target.target) {
			p = target;
		} else {
			p = user;
		}
		switch (type) {
		case modify:
			p.battleStatus.counters[counter] += n;
			break;
		case random:
			int r = iface.getRandom().nextInt(max - min) + min;
			p.battleStatus.counters[counter] = r;
			break;
		case set:
			p.battleStatus.counters[counter] = n;
			break;
		}
		if (text != null) {
			text = text.replace("$target", p.getName());
			text = text.replace("$lastMove", p.getMove(p.battleStatus.lastMove).getName());
			iface.print(text);
		}
	}
	
	private enum type {
		set, modify, random;
	}
}
