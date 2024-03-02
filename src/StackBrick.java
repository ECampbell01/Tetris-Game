/*
 * Tetris Game
 * The Stack Brick sub class
 * 11/14/2022
 * Author: Ethan Campbell
 */

public class StackBrick extends TetrisBrick{
    
    public StackBrick(int colN){

        super(colN);
    }
    
    public void initPosition(){
       
        position = new int[][]{ {4,1},
            {5,0}, {6,1}, {5,1} };
    }
    
    public void rotate(){
    
        if(orientation == 0){
        
            position[0][0] += 1;
            position[0][1] += 1;
            orientation += 1;
        }
        
        else if(orientation == 1){
        
            position[1][0] -= 1;
            position[1][1] += 1;
            orientation += 1;
        }
        
        else if(orientation == 2){
        
            position[2][0] -= 1;
            position[2][1] -= 1;
            orientation += 1;
        }
        
        else{
        
            position[0][0] -= 1;
            position[0][1] -= 1;
            position[1][0] += 1;
            position[1][1] -= 1;
            position[2][0] += 1;
            position[2][1] += 1;
            orientation -= orientation;
        }
    }
    
    public void unrotate(){
    
        if(orientation == 1){
        
            position[0][0] -= 1;
            position[0][1] -= 1;
            orientation -= 1;
        }
        
        else if(orientation == 2){
        
            position[1][0] += 1;
            position[1][1] -= 1;
            orientation -= 1;
        }
        
        else if(orientation == 3){
        
            position[2][0] += 1;
            position[2][1] += 1;
            orientation -= 1;
        }
        
        else{
        
            position[0][0] += 1;
            position[0][1] += 1;
            position[1][0] -= 1;
            position[1][1] += 1;
            position[2][0] -= 1;
            position[2][1] -= 1;
            orientation += 3;
        }
    }
}