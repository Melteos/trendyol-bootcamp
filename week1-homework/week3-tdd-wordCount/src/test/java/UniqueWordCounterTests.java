import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class UniqueWordCounterTests {

    @Test
    public void countUniqueWords_whenSentenceIsEmpty_shouldReturn0() {
        //Arrange
        UniqueWordCounter sut = new UniqueWordCounter();

        //Act
        int count = sut.countUniqueWords("");

        //Assert
        assertThat(count).isEqualTo(0);
    }

    @Test
    public void countUniqueWords_whenSentenceHasNoRepeatedWords_shouldReturnWordCount() {
        //Arrange
        UniqueWordCounter sut = new UniqueWordCounter();

        //Act
        int count = sut.countUniqueWords("This is a test.");

        //Assert
        assertThat(count).isEqualTo(4);
    }

    @Test
    public void countUniqueWords_whenSentenceHasRepeatedWords_shouldReturnUniqueWordCount() {
        //Arrange
        UniqueWordCounter sut = new UniqueWordCounter();

        //Act
        int count = sut.countUniqueWords("This This is is a a test test.");

        //Assert
        assertThat(count).isEqualTo(4);
    }

    @Test
    public void countUniqueWords_whenSentenceHasWordsWithApostrophe_shouldIncludeItAsOneWord() {
        //Arrange
        UniqueWordCounter sut = new UniqueWordCounter();

        //Act
        int count = sut.countUniqueWords("This test is my cat's.");

        //Assert
        assertThat(count).isEqualTo(5);
    }

    @Test
    public void countUniqueWords_whenSentenceHasWordWithCapitalAndNonCapitalLetter_shouldIncludeItAsOneWord() {
        //Arrange
        UniqueWordCounter sut = new UniqueWordCounter();

        //Act
        int count = sut.countUniqueWords("This test TEST is my cat.");

        //Assert
        assertThat(count).isEqualTo(5);
    }

    @Test
    public void countUniqueWords_whenSentenceHasWhiteSpaceAtTheBeginningOrEnd_shouldNotCountSpaces() {
        //Arrange
        UniqueWordCounter sut = new UniqueWordCounter();

        //Act
        int count = sut.countUniqueWords("     This is my cat.      ");

        //Assert
        assertThat(count).isEqualTo(5);
    }
}
