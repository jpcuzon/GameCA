/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Object;

import gameca.GamePanel;
import java.awt.Graphics2D;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author claud
 */
public class Castle extends Object {

    public Castle() {
        
        name = "Castle";
        
        try{
        
            image = ImageIO.read(getClass().getResourceAsStream("/objects/Castle.png"));
        }catch(IOException e){
        e.printStackTrace();}
        
    }

    @Override
    //changes the way this object is drawn to the screen
    public void Draw(GamePanel gp, Graphics2D g2) {
        
            screenX = worldX - gp.player.worldX + gp.player.screenX;
            screenY = worldY - gp.player.worldY + gp.player.screenY;
            size = gp.tileSize *5;
         
            //draws to the map
                
                g2.drawImage(image, screenX, screenY, size, size, null);
                
           
            }
    
    
    
}
