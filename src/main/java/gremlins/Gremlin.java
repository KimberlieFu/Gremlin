package gremlins;

import processing.core.PImage;
import processing.core.PApplet;
import java.util.*;

/**
 * Gremlin Class: 
 * Instance of a gremlin
 * Child: Item and Character
 */
public class Gremlin extends Character {
    private int upperBound = 4;
    private int currentDirection = 4;

    // Gremlin constructor
    public Gremlin(App app, PImage up, PImage down, PImage left, PImage right) {
        super (app, 1, false);
        this.projectileList = new ArrayList<Projectile>();
        
        this.yVel = 0;
        this.xVel = 0;

        this.up = up;
        this.down = down;
        this.left = left;
        this.right = right;

        this.direction = "right";

        this.projectileTimer = app.slimeballTimer;
        this.alive = true;
    }

    /**
     * Gremlin shoot projectile if timer is at 0
     */
    public void shoot() {
        Projectile slimeball = new Slimeball(app, app.slimeballImage, this.x, this.y);
        slimeball.shootDirection(app, this);
        this.projectileList.add(slimeball);
    }

    /**
     * Set gremlin direction to random
     * Does not select a direction from where it just came from
     * @param currentDirection Gremlin's initial direction
     * @param upperbound Upperbound for random selection
     * @return Gremlin's new direction
     */
    public int getRandomDirection(int currentDirection, int upperbound) {
        int result = MapLoader.getRandom(upperBound);

        if (result == currentDirection) {
            return getRandomDirection(currentDirection, upperbound);
        } 
        return result;
    }

    /**
     * Set the gremlin's movement to the direction
     * @param directionNumber Numeber that corresponds to a direction
     */
    public void setDirection(int directionNumber) {
        if (directionNumber == 0) {
            this.up();

        } else if (directionNumber == 1) {
            this.down();

        } else if (directionNumber == 2) {
            this.right();
        
        } else if (directionNumber == 3) {
            this.left();
        
        }
    }

    /**
     * Get gremlin's current direction
     * @return Gremlin's current direction
     */
    public int getCurrentDirection() {
        return currentDirection;
    }

    /**
     * Get Gremlin's slimeballs
     * @return List of slimeballs
     */
    public ArrayList<Projectile> getProjectileList() {
        return this.projectileList;
    }


    /**
     * Handles logic of gremlin
     */
    @Override
    public void tick() {
        CollisionCheck.checkAllBlockCollision(app, this, app.blocks);

        // If fireball hit gremlin
        if (app.wizard.getProjectileList() != null) {
            for (Projectile fireball : app.wizard.getProjectileList()) {
                boolean status = CollisionCheck.collide(app, this, fireball, 0);

                if (status == true) {
                    this.alive = false;
                }
            }
        }

        // Gremlin's slimeball timer
        if (this.projectileTimer <= 0.0) {

            if (app.wizard.getCurrentPowerUp() != 1) {
                this.shoot();
                this.projectileTimer = app.slimeballTimer + MapLoader.getRandom(200);

            }
            
        } else {
            this.projectileTimer --;
        }

        // If gremlin hit the wall set a new direction
        if (this.collisionOn == true) {
            this.centralizeX(app, app.blocks);
            this.centralizeY(app, app.blocks);
            this.setDirection(MapLoader.getRandom(currentDirection));
        }
    }


    /**
     * Draw the object
     * @param app Main application
     */
    @Override
    public void draw(PApplet app) {
        if (direction == "right") {
            app.image(this.right, this.x, this.y);

        } else if (direction == "left") {
            app.image(this.left, this.x, this.y);

        } else if (direction == "up") {
            app.image(this.up, this.x, this.y);

        } else if (direction == "down") {
            app.image(this.down, this.x, this.y);

        }
    }
}
