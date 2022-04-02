/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.awt.image.BufferedImage;

/**
 *
 * @author gmcoa
 */
public class Entity {
    
    public int worldX, worldY, y;
    public int speed;
    
    public BufferedImage up1, up2, left1, left2, right1,right2,down1,down2;
    public BufferedImage candAnim1, candAnim2;
    public String direction;
    
    public int spriteCounter = 0;
    public int spriteNum = 1;
    
}
