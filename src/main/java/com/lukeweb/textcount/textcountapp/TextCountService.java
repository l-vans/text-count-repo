package com.lukeweb.textcount.textcountapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Service
public class TextCountService {

	public ArrayList<String> filterText(@RequestParam("file") MultipartFile file) throws IOException {
	String[] words;
        String line; 
        InputStream inputStream = file.getInputStream(); 
        BufferedReader br =   new BufferedReader(new InputStreamReader(inputStream)); 
        ArrayList<String> finalWords = new ArrayList<String>();
        while((line = br.readLine()) != null) {     	
            words = line.split(" ");
            for (String s : words){
            	s = s.replaceAll("[\\n+\\t+\\s+]",""); //filter tabs, whitespace etc.
            	if (s.length() >= 1) {
            		if(Pattern.matches("(?U)[\\p{Punct}&&[^@&]]",s.substring(0,1))){ //filter first character of string
            			int i = 0;
            			int j = i + 1;
            			while(i <= s.length()-1 && Pattern.matches("(?U)[\\p{Punct}&&[^@&]]",s.substring(i,j)) && s.length() > 1) { //while char equals non-word, filter
            				s = s.substring(j, s.length());
            				i++;
            				j++;
            			}
				
            		}
			for(int i = s.length()-1; i >= 1; i--) { //reverse filtering		
				if((s.charAt(i) == '/' || s.charAt(i) == '!'
					|| s.charAt(i) == '"' || s.charAt(i) == '#' || s.charAt(i) == '$' || s.charAt(i) == '%' 
					|| s.charAt(i) == '\'' || s.charAt(i) == '(' || s.charAt(i) == ')' || s.charAt(i) == '*'
					|| s.charAt(i) == '+' || s.charAt(i) == ',' || s.charAt(i) == '-' || s.charAt(i) == '.'
					|| s.charAt(i) == '/' || s.charAt(i) == ':' || s.charAt(i) == ';' || s.charAt(i) == '<'
					|| s.charAt(i) == '=' || s.charAt(i) == '>' || s.charAt(i) == '>' || s.charAt(i) == '['
					|| s.charAt(i) == '\\' || s.charAt(i) == ']' || s.charAt(i) == '^' || s.charAt(i) == '_'
					|| s.charAt(i) == '`' || s.charAt(i) == '{' || s.charAt(i) == '|' || s.charAt(i) == '}' 
					|| s.charAt(i) == '~') && s.length() >= 1){							
						s = s.substring(0,i);
				}
							
				else {
					break;
				}
							
			}						
					
            	}

            	if(s.length() == 1 && Pattern.matches("(?U)[\\p{Punct}&&[^@&]]",s.substring(0,1))) { //filter out punctuation from word with single length
            		s = s.replace(s.substring(0,1), "");
            		
            	}
              	finalWords.removeAll(Arrays.asList("", null));
            	finalWords.add(s); 
            }
            
        }
        br.close();		
        return finalWords;
    }
	
    public int getCount(ArrayList<String> fWords) { //count words
	int count = 0;
      	for(@SuppressWarnings("unused") String f : fWords) {
    		count++;
    	}
      	return count;
    }
	
    public String getAverageLength(ArrayList<String> fWords, int c) {
    	final DecimalFormat df = new DecimalFormat("0.00");
    	double total = 0;
    	double av = 0;
        for (String s : fWords){
        	double current = s.length();
        	total = total + current;
        }
        
        av = total/c; //find mean of word lengths
        return "Average word length: " + df.format(av);
    }

    public String numberOfIndyWords(ArrayList<String> fWords) { //final word input
    	String finalFrequencies = "";
    	List<Integer> stringLengths = new ArrayList<Integer>();
        Map<Integer, Integer> counts= new HashMap<Integer, Integer>();
    	int current = 0;  	
	    
	for(String f : fWords) { //calculate length of each word
    		current = f.length();
    		stringLengths.add(current);
    		current = stringLengths.size();
    	}
		
	for(Integer s : stringLengths) { //add word counts to counts hashmap
            counts.put(s, counts.getOrDefault(s, 0) + 1);    		
    	}
		
        List<Map.Entry<Integer, Integer> > list = new ArrayList<Map.Entry<Integer, Integer> >(counts.entrySet()); //list to print out word lengths with their frequencies
        for (Map.Entry<Integer, Integer> entry : counts.entrySet()) { 
            finalFrequencies = finalFrequencies + "Number of words of length " + entry.getKey() + " is " + entry.getValue().toString() + System.lineSeparator();
        }
        
        Collections.sort(list, new Comparator<Map.Entry<Integer, Integer> >() //compare values in map to find word length with largest frequency
        {
        	public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2)
            {
        		if (o1.getValue() == o2.getValue()) {
        			return o2.getKey() - o1.getKey();
        		}
        		else {
        			return o2.getValue() - o1.getValue();
        		}
                        
            }
        });	      
        
        List<Integer> fLengths = new ArrayList<Integer>();
        Integer mostFKey = list.get(0).getKey(); 
        Integer mostFValue = list.get(0).getValue();
        fLengths.add(mostFKey); //get the largest frequency
        
        if(list.size() > 1) { 
            for(int f = 1; f <= list.size()-1; f++) {
            	if(list.get(f).getValue() == mostFValue) {
            		fLengths.add(list.get(f).getKey());
            	}
            }        	
        }

 
    	String fs = "The largest word length frequency is " + mostFValue + ", ";
    	String fs1 = fs + "for word lengths of ";
    	String fs2 = fs + "for the word length of ";
    	
    	if(fLengths.size() == 2) {
    		String locF = "";
    		locF = locF + mostFKey + " & ";
    		locF = locF + list.get(1).getKey();
    		finalFrequencies = finalFrequencies + fs1 + locF;
    	}
    	else if (fLengths.size() > 2) {
    		String locF = fs1 + mostFKey; 
    		for (int i = 1; i <= fLengths.size()-1; i++) {
    			locF = locF + "," + fLengths.get(i);
    		}
    		finalFrequencies = finalFrequencies + locF;
    	}
    	else {
    		fs2 = fs2 + list.get(0).getKey();
    		finalFrequencies = finalFrequencies + fs2;
    	}	
    	
    	return finalFrequencies;
    } 
	
}
