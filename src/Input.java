import java.util.*;
import java.io.*;

public class Input {
    private int n, m, p;
    private List<Piece> pieces;
    public Input() {
        this.pieces = new ArrayList<>();
    }
    public static void main(String[] args) {
        Input input = new Input();
        input.readInputFromFile("input.txt");
        input.printStoredPieces();
        Piece test = input.getPieces().get(0);
        test.rotate();
        input.printStoredPieces();
        test.mirror();
        input.printStoredPieces();

    }

    public void readInputFromFile(String filename) {
        //header awalan
        try (Scanner scanner = new Scanner(new File(filename))) {
            n = scanner.nextInt();
            m = scanner.nextInt();
            p = scanner.nextInt();
            scanner.nextLine();
            scanner.nextLine();
            List<int[]> shapeCoords = new ArrayList<>();
            char currentChar = '#';
            int i = 0;


            while (scanner.hasNextLine()) { //baca isi
                String line = scanner.nextLine();
                if(line.trim().isEmpty()) 
                    continue;
                char firstChar = line.trim().charAt(0);

                if (currentChar== '#') {
                 currentChar = firstChar;
                }
                if(firstChar != currentChar) {
                    pieces.add(new Piece(currentChar, shapeCoords, 0, 0));
                    shapeCoords = new  ArrayList<>();
                    currentChar = firstChar;
                    i = 0;
                    if (pieces.size() > p-2) { //kalo kelebihan (tetep diitung yang ga lebih)
                        System.err.println("Too many pieces");
                        break;
                    }
                }

                for (int col =0; col < line.length(); col++) {
                    if (line.charAt(col) == currentChar) {
                        shapeCoords.add(new int[]{i, col});
                    }
                }
                i++  ;
             } 
            if (!shapeCoords.isEmpty()) {
                pieces.add(new Piece(currentChar, shapeCoords, 0, 0));
            }
        } catch (FileNotFoundException e) {
            System.err.println("File Not Found");
        }

        }

    public List<Piece> getPieces() {
        return pieces;
    }

    public void printStoredPieces() {
        for (Piece piece : pieces) {
            System.out.println(piece.getShape() + " Rot: " +piece.getRot() + " Mir: " +piece.getMir());
            piece.printShape();
            System.out.println();}
    }
    
}



