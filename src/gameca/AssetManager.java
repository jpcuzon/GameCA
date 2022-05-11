/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gameca;

import Object.ObjCastle;
import Object.ObjChest;
import Object.ObjGate;
import Object.ObjVillageHouse;
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
        gp.object[0] = new ObjCastle();
        gp.object[0].worldX = (13/2) * gp.tileSize;
        gp.object[0].worldY = 3 * gp.tileSize;
        
        gp.object[1] = new ObjChest();
        gp.object[1].worldX = 40 * gp.tileSize;
        gp.object[1].worldY = 21 * gp.tileSize;
        
        gp.object[2] = new ObjGate();
        gp.object[2].worldX = 8 * gp.tileSize;
        gp.object[2].worldY = 14 * gp.tileSize;
        
        gp.object[3] = new ObjVillageHouse(1);
        gp.object[3].worldX = 13 * gp.tileSize;
        gp.object[3].worldY = 40 * gp.tileSize;
        
        gp.object[4] = new ObjVillageHouse(1);
        gp.object[4].worldX = 20 * gp.tileSize;
        gp.object[4].worldY = 45 * gp.tileSize;
        
        gp.object[5] = new ObjVillageHouse(1);
        gp.object[5].worldX = 17 * gp.tileSize;
        gp.object[5].worldY = 37 * gp.tileSize;
        
        gp.object[6] = new ObjVillageHouse(2);
        gp.object[6].worldX = 20 * gp.tileSize;
        gp.object[6].worldY = 41 * gp.tileSize;
        
        gp.object[7] = new ObjVillageHouse(2);
        gp.object[7].worldX = 12 * gp.tileSize;
        gp.object[7].worldY = 36 * gp.tileSize;
        
        gp.object[8] = new ObjVillageHouse(2);
        gp.object[8].worldX = 15 * gp.tileSize;
        gp.object[8].worldY = 44 * gp.tileSize;
    }
    
}
