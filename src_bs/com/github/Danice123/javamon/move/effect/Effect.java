package com.github.Danice123.javamon.move.effect;

import com.github.Danice123.javamon.battlesystem.BattleFunction;
import com.github.Danice123.javamon.move.Move;
import com.github.Danice123.javamon.pokemon.PokeInstance;

public abstract class Effect {
	
	public abstract void use(BattleFunction iface, PokeInstance user, PokeInstance target, Move move);
	
}
