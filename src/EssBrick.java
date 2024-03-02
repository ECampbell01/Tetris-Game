/*
 * Tetris Game
 * The Ess Brick sub class
 * 11/14/2022
 * Author: Ethan Campbell
 */

public class EssBrick extends TetrisBrick{
     
    public EssBrick(int colN){

        super(colN);
    }
    
    public void initPosition(){
        
        position = new int[][]{ {4,1},
            {5,1}, {5,0}, {6,0} };
    }
    
    public void rotate(){
    
        if(orientation == 0){
        
            position[0][0] += 3;
            position[0][1] += 1;
            position[1][0] += 2;
            position[2][0] += 1;
            position[2][1] += 1;
            orientation += 1;
        }
        
        else{
        
            position[0][0] -= 3;
            position[0][1] -= 1;
            position[1][0] -= 2;
            position[2][0] -= 1;
            position[2][1] -= 1;
            orientation -= orientation;
        }
    }
    
    public void unrotate(){
    
        if(orientation == 1){
        
            position[0][0] -= 3;
            position[0][1] -= 1;
            position[1][0] -= 2;
            position[2][0] -= 1;
            position[2][1] -= 1;
            orientation -= 1;
        }
        
        else{
        
            position[0][0] += 3;
            position[0][1] += 1;
            position[1][0] += 2;
            position[2][0] += 1;
            position[2][1] += 1;
            orientation += 1;
        }
    }
}