///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package entity;
//
//import gameca.GamePanel;
//import gameca.KeyHandler;
//import java.awt.Graphics2D;
//import java.awt.image.BufferedImage;
//import java.io.IOException;
//import javax.imageio.ImageIO;
//
///**
// *
// * @author gmcoa
// */
//public class MiscCandle extends Entity{
//    
//    GamePanel gp;
//    
//    public MiscCandle(GamePanel gp){
//        this.gp = gp;
//        
//        setDefaultValues();
//        getPlayerImage();
//        
//    }
//    
//    public void setDefaultValues(){
//        
//        x = 100;
//        y = 672;
//        
//        
//    }
//    
//    public void getPlayerImage(){
//        
//        try{
//            candAnim1 = ImageIO.read(getClass().getResourceAsStream("/misc/Misc_Candle_1.png"));
//            candAnim2 = ImageIO.read(getClass().getResourceAsStream("/misc/Misc_Candle_2.png"));
//            
//            
//            
//        }catch(IOException e){
//            e.printStackTrace();
//        }
//        
//    }
//    
//    
//    public void update(){
//  
//
//        spriteCounter++;
//        if(spriteCounter > 15){
//            if(spriteNum == 1){
//                spriteNum = 2;
//            }
//            else if(spriteNum == 2){
//                spriteNum = 1;
//            }
//            spriteCounter = 0;
//        }
//            
//        
//        
//        
//       
//    }
//    
//    public void draw(Graphics2D g2, int A, int B){
//        
////        g2.setColor(Color.white);
////        g2.fillRect(x, y, gp.tileSize, gp.tileSize);
//
//        BufferedImage image = null;
//        
//        
//            
//        if(spriteNum == 1){
//            image = candAnim1;
//        }
//        if(spriteNum == 2){
//            image = candAnim2;
//        }
//                
//        g2.drawImage(image, A, B, gp.tileSize *2 , gp.tileSize * 2, null);
//        
//        
//        
//        
//    }
//    
//    
//}
//    
//
