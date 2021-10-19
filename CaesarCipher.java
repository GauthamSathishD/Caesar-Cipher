/*Gautham Sathish - Caesar Cipher Assignment
The purpose of this assignment is to 
*/
package caesarcipher;

import java.util.Scanner;

public class CaesarCipher {

    public static String code(String message, int shiftNum) {//METHOD TO ENCODE/DECODE MESSAGES

        String alphabet = "abcdefghijklmnopqrstuvwxyz";//create a string to store lower-case alphabet
        String upAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";//create a string to store upper-case alphabet
        int length = message.length() - 1;//subtract 1 from length of string because .length() isn't zero-indexed and charAt is
        int letter;//store the old letter's location
        char newLetter = 0;//store new letter
        int position;//store position of new letter
        int newPosition = 0;//store position of new letter
        String result = "";//store final encoded/decoded result

        for (int c = 0; c <= length; c++) {//run while there are still characters in the message
            if (alphabet.indexOf(message.substring(c, c + 1)) == -1 && upAlphabet.indexOf(message.substring(c, c + 1)) == -1) {
                result = result + message.substring(c, c + 1);
            } else {//if the character is a letter

                letter = message.charAt(c);//get the letter that needs to be decoded/encoded from the message

                boolean upper;//boolean to check whether or not a letter is upper case
                upper = Character.isUpperCase(letter);//if the letter in the message is upper case, upper will be true

                if (upper == false) {//if upper is false (the letter is lower case)
                    position = alphabet.indexOf(letter);//find where the letter is in the alphabet
                    newPosition = alphabet.indexOf(letter) + shiftNum;//find out where the encoded/decoded letter's position in the alphabet is

                    if (newPosition > 25) {//if the position is greater than 25
                        newPosition = newPosition - 26;//subtract 26, because there isn't a 26th or greater character in the alphabet string
                    } else if (newPosition < 0) {//if the position is less than 0
                        newPosition = newPosition + 26;//add 26 because there isn't a -1 or less character in the alphabet string
                    }

                    newLetter = alphabet.charAt(newPosition);//let newLetter be from the lower case alphabet

                } else if (upper == true) {//if upper is true (the letter is upper case)
                    position = upAlphabet.indexOf(letter);//find where the letter is in the alphabet
                    newPosition = upAlphabet.indexOf(letter) + shiftNum;//find out where the encoded/decoded letter's position in the alphabet is

                    if (newPosition > 25) {//if the position is greater than 25
                        newPosition = newPosition - 26;//subtract 26, because there isn't a 26th or greater character in the alphabet string
                    } else if (newPosition < 0) {//if the position is less than 0
                        newPosition = newPosition + 26;//add 26 because there isn't a -1 or less character in the alphabet string
                    }

                    newLetter = upAlphabet.charAt(newPosition);//let newLetter be from the upper case alphabet

                }

                result = result + newLetter;//add the new decoded/encoded letter to the result
            }
        }

        return result;//return result to main to be outputted
    }

    public static String[] bruteForce(String message) {//METHOD TO AUTOMATICALLY FIND OUT WHICH DECODED MESSAGE IS THE ORIGINAL

        String result;//store final encoded/decoded result
        String decoded = "";//store most likely to be original message
        int shifted = 0;//number that the message was shifted by
        String[] decodedMessages;//create an array to store decoded messages
        decodedMessages = new String[26];//let it have 26 locations to store the 26 decoded versions of the message

        for (int shiftNum = 0; shiftNum >= -25; shiftNum--) {//loop so that the message is shifted 26 times, from a shift of 0 to a shift of 25
            result = "";//reset result back to ""
            result = code(message, shiftNum);//call encode/decode method to decode the message
            if (decoded.equals("")) {
                if ((result.contains("this")) || (result.contains("THIS")) || (result.contains(" AND ")) || (result.contains("MY ")) || (result.contains(" are ")) || (result.contains(" and ")) || (result.contains(" is ")) || (result.contains(" a ")) || (result.contains("the")) || (result.contains("you")) || (result.contains(" an ")) || (result.contains(" we ")) || (result.contains("We ")) || (result.contains("week")) || (result.contains("new")) || (result.contains(" plan ")) || (result.contains("Plan ")) || (result.contains("one")) || (result.contains("two")) || (result.contains("three")) || (result.contains(" four ")) || (result.contains(" five ")) || (result.contains("six")) || (result.contains("seven")) || (result.contains("eight")) || (result.contains("nine")) || (result.contains(" ten ")) || (result.contains("eleven")) || (result.contains("twelve")) || (result.contains(" fifteen ")) || (result.contains(" thirty ")) || (result.contains(" forty ")) || (result.contains(" after ")) || (result.contains("today")) || (result.contains(" tomorrow ")) || (result.contains(" instructions ")) || (result.contains(" command ")) || (result.contains(" next ")) || (result.contains("Today ")) || (result.contains("Tomorrow ")) || (result.contains(" every ")) || (result.contains("people")) || (result.contains("message")) || (result.contains("everywhere")) || (result.contains("location")) || (result.contains("night")) || (result.contains(" day ")) || (result.contains("time")) || (result.contains("Hi ")) || (result.contains("Hi")) || (result.contains("name")) || (result.contains("Hello")) || (result.contains("hello")) || (result.contains("good")) || (result.contains("How ")) || (result.contains("Why ")) || (result.contains("listen")) || (result.contains("great")) || (result.contains("Greetings")) || (result.contains("hey")) || (result.contains(" bad ")) || (result.contains("secret")) || (result.contains("words")) || (result.contains("here")) || (result.contains("there"))) {
                    //above: check for a few common words used in English to see if any of the results are likely to be the original message
                    decoded = result;//let decoded equal the most likely original message
                    shifted = shiftNum;//store the number that shifted the message to the original message in shifted
                }
            }

            decodedMessages[shiftNum * -1] = result;//store the decoded message in its corresponding position (according to number of shifts) in the array
        }
        System.out.println("The best decode was with key " + (shifted * -1));//tell user what the original message was shifted by
        System.out.println("Decoded message is: " + decoded);//print decoded message
        System.out.println(" ");//add a space to separate the result from the different decoded versions
        return decodedMessages;//return the array of decoded strings to main
    }

    public static void main(String[] args) {

        Scanner keyboard = new Scanner(System.in);

        String option = "";//create an option string that stores what the user wants to do
        String decodedMessages[];//create an array of strings so that the bruteForce method can return an array of the decoded strings to main
        decodedMessages = new String[26];//make the size 26 to store the 26 decoded versions of the encoded message for the bruteForce method

        while (!"q".equals(option)) {//while the user has not entered "q" (quit) as an option

            String message = "";//create a string to store the encoded/decoded message that the user inputs
            String result;//create a string to store the encoded/decoded message that is outputted
            int shiftNum = 0;//store the user's desired number to shift the message

            System.out.print("Encode(e) Decode(d) BruteForce(b) Quit(q): ");//show user all options
            option = keyboard.nextLine();//get the option from the user

            if ("e".equals(option)) {//if the user wants to encode the message
                System.out.print("Phrase To Encode: ");//ask for message
                message = keyboard.nextLine();//get message from user
                while (message.equals("")) {
                    System.out.print("Invalid entry. Enter a string: ");
                    message = keyboard.nextLine();//store number of shifts in shiftNum
                }
                System.out.print("Shift Right By: ");//ask for number of shifts
                boolean invalidInput = true;
                do {
                    try {
                        shiftNum = keyboard.nextInt();//store number of shifts in shiftNum
                        while (shiftNum > 25 || shiftNum < 0) {
                            System.out.print("Enter a number between 0 and 25. Shift Right By: ");
                            shiftNum = keyboard.nextInt();//store number of shifts in shiftNum
                        }
                        invalidInput = false;
                    } catch (Exception e) {
                        System.out.print("Enter a number between 0 and 25. Shift Right By: ");
                        keyboard.nextLine();
                    }
                } while (invalidInput);
                result = code(message, shiftNum);//call the code method to encode the message
                System.out.println("The encoded message is: " + result);//print the result
                System.out.println("--------------------------------------------------------------------------------------------------");//add a line to separate from the next use
                System.out.println(" ");//add a space to separate this from the start of the next option
                keyboard.nextLine();

            } else if ("d".equals(option)) {//if the user wants to decode the message

                System.out.print("Phrase To Decode: ");//ask for message
                message = keyboard.nextLine();//get message from user
                while (message.equals("")) {
                    System.out.print("Invalid entry. Enter a string: ");
                    message = keyboard.nextLine();//store number of shifts in shiftNum
                }
                System.out.print("Shift Left By: ");//ask for number of shifts
                boolean invalidInput = true;
                do {
                    try {
                shiftNum = keyboard.nextInt();//store number of shifts in shiftNum
                while (shiftNum > 25 || shiftNum < 0) {
                    System.out.print("Enter a number between 0 and 25. Shift Left By: ");
                    shiftNum = keyboard.nextInt();//store number of shifts in shiftNum
                }
                shiftNum = shiftNum * (-1);
                invalidInput = false;
                    } catch (Exception e) {
                        System.out.print("Enter a number between 0 and 25. Shift Left By: ");
                        keyboard.nextLine();
                    }
                } while (invalidInput);
                result = code(message, shiftNum);//call code method to decode the message
                System.out.println("The decoded message is: " + result);//print the result
                System.out.println("--------------------------------------------------------------------------------------------------");//add a line to separate from the next use
                System.out.println(" ");//add a space to separate this from the start of the next option
                keyboard.nextLine();

            } else if ("b".equals(option)) {//if the user wants to automatically decode the message

                System.out.print("Phrase To Brute Force: ");//ask for message
                message = keyboard.nextLine();//get message from user
                while (message.equals("")) {
                    System.out.print("Invalid entry. Enter a string: ");
                    message = keyboard.nextLine();//store number of shifts in shiftNum
                }
                decodedMessages = bruteForce(message);//call the bruteForce method to get the array of decoded string
                for (int c = 0; c <= 25; c++) {//count from 0 to 25 so that all the positions in the decodedMesssages array can be printed out for the user to see
                    if (c <= 9) {//if the number does not have two digits, add an extra space so that the printed versions line up
                        System.out.println("For shift of " +c+", decoded is: "+decodedMessages[c]);//print out version
                    } else {//don't add an extra space if the shiftNumber has two digits
                        System.out.println("For shift of " +c+", decoded is: "+decodedMessages[c]);
                    }
                }
                

                System.out.println("--------------------------------------------------------------------------------------------------");//add a line to separate from the next use
                System.out.println(" ");//add a space between the possibilities and the one determined to be the original message
            } else if (!"q".equals(option)) {
                System.out.println("Enter a valid input");
            }

        }
System.out.println("Thanks for Ciphering!");
    }
}
