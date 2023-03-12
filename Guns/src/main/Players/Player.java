package main.Players;

import main.Enemys.Enemy;
import main.Keys.HandleKeys;
import main.MainCode;
import javax.media.opengl.GL;

public class Player {

    //Enemy setting
    float xWorld;
    float yWorld;
    public float scale = 0.1f;
    public float speed = 0.09f;
    //Manar: power score and life score to check on it
    public int liveScore = 5;
    public int powerScore = 1;
    public int maxSpecialBullet = 10;
    public boolean specialHitted;

    public boolean isSpecialHitted() {
        return specialHitted;
    }

    public void setSpecialHitted(boolean specialHitted) {
        this.specialHitted = specialHitted;
    }
    //Keyboard orders
    HandleKeys key;
    //default object to attach the class with the maincode class
    public Collision c = new Collision(0 * speed * scale, 0 * speed * scale, 0.09f);
    GL gl;
    int textureIndex = 1;

    public Player(GL gl, HandleKeys key, float x, float y) {
        this.key = key;
        this.gl = gl;

        xWorld = x;
        yWorld = y;
        speed = 0.09f;

    }

    public Player(GL gl, HandleKeys key) {
        this.key = key;
        this.gl = gl;

        xWorld = 0f;
        yWorld = -100f;
        speed = 0.09f;
    }

    public void setXWorld(float x) {
        xWorld = x;
    }

    public float getXWorld() {
        return this.xWorld;
    }

    public void setYWorld(float y) {
        yWorld = y;
    }

    public float getYWorld() {
        return this.yWorld;
    }

    public void setliveScore(float liveScore) {
        liveScore = liveScore;
    }

    public float getliveScore() {
        return this.liveScore;
    }

    public void drawPlayer(GL gl) {

        gl.glEnable(GL.GL_BLEND);
        gl.glBindTexture(GL.GL_TEXTURE_2D, textureIndex);	// Turn Blending On

        //gl.glColor3f(1, 1, 0);
        gl.glPushMatrix();

        gl.glTranslated((xWorld) * scale * speed, (yWorld) * scale * speed, 1);
        //System.out.println("xWorld: " + xWorld +  ", yWorld: " + yWorld );
        gl.glScaled(scale, scale, 1);
        gl.glBegin(GL.GL_QUADS);
        // Front Face

        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(-1.0f, -1.0f, -1.0f);
        gl.glTexCoord2f(1.0f, 0.0f);
        gl.glVertex3f(1.0f, -1.0f, -1.0f);
        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(1.0f, 1.0f, -1.0f);
        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glVertex3f(-1.0f, 1.0f, -1.0f);
        gl.glEnd();
        gl.glPopMatrix();
        gl.glDisable(GL.GL_BLEND);
        c.drawCirclie(gl, xWorld * scale * speed, yWorld * scale * speed);

    }

    public void move(double maxX, double maxY) {

        if (key.isKeyPressed(key.UP)) {
            if (yWorld < maxY) {

                yWorld++;
                textureIndex++;
                textureIndex = (textureIndex % 4) + 1;

            }
        } else if (key.isKeyPressed(key.DOWN)) {
            if (yWorld > -100) {

                yWorld--;
                textureIndex++;
                textureIndex = (textureIndex % 4) + 1;
            }
        } else if (key.isKeyPressed(key.LEFT)) {
            if (xWorld + 5 > -100) {

                xWorld--;
                textureIndex = (textureIndex % 4) + 1;
            }
        } else if (key.isKeyPressed(key.RIGHT)) {
            if (xWorld - 5 < maxX) {
                xWorld++;
                textureIndex = (textureIndex % 4) + 1;
            }
        }
    }

    public void resolveColision(Object c2) {
        //Manar: resolve collesion between healthy ,enemy and power up and change the liveScore & powerScore vars
        if (c2 instanceof Enemy) {
            if (detectCollision(((Enemy) c2).c)) {
                liveScore--;
                MainCode.destroy((Enemy) c2);
            }
        } else if (c2 instanceof PowerUp) {
            if (detectCollision(((PowerUp) c2).c)) {
                MainCode.destroy((PowerUp) c2);
                powerScore++;
                specialHitted = false;

            }

        } else if (c2 instanceof Healthy) {
            if (detectCollision(((Healthy) c2).c)) {
                MainCode.destroy((Healthy) c2);
                if (liveScore < 5) {
                    liveScore++;
                }
                System.out.println(liveScore);
            }
        } else if (c2 instanceof specialGift) {
            if (detectCollision(((specialGift) c2).c)) {
                MainCode.destroy((specialGift) c2);
                MainCode.hittedSpecial = true;
//                MainCode.timeCountspecialAttack++;
                
                specialHitted = true;
                MainCode.frameCountspecialAtttack = 0;
               
            }
        }

    }

    public boolean detectCollision(Collision c2) {
        double offset = 0.01;
        double r = (c.r - offset) + (c2.r - offset);

        return (Math.abs(c.x - c2.x) <= r) && (Math.abs(c.y - c2.y) <= r);

    }

}
