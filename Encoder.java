import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Encoder {

    public int[] StringToAscii(String pathFile) {
        try {
            
            String text = new String(Files.readAllBytes(Paths.get(pathFile)));

            
            String onlyChars = text.replaceAll("[^a-zA-ZçÇğĞıİöÖşŞüÜ]", "");

           
            int[] alphabeticOrder = new int[onlyChars.length()];
            for (int i = 0; i < onlyChars.length(); i++) {
                char ch = onlyChars.charAt(i);
                
                if (ch >= 'a' && ch <= 'z') {
                    alphabeticOrder[i] = ch - 96; 
                } else if (ch >= 'A' && ch <= 'Z') {
                    alphabeticOrder[i] = ch - 64; 
                } else {
                    alphabeticOrder[i] = 0; // Türkçe karakterler için özel bir işlem gerekebilir
                }
            }

            return alphabeticOrder;
        } catch (IOException e) {
            System.out.println("error: " + e.getMessage());
            return new int[0]; // 
        }
    }

    public static void main(String[] args) {
        Encoder encoder = new Encoder();
        String pathFile = "metin.txt"; // 

        int[] alphabeticOrder = encoder.StringToAscii(pathFile);

        // Sonucu ekrana yazdır
        System.out.print("Alphabetic Orders: ");
        for (int num : alphabeticOrder) {
            System.out.print(num + " ");
        }
    }
}
