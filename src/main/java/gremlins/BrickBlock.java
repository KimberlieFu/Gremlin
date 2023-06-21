package gremlins;

import processing.core.PImage;
import processing.core.PApplet;

/**
 * BrickBlock Class: 
 * Instance of a brick block
 * Child: Item and Block
 */
public class BrickBlock extends Block {
    private int breakTimer = 4;

    // BrickBlock Constructor
    public BrickBlock(App app, PImage sprite, int x, int y) {
        this.breakTimer = 0;
        this.breakAnimation = false;
        this.broken = false;
        this.sprite = sprite;
        this.x = x;
        this.y = y;
    }

    /**
     * Handles logic of BrickBlock
     */
    @Override
    public void tick() {
        if (this.breakAnimation == true) {
            if (this.breakTimer == 0) {
                this.breakTimer = 1;
 
            } else if (this.breakTimer == 4){
                this.broken = true;
                
            } else {
                this.breakTimer += 1;

            }
        }
    }

    /**
     * Draw the object
     * @param app Main application
     */
    @Override
    public void draw(PApplet app) {
        if (this.breakTimer == 1) {
            app.image(this.part1, this.x, this.y);
        
        } else if (this.breakTimer == 2){
            app.image(this.part2, this.x, this.y);

        } else if (this.breakTimer == 3) {
            app.image(this.part3, this.x, this.y);
        
        } else if (this.breakTimer == 4) {
            app.image(this.part4, this.x, this.y);

        } else {
            app.image(this.sprite, this.x, this.y);

        }
    }   
}
