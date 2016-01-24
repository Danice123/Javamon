package com.github.Danice123.javamon.battlesystem;

import java.util.Random;

public interface BattleFunction {

	public void print(String print);
	
	public void printnw(String print);
	
	public Random getRandom();
	
	public int openMenu();
	
	public int openSwitchMenu(Party party, boolean force);
	
	public void quit();
	
	//Events
	public void battleWildStart();
	
	public void playerThrowPokemon();
}
