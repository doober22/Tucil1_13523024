import java.util.*;
class Brute {
    private Board board;
    private List<Piece> pieces;
    private long attempts = 0;

    public Brute(Board board, List<Piece> pieces) {
        this.board = board;
        this.pieces = pieces;
    }

    public boolean solve(int index) {
        if (index >= pieces.size()) {
            if (board.isFull()) {
                return true;
            }
        }
        for (int i = index; i < pieces.size(); i++) {
            Collections.swap(pieces, index, i); //nuker dari list pieces
    
            Piece piece = pieces.get(index); //ngambil piece
            for (int rot = 0; rot < 4; rot++) {
                for (int mir = 0; mir < 2; mir++) { 
                    int[] bestPos = board.topLeft(piece);
                    if (bestPos != null) {
                        int r = bestPos[0], c = bestPos[1];

                        for (int k = 0; k < r; k++) {
                            for (int nc = 0; nc < board.cols; nc++) {
                                if (board.grid[k][nc] == '.') {return false;} //cek baris atas terisi
                            }
                        }

                        board.placePiece(piece, r, c);
                        attempts++;
                        if (attempts == 2) {
                            System.out.println("Processing... "); //biar keliatan jalan
                        }
                        if (solve(index + 1)) {return true;}
                        board.removePiece(piece, r, c);
                    }
                    piece.mirror();
                }
                piece.rotate();
            }
    
            Collections.swap(pieces, index, i);
        }
        return false;
    }

    public long getAttempts() {
        return attempts;
    }
}