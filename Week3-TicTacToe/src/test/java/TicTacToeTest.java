import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TicTacToeTest {

    @Test
    public void play_WhenBoardIsEmpty_ShouldPutXOnGivenPosition() {
        //Arrange
        TicTacToe sut = new TicTacToe();
        Position p = new Position(1,1);

        //Act
        sut.play(p);

        //Assert
        assertThat(sut.board[1][1]).isEqualTo("X");
    }

    @Test
    public void play_WhenSecondPlayIsHappening_ShouldPutOOnGivenPosition() {
        //Arrange
        TicTacToe sut = new TicTacToe();
        Position p1 = new Position(1,1);
        sut.play(p1);
        Position p2 = new Position(2,3);

        //Act
        sut.play(p2);

        //Assert
        assertThat(sut.board[2][3]).isEqualTo("O");
    }

    @Test
    public void play_WhenPlayIsMadeToAFilledPosition_ShouldThrowIllegalArgumentException() {
        //Arrange
        TicTacToe sut = new TicTacToe();
        Position p1 = new Position(1,1);
        sut.play(p1);

        //Act
        Throwable throwable = catchThrowable( () -> sut.play(p1));

        //Assert
        assertThat(throwable).isInstanceOf(IllegalArgumentException.class).hasMessage("That position is already played");
    }

    @Test
    public void isBoardFull_WhenBoardIsFull_ShouldReturnTrue() {
        //Arrange
        TicTacToe sut = new TicTacToe();
        sut.play(new Position(1,1));
        sut.play(new Position(1,2));
        sut.play(new Position(1,3));
        sut.play(new Position(2,1));
        sut.play(new Position(2,2));
        sut.play(new Position(2,3));
        sut.play(new Position(3,1));
        sut.play(new Position(3,2));
        sut.play(new Position(3,3));

        //Act
        boolean result = sut.isBoardFull();

        //Assert
        assertTrue(result);
    }

    @Test
    public void isBoardFull_WhenBoardIsNotFull_ShouldReturnFalse() {
        //Arrange
        TicTacToe sut = new TicTacToe();
        sut.play(new Position(1,1));

        //Act
        boolean result = sut.isBoardFull();

        //Assert
        assertFalse(result);
    }

    @Test
    public void isGameFinished_WhenXWinsPos1_ShouldEndGameAndSetXWinner() {
        //Arrange
        TicTacToe sut = new TicTacToe();
        sut.play(new Position(1,1));
        sut.play(new Position(2,1));
        sut.play(new Position(1,2));
        sut.play(new Position(3,3));
        sut.play(new Position(1,3));

        //Act
        String result = sut.winner;

        //Assert
        assertTrue(sut.isGameFinished());
        assertThat(result).isEqualTo("X");
    }

}
