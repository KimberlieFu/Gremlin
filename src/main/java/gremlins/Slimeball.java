package gremlins;

import processing.core.PImage;
import processing.core.PApplet;

/**
 * Slimeball Class:
 * Instance of slimeball
 * Child: Item and Projectile
 */
public class Slimeball extends Projectile {

    // Slimeball constructor
    public Slimeball (App app, PImage sprite, int x, int y) {
        super(app, 4, false);

        this.sprite = sprite;
        this.collided = false;
        this.x = x;
        this.y = y;
    }

    /**
     * Handles logic of slimeball
     */
    @Override
    public void tick() {
    CollisionCheck.checkAllBlockCollision(app, this, app.blocks);
        if (app.wizard.getProjectileList() != null) {
            for (Projectile fireball : app.wizard.getProjectileList()) {
                boolean status = CollisionCheck.collide(app, this, fireball, 0);

                if (status == true) {
                    this.broken = true;
                }
            }
        }
    }
 
    /**
     * Draw the object
     * @param app Main application
     */
    @Override
    public void draw(PApplet app) {
        app.image(this.sprite, this.x, this.y);
    }
}
