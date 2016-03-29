package com.cureatr.testpackage;

import java.util.Random;

public class RandomNumber {

	public static void main(String[] args) {
		Random ran = new Random();
		for(int i=1; i<=20; i++){
			int x = ran.nextInt(9999) + 10000;
			System.out.println(x);
		}
	}

}
