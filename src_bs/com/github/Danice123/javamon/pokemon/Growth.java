package com.github.Danice123.javamon.pokemon;

public enum Growth {
	Erratic, Fast, Medium, Medium_slow, Slow, Fluctuating;
	
	public static int getExpNeeded(Growth g, int level) {
		switch (g) {
		case Erratic:
			if (level <= 50)
				return (int) ((level^3) * (100 - level) / 50f);
			if (level <= 68)
				return (int) ((level^3) * (150 - level) / 100f);
			if (level <= 98)
				return (int) ((level^3) * (Math.floor((1911 - 10 * level) / 3)) / 500);
			return (int) ((level^3) * (160 - level) / 100f);
		case Fast:
			return (int) (4 * (level^3) / 5f);
		case Medium:
			return level^3;
		case Medium_slow:
			return (int) ((6 * (level^3) / 5f) - (15 * (level^2)) + (100 * level) - 140);
		case Slow:
			return (int) (5 * (level^3) / 5f);
		case Fluctuating:
			if (level <= 15)
				return (int) ((level^3) * ((Math.floor((level + 1) / 3) + 24) / 50));
			if (level <= 36)
				return (int) ((level^3) * ((level + 14) / 50f));
			return (int) ((level^3) * ((Math.floor(level/2) + 32) / 50));
		}
		return 0;
	}
	
	public static Growth getGrowth(int i) {
		switch (i) {
		case 1:
			return Slow;
		case 2:
			return Medium;
		case 3:
			return Fast;
		case 4:
			return Medium_slow;
		case 5:
			return Erratic;
		case 6:
			return Fluctuating;
		}
		return null;
	}

}
