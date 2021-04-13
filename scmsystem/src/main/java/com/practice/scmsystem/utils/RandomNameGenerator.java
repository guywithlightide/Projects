package com.practice.scmsystem.utils;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class RandomNameGenerator {

	private static final int INTWORDLENTGH = 5;
	
	private static Set<String> stGeneratedStrings = new HashSet<String>();
	private static final String [] ALPHANUM= {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P",
			"Q","R","S","T","U","V","W","X","Y","Z","0","1","2","3","4","5","6","7","8","9"};	
	public String generateString(){
		
		Random random = new Random(System.currentTimeMillis());
		
		String generatedString = new String();
		while(generatedString.length()<=INTWORDLENTGH)
		{
			try {
				generatedString = generatedString.concat(ALPHANUM[Math.floorMod(random.nextInt(),ALPHANUM.length)]);
			}catch (ArrayIndexOutOfBoundsException e) {
			}
			if(stGeneratedStrings.contains(generatedString))
				generatedString = "";
		}
		stGeneratedStrings.add(generatedString);
		return generatedString;
	}
}
