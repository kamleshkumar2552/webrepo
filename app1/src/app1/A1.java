package app1;

import java.util.Random;

public class A1 {

	public static void main(String[] args) {
     String a=	randomusername();
	System.out.println(a);
	
	}
	
	
	private static String randomusername () {
		char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < 5; i++) {
		    char c = chars[random.nextInt(chars.length)];
		    sb.append(c);
		}
		String output = sb.toString();
     return output;

	}

}
