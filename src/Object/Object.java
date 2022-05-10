/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Object;

import gameca.GamePanel;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 *
 * @author claud
 */
public class Object {
    
    public BufferedImage image;
    public String name;
    public boolean collision = false;
    public int worldX, worldY,screenX,screenY,size;
    
    public Rectangle solidArea = new Rectangle(0,0,80,80);
    public int solidAreaDefX = solidArea.x;
    public int solidAreaDefY = solidArea.y;
    
    public void Draw(GamePanel gp, Graphics2D g2){
    
            screenX = worldX - gp.player.worldX + gp.player.screenX;
            screenY = worldY - gp.player.worldY + gp.player.screenY;
            size = gp.tileSize;
         
            //same draw method as the tile manager 
           
            if(
                    worldX + gp.tileSize > (gp.player.worldX)- (gp.player.screenX) && 
                    worldX - gp.tileSize < (gp.player.worldX) + (gp.player.screenX) && 
                    worldY + gp.tileSize > gp.player.worldY - gp.player.screenY && 
                    worldY - gp.tileSize < gp.player.worldY + gp.player.screenY){ 
                
                g2.drawImage(image, screenX, screenY, size, size, null);
                
           
            }
    }

    public boolean isCollision() {
        return collision;
    }
    
    
    public void setCollision(boolean collision) {
        this.collision = collision;
    }
    
}
