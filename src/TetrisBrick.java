/*
 * Tetris Game
 * The Tetris Brick super class
 * 11/14/2022
 * Author: Ethan Campbell
 */

public abstract class TetrisBrick {
   
    protected int[][] position = new int[4][2];
    protected int numSegments;
    protected int colorNum;
    protected int orientation;
    
    public TetrisBrick(int colN){
   
        orientation = 0;
        numSegments = 4;
        colorNum = colN;
    }
    
    public int getSegPosition(int row, int col){
    
        return position[row][col];
    }
    
    public void moveDown() {
        for (int i = 0; i < numSegments; i++) {
            position[i][1] += 1;
        }
    }
    
   public void moveUp() {
        for (int i = 0; i < numSegments; i++) {
            position[i][1] -= 1;
        }
    }

    public void moveLeft() {
        for (int i = 0; i < numSegments; i++) {
            position[i][0] -= 1;
        }
    }

    public void moveRight() {
        for (int i = 0; i < numSegments; i++) {
            position[i][0] += 1;
        }
    }
    
    public int getColorNumber(){
    
        return colorNum;
    }
    
    public abstract void rotate();
    
    public abstract void unrotate();
    
    public abstract void initPosition();
}