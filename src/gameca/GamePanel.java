
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gameca;

//import entity.MiscCandle;
import Object.Castle;
import Object.Object;
import entity.NPCWise;
import entity.Player;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import tile.TileManager;

/**
 *
 * @author gmcoa
 */
public class GamePanel extends JPanel implements Runnable{
    
    //Screen settings
    
    final int originalTileSize = 16; //16x16 tile 
    final int scale = 5;
    
    public final int tileSize = originalTileSize * scale; //48x48
    public final int maxScreenCol = 16;
    public final int maxScreenRow = (maxScreenCol * 9)/16;
    public final int screenWidth = tileSize * maxScreenCol;  //1536 pixels
    public final int screenHeight = tileSize * maxScreenRow; //864 pixels
    
//    //World Settings
//    public final int maxWorldCol = 96;  //number of columns in the map tile
//    public final int maxWorldRow = 9;
//    public final int worldWidth = tileSize * maxWorldCol;
//    public final int worldHeight = tileSize* maxWorldRow;
    
    //World Settings
    public final int maxWorldCol = 50;  //number of columns in the map tile
    public final int maxWorldRow = 50;
//    public final int worldWidth = tileSize * maxWorldCol;
//    public final int worldHeight = tileSize* maxWorldRow;
    
    
    //FPS
    int FPS = 60;
    
    //System
    TileManager tileManager = new TileManager(this);
    KeyHandler keyHandler = new KeyHandler();
    Sound soundEffect = new Sound();
    Sound music = new Sound();
    public CollisionCheck cCheck = new CollisionCheck(this);
    public AssetManager aManager = new AssetManager(this);
    public UI ui = new UI(this);
    Thread gameThread;
    
    //Entities and Objects
    public Player player = new Player(this, keyHandler);
    public NPCWise wise = new NPCWise(this);
    
    public Object object[]= new Object[10] ;  //array to store as many as 10 objects to be shown at a time
    
    
    
    
    
    
    
    public GamePanel(){
        
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true); //all drawing from this component will be done in an offscreen painting buffer; increases render performance
        this.addKeyListener(keyHandler);
        this.setFocusable(true); //frame can be focused to input
        
        
    }
    
    

    public void startGameThread(){
        
        gameThread = new Thread(this);
        gameThread.start();
        
    }
    
    public void setGame(){
    
//        setObject();
        aManager.setObj();
        
//        playMusic(0);
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
        
//        candle.update();
        wise.update();
        player.update();
    }
    
    //sets the bound limits of the object based on the screen size
    public static int clamp(int var, int min, int max){
        
        if(var >= max){
            return var = max;
        }else if(var <= min){
            return var = min;
        }else{return var;}
        
    }
    
    public void paintComponent(Graphics g){
        
        super.paintComponent(g);
        
        Graphics2D g2 = (Graphics2D)g;
       //draws tile 
        tileManager.draw(g2);
//        candle.draw(g2, tileSize*2,tileSize*5);
//        candle.draw(g2, tileSize*6,tileSize*5);
//        candle.draw(g2, tileSize*10,tileSize*5);
//        candle.draw(g2, tileSize*14,tileSize*5);

        //draws object
        for(int i = 0; i <object.length; i ++){
        //checks if the array is not empty before drawing
            if (object[i] != null){
                //draws the object
                object[i].Draw(this, g2);
            }
        }
        
        
        
        //draws entities
        
        wise.draw(g2, tileSize*3, tileSize*15);
        player.draw(g2);
        
        
        
        //UI
        ui.draw(g2);
        
        g2.dispose(); //release any resources that this is using
        
    }
    
    public void playMusic(int i){
        
        music.setFile(i);
        music.play();
        music.loop();
        
    }
    
    public void stopMusic(){
        music.stop();
    }
    
    public void playSoundEffect(int i){
        
        soundEffect.setFile(i);
        soundEffect.play();
    }
    
    //I moved them to the assetmanager
//    public void setObject(){
//        
//        //castle
//        object[0] = new Castle();
//        object[0].worldX = (13/2) * tileSize;
//        object[0].worldY = 3 * tileSize;
//        
//    }
    
    
    
}
