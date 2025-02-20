import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.imageio.IIOException;


public class Encoder {
    

    public int[] StringtoAscii(String filePath){
        try {
            //Read text from file
            String text = new String(Files.readAllBytes(Paths.get(filePath)));
            //Only gets chars from the text and turn it into char array
            String onlyChar = text.replaceAll("[^a-zA-ZçÇğĞıİöÖşŞüÜ]", "");
            //Turn chars into ascii
            int[] asciiValues = new int[onlyChar.length()];
            for(int i =0;i < onlyChar.length();i++){
                asciiValues[i] = (int) onlyChar.charAt(i);
            }
            return asciiValues;


        }catch(IOException e){
            System.out.println("Error: "+ e.getMessage());
            return new int[0];
        }
        
        
    }
    public static void main(String[] args) {
        Encoder encoder = new Encoder();
        String filePath = "metin.txt"; // Okunacak dosya yolu

        int[] asciiArr = encoder.StringtoAscii(filePath);

        for(int num : asciiArr){
            System.out.println(num+ " ");

        }
        
    }

}
