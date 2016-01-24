package com.github.Danice123.javamon.battlesystem;

import com.github.Danice123.javamon.pokemon.PokeInstance;


public class Party {
	
	private PokeInstance[] party;
	private int size = 0;
	
	public Party() {
		party = new PokeInstance[6];
	}
	
	public int getSize() {
		return size;
	}
	
	public boolean add(PokeInstance pokemon) {
		if (size < party.length) {
			party[size] = pokemon;
			size++;
			return true;
		}
		return false;
	}
	
	public void switchPokemon(int a, int b) {
		PokeInstance pA = party[a];
		PokeInstance pB =  party[b];
		party[a] = pB;
		party[b] = pA;
	}
	
	public PokeInstance getPokemon(int i) {
		return party[i];
	}
}
