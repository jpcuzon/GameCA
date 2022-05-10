/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import gameca.GamePanel;
import gameca.KeyHandler;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
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
        getPlayerImage();
        
    }
    
    public void setDefaultValues(){
        //where the player is drawn on the screen when the game starts
        worldX = gp.tileSize*8;
        worldY = gp.tileSize*15;
        speed = 6;
        direction = "down";
        
    }
    
    public void getPlayerImage(){
        
        try{
            up1 = ImageIO.read(getClass().getResourceAsStream("/warrior/Walk_Up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/warrior/Walk_Up_2.png"));
            up3 = ImageIO.read(getClass().getResourceAsStream("/warrior/Walk_Up_3.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/warrior/Walk_Down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/warrior/Walk_Down_2.png"));
            down3 = ImageIO.read(getClass().getResourceAsStream("/warrior/Walk_Down_3.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/warrior/Walk_Left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/warrior/Walk_Left_2.png"));
            left3 = ImageIO.read(getClass().getResourceAsStream("/warrior/Walk_Left_3.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/warrior/Walk_Right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/warrior/Walk_Right_2.png"));
            right3 = ImageIO.read(getClass().getResourceAsStream("/warrior/Walk_Right_3.png"));
            
            
        }catch(IOException e){
            e.printStackTrace();
        }
        
    }

      
    public void update(){
        //The player runs/moves faster when space is pressed
        if(keyHandler.shiftPressed == true){
            speed = 8;
        }else{speed = 5;}
        
        if(keyHandler.upPressed == true || keyHandler.downPressed == true  //only animates the sprite when a key is pressed
                || keyHandler.leftPressed == true || keyHandler.rightPressed == true){
            
            
            if(keyHandler.upPressed == true){
                direction = "up";
            }else if(keyHandler.downPressed == true){
                direction = "down";
            }else if(keyHandler.leftPressed == true){
                direction = "left";
            }else if(keyHandler.rightPressed == true){
                direction = "right";
            }
            
            //this checks if there is tile collision and if it happpens the player cannot move in that direction
            collisionOn = false;
            gp.cCheck.checkTile(this);
            
            //this checks if there is an object collision and prevents the player to walk through it
            int objectIndex = gp.cCheck.checkObject(this, true);
            
            if(keyHandler.actionPressed == true){
                System.out.println(key);
                openObject(objectIndex);
            }
            
//            if(keyHandler.actionPressed == true && objectIndex == 1){
//                openChest(objectIndex);
//                key++;
//            }
//            openGate(objectIndex);
//            openChest(objectIndex);
            
            if(collisionOn == false){
                
                switch(direction){
                    case"up":
                        worldY -= speed;
                        break;
                    case"down":
                        worldY += speed;
                        break;
                    case"left":
                        worldX -= speed;
                        break;
                    case"right":
                        worldX += speed;
                        break;
                        
                }
            }
            
           //Clamp
           
//            worldX = gp.clamp(worldX, 0, gp.screenWidth - (gp.tileSize*2));
           // worldX = gp.clamp(worldX, gp.tileSize, gp.worldWidth);
//            y = gp.clamp(y, gp.screenHeight - (gp.tileSize*3), gp.screenHeight - (gp.tileSize*2));
           // worldY = gp.clamp(worldY, gp.screenHeight - (gp.tileSize*3), gp.screenHeight - (gp.tileSize*2));

            
            spriteCounter++;
            if(spriteCounter > 10){
                switch(spriteNum){
                    case 1:
                        spriteNum = 2;
                        break;
                    case 2:
                        spriteNum = 3;
                        break;
                    case 3:
                        spriteNum = 4;
                        break;
                    case 4:
                        spriteNum = 1;
                        break;
                }
                spriteCounter = 0;
            }
            
        }
        
        
        
        
       
    }
    
    public void openObject(int i){
        
        if(i!=999){  //999 is just a default value we put to determine that no object is touched
            
            String objName = gp.object[i].name;
            
            switch(objName){
                case "Chest":
                    if(gp.object[i].isOpen() == false){
                        try{
                        gp.object[i].image = ImageIO.read(getClass().getResourceAsStream("/Objects/Obj_Chest_Opened.png"));
                        }catch(IOException e){
                            e.printStackTrace();
                        }
                        gp.object[i].setOpen(true);
                        key++;
                    }
                    
                    break;
                case "Gate":
                    if(key>0 && gp.object[i].isOpen() == false){
                        gp.object[i].setCollision(false);
                        try{
                            gp.object[i].image = ImageIO.read(getClass().getResourceAsStream("/Objects/Obj_Gate_Opened.png"));
                        }catch(IOException e){
                            e.printStackTrace();
                        }
                        key--;
                    }
                    break;
                
            }
            
            
        }
        
    }
    
    public void openGate(int i){
        
        if(i!=999){  //999 is just a default value we put to determine that no object is touched
            
            if(i==2){
                //changes the image of the gate to an opened state

                gp.object[i].setCollision(false);
                try{
                    gp.object[i].image = ImageIO.read(getClass().getResourceAsStream("/Objects/Obj_Gate_Opened.png"));
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
            
            
        }
        
    }

    public void openChest(int i){
        
        if(i!=999){  //999 is just a default value we put to determine that no object is touched
            
            if(i==1){
                //changes the image of the gate to an opened state

                try{
                    gp.object[i].image = ImageIO.read(getClass().getResourceAsStream("/Objects/Obj_Chest_Opened.png"));
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
            
            
        }
        
    }
    
    
    public void draw(Graphics2D g2){
        

        BufferedImage image = null;
        
        switch(direction){
            case "up": 
            if(keyHandler.upPressed == true){ //check if the keys are pressed and sets the image to a default pose if unpressed.
                switch(spriteNum){
                    case 1:
                        image = up1;
                        break;
                    case 2:
                        image = up2;
                        break;
                    case 3:
                        image = up1;   
                        break; 
                    case 4:
                        image = up3;
                        break;
                }
            }else{image = up1;}
            break;
            
            case "down": 
                if(keyHandler.downPressed == true){ //check if the keys are pressed and sets the image to a default pose if unpressed.
                switch(spriteNum){
                    case 1:
                        image = down1;
                        break;
                    case 2:
                        image = down2;
                        break;
                    case 3:
                        image = down1;   
                        break; 
                    case 4:
                        image = down3;
                        break;
                }
            }else{image = down1;}
            break;
            case "left":
                if(keyHandler.leftPressed == true){ //check if the keys are pressed and sets the image to a default pose if unpressed.
                switch(spriteNum){
                    case 1:
                        image = left1;
                        break;
                    case 2:
                        image = left2;
                        break;
                    case 3:
                        image = left1;   
                        break; 
                    case 4:
                        image = left3;
                        break;
                }
                }else{image = left1;}
                break;
            case "right":
                if(keyHandler.rightPressed == true){ //check if the keys are pressed and sets the image to a default pose if unpressed.
                switch(spriteNum){
                    case 1:
                        image = right1;
                        break;
                    case 2:
                        image = right2;
                        break;
                    case 3:
                        image = right1;   
                        break; 
                    case 4:
                        image = right3;
                        break;
                }
            }else{image = right1;}
                break;
            
            
        }
        g2.drawImage(image, screenX, screenY, gp.tileSize , gp.tileSize , null);
        
        
        
        
    }
    
    
}
