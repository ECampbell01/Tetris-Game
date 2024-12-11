/*
 * Tetris Game
 * Class that houses the game display and instantiates the game
 * 11/28/2022
 * Author: Ethan Campbell
 */

import java.awt.event.*;
import javax.swing.*;

public class TetrisWindow extends JFrame implements ActionListener{
    
    private int win_Wid = 500;
    private int win_Hei = 600;       
    
    private TetrisDisplay display;
    private TetrisGame game;
    
    int game_cols = 12;
    int game_rows = 20;
    int medFontSize = 25;
    
    JMenuBar menuBar;
    JMenu gameMenu;
    JMenuItem newItem;
    JMenuItem saveItem;
    JMenuItem loadItem;
    
    public TetrisWindow(){

        this.setTitle("Tetris: Created by Ethan Campbell");
        this.setSize(win_Wid, win_Hei);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        game = new TetrisGame(game_rows, game_cols);
        display = new TetrisDisplay(game);
        this.add(display);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        
        menuBar = new JMenuBar();
       
        gameMenu = new JMenu("Game");
        
        newItem = new JMenuItem("New");
        saveItem = new JMenuItem("Save");
        loadItem = new JMenuItem("Load");
        
        gameMenu.add(newItem);
        gameMenu.add(saveItem);
        gameMenu.add(loadItem);
        
        newItem.addActionListener(this);
        saveItem.addActionListener(this);
        loadItem.addActionListener(this);
        
        menuBar.add(gameMenu);
        
        this.setJMenuBar(menuBar);
        
        this.setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae){
    
        if(ae.getSource() == newItem)
            game.newGame();
        
        if(ae.getSource() == saveItem)
            game.saveToFile("SaveGame.dat");
        
        if(ae.getSource() == loadItem)
            game.retrieveFromFile("SaveGame.dat");
    }
    
    public static void main(String[] args) {
        
        new TetrisWindow();
    }
}