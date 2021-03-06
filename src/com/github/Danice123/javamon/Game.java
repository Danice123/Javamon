package com.github.Danice123.javamon;

import java.io.File;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.github.Danice123.javamon.entity.Player;
import com.github.Danice123.javamon.entity.sprite.Spriteset;
import com.github.Danice123.javamon.loader.EntityLoader;
import com.github.Danice123.javamon.loader.PokemonLoader;
import com.github.Danice123.javamon.loader.ScriptLoader;
import com.github.Danice123.javamon.loader.TriggerLoader;
import com.github.Danice123.javamon.map.EntityList;
import com.github.Danice123.javamon.map.TriggerList;
import com.github.Danice123.javamon.pokemon.PokeDB;
import com.github.Danice123.javamon.screen.Loading;
import com.github.Danice123.javamon.screen.World;
import com.github.Danice123.javamon.script.Script;
import com.thoughtworks.xstream.XStream;

public class Game implements Runnable {
	
	private Display display;
	private AssetManager assets;
	
	private Player player;
	private World world;
	
	public PokeDB db;
	
	public static Game game;
	
	public Game(Display display) {
		this.display = display;
		load();
		game = this;
	}
	
	private void load() {
		assets = new AssetManager();
		assets.setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));
		assets.setLoader(Script.class, new ScriptLoader(new InternalFileHandleResolver()));
		assets.setLoader(EntityList.class, new EntityLoader(new InternalFileHandleResolver()));
		assets.setLoader(PokeDB.class, new PokemonLoader(new InternalFileHandleResolver()));
		assets.setLoader(TriggerList.class, new TriggerLoader(new InternalFileHandleResolver()));
		
		//Maps
		FileHandle f = new FileHandle("res/maps/");
		for (FileHandle map : f.list()) {
			assets.load(map.path() + "/map.tmx", TiledMap.class);
			assets.load(map.path() + "/entity.lst", EntityList.class);
			assets.load(map.path() + "/trigger.lst", TriggerList.class);
			for (FileHandle script: map.list())
				if (script.extension().equals("ps"))
					assets.load(script.path(), Script.class);
		}
		
		//Sprites
		f = new FileHandle("res/entity/sprites");
		for (FileHandle img : f.list())
			if (img.extension().equals("png"))
				assets.load(img.path(), Texture.class);
		
		//Gui
		assets.load("res/gui/border.png", Texture.class);
		assets.load("res/gui/arrow.png", Texture.class);
		assets.load(com.github.Danice123.javamon.screen.menu.gen1.Pokedex.pokeball, Texture.class);
		
		//Scripts
		f = new FileHandle("res/scripts");
		for (FileHandle s : f.list())
			assets.load(s.path(), Script.class);
		
		//Pokemon
		assets.load("db/pokemon", PokeDB.class);
	}
	
	public AssetManager getAssets() {
		return assets;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public World getWorld() {
		return world;
	}

	@Override
	public void run() {
		//Loading
		Loading load = new Loading(this);
		display.setScreen(load);
		synchronized (load) {
			try {load.wait();} catch (InterruptedException e) {e.printStackTrace();}
		}
		
		//Startup
		db = assets.get("db/pokemon");
		world = new World(this);
		player = new Player(this, new Spriteset((Texture) assets.get("res/entity/sprites/Red.png")));
		
		File save = new File("Player.xml");
		String mN;
		if (save.exists()) {
			XStream s = new XStream();
			SaveFile sf = (SaveFile) s.fromXML(save);
			mN = player.load(sf);
		} else {
			player.setCoords(3, 3, 0);
			mN = "Pallet_Town_Red_Home_2";
		}
		
		new Thread(player).start();
		Gdx.input.setInputProcessor(player.control);
		
		world.loadMap(mN);
		
		//Starting game
		display.setScreen(world);
		load.finished();
		
		world.loadMapSynch(mN);
	}
	
}
