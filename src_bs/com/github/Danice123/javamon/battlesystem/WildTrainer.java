package com.github.Danice123.javamon.battlesystem;

import com.badlogic.gdx.graphics.Texture;
import com.github.Danice123.javamon.pokemon.PokeInstance;

public class WildTrainer implements Trainer {
	
	Party party;
	
	public WildTrainer(PokeInstance wild) {
		party = new Party();
		party.add(wild);
	}

	@Override
	public Party getParty() {
		return party;
	}

	@Override
	public int firstPokemon() {
		return 0;
	}

	@Override
	public boolean hasPokemonLeft() {
		return false;
	}

	@Override
	public Texture getImage() {
		return null;
	}

	@Override
	public Texture getBackImage() {
		return null;
	}

	@Override
	public String getName() {
		return null;
	}

}
