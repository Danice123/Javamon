package com.github.Danice123.javamon.move.effect;

public enum Target {
	user, target;

	public static Target getTarget(String s) {
		switch (s) {
		case "user":
			return user;
		case "target":
			return target;
		}
		return null;
	}
}
