package com.github.Danice123.javamon.battlesystem;

import com.github.Danice123.javamon.move.Move;
import com.github.Danice123.javamon.pokemon.PokeInstance;
import com.github.Danice123.javamon.pokemon.Stat;
import com.github.Danice123.javamon.pokemon.Status;

public class Battlesystem implements Runnable {
	
	private BattleFunction iface;
	private Trainer player;
	private Trainer enemy;
	private int[] pUsed;
	private boolean isWild;
	
	private boolean run = false;
	
	public Battlesystem(Trainer player, Trainer enemy) {
		this.player = player;
		this.enemy = enemy;
		pUsed = new int[2];
		pUsed[0] = player.firstPokemon();
		pUsed[1] = enemy.firstPokemon();
		isWild = false;
	}
	
	public Battlesystem(Trainer player, PokeInstance wild) {
		this.player = player;
		enemy = new WildTrainer(wild);
		pUsed = new int[2];
		pUsed[0] = player.firstPokemon();
		pUsed[1] = enemy.firstPokemon();
		isWild = true;
	}
	
	public void init(BattleFunction iface) {
		this.iface = iface;
		synchronized (iface) {
			try {
				iface.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public Trainer getPlayer() {
		return player;
	}
	
	public Trainer getEnemy() {
		return enemy;
	}

	public void run() {
		iface.battleWildStart();
		getPlayerPokemon().battleStatus = new BattleStatus();
		getEnemyPokemon().battleStatus = new BattleStatus();
		
		if (isWild)
			iface.print("A wild " + getEnemyPokemon().getName() + " appeared!");
		else
			iface.print(enemy.getName() + " wants to battle!");
		iface.printnw("Go! " + getPlayerPokemon().getName() + "!!");
		iface.playerThrowPokemon();
		
		boolean win = false;
		do {
			mainLoop();
			if (run)
				break;
			
			if (checkFainted(getPlayerPokemon())) {
				iface.print(getPlayerPokemon().getName() + " has Fainted!");
				if (player.hasPokemonLeft()) {
					int n = iface.openSwitchMenu(player.getParty(), true);
					pUsed[0] = n;
					getPlayerPokemon().battleStatus = new BattleStatus();
					iface.print("Go! " + getPlayerPokemon().getName() + "!!");
				} else {
					win = false;
					break;
				}
			}
			
			if (checkFainted(getEnemyPokemon())) {
				iface.print(getEnemyPokemon().getName() + " has Fainted!");
				if (enemy.hasPokemonLeft()) {
					pUsed[1]++;
					getEnemyPokemon().battleStatus = new BattleStatus();
					iface.print("Trainer threw out " + getEnemyPokemon().getName() + "!");
				} else {
					win = true;
					break;
				}
			}
		} while (true);
		
		if (run) {
			iface.print("You Ran...");
			iface.quit();
			return;
		}
		if (win) {
			iface.print("You Win!");
		} else {
			iface.print("You Lose...");
		}
		
		iface.quit();
		
		synchronized (this) {
			notifyAll();
		}
	}
	
	private void mainLoop() {
		do {
			//debug
			//iface.print(getPlayerPokemon().getName() + "-" + getPlayerPokemon().getCurrentHealth());
			//iface.print("");
			//iface.print(getEnemyPokemon().getName() + "-" + getEnemyPokemon().getCurrentHealth());
			//iface.print("");
			//debug
			
			int res = iface.openMenu();
			
			if (res == -1) { //run
				if (!isWild) {
					iface.print("You can't run from a trainer battle!");
					continue;
				}
				run = true;
				break;
			}
			
			if (res == -2) {
				int n = iface.openSwitchMenu(player.getParty(), false);
				if (n == -1)
					continue;
				pUsed[0] = n;
				getPlayerPokemon().battleStatus = new BattleStatus();
				iface.print("Go! " + getPlayerPokemon().getName() + "!!");
				continue; //handle switch
			}
			
			//Player move
			int Pmove = -1;
			if (res > 3) {
				pUsed[0] = res - 4;
				getPlayerPokemon().battleStatus = new BattleStatus();
				iface.print("Go! " + getPlayerPokemon().getName() + "!!");
			} else {
				Pmove = res;
			}
			
			//Enemy move
			int Emove = iface.getRandom().nextInt(getEnemyPokemon().getMoveAmount());
			
			if (Pmove == -1) {
				turn(getEnemyPokemon(), getPlayerPokemon(), Emove);
				if (checkFainted(getPlayerPokemon()) || checkFainted(getEnemyPokemon())) break;
				continue;
			}
			
			//Check Speed
			if (isFaster(getPlayerPokemon(), Pmove, getEnemyPokemon(), Emove)) {
				turn(getPlayerPokemon(), getEnemyPokemon(), Pmove);
				if (checkFainted(getPlayerPokemon()) || checkFainted(getEnemyPokemon())) break;
				
				turn(getEnemyPokemon(), getPlayerPokemon(), Emove);
				if (checkFainted(getPlayerPokemon()) || checkFainted(getEnemyPokemon())) break;
			} else {
				turn(getEnemyPokemon(), getPlayerPokemon(), Emove);
				if (checkFainted(getPlayerPokemon()) || checkFainted(getEnemyPokemon())) break;
				
				turn(getPlayerPokemon(), getEnemyPokemon(), Pmove);
				if (checkFainted(getPlayerPokemon()) || checkFainted(getEnemyPokemon())) break;
			}
			
			//HACKS
			//disable
			if (getPlayerPokemon().battleStatus.flags[4] && !getPlayerPokemon().battleStatus.flags[5]) {
				getPlayerPokemon().battleStatus.counters[5] = getPlayerPokemon().battleStatus.lastMove;
				getPlayerPokemon().battleStatus.flags[5] = true;
			}
			if (getEnemyPokemon().battleStatus.flags[4] && !getEnemyPokemon().battleStatus.flags[5]) {
				getEnemyPokemon().battleStatus.counters[5] = getEnemyPokemon().battleStatus.lastMove;
				getEnemyPokemon().battleStatus.flags[5] = true;
			}
		} while (true);
	}
	
	private void turn(PokeInstance user, PokeInstance target, int move) {
		boolean attack = true;
		
		//prechecks--------------------------------------------------------------
		//Recharge
		if (user.battleStatus.flags[12]) {
			iface.print(user.getName() + " must recharge!");
			user.battleStatus.flags[12] = false;
			attack = false;
		}
		//Flinch
		if (user.battleStatus.flags[1]) {
			iface.print(user.getName() + " flinched!");
			user.battleStatus.flags[1] = false;
			attack = false;
		}
		//Confuse
		if (user.battleStatus.flags[0]) { 
			iface.print(user.getName() + " is confused!");
			user.battleStatus.counters[0]--;
			if (user.battleStatus.counters[0] == 0) {
				user.battleStatus.flags[0] = false;
				iface.print(user.getName() + " snapped out of confusion!");
			} else {
				if (iface.getRandom().nextBoolean()) {
					user.changeHealth(-confusionCalc(user));
					iface.print(user.getName() + " hurt itself in confusion!");
					attack = false;
				}
			}
		}
		//Disable
		if (user.battleStatus.flags[4]) {
			if (user.battleStatus.counters[4] == 0) {
				user.battleStatus.flags[4] = false;
				iface.print(user.getName() + "'s " + user.getMove(user.battleStatus.counters[5]).getName() + " has been un-disabled!");
				user.battleStatus.flags[5] = false;
				user.battleStatus.counters[5] = 0;
			} else if (move == user.battleStatus.counters[5]) {
				iface.print(user.getMove(user.battleStatus.counters[5]).getName() + " is disabled!");
				attack = false;
				user.battleStatus.counters[4]--;
			}
		}
		//prechecks--------------------------------------------------------------
		
		//pre-move status check--------------------------------------------------
		switch (user.status) {
		case Sleep:
			user.sleepCounter--;
			if (user.sleepCounter == 0) {
				user.status = Status.None;
				iface.print(user.getName() + " woke up!");
			} else {
				iface.print(user.getName() + " is asleep!");
				attack = false;
			}
			break;
		case Freeze:
			if (iface.getRandom().nextInt(100) < 20) {
				user.status = Status.None;
				iface.print(user.getName() + " thawed!");
			} else {
				iface.print(user.getName() + " is frozen solid!");
				attack = false;
			}
			break;
		case Paralysis:
			if (iface.getRandom().nextInt(100) < 25) {
				iface.print(user.getName() + " is unable to move!");
				attack = false;
			}
			break;
		default:
			break;
		}
		//pre-move status check--------------------------------------------------
		
		//move-------------------------------------------------------------------
		if (attack) {
			//Continue Attack Modifier
			if (user.battleStatus.flags[6]) {
				Move cont = Move.getMove(user.getMove(user.battleStatus.lastMove).getName() + "_con");
				iface.print(user.getName() + " uses " + cont.getName() + "!");
				cont.getEffect().use(iface, user, target, cont);
				user.battleStatus.counters[6]--;
				if (user.battleStatus.counters[6] == 0) {
					user.battleStatus.flags[6] = false;
					
					//Confuse after end Modifier
					if (user.battleStatus.flags[7]) {
						user.battleStatus.flags[0] = true;
						user.battleStatus.counters[0] = iface.getRandom().nextInt(3) + 2;
						iface.print(user.getName() + " became confused from exhustion!");
						user.battleStatus.flags[7] = false;
					}
				}
			} else {
				iface.print(user.getName() + " uses " + user.getMove(move).getName() + "!");
				user.getMove(move).getEffect().use(iface, user, target, user.getMove(move));
				user.battleStatus.lastMove = move;
			}
			
			//Successful Move checks
			//Disable
			if (user.battleStatus.flags[4]) {
				user.battleStatus.counters[4]--;
			}
		}
		//move-------------------------------------------------------------------
		
		//post-move status check-------------------------------------------------
		switch (user.status) {
		case Burn:
			user.changeHealth(-(user.getHealth() / 8));
			iface.print(user.getName() + " is hurt by its burn!");
			iface.print("--DEBUG " + (user.getHealth() / 8) + " damage");
			break;
		case Poison:
			user.changeHealth(-(user.getHealth() / 8));
			iface.print(user.getName() + " is hurt by the poison!");
			iface.print("--DEBUG " + (user.getHealth() / 8) + " damage");
			break;
		case Toxic:
			int n = user.battleStatus.counters[3] + 1;
			user.changeHealth((int) -(user.getHealth() * (n/16.0)));
			iface.print(user.getName() + " is hurt by the poison!");
			iface.print("--DEBUG " + n + "n -" + (user.getHealth() * (n/16.0)) + " damage");
			user.battleStatus.counters[3]++;
			break;
		default:
			break;
		}
		//post-move status check-------------------------------------------------
		
		//postchecks-------------------------------------------------------------
		//Leech Seed
		if (user.battleStatus.flags[9]) {
			user.changeHealth(-(user.getHealth() / 8));
			target.changeHealth(user.getHealth() / 8);
			iface.print(user.getName() + " was drained by the leech seed!");
		}
		//postchecks-------------------------------------------------------------
	}
	
	private boolean checkFainted(PokeInstance p) {
		return p.status == Status.Fainted;
	}
	
	private boolean isFaster(PokeInstance one, int oneM, PokeInstance two, int twoM) {
		if (one.getMove(oneM).getSpeed() == two.getMove(twoM).getSpeed()) {
			return one.getSpeed() > two.getSpeed();
		} else {
			return one.getMove(oneM).getSpeed() > two.getMove(twoM).getSpeed();
		}
	}
	
	public PokeInstance getPlayerPokemon() {
		return player.getParty().getPokemon(pUsed[0]);
	}
	
	public PokeInstance getEnemyPokemon() {
		return enemy.getParty().getPokemon(pUsed[1]);
	}
	
	private int confusionCalc(PokeInstance user) {
		int damage = (((2 * user.getLevel() / 5 + 2) * user.getAttack() * 40 / user.getDefense()) / 50) + 2;
		damage *= user.battleStatus.getMultiplier(Stat.attack);
		return damage;
	}
}
