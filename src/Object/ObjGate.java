/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Object;

import gameca.GamePanel;
import java.awt.Graphics2D;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author gmcoa
 */
public class ObjGate extends Object{
    
    public ObjGate(){
        
        name = "Gate";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/Objects/Obj_Gate_Closed.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
        
        collision = true;
        
        
    }
//
//    @Override
//    //changes the way this object is drawn to the screen
//    public void Draw(GamePanel gp, Graphics2D g2) {
//        
//            screenX = worldX - gp.player.worldX + gp.player.screenX;
//            screenY = worldY - gp.player.worldY + gp.player.screenY;
//            size = gp.tileSize;
//            
//         
//            //draws to the map
//                
//                g2.drawImage(image, screenX, screenY, size, size, null);
//                
//           
//            }
//    
    
}
