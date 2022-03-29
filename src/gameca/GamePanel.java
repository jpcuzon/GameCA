/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gameca;

import entity.Player;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import tile.TileManager;

/**
 *
 * @author gmcoa
 */
public class GamePanel extends JPanel implements Runnable{
    
    //Screen settings
    
    final int originalTileSize = 16; //16x16 tile 
    final int scale = 6;
    
    public final int tileSize = originalTileSize * scale; //48x48
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;//= (maxScreenCol * 9)/16;
    final int screenWidth = tileSize * maxScreenCol;  //768 pixels
    final int screenHeight = tileSize * maxScreenRow; //576 pixels
    
    //FPS
    int FPS = 60;
    
    
    TileManager tileManager = new TileManager(this);
    KeyHandler keyHandler = new KeyHandler();
    Thread gameThread;
    Player player = new Player(this, keyHandler);
    
    
    
    
    
    public GamePanel(){
        
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true); //all drawing from this component will be done in an offscreen painting buffer; increases render performance
        this.addKeyListener(keyHandler);
        this.setFocusable(true); //frame can be focused to input
        
        
    }

    public void startGameThread(){
        
        gameThread = new Thread(this);
        gameThread.start();
        
    }
    
    
    
    @Override
//    ===== SLEEPER GAME LOOP METHOD ==========
//    ==Some says sleeper method is not as accurate
//    public void run() {
//        
//        //Game Loop; Core of games
//        
//        this.requestFocus(); //automatically sets the focus on the window after running
//        
//        double drawInterval = 1000000000/FPS; //draw the screen every 0.01666 seconds
//        double nextDrawTime = System.nanoTime() + drawInterval;
//        
//        while(gameThread!=null){ //as long as the thread exists, the method is executed
//            
//            //1. UPDATE: Update information such as character position
//            update();
//            
//            //2. DRAW: Draw the screen with the updated info
//            repaint();
//            
//            
//            try { 
//                double remainingTime = nextDrawTime - System.nanoTime();
//                remainingTime = remainingTime/1000000; //convert it to milliseconds
//                
//                if(remainingTime < 0){
//                    remainingTime = 0;
//                }
//                
//                
//                Thread.sleep((long) remainingTime); //The thread will not do anything during the remaining type of the tick/frame
//                
//                nextDrawTime += drawInterval;
//                
//                
//            } catch (InterruptedException ex) {
//                Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//        
//    
//    }
    // ============= Delta Game Loop Method =================
    
    public void run(){
        
        //Game Loop; Core of games
        this.requestFocus(); //automatically sets the focus on the window after running
        
        
        
        double drawInterval = 1000000000/FPS;//draw the screen every 0.01666 seconds
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;
        
        while(gameThread != null){//as long as the thread exists, the method is executed
            
            currentTime = System.nanoTime();
            
            delta += (currentTime - lastTime) / drawInterval;
            
            timer += (currentTime - lastTime);
            
            lastTime = currentTime;
            
            if(delta >= 1){
                //1. UPDATE: Update information such as character position
                update(); 
                
                //2. DRAW: Draw the screen with the updated info
                repaint();
                
                
                delta--; //resets the delta
                
                drawCount++;
                
            }
            
            if(timer >= 1000000000){
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
            
            
            
        }
        
        
        
    }
    
    public void update(){
        
         player.update();
    }
    
    public void paintComponent(Graphics g){
        
        super.paintComponent(g);
        
        Graphics2D g2 = (Graphics2D)g;
        
        tileManager.draw(g2);
        player.draw(g2);
        
        g2.dispose(); //release any resources that this is using
        
    }
    
}
