/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gameca;

import Object.Castle;
import Object.ObjChest;
import Object.ObjGate;
/**
 *
 * @author gmcoa
 */
public class AssetManager {
    
    GamePanel gp;
    
    public AssetManager(GamePanel gp){
        
        this.gp = gp;
    }
    
    public void setObj(){
        
          //castle
        gp.object[0] = new Castle();
        gp.object[0].worldX = (13/2) * gp.tileSize;
        gp.object[0].worldY = 3 * gp.tileSize;
        
        gp.object[1] = new ObjChest();
        gp.object[1].worldX = 2 * gp.tileSize;
        gp.object[1].worldY = 11 * gp.tileSize;
        
        gp.object[2] = new ObjGate();
        gp.object[2].worldX = 8 * gp.tileSize;
        gp.object[2].worldY = 10 * gp.tileSize;
        
    }
    
}
