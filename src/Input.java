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
                    if (pieces.size() > p-1) { //kalo kelebihan (tetep diitung yang ga lebih)
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
    public int[] getBoardDim() {
        return new int[]{n, m};
    }

    public void printStoredPieces() {
        for (Piece piece : pieces) {
            System.out.println(piece.getShape() + " Rot: " +piece.getRot() + " Mir: " +piece.getMir());
            piece.printShape();
            System.out.println();}
    }
    
    public int getNpiece() {
        return  p;
    }
}

class Output {
    public static void writeToFile(char[][] grid, long attempts, long timeTaken) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Save the solution? (yes/no): ");
        String response = scanner.nextLine().trim().toLowerCase();
        if (!response.equals("yes")) {return;}
        
        System.out.print("Save filename (with .txt): ");
        String filename = scanner.nextLine();
        String filePath = "test/" + filename;

        try (FileWriter writer = new FileWriter(filePath)){
            for (char[] row : grid) {
                for (char cell : row) {
                    writer.write(cell +" ");
                }
                writer.write("\n");
            }
            writer.write("\nAttempts: " + attempts);
            writer.write("\nTime taken: " + timeTaken +" ms\n");
            System.out.println("Solution saved to " +filePath);
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}




