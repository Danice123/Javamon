package com.github.Danice123.javamon.move.effect;

import com.github.Danice123.javamon.battlesystem.BattleFunction;
import com.github.Danice123.javamon.move.DamageType;
import com.github.Danice123.javamon.move.Move;
import com.github.Danice123.javamon.pokemon.PokeInstance;
import com.github.Danice123.javamon.pokemon.Stat;
import com.github.Danice123.javamon.pokemon.Type;

public class Damage extends Effect {
	
	private int power;
	private int drain = 0;
	private int recoil = 0;
	private int crit = 0;

	public void use(BattleFunction iface, PokeInstance user, PokeInstance target, Move move) {
		//Calculate
		int damage = damageCalc(user, target, move);
		int critmod = crit(iface, crit + user.battleStatus.counters[2]);
		damage = damage * critmod;
		
		
		//Do Damage
		target.changeHealth(-damage);
		iface.print("--DEBUG-- " + damage + " damage");
		if (critmod > 1)
			iface.print("It was a critical hit!");
		
		//return if extra effects
		if (effectM(target, move) != null) {
			iface.print(effectM(target, move));
		}
		
		//Drain
		if (drain > 0) {
			user.changeHealth(damage / (100 / drain));
			iface.print(user.getName() + " drained " + target.getName() + "'s health!");
		}
		
		//Recoil
		if (recoil > 0) {
			user.changeHealth(damage / (100 / recoil));
			iface.print(user.getName() + " took damage from the attack!");
		}
	}
	
	public String effectM(PokeInstance target, Move move) {
		float a = Type.getEffectiveness(target.getPokemon().type1, target.getPokemon().type2, move.getType());
		if (a == 1.0) {
			return null;
		}
		if (a == 4.0) {
			return "It hit at max effectiveness!";
		}
		if (a == 2.0) {
			return "It was super effective!";
		}
		if (a == 0.5) {
			return "It wasn't very effective...";
		}
		if (a == 0.25) {
			return "It hardly did anything...";
		}
		if (a == 0.0) {
			return "The attack didn't seem to do anything!";
		}
		return null;
	}
	
	public int damageCalc(PokeInstance user, PokeInstance target, Move move) {
		int damage = 0;
		if (move.getDamageType() == DamageType.PHYSICAL) {
			damage = (((2 * user.getLevel() / 5 + 2) * user.getAttack() * power / target.getDefense()) / 50) + 2;
		}
		if (move.getDamageType() == DamageType.SPECIAL) {
			damage = (((2 * user.getLevel() / 5 + 2) * user.getSpecialAttack() * power / target.getSpecialDefense()) / 50) + 2;
		}
		if (move.getType() == user.getPokemon().type1 || move.getType() == user.getPokemon().type2) {
			damage *= 1.5;
		}
		if (move.getDamageType() == DamageType.PHYSICAL) {
			damage *= user.battleStatus.getMultiplier(Stat.attack);
		}
		if (move.getDamageType() == DamageType.SPECIAL) {
			user.battleStatus.getMultiplier(Stat.Sattack);
		}
		damage *= Type.getEffectiveness(target.getPokemon().type1, target.getPokemon().type2, move.getType());
		return damage;
	}
	
	public int crit(BattleFunction iface, int rate) {
		int chance;
		switch (rate) {
		case 0:
			chance = 625;
			break;
		case 1:
			chance = 1250;
			break;
		case 2:
			chance = 2500;
			break;
		case 3:
			chance = 3333;
			break;
		case 4:
			chance = 5000;
			break;
		default:
			chance = 10000;
			break;
		}
		if (chance >= iface.getRandom().nextInt(10000)) {
			return 2;
		}
		return 1;
	}

}
