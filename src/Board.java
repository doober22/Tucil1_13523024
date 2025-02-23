import java.util.*;

class Board {
    public final int rows, cols;
    public final char[][] grid;
    private static final String[] COLORS = {
        "\u001B[31m", "\u001B[32m", "\u001B[33m", "\u001B[34m", "\u001B[35m", "\u001B[36m", "\u001B[91m", "\u001B[92m", "\u001B[93m", "\u001B[94m", "\u001B[95m", "\u001B[96m", "\u001B[97m",
        "\u001B[41m", "\u001B[42m", "\u001B[43m", "\u001B[44m", "\u001B[45m", "\u001B[46m", "\u001B[101m", "\u001B[102m", "\u001B[103m", "\u001B[104m", "\u001B[105m", "\u001B[106m", "\u001B[107m"
    };
    private static final String RESET = "\u001B[0m";

    public Board(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.grid = new char[rows][cols];
        for (char[] row : grid) Arrays.fill(row,'.');
    }

    public boolean canPlace(Piece piece, int r, int c) {
        for (int[] coord : piece.getCoords()) {
            int nr = r +coord[0], nc = c +coord[1];
            if (nr < 0 || nr >= rows|| nc < 0 || nc >= cols|| grid[nr][nc] != '.') {
                return false;}
        }
        return true;
    }

    public void placePiece(Piece piece, int r, int c) {
        for (int[] coord : piece.getCoords()){
            grid[r + coord[0]][c + coord[1]] = piece.getShape();
        }
    }

    public void removePiece(Piece piece, int r, int c) {
        for (int[] coord : piece.getCoords()){
            grid[r + coord[0]][c + coord[1]] = '.';
        }
    }

    public void printBoard() {
        System.out.println("\nCurrent Board:");
        for (char[] row : grid) {
            for (char cell : row) {
                    if (cell == '.') {
                        System.out.print(cell + " ");
                    } else {
                        String color = COLORS[(cell - 'A') % COLORS.length];
                        System.out.print(color + cell + RESET + " ");
                    }
            }
            System.out.println();
        }
    }

    public int[] topLeft(Piece piece) {
        for (int r = 0; r <rows; r++) {
            for (int c = 0; c <cols; c++) {
                if (canPlace(piece, r, c)){
                    return new int[]{r, c};}
            }
        }
        return null;
    }

    public boolean isFull() {
        for (char[] row : grid) {
            for (char cell : row) {
                if (cell =='.') {return false ;}
            }
        }
        return true;
    }

    public char[][] getGrid() {
        return grid;
    }
}