package com.github.Danice123.javamon;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

import com.github.Danice123.javamon.battlesystem.Party;
import com.github.Danice123.javamon.entity.Dir;
import com.github.Danice123.javamon_bs.PokeData;
import com.thoughtworks.xstream.XStream;

public class SaveFile {
	
	//Player
	public HashMap<String, Boolean> flag;
	public PokeData pokeData;
	public String name;
	public Party party;
	
	//Walkable
	public Dir facing;
	
	//Entity
	public int layer;
	public int x;
	public int y;
	public String mapName;

	
	public void save() {
		XStream s = new XStream();
		
		FileOutputStream out;
		try {
			out = new FileOutputStream(new File("Player.xml"));
			
			s.toXML(this, out);
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
