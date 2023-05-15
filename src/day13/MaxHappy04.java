package day13;

import java.util.ArrayList;
import java.util.List;

public class MaxHappy04 {

	public static class Employee {
		public int happy;
		public List<Employee> nexts;

		public Employee(int h) {
			happy = h;
			nexts = new ArrayList<>();
		}

	}
	
	/**
	 * this function is collect employee happy value 
	 * @param boss
	 * @return
	 */
	public static int maxHappy1(Employee boss) {
		if(boss == null){
			return 0;
		}
		return process1(boss,false);
	}

	public static int process1(Employee cur, boolean up) {
		if(up){
			int ans = 0;
			for (Employee next : cur.nexts) {
				ans += process1(next,false);
			}
			return ans;
		}else {
			int come = cur.happy;
			int noCome = 0;
			for (Employee next : cur.nexts) {
				come += process1(next,true);
				noCome += process1(next,false);
			}
			return Math.max(come, noCome);
		}
	}
	
	/**
	 * this function is collect employee happy
	 * recursion 
	 * @param head
	 * @return
	 */
	public static int maxHappy2(Employee head) {
		Info process = process(head);
		return Math.max(process.comeHappy, process.noComeHappy);
	}

	public static class Info {

		private int comeHappy;
		
		private int noComeHappy;

		public Info(int comeHappy, int noComeHappy) {
			super();
			this.comeHappy = comeHappy;
			this.noComeHappy = noComeHappy;
		}
		
		
		
	}

	public static Info process(Employee x) {
		if(x == null) {
			return new Info(0,0);
		}
		
		// if the leader no come ==> happy = 0; 
		// else happy = happy;
		int come = x.happy;
		int noCome = 0;
		
		
		for(Employee e : x.nexts) {
			Info info = process(e);
			// leader come ==> subdivision no come  
			come += info.noComeHappy;
			
			// leader no come ==> subdivision cloud come or no come
			noCome += Math.max(info.comeHappy, info.noComeHappy);
		}
		
		

		return new Info(come,noCome);
		
	}

	// for test
	public static Employee genarateBoss(int maxLevel, int maxNexts, int maxHappy) {
		if (Math.random() < 0.02) {
			return null;
		}
		Employee boss = new Employee((int) (Math.random() * (maxHappy + 1)));
		genarateNexts(boss, 1, maxLevel, maxNexts, maxHappy);
		return boss;
	}

	// for test
	public static void genarateNexts(Employee e, int level, int maxLevel, int maxNexts, int maxHappy) {
		if (level > maxLevel) {
			return;
		}
		int nextsSize = (int) (Math.random() * (maxNexts + 1));
		for (int i = 0; i < nextsSize; i++) {
			Employee next = new Employee((int) (Math.random() * (maxHappy + 1)));
			e.nexts.add(next);
			genarateNexts(next, level + 1, maxLevel, maxNexts, maxHappy);
		}
	}

	public static void main(String[] args) {
		int maxLevel = 4;
		int maxNexts = 7;
		int maxHappy = 100;
		int testTimes = 100000;
		for (int i = 0; i < testTimes; i++) {
			Employee boss = genarateBoss(maxLevel, maxNexts, maxHappy);
			if (maxHappy1(boss) != maxHappy2(boss)) {
				System.out.println("Oops!");
			}
		}
		System.out.println("finish!");
	}

}
