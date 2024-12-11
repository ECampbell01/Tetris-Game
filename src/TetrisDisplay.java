/*
 * Tetris Game
 * Class that provides the display for our game
 * 11/28/2022
 * Author: Ethan Campbell
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TetrisDisplay extends JPanel{
    
    private int cell_Size = 20;
    private int start_X = 120;
    private int start_Y = 70;
    private int wallWid = 20;
    private int speed = 1000;
    
    private Color[] colors = {Color.WHITE, Color.ORANGE, Color.GREEN, 
    Color.YELLOW, Color.MAGENTA, Color.RED, Color.BLUE, Color.CYAN};
    
    private TetrisGame game;
    private Timer timer;
    private boolean pause = false;
    
    public TetrisDisplay(TetrisGame gam){
    
        game = gam;
        
        timer = new Timer(speed, new ActionListener() {
            
            public void actionPerformed(ActionEvent ae) {
               if (pause == false)
                cycleMove();
            }
        });
        
        this.addKeyListener(new KeyAdapter(){
        
            public void keyPressed(KeyEvent ke){
            
                translateKey(ke);
            }
        });
        
        this.setFocusable(true);
        this.setFocusTraversalKeysEnabled(false);
            
        timer.start();
    }
    
    private void drawScore(Graphics g){
    
        int score = game.getScore();
        g.setColor(Color.BLUE);
        g.setFont(new Font("Cooper Black", Font.BOLD, 30));
        FontMetrics metrics = getFontMetrics(g.getFont());
        g.drawString("Score: " + score, (start_X * 2 - metrics.stringWidth("Score: " + score))/wallWid, g.getFont().getSize());
    }
    
    public void translateKey(KeyEvent ke){
    
        int code = ke.getKeyCode();
        
        switch(code){
        
            case KeyEvent.VK_UP:
                game.makeMove("U");
                break;
            
            case KeyEvent.VK_RIGHT:
                game.makeMove("R");
                break;
                
            case KeyEvent.VK_LEFT:
                game.makeMove("L");
                break;
                
            case KeyEvent.VK_DOWN:
                game.makeMove("D");
                break;
                
            case KeyEvent.VK_N:
                game.newGame();
                break;
                
            case KeyEvent.VK_SPACE:
                pause = !pause;
                break;
        }
        repaint();
    }
                
    public void cycleMove() {
        
        game.makeMove("D");
        game.clearLines();
        repaint();
    }
    
    public void paintComponent(Graphics g){
    
        Graphics2D g2 = (Graphics2D)g;
        g2.setStroke(new BasicStroke(2));
        super.paintComponent(g);
        drawWell(g);
        drawBrick(g);
        drawBackground(g);
        gameOver(g);
        drawScore(g);
    }
    
    public void gameOver(Graphics g){
        
        int bigFont = 50;
        int gameOverX = 0;
        int gameOverY = 50;
        int gameOverWid = 500;
        int gameOverHei = 150;
        int stringX = 100;
        int stringY = 140;
        
        if(game.checkBoard() == true){
            
            timer.stop();
            
            g.setColor(Color.WHITE);
            g.fillRect(gameOverX, gameOverY, gameOverWid, gameOverHei);
            g.setColor(Color.BLACK);
            g.drawRect(gameOverX, gameOverY, gameOverWid, gameOverHei);
            
            g.setColor(Color.BLUE);
            g.setFont(new Font("Cooper Black", 0, bigFont));
            g.drawString("Game Over!", stringX, stringY);
        }
        
        else
            timer.start();
    }
    
    public void drawWell(Graphics g) {
        int adjustedStartX = start_X;
        int adjustedStartY = start_Y;

        g.setColor(Color.WHITE);
        g.fillRect(adjustedStartX - wallWid, adjustedStartY - wallWid,
                cell_Size * game.getCols() + 2 * wallWid, cell_Size * game.getRows() + 2 * wallWid);

        g.setColor(Color.BLACK);
        g.fillRect(adjustedStartX - wallWid, adjustedStartY - wallWid, 
                wallWid, cell_Size * game.getRows() + 2 * wallWid); // Left wall

        g.fillRect(adjustedStartX + cell_Size * game.getCols(), adjustedStartY - wallWid, 
                wallWid, cell_Size * game.getRows() + 2 * wallWid); // Right wall

        g.fillRect(adjustedStartX - wallWid, adjustedStartY + cell_Size * game.getRows(), 
                cell_Size * game.getCols() + 2 * wallWid, wallWid); // Bottom wall
    }
    
    public void drawBackground(Graphics g) {
        int colorNum;
        int adjustedStartX = start_X;
        int adjustedStartY = start_Y;

        for (int rowNum = 0; rowNum < game.getRows(); rowNum++) {
            for (int colNum = 0; colNum < game.getCols(); colNum++) {
                colorNum = game.fetchBoardPosition(rowNum, colNum);

                if (colorNum != 0) {
                    g.setColor(colors[colorNum]);
                    g.fillRect(adjustedStartX + colNum * cell_Size, adjustedStartY + rowNum * cell_Size, cell_Size, cell_Size);
                    g.setColor(Color.BLACK);
                    g.drawRect(adjustedStartX + colNum * cell_Size, adjustedStartY + rowNum * cell_Size, cell_Size, cell_Size);
                }
            }
        }
    }
    
    private void drawBrick(Graphics g) {
        int color = game.getFallingBrickColor();
        int adjustedStartX = start_X;
        int adjustedStartY = start_Y;

        for (int i = 0; i < game.getNumSeg(); i++) {
            g.setColor(colors[color]);
            g.fillRect(adjustedStartX + game.getSegCol(i) * cell_Size,
                    adjustedStartY + (game.getSegRow(i) - 1) * cell_Size, cell_Size, cell_Size);

            g.setColor(Color.BLACK);
            g.drawRect(adjustedStartX + game.getSegCol(i) * cell_Size,
                    adjustedStartY + (game.getSegRow(i) - 1) * cell_Size, cell_Size, cell_Size);
        }
    }
}