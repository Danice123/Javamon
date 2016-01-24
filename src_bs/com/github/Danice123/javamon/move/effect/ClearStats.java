package com.github.Danice123.javamon.move.effect;

import com.github.Danice123.javamon.battlesystem.BattleFunction;
import com.github.Danice123.javamon.move.Move;
import com.github.Danice123.javamon.pokemon.PokeInstance;

public class ClearStats extends Effect {
	
	private Target target;
	private String text;
	
	public void use(BattleFunction iface, PokeInstance user, PokeInstance target, Move move) {
		if (this.target == Target.target) {
			target.battleStatus.resetStats();
		} else {
			user.battleStatus.resetStats();
		}
		if (text != null) {
			text = text.replace("$target", target.getName());
			if (target.battleStatus.lastMove != -1)
				text = text.replace("$lastMove", target.getMove(target.battleStatus.lastMove).getName());
			iface.print(text);
		}
	}

}
