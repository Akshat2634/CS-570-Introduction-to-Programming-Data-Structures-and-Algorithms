import java.util.ArrayList;
import java.util.Stack;

/**
 * Class that solves maze problems with backtracking.
 * @author Koffman and Wolfgang
 **/
public class Maze implements GridColors {

    /** The maze */
    private TwoDimGrid maze;

    public Maze(TwoDimGrid m) {
        maze = m;
    }

    /** Wrapper method. */
    public boolean findMazePath() {
        return findMazePath(0, 0); // (0, 0) is the start point.
    }

    /**
     * Attempts to find a path through point (x, y).
     * @pre Possible path cells are in BACKGROUND color;
     *      barrier cells are in ABNORMAL color.
     * @post If a path is found, all cells on it are set to the
     *       PATH color; all cells that were visited but are
     *       not on the path are in the TEMPORARY color.
     * @param x The x-coordinate of current point
     * @param y The y-coordinate of current point
     * @return If a path through (x, y) is found, true;
     *         otherwise, false
     */
    public boolean findMazePath(int x, int y) {
        // COMPLETE HERE FOR PROBLEM 1
        if (x < 0 || y < 0 || x >= maze.getNCols() || y >= maze.getNRows()) {
            return false;
        }
        if (!maze.getColor(x, y).equals(NON_BACKGROUND)) {
            return false;
        }
        if (x == maze.getNCols() - 1 && y == maze.getNRows() - 1) {
            maze.recolor(x, y, PATH);
            return true;
        }
        maze.recolor(x, y, PATH);
        if(findMazePath(x+1, y) || findMazePath(x-1, y) || findMazePath(x, y+1) || findMazePath(x, y-1)) {
            return true;
        } else {
            maze.recolor(x, y, TEMPORARY);
            return false;
        }
    }

    // ADD METHOD FOR PROBLEM 2 HERE
    public void findMazePathStackBased(int x,int y,ArrayList<ArrayList<PairInt>> result,Stack<PairInt> trace) {
        if (x < 0 || y < 0 || x >= maze.getNCols() || y >= maze.getNRows()) {
        }
        if (!maze.getColor(x, y).equals(NON_BACKGROUND)) {
        }
        if (x == maze.getNCols() - 1 && y == maze.getNRows() - 1) {
            trace.push(new PairInt (x, y));
            ArrayList<PairInt> a = new ArrayList<> (trace);
            result.add(a);
            maze.recolor(x, y, NON_BACKGROUND);
            trace.pop();
        }
        trace.push(new PairInt (x, y));
        maze.recolor(x, y, PATH);
        findMazePathStackBased(x+1, y, result, trace);
        findMazePathStackBased(x-1, y, result, trace);
        findMazePathStackBased(x, y+1, result, trace);
        findMazePathStackBased(x, y-1, result, trace);
        maze.recolor(x, y, NON_BACKGROUND);
        trace.pop();
    }
    public ArrayList<ArrayList<PairInt>> findAllMazePaths(int x, int y){
        ArrayList<ArrayList<PairInt>> result = new ArrayList<>();
        Stack<PairInt> trace = new Stack<>();
        findMazePathStackBased(0, 0, result, trace);
        return result;
    }
    // ADD METHOD FOR PROBLEM 3 HERE
    public ArrayList<PairInt> findMazePathMin(int x,int y){
        int index = 0;
        ArrayList<ArrayList<PairInt>> path = findAllMazePaths(x, y);
        if (path.size() == 0) {
            throw new ArrayIndexOutOfBoundsException("No Path");
        }

        int min = path.get(0).size();
        int i = 1;
        while(i < path.size()) {
            if(min > path.get(i).size()) {
                min = path.get(i).size();
                index = i;
            }
            i++;
        }

        ArrayList<PairInt> shortest = path.get(index);
        int j = 0;
        while (j < shortest.size()) {
            maze.recolor(shortest.get(i).getX(), shortest.get(i).getY(), PATH);
            j++;
        }
        return shortest;
    }
    

    /*<exercise chapter="5" section="6" type="programming" number="2">*/
    public void resetTemp() {
        maze.recolor(TEMPORARY, BACKGROUND);
    }
    /*</exercise>*/

    /*<exercise chapter="5" section="6" type="programming" number="3">*/
    public void restore() {
        resetTemp();
        maze.recolor(PATH, BACKGROUND);
        maze.recolor(NON_BACKGROUND, BACKGROUND);
    }
    /*</exercise>*/
}
/*</listing>*/
