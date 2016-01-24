package com.github.Danice123.javamon.battlesystem;

import java.util.EnumMap;

import com.github.Danice123.javamon.pokemon.Stat;

public class BattleStatus {
	
	public int lastMove = -1;
	
	private EnumMap<Stat, Integer> stats;
	
	public boolean[] flags;
	public int[] counters;
	
	public BattleStatus() {
		resetStats();
		
		flags = new boolean[100];
		counters = new int[100];
	}
	
	public void resetStats() {
		stats = new EnumMap<Stat, Integer>(Stat.class);
		stats.put(Stat.attack, 0);
		stats.put(Stat.defense, 0);
		stats.put(Stat.speed, 0);
		stats.put(Stat.Sattack, 0);
		stats.put(Stat.Sdefense, 0);
		stats.put(Stat.accuracy, 0);
		stats.put(Stat.evasion, 0);
	}
	
	public boolean modify(Stat stat, int amount) {
		if (amount > 0) {
			if (stats.get(stat) == 6) {
				return false;
			}
			stats.put(stat, stats.get(stat) + amount);
			if (stats.get(stat) > 6) {
				stats.put(stat, 6);
			}
		} else {
			if (stats.get(stat) == -6) {
				return false;
			}
			stats.put(stat, stats.get(stat) + amount);
			if (stats.get(stat) < -6) {
				stats.put(stat, -6);
			}
		}
		return true;
	}
	
	public int getAccuracy() {
		int a = stats.get(Stat.accuracy);
		if (a < 0) {
			return 300 / (3 - a);
		} else {
			return (3 + a) * 100 / 3;
		}
	}
	
	public int getEvasion() {
		int e = stats.get(Stat.evasion);
		if (e < 0) {
			return (3 - e) * 100 / 3;
		} else {
			return 300 / (3 + e);
		}
	}
	
	public double getMultiplier(Stat stat) {
		if (stat == Stat.accuracy || stat == Stat.evasion)
			return 1.0;
		switch (stats.get(stat)) {
		case -6:
			return 0.25;
		case -5:
			return 0.29;
		case -4:
			return 0.33;
		case -3:
			return 0.40;
		case -2:
			return 0.50;
		case -1:
			return 0.66;
		case 1:
			return 1.5;
		case 2:
			return 2.0;
		case 3:
			return 2.5;
		case 4:
			return 3.0;
		case 5:
			return 3.5;
		case 6:
			return 4.0;
		}
		return 1.0;
	}

}
