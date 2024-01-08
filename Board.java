import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Board {

  int[][] board;

  Board() {
    board = new int[9][9];
  }

  Board(int[][] board) {
    this.board = board;
  }

  public boolean editBoard(int value, int row, int col) {
    int currentValue = board[row][col];
    board[row][col] = value;
    if (!isValid()) {
      board[row][col] = currentValue;
      return false;
    }
    return true;
  }

  public List<Integer> getPossibleValuesForSquare(int row, int col) {
    List<Integer> possibleValues = new ArrayList<>();
    for (int i = 1; i <= 9; i++) {
      if (editBoard(i, row, col)) {
        possibleValues.add(i);
        editBoard(0, row, col);
      }
    }
    return possibleValues;
  }

  public boolean isValid() {
    // rows
    for (int[] row : board) {
      if (!isValid(row)) return false;
    }

    // cols
    for (int i = 0; i < 9; i++) {
      int[] col = new int[9];
      for (int j = 0; j < 9; j++) {
        col[j] = board[i][j];
      }
      if (!isValid(col)) return false;
    }

    // grid
    int index = 0;
    for (int row = 0; row < 3; row++) {
      for (int col = 0; col < 3; col++) {
        int[] grid = new int[9];
        for (int i = row * 3; i < row * 3 + 3; i++) {
          for (int j = col * 3; j < col * 3 + 3; j++) {
            grid[index] = board[i][j];
            index++;
          }
        }
        if (!isValid(grid)) return false;
        index = 0;
      }
    }
    return true;
  }

  private boolean isValid(int[] cells) {
    Set<Integer> set = new HashSet<>();
    for (int cell : cells) {
      if (cell == 0) continue;
      if (!set.add(cell)) {
        return false;
      }
    }
    return true;
  }

  public void printBoard() {
    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 9; j++) {
        System.out.print(this.board[i][j] + " ");
        if (j == 2 || j == 5) {
          System.out.print("| ");
        }
      }
      System.out.println("");
      if (i == 2 || i == 5) {
        System.out.print("------+-------+------\n");
      }
    }
  }
}
