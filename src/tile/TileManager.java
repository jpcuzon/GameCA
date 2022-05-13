/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tile;

import gameca.GamePanel;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;

/**
 *
 * @author gmcoa
 */
public class TileManager {
    
    private GamePanel gp;
    private Tile[] tile;
    private Tile[] miscTile;
    private int mapTileNum[][];
    
    
    public TileManager(GamePanel gp){
        this.gp = gp;
        
        tile = new Tile[20];
        miscTile = new Tile[5];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
        
        getTileImage();
//        loadMap("/maps/Map_Castle_01.txt"); //puts the location of file in the method call instead of hardcoding it inside the method for easy map access
        loadMap("/maps/WorldMap.txt"); //puts the location of file in the method call instead of hardcoding it inside the method for easy map access

    }
    
    
    public void getTileImage(){
        
//        setTile(0, "Background_Wall", true);
        setTile(1, "Background_Water_Left", true);
        setTile(2, "Background_Water_CornerL", true);
        setTile(3, "Background_Water_Top", true);
        setTile(4, "Background_Water_CornerR", true);
        setTile(5, "Background_Water_Right", true);
        setTile(6, "Background_Water", true);
        setTile(7, "Background_Tree", true);
        setTile(8, "Background_Dirt", false);
        setTile(9, "Background_Grass", false);
        setTile(10, "Background_Bridge", false);
        setTile(11, "Background_Wall", true);
        setTile(12, "Background_WallsideL", true);
        setTile(13, "Background_WallUpCornerL", true);
        setTile(14, "Background_WallUpCornerR", true);
        setTile(15, "Background_WallSideR", true);
        setTile(16, "Background_WallDownCornerL", true);
        setTile(17, "Background_WallDownCornerR", true);

    }
    
    //loads the image of a tile to the tile array
    public void setTile(int mapIndex, String mapData, boolean collision){
        
        try{
            tile[mapIndex] = new Tile();
            tile[mapIndex].image = ImageIO.read(getClass().getResourceAsStream("/tiles/"+ mapData +".png"));
            tile[mapIndex].collision = collision;
        }catch(IOException e){
            e.printStackTrace();
        }
        
    }
  
    
    // loads a map data from a text file
    public void loadMap(String mapData){
        
        try{
            
            InputStream is = getClass().getResourceAsStream(mapData);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            
            int col = 0;
            int row = 0;
            
            while(col < gp.maxWorldCol && row < gp.maxWorldRow){
                
                String line = br.readLine();
                
                while(col < gp.maxWorldCol){
                    String numbers[] = line.split(" ");
                    
                    
                    int num = Integer.parseInt(numbers[col]);
                    
                    mapTileNum[col][row] = num;
                    col++;
                }
                if(col == gp.maxWorldCol){
                    col = 0;
                    row++;
                }
                
                
            }
            br.close();
            
        }catch(IOException e){
            e.printStackTrace();
        }
        
        
    }
    
    // loads a map data from a text file
    public void loadWorldMap(String mapData){
        
        try{
            
            InputStream is = getClass().getResourceAsStream(mapData);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            
            int col = 0;
            int row = 0;
            
            while(col < gp.maxWorldCol && row < gp.maxWorldRow){
                
                String line = br.readLine();
                
                while(col < gp.maxWorldCol){
                    String numbers[] = line.split(" ");
                    
                    int num = Integer.parseInt(numbers[col]);
                    
                    mapTileNum[col][row] = num;
                    col++;
                }
                if(col == gp.maxWorldCol){
                    col = 0;
                    row++;
                }
                
                
            }
            br.close();
            
        }catch(IOException e){
            e.printStackTrace();
        }
        
        
    }
    
    public void draw(Graphics2D g2){

        int worldCol = 0;
        int worldRow = 0;
//        int x = 0;
       //      int y = 0;
        
        while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow){
            
            int tileNum = mapTileNum[worldCol][worldRow];
             
            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;
            
            //JP map
//For performance efficiency; Only draw the map that is on the screen instead of drawing the whole map
//            if(worldX > (gp.player.worldX)- (gp.player.screenX * 2) && worldX < (gp.player.worldX) + (gp.player.screenX* 15) 
//                    && worldY > gp.player.worldY - gp.player.screenY && worldY < gp.player.worldY + gp.player.screenY){ 
//                //since the castle is side-scrolling, we don'y need camera for Y for now
//                
//                g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
//            }
//          


             //For performance efficiency; Only draw the map that is on the screen instead of drawing the whole map
            if(
                    worldX + gp.tileSize > (gp.player.worldX)- (gp.player.screenX) && 
                    worldX - gp.tileSize < (gp.player.worldX) + (gp.player.screenX) && 
                    worldY + gp.tileSize > gp.player.worldY - gp.player.screenY && 
                    worldY - gp.tileSize < gp.player.worldY + gp.player.screenY){ 
                //since the castle is side-scrolling, we don'y need camera for Y for now
                
                g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            }
            
            
            worldCol++;
//            x += gp.tileSize;
           //when it reaches the end of the row, it changes to the next row 
            if(worldCol == gp.maxWorldCol){
                worldCol = 0;
//                x = 0;
                worldRow++;
//                y += gp.tileSize;
            }
            
        }
        
        
    }

    public GamePanel getGp() {
        return gp;
    }

    public void setGp(GamePanel gp) {
        this.gp = gp;
    }

    public Tile[] getTile() {
        return tile;
    }

    public void setTile(Tile[] tile) {
        this.tile = tile;
    }

    public Tile[] getMiscTile() {
        return miscTile;
    }

    public void setMiscTile(Tile[] miscTile) {
        this.miscTile = miscTile;
    }

    public int[][] getMapTileNum() {
        return mapTileNum;
    }

    public void setMapTileNum(int[][] mapTileNum) {
        this.mapTileNum = mapTileNum;
    }
    
    
    
    
}
