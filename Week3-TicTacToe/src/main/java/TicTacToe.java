public class TicTacToe {

    public String[][] board;
    public String winner;
    boolean Xturn = true;

    public TicTacToe() {
        board = new String[4][4]; //To start indexing from 1, creating 3x3 empty board
    }

    public void play(Position p) {
        if(board[p.x][p.y]!=null) {
            throw new IllegalArgumentException("That position is already played");
        }
        if(Xturn) {
            board[p.x][p.y] = "X";
            Xturn = false;
        }
        else {
            board[p.x][p.y] = "O";
            Xturn = true;
        }
    }

    public boolean isBoardFull() {
        for(int i=1; i<=3; i++) {
            for(int j=1; j<=3; j++) {
                if(board[i][j]==null) {
                    return false;
                }
            }
        }
        return true;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public boolean isGameFinished() {
        if(isBoardFull()) {
            if(  )
        }
        return false;
    }
}
