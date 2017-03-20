import java.util.List;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Game {
  // private static String[] allWords = {"apple", "banana", "java", "unittest", "epicodus", "train", "lemur", "photograph", "antidisestablishmentarianism", "juneau", "notebook", "computer", "skyscraper", "bicycle", "hat", "at", "headphones", "keyboard", "bottle", "sunshine", "raccoon", "apartment", "string"};
  private static String[] easy = {"apple", "banana", "java", "train", "hat", "at", "string"};
  private static String[] medium = {"unittest", "epicodus", "photograph", "headphones", "sunshine", "apartment", "computer"};
  private static String[] hard = {"antidisestablishmentarianism", "awkward", "bagpipes", "gazebo", "ivory", "jukebox", "kayak"};
  private static String[][] allWords = {easy, medium, hard};
  private String word;
  private List<Character> lettersGuessed = new ArrayList<Character>();

  public Game(int difficulty, int index) {
    word = allWords[difficulty][index];
  }

  public void guessLetter(char letter) {
    if(!Pattern.matches("[^a-z]", Character.toString(letter))) {
      if (!lettersGuessed.contains(letter)) {
        lettersGuessed.add(letter);
      }
    }
  }

  public List<Character> getLettersGuessed() {
    return lettersGuessed;
  }

  public String getWord() {
    return word;
  }

  public String getHiddenWord() {
    String revealedWord = "";
    for (int i = 0; i < word.length(); i++) {
      if (lettersGuessed.contains(word.charAt(i))) {
        revealedWord += word.charAt(i) + " ";
      } else {
        revealedWord += "_ ";
      }
    }
    return revealedWord.trim();
  }

  public int wrongGuesses() {
    int wrongGuessCount = 0;
    for (Character guess : lettersGuessed) {
      if (word.indexOf(guess) == -1) {
        wrongGuessCount++;
      }
    }
    return wrongGuessCount;
  }

  public static int getWordCount(int difficulty) {
    return allWords[difficulty].length;
  }
}
