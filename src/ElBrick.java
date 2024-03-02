/*
 * Tetris Game
 * The El Brick sub class
 * 11/14/2022
 * Author: Ethan Campbell
 */

public class ElBrick extends TetrisBrick{
    
    public ElBrick(int colN){

        super(colN);
    }
    
    public void initPosition(){
        
        position = new int[][]{ {4,0},
            {4,1}, {4,2}, {5,2} };
    }
    
    public void rotate(){
    
        if(orientation == 0){
        
            position[0][0] += 3;
            position[0][1] += 2;
            position[1][0] += 2;
            position[1][1] += 1;
            position[2][0] += 1;
            position[2][1] += 1;
            orientation += 1;
        }
        
        else if(orientation == 1){
        
            position[0][0] -= 3;
            position[0][1] -= 2;
            position[1][0] -= 1;
            position[1][1] -= 2;
            position[2][1] -= 2;
            orientation += 1;
        }
        
        else if(orientation == 2){
        
            position[0][1] += 2;
            position[1][0] += 1;
            position[1][1] += 1;
            position[2][0] += 1;
            position[2][1] += 1;
            orientation += 1;
        }
        
        else{
        
            position[0][1] -= 2;
            position[1][0] -= 2;
            position[2][0] -= 2;
            orientation -= orientation;
        }
    }
    
    public void unrotate(){
    
        if(orientation == 1){
        
            position[0][0] -= 3;
            position[0][1] -= 2;
            position[1][0] -= 2;
            position[1][1] -= 1;
            position[2][0] -= 1;
            position[2][1] -= 1;
            orientation -= 1;
        }
        
        else if(orientation == 2){
        
            position[0][0] += 3;
            position[0][1] += 2;
            position[1][0] += 1;
            position[1][1] += 2;
            position[2][1] += 2;
            orientation -= 1;
        }
        
        else if(orientation == 3){
        
            position[0][1] -= 2;
            position[1][0] -= 1;
            position[1][1] -= 1;
            position[2][0] -= 1;
            position[2][1] -= 1;
            orientation -= 1;
        }
        
        else{
        
            position[0][1] += 2;
            position[1][0] += 2;
            position[2][0] += 2;
            orientation += 3;
        }
    }
}