/**
 * Author: Rohtak Patwardhan
 * Email: rspatwardha2@wisc.edu
 * Citations: ChatGPT helped with 200 random words and adding colors
 * 
 */

import java.util.Scanner; 
import java.util.Random; 
import java.util.ArrayList; 

/**
 * This class contains the code for playing Wordle in the terminal.
 * Bugs: none known
 *
 * @author Rohtak Patwardhan
 * 
 */

public class TerminalWordle {



    /**
     * Main method used to simply call the instructions and playGame methods. 
     * This format was intentionally chosen for modularity and complexity.
     * @param args 
     * 
     */

    public static void main(String [] args) { 

        printInstructions(); 
        playGame(); 
        
    }

    /**
     * This method contains a Word Bank that has 200 AI-generated random words.
     * @return returns a word bank to the playGame method
     * 
     */

    public static String[] wordBank() {

        return new String[] {
            "APPLE","BRAIN","CRANE","PLANT","GRASS",
            "STONE","BRICK","TRAIL","SHINE","GHOST",
            "FLASH","SHARE","PRIME","GLASS","FRAME",
            "CLOUD","WATER","FROST","SNAKE","TIGER",
            "MONEY","SMILE","PAPER","QUICK","SWEET",
            "HEART","LIGHT","NIGHT","EARTH","SOUND",
            "ROUND","SOUTH","NORTH","EAGLE","SPICE",
            "SUGAR","BREAD","FRUIT","GRAPE","MANGO",
            "LEMON","PEACH","BERRY","OLIVE","STEEL",
            "METAL","COPPER","SILKY","BLEND","CLEAN",
            "CLEAR","MATCH","CATCH","THINK","TEACH",
            "LEARN","WRITE","READS","DRIVE","RIDER",
            "MOTOR","ROBOT","LASER","SOLAR","LUNAR",
            "RIVER","OCEAN","SHORE","BLOOM","LEAFY",
            "WOODS","FIELD","HOUSE","ROOMS","CHAIR",
            "TABLE","FLOOR","CELEB","STARS","NOBLE",
            "ROYAL","MAGIC","SPELL","POWER","GIANT",
            "SMALL","WHEEL","SPEED","SHIFT","BRAKE",
            "BUILD","MAKER","CRAFT","SKILL","TOOLS",
            "SMART","LOGIC","ARRAY","CLASS","OBJECT",
            "VALUE","INPUT","DEBUG","LOOPS","STACK",
            "QUEUE","ERROR","CLOSE","OPENN","BEGIN",
            "FINAL","TRUST","FAITH","LUCKY","HAPPY",
            "ANGRY","TIRED","BOOST","BRAVE","UNITY",
            "FOCUS","SHARP","BLUNT","POINT","SCORE",
            "LEVEL","QUEST","MAPLE","EMBER","FLAME",
            "RANGE","SLOPE","DELTA","SIGMA","TANGO",
            "DELVE","FORCE","SHAPE","COLOR","PAINT",
            "MUSIC","NOTES","RHYME","SONIC","VIBES",
            "GAMER","PIXEL","BOARD","CARDS","DREAM",
            "GUIDE","START","ENTER","ALERT","CHECK",
            "CHAIN","PLANE","TRAIN","BASIC","SUPER",
            "CROWN","WORLD","SPACE","ALIEN","HOVER",
            "STORM","WINDY","CHILL","SPARK","CABLE",
            "LATCH","NERVE","SCOPE","FRAME","TRACE",
            "MODEL","FOAMY","SHOUT","WHISP","GRIND",
            "SHEET","METRO","URBAN","RUSTY","SHOVE",
            "CLIMB","REACH","PLUCK","GRASP","SHIFT"
        };
    }

    /**
     * Uses the Random class to generate a random integer between 0 and the 
     * array length, inclusive and exclusively respectively. 
     * @param bank (takes the bank parameter, which is an array of words)
     * @return (returns a random word from the bank to playGame)
     * 
     */ 

    public static String pickRandomWord(String[] bank) { 
        
        Random r = new Random();
        String secret = bank[r.nextInt(bank.length)]; 

        return secret; 

    }

    /**
     * Used to print the board's appearance into the terminal. 
     * The board displays how the random / secret word contains 5 characters. 
     * @param guesses (stores the guesses the user takes when playing Wordle)
     * @param evaluation (stores how each each letter matches the secret word)
     * 
     */ 

    public static void printBoard(ArrayList<String> guesses, int[][] evaluation) { 

        System.out.println("====== BOARD ======");
        
        for (int i = 0; i < guesses.size(); i++) {
            printGuess(guesses.get(i), evaluation[i]);
        }

        for (int i = guesses.size(); i < 6; i++) {
            System.out.println("     _ _ _ _ _     ");
        }

        System.out.println("==================="); 

    }
    
    /**
     * The following static objects define the colors used in the program to 
     * differentiate between whether a letter is correct, incorrect, or used.
     *  
     */ 

    public static String GREEN = "\u001B[42m";
    public static String YELLOW = "\u001B[43m";
    public static String GREY = "\u001B[100m";
    public static String RESET = "\u001B[0m";

    /**
     * The printGuess method is used to print the character and the associated
     * color, depending on how the word the user entered matches the answer.
     * @param guess (used to store the guess of the user)
     * @param result (array used to associate guess result with colors)
     * 
     */ 

    public static void printGuess(String guess, int[] result) {

        for (int i = 0; i < 5; ++i) { 

            char c = guess.charAt(i);  

            if (result[i] == 2) {
                System.out.print(GREEN + " " + c + " " + RESET + " ");
            } 
            else if (result[i] == 1) {
                System.out.print(YELLOW + " " + c + " " + RESET + " ");
            } 
            else {
                System.out.print(GREY + " " + c + " " + RESET + " ");
            }
        }

        System.out.println();

    }

    /**
     * The playGame method handles the main gameplay loop for Terminal Wordle.
     * 
     * It handles the following tasks: 
     * 
     * 1. Selects a secret random word 
     * 2. Prompts user to enter a word
     * 3. Checks user's guess to see if it's 5 letters long
     * 4. Evaluates each guess against the secret word
     *  - (2) Green, correct position and letter
     *  - (1) Yellow, incorrect position, correct letter
     *  - (0) Gray, incorrect letter
     * 5. Updates the guesses list and evaluation array for printBoard
     * 6. Ends the game if the user runs out of guesses or is correct
     * 
     */ 

    public static void playGame() { 

        String[] bank = wordBank(); // get the list of words from wordBank 
        String secret = pickRandomWord(bank); // pick a random word 

        Scanner scnr = new Scanner(System.in); 

        ArrayList<String> guesses = new ArrayList<>(); 
        int[][] evaluation = new int[6][5];

        int tries = 0; 

        while (tries < 6) { // max guesses = 6 
            
            printBoard(guesses,evaluation);

            String guess = scnr.next();

            while (guess.length() < 5 || guess.length() > 5 ) { // checks input
                System.out.println("Please enter a new word");
                guess = scnr.next(); 
            }
            
            boolean inputInvalid = true; 

            while (inputInvalid) { 
                inputInvalid = false; 
                for (int i = 0; i < 5; i++) {
                    if (guess.charAt(i) < 65 || guess.charAt(i) > 122) { 
                        System.out.println("Please enter a new word"); 
                        guess = scnr.next(); 
                        inputInvalid = true;    
                    }
                }
                for (int j = 0; j < 5; j++) {
                    if (guess.charAt(j) <= 96 && guess.charAt(j) >= 91) {
                        System.out.println("Please enter a new word"); 
                        guess = scnr.next();
                        inputInvalid = true;
                    }
                }

            }
            
            guess = guess.trim().toUpperCase(); // stores guess
        
 
            int[] result = new int[5]; 
            
            // First pass: green
            for (int i = 0; i < 5; i++) { 
                if (guess.charAt(i) == secret.charAt(i)) { 
                    result[i] = 2; 
                }
            }

            // Second pass: yellow
            for (int i = 0; i < 5; i++) { 
                char c = guess.charAt(i); 
                if (result[i] != 2 && secret.contains("" + c)) { 
                    result[i] = 1; 
                }
            }

            // add guess and result to the guesses and evaluation structures
            guesses.add(guess);
            evaluation[tries] = result;
            tries++; // increment tries 

            if (guess.equals(secret)) {
                printBoard(guesses, evaluation);
                System.out.println("CONGRATULATIONS! You guessed the word!");
                return; 
            }
        }

        // If all 6 tries are used
        printBoard(guesses, evaluation);
        System.out.println("GAME OVER! The word was: " + secret);

    }

    /**
     * Prints Instructions for the user to follow 
     * Called by main method
     * 
     */ 
    
    public static void printInstructions() {

        System.out.println("-----------------------------------");
        System.out.println("          TERMINAL WORDLE          ");
        System.out.println("-----------------------------------");
        System.out.println("         INSTRUCTIONS BELOW:       ");
        System.out.println("-----------------------------------");
        System.out.println("1. You have 6 GUESSES");
        System.out.println("2. THE WORD is 5 LETTERS LONG ");
        System.out.println("3. GRAY = LETTER NOT USED IN THE WORD");
        System.out.println("4. YELLOW = LETTER USED IN THE WORD");
        System.out.println("5. GREEN = CORRECT LETTER & POSITION");
        System.out.println("-----------------------------------");        

    }
}
