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
    
    public void moveDown(){
        
       position[0][1] += 1;
       position[1][1] += 1;
       position[2][1] += 1;
       position[numSegments - 1][1] += 1;

    }
    
    public void moveUp(){
    
       position[0][1] -= 1;
       position[1][1] -= 1;
       position[2][1] -= 1;
       position[numSegments - 1][1] -= 1;
    }
    
    public void moveLeft(){
    
       position[0][0] -= 1;
       position[1][0] -= 1;
       position[2][0] -= 1;
       position[numSegments - 1][0] -= 1;
    }
    
    public void moveRight(){
    
       position[0][0] += 1;
       position[1][0] += 1;
       position[2][0] += 1;
       position[numSegments - 1][0] += 1;
    }
    
    public int getColorNumber(){
    
        return colorNum;
    }
    
    public abstract void rotate();
    
    public abstract void unrotate();
    
    public abstract void initPosition();
}