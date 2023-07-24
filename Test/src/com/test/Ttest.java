package com.test;

public class Ttest {

	public static void main(String[] args) {
		int x = 123421;
		String stringx = Integer.toString(x);
		char[] charx = stringx.toCharArray();
		if(charx.length%2==0) {
			for(int i = 0; i<charx.length/2; i++) {
				if(charx[i]!=charx[charx.length-1-i]) {
					System.out.println("a" + i);
				}
			}
		} else if(charx.length%2==1) {
			for(int i = 0; i<(charx.length-1)/2; i++) {
				if(charx[i]!=charx[charx.length-1-i]) {
					System.out.println("b" + i);
				}
			}
		}
	}

}
