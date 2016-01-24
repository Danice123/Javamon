package com.github.Danice123.javamon.map;

import java.util.ArrayList;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.MapLayers;
import com.github.Danice123.javamon.battlesystem.Party;
import com.github.Danice123.javamon.entity.Entity;
import com.github.Danice123.javamon.entity.NPC;
import com.github.Danice123.javamon.entity.Sign;
import com.github.Danice123.javamon.entity.Stationary;
import com.github.Danice123.javamon.entity.Trainer;
import com.github.Danice123.javamon.entity.Walkable;
import com.github.Danice123.javamon.entity.sprite.Spriteset;
import com.github.Danice123.javamon.pokemon.PokeInstance;
import com.github.Danice123.javamon.pokemon.Pokemon;
import com.github.Danice123.javamon.script.Script;

public class EntityList {
	
	public ArrayList<EntityInfo> entities;
	
	public void load(AssetManager assets, MapHandler assign, MapLayers map, String mapName) {
		for (EntityInfo e : entities) {
			Entity entity = null;
			switch (e.type) {
			case Sign:
				entity = new Sign(e.name, new Script((Script) assets.get("res/scripts/Sign.ps")));
				break;
			case NPC:
				if (e.script == null)
					entity = new NPC(e.name, 
							new Spriteset((Texture) assets.get("res/entity/sprites/" + e.spriteset + ".png")), null);
				else
					entity = new NPC(e.name, 
							new Spriteset((Texture) assets.get("res/entity/sprites/" + e.spriteset + ".png")),
							new Script((Script) assets.get("res/maps/" + mapName + "/" + e.script + ".ps")));
				((Walkable) entity).face(e.facing);
				break;
			case Trainer:
				Party party = new Party();
				for (String s : e.party) {
					PokeInstance p = new PokeInstance(Pokemon.getPokemon(s.split(" ")[0]), Integer.parseInt(s.split(" ")[1]));
					party.add(p);
				}
				
				entity = new Trainer(e.name, new Spriteset((Texture) assets.get("res/entity/sprites/" + e.spriteset + ".png")), null, party);
				((Walkable) entity).face(e.facing);
				break;
			case Object:
				if (e.script == null)
					entity = new Stationary(e.name,
							new Spriteset((Texture) assets.get("res/entity/sprites/" + e.spriteset + ".png")), null);
				else
					entity = new Stationary(e.name,
							new Spriteset((Texture) assets.get("res/entity/sprites/" + e.spriteset + ".png")),
							new Script((Script) assets.get("res/maps/" + mapName + "/" + e.script + ".ps")));
				break;
			default:
				break;
			}
			if (entity == null)
				System.out.println("Problem");
			entity.setCoords(e.x, e.y, e.layer);
			entity.addStrings(e.strings);
			entity.hidden = e.hidden;
			entity.place(assign);
			map.get(e.layer).getObjects().add(entity);
		}
	}
	
	public enum Type {
		Sign, NPC, Object, Trainer;
	}
}
