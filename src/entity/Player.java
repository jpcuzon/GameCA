/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import gameca.GamePanel;
import gameca.KeyHandler;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author gmcoa
 */
public class Player extends Entity{
    
    GamePanel gp;
    KeyHandler keyHandler;
    
    
    public final int screenX;
    public final int screenY;
    
    public Player(GamePanel gp, KeyHandler keyHandler){
        this.gp = gp;
        this.keyHandler = keyHandler;
        
        screenX = gp.tileSize;
        screenY = gp.tileSize*7;
        y = gp.tileSize*7;
        
        setDefaultValues();
        getPlayerImage();
        
    }
    
    public void setDefaultValues(){
        
        worldX = gp.tileSize;
        worldY = gp.tileSize*7;
        speed = 7;
        direction = "down";
        
    }
    
    public void getPlayerImage(){
        
        try{
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/Walk_Up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/Walk_Up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/Walk_Down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/Walk_Down_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/Walk_Left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/Walk_Left_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/Walk_Right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/Walk_Right_2.png"));
            
            
        }catch(IOException e){
            e.printStackTrace();
        }
        
    }
    
    
    public void update(){
        
        if(keyHandler.upPressed == true || keyHandler.downPressed == true  //only animates the sprite when a key is pressed
                || keyHandler.leftPressed == true || keyHandler.rightPressed == true){
            
            
            if(keyHandler.upPressed == true){
                direction = "up";
                y -= speed;
            }else if(keyHandler.downPressed == true){
                direction = "down";
                y += speed;
            }else if(keyHandler.leftPressed == true){
                direction = "left";
                worldX -= speed;
            }else if(keyHandler.rightPressed == true){
                direction = "right";
                worldX += speed;
            }

//            worldX = gp.clamp(worldX, 0, gp.screenWidth - (gp.tileSize*2));
            worldX = gp.clamp(worldX, gp.tileSize, gp.worldWidth);
            y = gp.clamp(y, gp.screenHeight - (gp.tileSize*3), gp.screenHeight - (gp.tileSize*2));
            
            spriteCounter++;
            if(spriteCounter > 15){
                if(spriteNum == 1){
                    spriteNum = 2;
                }
                else if(spriteNum == 2){
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
            
        }
        
        
       
    }
    
    public void draw(Graphics2D g2){
        

        BufferedImage image = null;
        
        switch(direction){
            case "up": 
                if(spriteNum == 1){
                    image = up1;
                }
                if(spriteNum == 2){
                    image = up2;
                }
                break;
            case "down": 
                if(spriteNum == 1){
                    image = down1;
                }
                if(spriteNum == 2){
                    image = down2;
                }
                break;
            case "left":
                if(spriteNum == 1){
                    image = left1;
                }
                if(spriteNum == 2){
                    image = left2;
                }
                break;
            case "right":
                if(spriteNum == 1){
                    image = right1;
                }
                if(spriteNum == 2){
                    image = right2;
                }
                break;
            
            
        }
        g2.drawImage(image, screenX, y, gp.tileSize *2 , gp.tileSize * 2, null);
        
        
        
        
    }
    
    
}
