import java.util.List;

public class Solver {

  private boolean isSolved(Board board) {
    if (!board.isValid()) return false;
    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 9; j++) {
        if (board.board[i][j] == 0) {
          return false;
        }
      }
    }
    return true;
  }

  public boolean solve(Board board) {
    if (isSolved(board)) {
      board.printBoard();
      return true;
    }

    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 9; j++) {
        if (board.board[i][j] == 0) {
          List<Integer> possibleValues = board.getPossibleValuesForSquare(i, j);

          for (int value : possibleValues) {
            if (board.editBoard(value, i, j) && solve(board)) {
              return true;
            }

            board.editBoard(0, i, j);
          }

          return false;
        }
      }
    }

    return false;
  }
}
