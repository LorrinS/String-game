/*
Lorrin Shen 
Apr 6 2020
String Game Assignment : Hangman
ICS3U7-01 Ms. Strelkovska
*/
import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;

public class ShenL_StringGame{
	
	static Scanner sc = new Scanner(System.in);
	
	public static void main(String args[])throws Exception{
		start();
		}
		
	public static void title(){ //Ascii art of the title
		line(100);
		System.out.println("  _    _                                         ");
		System.out.println(" | |  | |                                        ");
		System.out.println(" | |__| | __ _ _ __   __ _ _ __ ___   __ _ _ __  ");
		System.out.println(" |  __  |/ _` | '_ \\ / _` | '_ ` _ \\ / _` | '_ \\ ");
		System.out.println(" | |  | | (_| | | | | (_| | | | | | | (_| | | | |");
		System.out.println(" |_|  |_|\\__,_|_| |_|\\__, |_| |_| |_|\\__,_|_| |_|");
		System.out.println("                      __/ |                      ");
		System.out.println("                     |___/                       ");
		line(100);
	}

	public static void start()throws Exception{ 
		String menu="";
		String lvl ="";
		String cat ="";
		
		while( (!(menu.equals("1"))) && (!(menu.equals("2"))) && (!(menu.equals("3"))) ){ //take in user input at menu
			System.out.println("1. Start / Continue \t2. Instructions / Rules \t3. Exit ");
			System.out.print("input: ");
			menu = sc.nextLine();
			menu = menu.trim();
			if(menu.equals("1")){ //if user enters start/continue show title, ask for level and category choice, then start the game
				title();
				lvl = level(); 
				cat = category();
				play(lvl, cat);
			}
			else if(menu.equals("2")){ //explain the game to user 					
				instructions();
			}
			else if(menu.equals("3"))
				System.exit(0);
			else if(menu.equals(""))
				System.out.println("No Input Detected. Enter 1, 2, or 3.");
			else
				System.out.println("Invalid Input. Enter 1, 2, or 3.");
		}
	}
	
	public static void instructions()throws Exception{
		System.out.println("");
		System.out.format("%-30s -%-15s %n","How The Game Works:","A random word gets generated according to the difficulty and category chosen.");
		System.out.format("%-30s -%-15s %n","","You, the player, are trying to uncover the word.");
		System.out.format("%-30s -%-15s %n","","Everytime you, the player, guess a right letter, every instance of that letter in the word will be revealed.");
		System.out.format("%-30s -%-15s %n","","You, the player, may also guess the exact word.");
		System.out.println("");
		System.out.format("%-30s -%-15s %n","Win Factor:","Uncover all the letters of the mystery words.");
		System.out.println("");
		System.out.format("%-30s -%-15s %n","Lose factor: ","Have all the body parts of the stick figure drawn(head, body, arms, legs, feet, hands).");
		System.out.println("");
		System.out.format("%-30s -%-15s %n","Methods Available:","Guess the exact word completely. all types of characters will count.");
		System.out.format("%-30s -%-15s %n","","Guess letter by letter, anything outside of the alphabet does not count.");
		System.out.println("");
		System.out.format("%-30s -%-15s %n","Penalties And Exceptions:","Every wrong letter or wrong word guessed results in a body part.");
		System.out.format("%-30s -%-15s %n","","10 wrong guesses and you lose.");
		System.out.format("%-30s -%-15s %n","","When guessing per letter, anything outside the alphabet will not count.");
		System.out.format("%-30s -%-15s %n","","When whole words, make sure your input is your guess character per character, anything outside the abc included.");
		System.out.println("");
		System.out.format("%-30s -%-15s %n","Notes:","When guessing whole words make sure to include all punctuation(apostrophes, spaces, etc.).");
		System.out.format("%-30s -%-15s %n","","There will always be a list of all the letters that have yet to be guessed. ");
		System.out.println("");
		start();
	}

	public static String level(){ //user chooses the difficulty they want to play at
		String lvl = "";
		while( (!(lvl.equals("1"))) || (!(lvl.equals("2"))) || (!(lvl.equals("3"))) ){
			System.out.println("DIFFICULTY: ");
			System.out.println("1. Easy(2-5 letters) \t2. Medium(5-8 letters) \t3. Hard(9+ letters)");
			System.out.print("input: ");
			lvl = sc.nextLine();
			lvl=lvl.trim();
			if(lvl.equals("1") || lvl.equals("2") || lvl.equals("3")) //what the users choice is
				return lvl;
			else if(lvl.equals("")) //if the user input enter key right away
				System.out.println("No Input Detected. Enter 1, 2, or 3.");
			else //if the user does not input 1 2 or 3
				System.out.println("Invalid Input. Enter 1, 2, or 3.");
		}
		return lvl;
	}
	
	public static String category(){ //user chooses a category of words they want to play at
		String cat = "";
		while( (!(cat.equals("1"))) || (!(cat.equals("2"))) || (!(cat.equals("3"))) ){
			System.out.println("CATEGORIES: ");
			System.out.println("1. Country Names \t2. Colours \t3. the Elements of the Periodic Table");
			System.out.print("input: ");
			cat = sc.nextLine();
			cat = cat.trim();
			if( cat.equals("1") || cat.equals("2") || cat.equals("3") ) //what the users choice is
				return cat;
			else if(cat.equals("")) //if the user input enter key right away
				System.out.println("No Input Detected. Enter 1, 2, or 3.");
			else // if the user does not input 1 2 or 3
				System.out.println("Invalid Input. Enter 1, 2, or 3.");
		}
		return cat;
	}		
	
	public static void play(String level, String category)throws Exception{
		String word = word(level, category);
		word = word.toLowerCase();
		String temp = word.replaceAll("[a-zA-Z]","_"); //http://www.regular-expressions.info/charclass.html //it replaces chars in between a-z and A-Z with "_"
		String guess ="";
		String abc="a b c d e f g h i j k l m n o p q r s t u v w x y z";
		String letter ="";
		String progress = "";
		int wrong =0;
		int correct =0;
		ArrayList<String> wordGuesses = new ArrayList<String>(); //https://www.w3schools.com/java/java_arraylist.asp
		boolean check = false;
		
		line(100);
		
		//System.out.println(word); //for testing purposes
		
		System.out.println("LET THE GAMES BEGIN!");
		
		while (true){
			
			if(temp.equals(word) || wrong==10) //if the user uncovers the whole word or reaches 10 incorrect guesses break loop and go to gameover
				break;
			
			hangman(wrong); //print the hangman drawing
			
			check=false; //reset check var
			progress=""; //reset the progress var
			for (int i =0; i<temp.length();i++){ //add spaces in between "_" to make the word length more visible
				if(i+1==temp.length())
					progress+=temp.charAt(i);
				else if(String.valueOf(temp.charAt(i))==" ")
					progress+="  ";
				else
					progress+=temp.substring(i,i+1)+" ";
			}
			
			System.out.println(progress+"\n"); //the user progress (ex: a _ _ b _ _ )
			
			//user guess
			System.out.println("letters that have not been guessed yet:\n"+abc);
			System.out.println("Correct Guesses / Wrong Guesses:  "+correct+" / "+wrong); 
			System.out.print("Guess number "+(wrong+correct)+": ");
			guess = sc.nextLine();
			guess=(guess.toLowerCase()).trim();
			line(100);
			
			if(guess.equals(""))
				System.out.println("No Input Detected.\n");
				
			if(guess.length()>1){ //if user enters a word
			
				if(guess.equals(word)){ //if the user guesses the mystery word right
					System.out.println(guess+" is the mystery word.\n");
					temp=guess;
					correct+=1;
					gameover(word,temp,wrong,correct);
				}
				
				for(int i =0; i<wordGuesses.size();i++){ //check if the user already guessed this word
					if(guess.equals(wordGuesses.get(i))){ 
						System.out.println("You already guessed this word. Please try again.\n");
						check=true;
					}
				}
				
				if( (!(guess.equals(word))) && check==false){ //if the word the user guessed is not the mystery word and has not been guessed yet
					System.out.println(guess+" is not the mystery word.\n");
					wrong+=1;
				}
				wordGuesses.add(guess);
			}
			
			
			if(guess.length()==1 && (guess.charAt(0)<97 || guess.charAt(0)>122)) //user did not guess a letter
				System.out.println(guess+" is not a letter. Please try again.\n");
				
			else if(guess.length()==1 && !(abc.contains(guess))) //user has already guessed this letter
				System.out.println("You already guessed this letter. Please try again.\n");
			
			temp=""; //since temp var is always built anew everytime, reset it
			
			if(guess.length()==1 && abc.contains(guess)){ //user guessed a new letter
				abc=abc.replace(guess," "); //remove the letter that the user guessed from the list of letters that have not been guessed yet.
				
				if(word.contains(guess)){ //if the user guessed a letter that is in the mystery word
					System.out.println("The letter "+guess+" is in the mystery word.\n");
					correct+=1;
				}
				
				else{ //if the user guessed a letter that is not in the mystery word
					System.out.println("The letter "+guess+" is not in the mystery word.\n");
					wrong+=1;
				}                  
			}
			for (int i = 0; i<word.length(); i++){ //print user progress 
				letter=String.valueOf(word.charAt(i)); //letter is each individual char of the mystery word
				
				if(letter.equals(" "))
					temp+=letter;
				else if(abc.contains(letter)) //check with list of letters that have not been guessed, if the letter was not guessed replace it with "_" in temp
					temp+="_";
					
				else //if the letter has been guessed already reveal the letter as is in temp
					temp+=letter;
				}
		}
		
		gameover(word,temp,wrong,correct);
		
		}
		
	public static String word(String level, String category) throws Exception{
/* words were taken from enchanted learning's website according to their category
Words in each txt file:
country1 = 35 names
country2 = 91 names
country3 = 63 names
colours1 = 50 colours
colours2 = 47 colours
colours3 = 18 colours
elements1 = 10 elements
elements2 = 68 elements
elements3 = 40 elements */

		String word="";
		int randomNum=0;
		int numOfWords =0;
		String lvl = "";
		String cat = "";
		String fileName = "";
		Scanner scFile= null;
		String choice = level+category;
		
		switch(choice){ //according to level and category determine fileName and the number of words in them
			case "11": fileName="country1.txt"; numOfWords=35; break;
			case "12": fileName="colours1.txt"; numOfWords=50;  break;
			case "13": fileName="elements1.txt"; numOfWords=10;  break; 
			case "21": fileName="country2.txt"; numOfWords=91; break;
			case "22": fileName="colours2.txt"; numOfWords=47;  break; 
			case "23": fileName="elements2.txt"; numOfWords=68;  break;
			case "31": fileName="country3.txt"; numOfWords=63; break;
			case "32": fileName="colours3.txt"; numOfWords=18;  break;
			case "33": fileName="elements3.txt"; numOfWords=40;  break;
		}
		scFile= new Scanner(new File(fileName));
		randomNum= (int)(Math.random()*numOfWords)+1; //find random number within the amount of words in the 
		
		for(int i=0; i<randomNum; i++) //find random word 
			word = scFile.nextLine();
		
		scFile.close();
		word=word.trim();
		return word;
	}
		
	public static void hangman(int wrong){
		//initial hangman drawing without any body parts
		String line1= "  _______\n";
		String line2= " |/      |\n";
		String line3= " | \n";
		String line4= " | \n";
		String line5= " | \n";
		String line6= " | \n";
		String line7= " | \n";
		String line8= "_|___ \n";
		
		for (int i =0; i<wrong+1;i++){ //add the body parts according to how many incorrect their are
			switch(i){
				case 0: break;
				case 1: line3= " |       O\n"; break; //head
				case 2: line4= " |       |\n"; 
						line5= " |       | \n"; break; //body
				case 3: line4= " |       |\\\n"; break; //right arm
				case 4: line4= " |      /|\\\n"; break; //left arm
				case 5: line6= " |        \\\n"; break; //right leg
				case 6: line6= " |      / \\\n"; break; //left leg
				case 7: line4= " |      /|\\_\n"; break; //right hand
				case 8: line4= " |     _/|\\_\n";break; //left hand
				case 9: line6= " |      / \\_\n"; break; //right foot
				case 10: line6= " |     _/ \\_\n"; break; //left foot Game Over
			}
		}
		
		System.out.println(line1+line2+line3+line4+line5+line6+line7+line8);
	}
	public static void gameover(String word, String temp, int wrong, int correct)throws Exception{
		double accuracy = Math.round( ((correct*100.0)/(wrong+correct)) *100.0) /100.0; 
		
		hangman(wrong);
		
		System.out.println("Game Over.");
		System.out.println("The mystery word was: "+word);
		System.out.println("Correct Guesses / Total Guesses:  "+correct+" / "+(wrong+correct));
		System.out.println("Guessing accuracy: "+accuracy+"%");
		
		
		if (wrong==10) //if the user ended up getting 10 incorrect guesses
			System.out.println("Unfortunately you could did not guess the word.");
		
		if(temp.equals(word)) //if the user uncovered the mystery word
			System.out.println("You uncovered the mystery word! Congratulations! You Win!");
		
		line(100);
		
		start(); //loop the game, ask user if they want to continue
	}
	public static void line(int times){ //print a line of x amount of *'s to seperate text
		String out = "";
		for(int i = 0; i<times ;i++){
			out+="*";
		}
		System.out.println("\n"+out+"\n");
	}
}
