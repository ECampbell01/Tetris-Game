/*
 * Tetris Game
 * Class that provides the logic for our game
 * 11/28/2022
 * Author: Ethan Campbell
 */

import java.util.*;
import java.io.*;

public class TetrisGame {
   
    private int rows;
    private int cols;
    private int numBrickTypes;
    private TetrisBrick fallingBrick;
    private Random randGen = new Random();
    private int[][] background;
    private int state;
    private int score;
    
    public TetrisGame(int ro, int co){

        rows = ro;
        cols = co;
        newGame();
    }
    
    public int getRows(){
        
        return rows;
    }
     
    public int getCols(){
        
        return cols;
    }
    
    public String toString(){

        String stuff = " " + background.length + " " + background[0].length + "\n";
        
        for (int row = 0; row < background.length; row++)
        {
            for (int col = 0; col < background[0].length; col++){
               
                stuff += "" + background[row][col] + " ";
            }
            
            stuff += "\n";
        }
        
        stuff = stuff.substring(0, stuff.length() - 1);
        return stuff;
    }
    
    public void saveToFile(String fName){
    
        File fileConnection = new File(fName);
        
        if(fileConnection.exists() && !fileConnection.canWrite()){
        
            System.err.print("Trouble opening file: " + fName);
            return;
        }
        
        try{
        
            FileWriter outWriter = new FileWriter(fileConnection);
            outWriter.write(this.toString());
            outWriter.close();
        }
        
        catch(IOException io){
        
            System.err.print("Trouble writing to file: " + fName);
        }
    }
    
    public void retrieveFromFile(String fName){
    
        initBoard(rows, cols);
        
        try{
        
            File fileConnection = new File(fName);
            Scanner inScan = new Scanner(fileConnection);
            rows = inScan.nextInt();
            cols = inScan.nextInt();
            
            background = new int[rows][cols];
            
            for(int row = 0; row < rows; row++)
            {
                for (int col = 0; col < cols; col++)
                {
                    background[row][col] = inScan.nextInt();
                }
            }
        }
        
        catch(FileNotFoundException fnfe){
        
            System.err.print("Trouble opening file to read: " + fName);
            return;
        }
        
        catch(Exception e){
        
            System.err.print("Error occured during read from file!");
        }
        
        spawnBrick();
    }
    
    private void spawnBrick(){
       
        int randNum = randGen.nextInt(1,8);
        
        switch(randNum){
            
            case 1:
                fallingBrick = new ElBrick(randNum);
                fallingBrick.initPosition();
                break;
            case 2:
                fallingBrick = new EssBrick(randNum);
                fallingBrick.initPosition();
                break;
            case 3:
                fallingBrick = new JayBrick(randNum);
                fallingBrick.initPosition();
                break;
            case 4:
                fallingBrick = new LongBrick(randNum);
                fallingBrick.initPosition();
                break;
            case 5:
                fallingBrick = new SquareBrick(randNum);
                fallingBrick.initPosition();
                break;
            case 6:
                fallingBrick = new StackBrick(randNum);
                fallingBrick.initPosition();
                break;
            case 7:
                fallingBrick = new ZeeBrick(randNum);
                fallingBrick.initPosition();
                break;
        }   
    }
    
    public int getScore(){

        return score;
    }
    
    public int getNumSeg(){
       
        return fallingBrick.numSegments;
    }
    
    public int getSegRow(int seg){
        
        return fallingBrick.position[seg][1];
    }
    
    public int getSegCol(int seg){
        
        return fallingBrick.position[seg][0];
    }
    
    public int getFallingBrickColor(){
    
        return fallingBrick.getColorNumber();
    }
    
    public void initBoard(int rs, int cs){
        
        background = new int[rs][cs];
        
        for(int row = 0; row < rs; row++){
        
            for(int col = 0; col < cs; col++){
            
                background[row][col] = 0;
            }
        }
    }
    
    public void newGame(){
   
        initBoard(rows, cols);
        spawnBrick();
        score = 0;
    }
    
    private void transferColor(int color, int row, int col){
    
        for(int dex = 0; dex < getNumSeg(); dex++){
        
            background[getSegRow(dex)][getSegCol(dex)] = color;
        }
    }
    
    public int clearLines(){
    
        boolean lineFilled;
        int clearedLines = 0;
        
        for(int row = rows - 1; row >= 0; row--){
        
            lineFilled = true;
            
            for(int col = 0; col < cols; col++){
            
                if(background[row][col] == 0){
                
                    lineFilled = false;
                    break;
                }
            }
            
            if(lineFilled){
            
                clearedLines++;
                clearLine(row);
                shiftDown(row);
                clearLine(0);
                row++;
            }
        }
        
        if(clearedLines == 1)
            score += 100;
        
        else if(clearedLines == 2)
            score += 300;
        
        else if(clearedLines == 3)
            score += 600;
        
        else if(clearedLines == 4)
            score += 1200;
        
        return clearedLines;
    }
    
    private void clearLine(int ro){
    
        for(int dex = 0; dex < cols; dex++){
        
            background[ro][dex] = 0;
        }
    }
    
    private void shiftDown(int ro){
    
        for(int row = ro; row > 0; row--){
        
            for(int col = 0; col < cols; col++){
                
                background[row][col] = background[row - 1][col];
            }
        }
    }
    
    public void makeMove(String direction){
            
        if(Objects.equals(direction, "D")){    
            fallingBrick.moveDown(); 
            
            if(!validateMove()){
                
                fallingBrick.moveUp();
                transferColor(fallingBrick.colorNum, rows, cols);
                spawnBrick();
            } 
        }
            
        else if(Objects.equals(direction, "L")){
            fallingBrick.moveLeft();
            
            if(!validateMove())
                fallingBrick.moveRight();
        }   
        
        else if(Objects.equals(direction, "R")){
            fallingBrick.moveRight();
            
            if(!validateMove())
                fallingBrick.moveLeft();
        }   
        
        else if(Objects.equals(direction, "U")){
            fallingBrick.rotate();
            
            if(!validateMove())
                fallingBrick.unrotate();
        }
    }
    
    public int fetchBoardPosition(int row, int col){
    
       return background[row][col];
    }
    
    public boolean checkBoard()
    {
        int check = 3;
        
        for (int row = 0; row < check; row++)
        {
            for (int col = 0; col < background[0].length; col++)
            {
                if (background[0][col]!= 0)
                    return true;
            }
        }
        
        return false;
    }
    
    private boolean validateMove(){
    
        for(int seg = 0; seg < fallingBrick.numSegments; seg++){
        
            int segrow = getSegRow(seg);
            int segcolumn = getSegCol(seg);
            
            if(segrow + 1 > rows || segcolumn > cols - 1 || segcolumn + 1 == 0) 
                return false;
            
            else if(background[segrow][segcolumn] > 0 )
                return false;
        }
        
        return true;
    }
}