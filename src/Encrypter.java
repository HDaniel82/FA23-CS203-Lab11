import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.Scanner;

public class Encrypter {

    private int shift;
    private String encrypted;

    /**
     * Default Constructor
     */
    public Encrypter() {
        this.shift = 1;
        this.encrypted = "";
    }

    /**
     * Non-default Constructor
     * @param s - custom shift amount
     */
    public Encrypter(int s) {
        this.shift = s;
        this.encrypted = "";
    }

    /**
     * Encrypts the content of a file and writes the result to another file.
     *
     * @param inputFilePath      the path to the file containing the text to be encrypted
     * @param encryptedFilePath the path to the file where the encrypted text will be written
     * @throws Exception if an error occurs while reading or writing the files
     */
    public void encrypt(String inputFilePath, String encryptedFilePath) throws Exception {
        //TODO: Call the read method, encrypt the file contents, and then write to new file
    	
    	String content = readFile(inputFilePath);
    	String encryptedContent = encryptText(content, shift);
    	writeFile(encryptedContent, encryptedFilePath);
    }
    
    private String encryptText (String text, int shift) {
    	StringBuilder encryptedText = new StringBuilder();
    	
    	for (char c : text.toCharArray()) {
    		if (Character.isLetter(c)) {
    			char shifted = (char) (c + shift);
    			if ((Character.isUpperCase(c) && shifted > 'Z') || 
    				(Character.isLowerCase(c) && shifted > 'z')) {
    				shifted = (char) (c - (26 - shift));
    			}
    			encryptedText.append(shifted);
    		}else {
    			encryptedText.append(c);
    		}	
    	}
    	return encryptedText.toString();
    }
    /**
     * Decrypts the content of an encrypted file and writes the result to another file.
     *
     * @param messageFilePath    the path to the file containing the encrypted text
     * @param decryptedFilePath the path to the file where the decrypted text will be written
     * @throws Exception if an error occurs while reading or writing the files
     */
    public void decrypt(String messageFilePath, String decryptedFilePath) throws Exception {
        //TODO: Call the read method, decrypt the file contents, and then write to new file
    	FileWriter filewriter = new FileWriter ("decrypted.txt");
    	String encryptedContent = readFile(messageFilePath);
    	String decrpytedContent = decryptText (encryptedContent, shift); 
    	writeFile(decrpytedContent, decryptedFilePath); 
    }
    private String decryptText(String text, int shift) {
    	StringBuilder decryptedText = new StringBuilder(); 
    	
    	for (char c : text.toCharArray()) {
    		if (Character.isLetter(c)) {
    			char decryptChar = (char) (c - shift);
    			if ((Character.isUpperCase(c) && decryptChar > 'Z') || 
    				(Character.isLowerCase(c) && decryptChar > 'z')) {
    				decryptChar = (char) (c + (26 - shift));
    			}
    			decryptedText.append(decryptChar);
    		}else {
    			decryptedText.append(c);
    		}	
    	}
    	return decryptedText.toString();
    }
    /**
     * Reads the content of a file and returns it as a string.
     *
     * @param filePath the path to the file to be read
     * @return the content of the file as a string
     * @throws Exception if an error occurs while reading the file
     */
    private static String readFile(String filePath) throws Exception {
        StringBuilder message = new StringBuilder();
        //TODO: Read file from filePath
        try (Scanner fileScanner = new Scanner(new File(filePath))){
        	while(fileScanner.hasNextLine()) {
        		message.append(fileScanner.nextLine()).append("\n");
        	}
        	fileScanner.close();
        	} catch (Exception e) {
        		System.out.println("Error: " + e.getMessage());
        	}
        return message.toString();
        }
    /**
     * Writes data to a file.
     *
     * @param data     the data to be written to the file
     * @param filePath the path to the file where the data will be written
     */
    private static void writeFile(String data, String filePath) {
        //TODO: Write to filePath
    	try (PrintWriter output = new PrintWriter(new FileWriter(filePath))){
    	output.print(data);
    	} catch (Exception e) {
    		System.out.println("Error reading file:" + e.getMessage());
    	}
    }

    /**
     * Returns a string representation of the encrypted text.
     *
     * @return the encrypted text
     */
    @Override
    public String toString() {
        return encrypted;
    }
}
