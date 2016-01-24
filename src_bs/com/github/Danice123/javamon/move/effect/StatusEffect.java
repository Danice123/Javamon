package com.github.Danice123.javamon.move.effect;

import com.github.Danice123.javamon.battlesystem.BattleFunction;
import com.github.Danice123.javamon.move.Move;
import com.github.Danice123.javamon.pokemon.PokeInstance;
import com.github.Danice123.javamon.pokemon.Status;

public class StatusEffect extends Effect {
	
	private Target target;
	private Status status;

	public void use(BattleFunction iface, PokeInstance user, PokeInstance target, Move move) {
			if (this.target == Target.target) {
				if (status != Status.Fainted && target.status != Status.None)
					return;
				target.status = status;
				switch (status) {
				case Burn:
					iface.print(target.getName() + " has been burnt!");
					break;
				case Freeze:
					iface.print(target.getName() + " has been frozen solid!");
					break;
				case Paralysis:
					iface.print(target.getName() + " has been paralyzed!");
					break;
				case Poison:
					iface.print(target.getName() + " has been poisoned!");
					break;
				case Toxic:
					iface.print(target.getName() + " has been badly poisoned!");
					break;
				case Sleep:
					iface.print(target.getName() + " has been put to sleep!");
					break;
				case Fainted:
					target.changeHealth(-target.getCurrentHealth());
					iface.print("The attack was a OHKO!");
					break;
				default:
					break;
				}
			} else {
				if (status != Status.Fainted && user.status != Status.None)
					return;
				user.status = status;
				switch (status) {
				case Burn:
					iface.print(user.getName() + " has been burnt!");
					break;
				case Freeze:
					iface.print(user.getName() + " has been frozen solid!");
					break;
				case Paralysis:
					iface.print(user.getName() + " has been paralyzed!");
					break;
				case Poison:
					iface.print(user.getName() + " has been poisoned!");
					break;
				case Toxic:
					iface.print(user.getName() + " has been badly poisoned!");
					break;
				case Sleep:
					iface.print(user.getName() + " has been put to sleep!");
					break;
				case Fainted:
					user.changeHealth(-user.getCurrentHealth());
					break;
				default:
					break;
			}
		}
	}
}
