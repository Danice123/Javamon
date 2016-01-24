package com.github.Danice123.javamon.move;

public enum DamageType {
	PHYSICAL, SPECIAL, NONE;
	
	public static DamageType getType(int i) {
		switch (i) {
		case 1:
			return NONE;
		case 2:
			return PHYSICAL;
		case 3:
			return SPECIAL;
		}
		return null;
	}
}
