package br.com.ulbra.tcc.services.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The ServiceUtil class
 * 
 * @author Paulo Pacheco
 *
 */
public class ServiceUtil {

	public static List<Character> getFailedRegexChars(String regex, String data){
		
		List<Character> failedChars = new ArrayList<Character>();
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(data);
		
		if(!matcher.matches()){			
			for( int i = 0 ; i < data.length() ; i++ ) {
				
				final char word = data.charAt(i);			       
				matcher = pattern.matcher(String.valueOf(word));
				boolean isCharValid = matcher.matches();
				
				if(!isCharValid) { 
					failedChars.add(word);
				}
			}
		}
		return failedChars;
	}
	
	public static void recursiveDelete(File file) {
        if (!file.exists()){
            return;
        }
        if (file.isDirectory()) {
            for (File f : file.listFiles()) {
                recursiveDelete(f);
            }
        }
        file.delete();
    }
}
