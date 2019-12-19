////////////////////////////////////////////////
/// Name: Sidney Seay                        ///
/// CSc 2010, Principles of Computer Science ///
/// Fall 2013                                ///
/// Assignment 9; Histogram Program          ///
/// Submitted:/2013                          ///
////////////////////////////////////////////////
import java.awt.Color;
import java.awt.Graphics;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.swing.text.html.HTMLDocument.HTMLReader.CharacterAction;

public class Histogram {
	/*
	 * @Remarks - Java Main Class
	 */
public static void main(String [] args){

	/*
	 *   NOTE:
	 *   File is read from the C drive folder Temp
	 * 
	 */
	
	int[] letterCounts = new int[26];
    char[] letters = {'A','B','C','D','E','F','G','H','I','J','K','L','M',
	                    'N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
	  
      String inputName = inputFileName();
      String fileData = readTextFile(inputName);
      int totalChars = 0;
      totalChars = countFileChars(fileData, letterCounts, letters, totalChars);
      displayLettersGraph(letterCounts, totalChars, letters, inputName);
  	 
}
/*
 * @Remarks - Method inputFileName - Display text Enter data file name
 * on console, accept user input, and return file name entered as a String
 * @param -  
 */	
 private static String inputFileName() {
	  System.out.println("Enter data file name: ");
      Scanner fileName = new Scanner(System.in);
      return fileName.next();
 }
 /*
  * @Remarks - Method readTextFile - read file input and return file data as 
  * a String. Contain Try Catch method for error handling when reading input file.
  * @param - String fileNamer 
  */	 
 private static String readTextFile(String fileName) {
	 String textContent = "";
	 String emptyString = "";
	 
		FileReader reader = null;
		try {
			File file = new File("c:\\Temp\\" + fileName + ".txt");
			reader = new FileReader(file);
	        char [] chars = new char[(int) file.length()];
	        reader.read(chars);
	        textContent = new String(chars);
	        reader.close();
	        return textContent;
		} catch (Exception e) {
            System.out.println("File name not found");			
		}
		return emptyString;

 }
 /*
  * @Remarks - Method countFileChars - compare each character in input file against array letters. Count each
  * character when a match is found, keep a total count of all valid characters, return total characters read.
  * @param - String fileData, int (array) letterCounter, char (array) letters, int totalChars 
  */	  
 private static int countFileChars(String fileData, int [] letterCounter, char [] letters, int totalChars) {
		char textValue;

		/*
		 *  Verify File contain text data
		 */
		if (fileData.length() > 0) {
			for (int i = 0; i < fileData.length(); i++){
				textValue = fileData.charAt(i);
				textValue = Character.toUpperCase(textValue);
				for (int j = 0; j < 26; j++) {
					if (textValue == letters[j]) {
					      	letterCounter[j]++;
					      	totalChars++;
					}
				}
			}			
		}
		return totalChars;
 }
 /*
  * @Remarks - Method displayLetterGraph - generate history gram graph of characters read from array
  * letterCounters.
  * @param - int (array) letterCounters, int totalChars 
  */ 
 private static void displayLettersGraph(int [] letterCounters, int totalChars, char [] letters, String inputName) {
	 DrawingPanel panelWindow = new DrawingPanel(1050,600);
     Graphics graphic = panelWindow.getGraphics();
     
	 boolean firstPass = true;
     int highestCount = 0;
     int saveCount = 0;
     int height = 0;
     int xPos = 20;
     int yPos = 560;
     for(int i = 0; i < 26; i++){
    	 if (firstPass) {
    		 saveCount = letterCounters[i];
    		 firstPass = false;
    	 }
    	 else {
        	 if(letterCounters[i] > saveCount){
        		 saveCount = letterCounters[i];
        	 }    		 
    	 }
     }
     highestCount = saveCount;
     graphic.drawString("File name: " +inputName, 10, 20);
     String sTotalChars = Integer.toString(totalChars);
     graphic.drawString("Number of letters in File =:" +sTotalChars, 10, 40);

     for(int i = 0; i < 26; i++){
    	 height = (int) 400.0 * letterCounters[i] / highestCount;    	 
        if (highestCount == letterCounters[i]) {
     		graphic.setColor(Color.RED);        	
        }else
        {
     		graphic.setColor(Color.BLUE);        	
        }
        
 		String sCount = String.valueOf(letterCounters[i]);
        graphic.drawString(sCount, xPos, yPos-height-2);
       
 		graphic.fillRect(xPos, yPos-height, 20, height);
		String sValue = String.valueOf(letters[i]);
 		graphic.drawString(sValue, xPos, 580);
 		xPos = xPos + 40; 		

     }     
panelWindow.save("hob.jpg");
panelWindow.save("lipo.jpg");
 
 }

 
}





