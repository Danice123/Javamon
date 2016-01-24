package com.github.Danice123.javamon.battlesystem;

import com.badlogic.gdx.graphics.Texture;


public interface Trainer {

	Party getParty();
	
	int firstPokemon();
	
	boolean hasPokemonLeft();
	
	Texture getImage();
	
	Texture getBackImage();
	
	String getName();
}
