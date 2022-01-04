//package com.lukeweb.textcount.textcountapp;
//
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import java.util.regex.Pattern;
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileReader;
//import java.io.InputStreamReader;
//import java.text.DecimalFormat;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.Comparator;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import javax.swing.JFileChooser;
//import java.io.File; 

//@SpringBootApplication
//public class TextCountApplication {
//
//	private static String[] words;
//	private static final DecimalFormat df = new DecimalFormat("0.00");
//	public static void main(String[] args) throws Exception {
//
//        String line; 
//        BufferedReader br = new BufferedReader(new FileReader("data.txt"));   
//        
//        ArrayList<String> finalWords = new ArrayList<String>();
//        while((line = br.readLine()) != null) {  
//          
//        	
//            words = line.split(" ");
//            
//            for (String s : words){
//            	s = s.replaceAll("[\\n+\\t+\\s+]","");
//            	if (s.length() >= 1) {
//            		if(Pattern.matches("(?U)[\\p{Punct}&&[^@&]]",s.substring(0,1))){
//            			int i = 0;
//            			int j = i + 1;
//            			while(i <= s.length()-1 && Pattern.matches("(?U)[\\p{Punct}&&[^@&]]",s.substring(i,j)) && s.length() > 1) {
//            				s = s.substring(j, s.length());
//            				i++;
//            				j++;
//            			}
//            		}
//
//						for(int i = s.length()-1; i >= 1; i--) {
//							
//							if((s.charAt(i) == '/' || s.charAt(i) == '!'
//									|| s.charAt(i) == '"' || s.charAt(i) == '#' || s.charAt(i) == '$' || s.charAt(i) == '%' 
//									|| s.charAt(i) == '\'' || s.charAt(i) == '(' || s.charAt(i) == ')' || s.charAt(i) == '*'
//									|| s.charAt(i) == '+' || s.charAt(i) == ',' || s.charAt(i) == '-' || s.charAt(i) == '.'
//									|| s.charAt(i) == '/' || s.charAt(i) == ':' || s.charAt(i) == ';' || s.charAt(i) == '<'
//									|| s.charAt(i) == '=' || s.charAt(i) == '>' || s.charAt(i) == '>' || s.charAt(i) == '['
//									|| s.charAt(i) == '\\' || s.charAt(i) == ']' || s.charAt(i) == '^' || s.charAt(i) == '_'
//									|| s.charAt(i) == '`' || s.charAt(i) == '{' || s.charAt(i) == '|' || s.charAt(i) == '}' 
//									|| s.charAt(i) == '~') && s.length() >= 1){							
//										s = s.substring(0,i);
//									}
//							
//							else {
//								break;
//							}
//							
//						}
//						
//					
//            	}
//
//            	if(s.length() == 1 && Pattern.matches("(?U)[\\p{Punct}&&[^@&]]",s.substring(0,1))) {
//            		s = s.replace(s.substring(0,1), "");
//            		
//            	}
//              	finalWords.removeAll(Arrays.asList("", null));
//            	finalWords.add(s); 
//            }
//            
//        }
//        br.close();
//        int count = getCount(finalWords);
//        System.out.println("Word count = " + count); 
//        System.out.println("Average word length: " + getAverageLength(finalWords,count)); 
//        numberOfIndyWords(finalWords); 
//        
//
//	}
//	public static int getCount(ArrayList<String> fWords) {
//		int count = 0;
//      	for(@SuppressWarnings("unused") String f : fWords) {
//    		count++;
//    	}
//      	return count;
//	}
//    public static String getAverageLength(ArrayList<String> fWords, int c) {
//        
//    	double total = 0;
//    	double av = 0;
//        for (String s : fWords){
//        	double current = s.length();
//        	total = total + current;
//        }
//        
//        av = total/c;
//        return df.format(av);
//    }
//    
//    public static void numberOfIndyWords(ArrayList<String> fWords) {
//    	List<Integer> stringLengths = new ArrayList<Integer>();
//        Map<Integer, Integer> counts= new HashMap<Integer, Integer>();
//    	int current = 0;
//    	
//		for(String f : fWords) {
//    		current = f.length();
//    		stringLengths.add(current);
//    		current = stringLengths.size();
//    	}
//		
//		for(Integer s : stringLengths) {
//            counts.put(s, counts.getOrDefault(s, 0) + 1);    		
//    	}
//		
//        List<Map.Entry<Integer, Integer> > list = new ArrayList<Map.Entry<Integer, Integer> >(counts.entrySet());
//        for (Map.Entry<Integer, Integer> entry : counts.entrySet()) {
//            System.out.println("Number of words of length " + entry.getKey() + " is " + entry.getValue().toString());
//        }
//        
//        Collections.sort(list, new Comparator<Map.Entry<Integer, Integer> >() 
//        {
//        	public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2)
//            {
//        		if (o1.getValue() == o2.getValue()) {
//        			return o2.getKey() - o1.getKey();
//        		}
//        		else {
//        			return o2.getValue() - o1.getValue();
//        		}
//                        
//            }
//        });	      
//        
//        List<Integer> fLengths = new ArrayList<Integer>();
//        Integer mostFKey = list.get(0).getKey();
//        Integer mostFValue = list.get(0).getValue();
//        fLengths.add(mostFKey);
//        
//        if(list.size() > 1) {
//            for(int f = 1; f <= list.size()-1; f++) {
//            	if(list.get(f).getValue() == mostFValue) {
//            		fLengths.add(list.get(f).getKey());
//            	}
//            }        	
//        }
//
// 
//    	String fs = "The largest word length frequency is " + mostFValue + ", ";
//    	String fs1 = fs + "for word lengths of ";
//    	String fs2 = fs + "for the word length of ";
//    	
//    	if(fLengths.size() == 2) {
//    		String locF = "";
//    		locF = locF + mostFKey + " & ";
//    		locF = locF + list.get(1).getKey();
//    		System.out.println(fs1 + locF);
//    	}
//    	else if (fLengths.size() > 2) {
//    		String locF = fs1 + mostFKey; 
//    		for (int i = 1; i <= fLengths.size()-1; i++) {
//    			locF = locF + "," + fLengths.get(i);
//    		}
//    		System.out.println(locF);
//    	}
//    	else {
//    		fs2 = fs2 + list.get(0).getKey();
//    		System.out.println(fs2);
//    	}	
//    } 
//    
//}
