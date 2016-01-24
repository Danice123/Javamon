package com.github.Danice123.javamon_bs;

import java.util.HashMap;
import java.util.Iterator;

public class PokeData {
	
	public HashMap<Integer, State> data;
	
	public PokeData() {
		data = new HashMap<Integer, State>();
	}
	
	public void seen(int index) {
		data.put(index, State.seen);
	}
	
	public void caught(int index) {
		data.put(index, State.caught);
	}
	
	public boolean isSeen(int index) {
		State s = data.get(index);
		if (s == State.seen || s == State.caught) {
			return true;
		}
		return false;
	}
	
	public boolean isCaught(int index) {
		if (data.get(index) == State.caught) {
			return true;
		}
		return false;
	}
	
	public int amountCaught() {
		int num = 0;
		for (Iterator<Integer> i = data.keySet().iterator(); i.hasNext();) {
			if (data.get(i.next()) == State.caught)
				num++;
		}
		return num;
	}
	
	public int amountSeen() {
		int num = 0;
		for (Iterator<Integer> i = data.keySet().iterator(); i.hasNext();) {
			int n = i.next();
			if (data.get(n) == State.caught || data.get(n) == State.seen)
				num++;
		}
		return num;
	}

	private enum State {
		none, seen, caught
	}
}