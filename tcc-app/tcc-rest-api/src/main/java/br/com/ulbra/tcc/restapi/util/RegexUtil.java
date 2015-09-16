package br.com.ulbra.tcc.restapi.util;


import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class RegexUtil {

	public static boolean isRegexValid(String patternString){
		
		if (patternString == null || patternString.trim().length() == 0) return false;
		try {
			Pattern.compile(patternString);
			return true;
		}
		catch (PatternSyntaxException pse) {
			return false;
		}
	}	
}
