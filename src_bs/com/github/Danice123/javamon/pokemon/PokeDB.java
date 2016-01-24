package com.github.Danice123.javamon.pokemon;

import java.util.HashMap;

import com.badlogic.gdx.files.FileHandle;

public class PokeDB {
	
	private HashMap<Integer, String> pokemonList;
	private int nPokemon;
	
	public PokeDB(FileHandle folder) {
		FileHandle[] pf = folder.list(".poke");
		
		pokemonList = new HashMap<Integer, String>();
		nPokemon = 0;
		for (int i = 0; i < pf.length; i++) {
			Pokemon p = Pokemon.getPokemon(pf[i].nameWithoutExtension());
			pokemonList.put(p.number, pf[i].nameWithoutExtension());
			if (nPokemon < p.number)
				nPokemon = p.number;
		}
	}
	
	public int getNumberPokemon() {
		return nPokemon;
	}
	
	public Pokemon getPokemon(int i) {
		return Pokemon.getPokemon(pokemonList.get(i));
	}
	
	/* Resource Intensive, please save */
	
	public Pokemon[] getPokemonList() {
		Pokemon[] list = new Pokemon[nPokemon];
		for (int i = 0; i < nPokemon; i++) {
			list[i] = Pokemon.getPokemon(pokemonList.get(i + 1));
		}
		return list;
	}
}
