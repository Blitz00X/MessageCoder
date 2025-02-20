import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Encoder {
    private static final String WORD_LIST_PATH = "google-10000-english-usa-no-swears-long.txt"; // Yeni kelime listesi

    private static int getAlphabeticOrder(char ch) {
        if (ch >= 'a' && ch <= 'z') {
            return ch - 'a' + 1;
        } else if (ch >= 'A' && ch <= 'Z') {
            return ch - 'A' + 1;
        }
        return 0;
    }

    private static int getWordSumMod26(String word) {
        int sum = 0;
        for (char ch : word.toCharArray()) {
            sum += getAlphabeticOrder(ch);
        }
        return sum % 26;
    }

    private static List<String> loadWordList() throws IOException {
        return Files.readAllLines(Paths.get(WORD_LIST_PATH))
                .stream()
                .map(String::toLowerCase)
                .map(word -> word.replaceAll("[^a-z]", "")) // Sadece harfleri al
                .filter(word -> word.length() >= 4) // En az 4 harfli kelimeleri seç
                .toList();
    }

    private static String findMatchingWord(int targetOrder, List<String> wordList) {
        String bestMatch = null;
        int minDifference = Integer.MAX_VALUE;
        int minLength = Integer.MAX_VALUE;

        for (String word : wordList) {
            int wordValue = getWordSumMod26(word);
            int difference = Math.abs(wordValue - targetOrder);

            if (wordValue == targetOrder && word.length() >= 4) { 
                if (word.length() < minLength) { 
                    minDifference = difference;
                    bestMatch = word;
                    minLength = word.length();
                }
            } else if (difference < minDifference && word.length() >= 4) { 
                minDifference = difference;
                bestMatch = word;
                minLength = word.length();
            }
        }
        return bestMatch;
    }

    public static void main(String[] args) {
        String textFilePath = "metin.txt"; // Okunacak metin dosyası

        try {
            String text = new String(Files.readAllBytes(Paths.get(textFilePath)));
            text = text.replaceAll("[^a-zA-Z]", ""); // Sadece harfleri al

            List<String> wordList = loadWordList();

            for (char ch : text.toCharArray()) {
                int alphabeticOrder = getAlphabeticOrder(ch);
                if (alphabeticOrder == 0) continue; 

                String matchingWord = findMatchingWord(alphabeticOrder, wordList);
                if (matchingWord != null) {
                    System.out.println("Harf: " + ch + " -> Kelime: " + matchingWord);
                } else {
                    System.out.println("Harf: " + ch + " -> Uygun kelime bulunamadı.");
                }
            }
        } catch (IOException e) {
            System.out.println("Hata: " + e.getMessage());
        }
    }
}
