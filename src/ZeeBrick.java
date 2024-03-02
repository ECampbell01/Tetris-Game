/*
 * Tetris Game
 * The Zee Brick sub class
 * 11/14/2022
 * Author: Ethan Campbell
 */

public class ZeeBrick extends TetrisBrick{
    
    public ZeeBrick(int colN){

        super(colN);
    }
    
    public void initPosition(){
        
        position = new int[][]{ {4,0},
            {5,0}, {5,1}, {6,1} };
    }
    
    public void rotate(){
        
        if(orientation == 0){
        
            position[0][0] += 1;
            position[0][1] += 2;
            position[1][0] += 1;
            orientation += 1;
        }
        
        else{
        
            position[0][0] -= 1;
            position[0][1] -= 2;
            position[1][0] -= 1;
            orientation -= orientation;
        }
    }
    
    public void unrotate(){
    
        if(orientation == 1){
        
            position[0][0] -= 1;
            position[0][1] -= 2;
            position[1][0] -= 1;
            orientation -= 1;
        }
        
        else{
        
            position[0][0] += 1;
            position[0][1] += 2;
            position[1][0] += 1;
            orientation += 1;
        }
    }
}
