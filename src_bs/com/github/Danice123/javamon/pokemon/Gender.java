package com.github.Danice123.javamon.pokemon;

public enum Gender {
	Male, Female, None;
	
	public String toString() {
		if (this == Male) {
			return "♂";
		}
		if (this == Female) {
			return "♀";
		}
		return "";
	}
}
