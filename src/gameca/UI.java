/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gameca;

import Object.ObjKey;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author gmcoa
 */
public class UI {
    
    GamePanel gp;
    Font arial_40;
    BufferedImage keyImage;
    public boolean messageOn = false;
    public String message = "";
    
    public UI(GamePanel gp){
        
        this.gp = gp;
        arial_40 = new Font("Lato", Font.ROMAN_BASELINE, 40);
        ObjKey key = new ObjKey();
        keyImage = key.image;
        
    }
    
    public void showMessage(String text){
        message = text;
        messageOn = true;
    }
    
    public void draw(Graphics2D g2){
        
        g2.setFont(arial_40);
        g2.setColor(Color.red);
        g2.drawImage(keyImage, 20, 15, 48 ,48, null);
        g2.drawString("x = " + gp.player.getKey(), 60, 50);
        
        if(messageOn = true){
            
            g2.setFont(g2.getFont().deriveFont(30F));
            g2.drawString(message, gp.tileSize/2, gp.tileSize*5);
            
        }
        
    }
    
}
