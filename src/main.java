import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter input file name (with .txt, located in 'test/' folder): ");
        String filename = scanner.nextLine();
        String filePath = "test/" + filename;

        Input input = new Input();
        input.readInputFromFile(filePath);
        int npieces = input.getNpiece();
        if (input.getPieces().size() < npieces){
            System.out.println("Not enough pieces");
        } else {
        int[] dimensions = input.getBoardDim();
        Board board = new Board(dimensions[0], dimensions[1]);
        Brute brute = new Brute(board, input.getPieces());

        long startTime = System.currentTimeMillis();
        boolean solved = brute.solve(0);
        long endTime = System.currentTimeMillis();
        long timeTaken = endTime - startTime;
        
        if (solved) {
            board.printBoard();
        } else {
            board.printBoard();
            System.out.println("No solutions found.");
        }
        System.out.println("Attempts: " + brute.getAttempts());
        System.out.println("Time taken: " + timeTaken + " ms");

            Output.writeToFile(board.getGrid(), brute.getAttempts(), timeTaken);
        }
            scanner.close();
    }
}

