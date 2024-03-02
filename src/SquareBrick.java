/*
 * Tetris Game
 * The Square Brick sub class
 * 11/14/2022
 * Author: Ethan Campbell
 */

public class SquareBrick extends TetrisBrick{
    
    public SquareBrick(int colN){

        super(colN);
    }
    
    public void initPosition(){
        
        position = new int[][]{ {4,0},
            {4,1}, {5,0}, {5,1} };
    }
    
    public void rotate(){
    
    }
    
    public void unrotate(){
    
    }
}