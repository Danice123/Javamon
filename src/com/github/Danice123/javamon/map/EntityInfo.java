package com.github.Danice123.javamon.map;

import java.util.ArrayList;
import java.util.HashMap;

import com.github.Danice123.javamon.entity.Dir;
import com.github.Danice123.javamon.map.EntityList.Type;

public class EntityInfo {

	public Type type;
	public String name;
	public String spriteset;
	public String script;
	public int x;
	public int y;
	public int layer;
	public HashMap<String, String> strings;
	public Dir facing;
	public boolean hidden = false;
	public ArrayList<String> party;
}
