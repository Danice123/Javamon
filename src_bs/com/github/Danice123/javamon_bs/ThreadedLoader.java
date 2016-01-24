package com.github.Danice123.javamon_bs;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import org.tmatesoft.sqljet.core.SqlJetException;
import org.tmatesoft.sqljet.core.SqlJetTransactionMode;
import org.tmatesoft.sqljet.core.table.SqlJetDb;

import com.github.Danice123.javamon.pokemon.Pokemon;

public class ThreadedLoader implements Runnable {
	
	private SqlJetDb db;
	private String[] moves;
	private String[] ability;
	private int id;
	
	public boolean finished = false;
	public Pokemon pokemon;
	
	public ThreadedLoader(SqlJetDb db, String[] moves, String[] ability, int id) throws SqlJetException {
		this.db = db;
		this.moves = moves;
		this.ability = ability;
		this.id = id;
		db.beginTransaction(SqlJetTransactionMode.READ_ONLY);
	}

	@Override
	public void run() {
		try {
			pokemon = new Pokemon(db, moves, ability, id);
			OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(new File("db/pokemon/" + pokemon.name + ".poke")));
			out.write(Pokemon.toXML(pokemon));
			out.close();
		} catch (SqlJetException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		finished = true;
		try {
			db.close();
		} catch (SqlJetException e) {
			e.printStackTrace();
		}
	}

}
