package gremlins;

import processing.core.PImage;
import processing.core.PApplet;

/**
 * Door Class: 
 * Instance of a door
 * Child: Item and Block
 */
public class Door extends Block {

    // DoorBlock constructor
    public Door(App app, PImage sprite, int x, int y) {
        this.app = app;
        this.sprite = sprite;
        this.x = x;
        this.y = y;
    }

    /**
     * Handles logic of door
     */
    @Override
    public void tick() {  
    }

    /**
     * Draw the object.
     * @param app Main application
     */
    @Override
    public void draw(PApplet app) {
        app.image(this.sprite, this.x, this.y);
    }
}
