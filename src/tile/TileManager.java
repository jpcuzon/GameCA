/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tile;

import gameca.GamePanel;
import java.awt.Graphics2D;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author gmcoa
 */
public class TileManager {
    
    GamePanel gp;
    Tile[] tile;
    
    
    public TileManager(GamePanel gp){
        this.gp = gp;
        
        tile = new Tile[10];
        
        getTileImage();
        
    }
    
    public void getTileImage(){
        
        try{
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Background_Grass.png"));
            
            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Background_Puddle.png"));
            
            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Background_Mud.png"));
            
        }catch(IOException e){
            e.printStackTrace();
        }
        
        
    }
    
    public void draw(Graphics2D g2){
        
        for(int i = 0; i<16;i++){
            for(int j = 0; j<16; j++){
                g2.drawImage(tile[0].image,(i*96),(j*96), gp.tileSize, gp.tileSize, null);
            }
            
        }
       
//        g2.drawImage(tile[0].image,0,0, gp.tileSize, gp.tileSize, null);
//        g2.drawImage(tile[1].image,96,0, gp.tileSize, gp.tileSize, null);
//        g2.drawImage(tile[0].image,192,0, gp.tileSize, gp.tileSize, null);
//        g2.drawImage(tile[1].image,288,0, gp.tileSize, gp.tileSize, null);
//        g2.drawImage(tile[0].image,384,0, gp.tileSize, gp.tileSize, null);
        
        
    }
    
    
}
