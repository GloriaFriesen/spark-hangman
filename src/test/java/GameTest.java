import org.junit.*;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;

public class GameTest {

  @Test
  public void newGame_instantiatedCorrectly() {
    Game testGame = new Game(0, 0);
    assertEquals(true, testGame instanceof Game);
  }

  @Test
  public void getWord_returnsAnInstanceOfString_true() {
    Game testGame = new Game(0, 0);
    assertEquals(true, testGame.getHiddenWord() instanceof String);
  }

  @Test
  public void guessLetter_returnsLettersGuessed() {
    Game testGame = new Game(0, 0);
    List<Character> expectedGuessList = new ArrayList<Character>();
    expectedGuessList.add('a');
    expectedGuessList.add('b');
    testGame.guessLetter('a');
    testGame.guessLetter('b');
    assertEquals(expectedGuessList, testGame.getLettersGuessed());
  }

  @Test
  public void getWord_returnsUnderscoresWhenNoGuesses() {
    Game testGame = new Game(0, 0);
    assertEquals("_ _ _ _ _", testGame.getHiddenWord());
  }

  @Test
  public void getWord_revealsLetterWhenGuessed() {
    Game testGame = new Game(0, 0);
    testGame.guessLetter('a');
    assertEquals("a _ _ _ _", testGame.getHiddenWord());
  }

  @Test
  public void guessLetter_doesNotAddLetterTwice() {
    Game testGame = new Game(0, 0);
    List<Character> expectedGuessList = new ArrayList<Character>();
    expectedGuessList.add('a');
    testGame.guessLetter('a');
    testGame.guessLetter('a');
    assertEquals(expectedGuessList, testGame.getLettersGuessed());
  }

  @Test
  public void wrongGuesses_returnsOneAfterWrongGuess_1() {
    Game testGame = new Game(0, 0);
    testGame.guessLetter('u');
    assertEquals(1, testGame.wrongGuesses());
  }

  @Test
  public void wrongGuesses_returnsTheeAfterWrongGuesses_3() {
    Game testGame = new Game(0, 0);
    testGame.guessLetter('u');
    testGame.guessLetter('t');
    testGame.guessLetter('r');
    assertEquals(3, testGame.wrongGuesses());
  }

  @Test
  public void guessLetter_doesNotAddNumberOrSpecialChar() {
    Game testGame = new Game(0, 0);
    List<Character> expectedGuessList = new ArrayList<Character>();
    expectedGuessList.add('a');
    testGame.guessLetter('a');
    testGame.guessLetter('9');
    testGame.guessLetter('-');
    assertEquals(expectedGuessList, testGame.getLettersGuessed());
  }

  @Test
  public void getWordCount_returnNumberOfWordsInArray_7() {
    assertEquals(7, Game.getWordCount(0));
  }
}
