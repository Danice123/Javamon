package com.github.Danice123.javamon.move;

import java.io.File;

import com.github.Danice123.javamon.move.effect.*;
import com.github.Danice123.javamon.move.require.*;
import com.github.Danice123.javamon.pokemon.Type;
import com.thoughtworks.xstream.XStream;

public class Move {
	
	public static String toXML(Move move) {
		return getXStream().toXML(move);
	}
	
	public static Move getMove(String name) {
		name = name.replace(' ', '_');
		try {
			return (Move) getXStream().fromXML(new File("db/move/" + name + ".move"));
		} catch (com.thoughtworks.xstream.io.StreamException e) {
			System.out.println(name + " Doesn't exist!");
			throw e;
		}
	}
	
	private static XStream getXStream() {
		XStream s = new XStream();
		s.alias("Move", Move.class);
		//Effects
		s.alias("Damage", Damage.class);
		s.alias("StaticDamage", StaticDamage.class);
		s.alias("LevelDamage", LevelDamage.class);
		s.alias("StatusEffect", StatusEffect.class);
		s.alias("SetFlag", SetFlag.class);
		s.alias("UnsetFlag", UnsetFlag.class);
		s.alias("SetCounter", SetCounter.class);
		s.alias("StatEffect", StatEffect.class);
		s.alias("Heal", Heal.class);
		s.alias("Multihit", Multihit.class);
		s.alias("Chance", Chance.class);
		s.alias("Nothing", Nothing.class);
		s.alias("ClearStats", ClearStats.class);
		//Requires
		s.alias("OHKOCheck", OHKO.class);
		s.alias("CheckLastMove", CheckLastMove.class);
		s.alias("CheckEnemy", CheckEnemy.class);
		s.alias("Misscoil", Misscoil.class);
		return s;
	}
	
	private String name;
	private Type type;
	private int PP;
	private int accuracy;
	private int speed;
	private DamageType DT;
	private Action effect;
	
	private boolean isContact;
	private boolean isProtectable;
	private boolean isReflectable;
	private boolean isSnatchable;
	private boolean isMirrorable;
	private boolean isPunch;
	private boolean isSound;
	
	private boolean canMiss;
	
	public String getName() {
		return name;
	}

	public Type getType() {
		return type;
	}
	
	public int getPP() {
		return PP;
	}
	
	public int getAccuracy() {
		return accuracy;
	}
	
	public int getSpeed() {
		return speed;
	}
	
	public DamageType getDamageType() {
		return DT;
	}
		
	public Action getEffect() {
		return effect;
	}
	
	public boolean getIsContact() {
		return isContact;
	}
	
	public boolean getIsProtectable() {
		return isProtectable;
	}
	
	public boolean getIsReflectable() {
		return isReflectable;
	}
	
	public boolean getIsSnatchable() {
		return isSnatchable;
	}

	public boolean getIsMirrorable() {
		return isMirrorable;
	}
	
	public boolean getIsPunch() {
		return isPunch;
	}
	
	public boolean getIsSound() {
		return isSound;
	}
	
	public boolean getCanMiss() {
		return canMiss;
	}
}
