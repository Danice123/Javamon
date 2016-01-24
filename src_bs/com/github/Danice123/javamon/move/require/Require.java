package com.github.Danice123.javamon.move.require;

import com.github.Danice123.javamon.battlesystem.BattleFunction;
import com.github.Danice123.javamon.move.Move;
import com.github.Danice123.javamon.pokemon.PokeInstance;

public abstract class Require {

	public abstract boolean check(BattleFunction iface, PokeInstance user, PokeInstance target, Move move);
}
