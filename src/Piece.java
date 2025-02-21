import java.util.*;

public class Piece {
    private char shape;
    private List<int[]> coords;
    private int rot, mir;

    public Piece(char shape, List<int[]> coords, int rot, int mir) {
        this.shape = shape;
        this.coords = new ArrayList<>(coords);
        this.rot = rot;
        this.mir = mir;
    }

    public char getShape() { 
        return shape; 
    }
    public int getRot() {
        return rot; 
    }
    public int getMir() {
        return mir; 
    }
    public List<int[]> getCoords() {
        return coords;
    }
    public void rotate() {
        List<int[]> coord2 = new ArrayList<>();
        for (int[] coord : coords) {
            coord2.add(new int[]{coord[1],  -coord[0]});
        }
        coords = coord2;
        rot = (rot +1)%4;
        norm();
    }

    public void mirror() {
        List<int[]> coord2 = new ArrayList<>();
        for (int[] coord : coords) {
            coord2.add(new int[]{coord[0],  -coord[1]});
        }
        coords = coord2;
        mir = 1 -mir;
        norm();
        }

    public void norm() {
        if (coords.isEmpty()) {
            return;
        }
        int minRow = Integer.MAX_VALUE;
        int minCol = Integer.MAX_VALUE;

        
        for (int[] coord : coords) {
            minRow = Math.min(minRow, coord[0]);
            minCol = Math.min(minCol, coord[1]);
        }
        for (int[] coord : coords) {
            coord[0] -= minRow;
            coord[1] -= minCol;
        }
    }

    public void printShape() {
        for (int[] coord : coords) {
            System.out.println(coord[0]+ "," +  coord[1]);
        }
    }
}


