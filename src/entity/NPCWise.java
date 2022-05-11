/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import gameca.GamePanel;
import gameca.KeyHandler;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author gmcoa
 */
public class NPCWise extends Entity{
    
    GamePanel gp;
    KeyHandler keyHandler;
    
    public final int screenX;
    public final int screenY;
    
    public NPCWise(GamePanel gp){
        this.gp = gp;
       //indicates where the player is drawn on the screen 
        //screenX = gp.tileSize;
//        screenY = gp.tileSize*7;
        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);
        
        worldX = gp.tileSize;
        worldY = gp.tileSize*7;
        
        solidArea = new Rectangle(8,16,gp.tileSize/2,gp.tileSize/2);
        
        solidAreaDefX = solidArea.x;
        solidAreaDefY = solidArea.y;
        setDefaultValues();
        getNPCImage();
        
    }
    
    public void getNPCImage(){
        
        try{
            NPCAnim1 = ImageIO.read(getClass().getResourceAsStream("/NPCWise/NPC_Anim_1.png"));
            NPCAnim2 = ImageIO.read(getClass().getResourceAsStream("/NPCWise/NPC_Anim_2.png"));
          
        }catch(IOException e){
            e.printStackTrace();
        }
        
    }
    
    public void setDefaultValues(){
        //where the player is drawn on the screen when the game starts
        worldX = gp.tileSize*3;
        worldY = gp.tileSize*15;
        
    }
    
    public void update(){
        
        spriteCounter++;
        if(spriteCounter > 15){
            switch(spriteNum){
                case 1:
                    spriteNum = 2;
                    break;
                case 2:
                    spriteNum = 1;
                    break;
            }
            spriteCounter = 0;
        }

    }
    
    public void draw(Graphics2D g2, int A, int B){
        

        BufferedImage image = null;
        
        
        if(spriteNum == 1){
            image = NPCAnim1;
        }
        if(spriteNum == 2){
            image = NPCAnim2;
        }
                
        g2.drawImage(image, A, B, gp.tileSize *2 , gp.tileSize * 2, null);
        
        
        
        
    }
        
}
        
