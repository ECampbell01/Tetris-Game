/*
 * Tetris Game
 * The Long Brick sub class
 * 11/14/2022
 * Author: Ethan Campbell
 */

public class LongBrick extends TetrisBrick{
    
    public LongBrick(int colN){

        super(colN);
    }
    
    public void initPosition(){
       
        position = new int[][]{ {4,0},
            {5,0}, {6,0}, {7,0} };
    }
    
    public void rotate(){
    
        if(orientation == 0){
        
            position[0][1] += 3;
            position[0][0] += 3;
            position[1][1] += 2;
            position[1][0] += 2;
            position[2][1] += 1;
            position[2][0] += 1;
            orientation += 1;
        }
        
        else{
        
            position[0][1] -= 3;
            position[0][0] -= 3;
            position[1][1] -= 2;
            position[1][0] -= 2;
            position[2][1] -= 1;
            position[2][0] -= 1;
            orientation -= orientation;
        }
    }
    
    public void unrotate(){
    
        if(orientation == 1){
        
            position[0][1] -= 3;
            position[0][0] -= 3;
            position[1][1] -= 2;
            position[1][0] -= 2;
            position[2][1] -= 1;
            position[2][0] -= 1;
            orientation -= 1;
        }
        
        else{
        
            position[0][1] += 3;
            position[0][0] += 3;
            position[1][1] += 2;
            position[1][0] += 2;
            position[2][1] += 1;
            position[2][0] += 1;
            orientation += 1;
        }
    }
}