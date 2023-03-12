package main.Players;

import main.Keys.HandleKeys;
import Textures.TextureReader;
import java.util.ArrayList;
import java.util.Objects;
import javax.media.opengl.GL;
import main.Enemys.Enemy;
import main.MainCode;

public class Bullet {

    float xWorld;
    float yWorld;
    float scale = 0.02f;
    public float speed = 0.02f;
    public Collision c = new Collision(xWorld, yWorld, 0.03f);
    //Manar: adding increasingVal to bullets Translation
    public float increasingVal;

    public float getIncreasingVal() {
        return increasingVal;
    }

    public void setIncreasingVal(float increasingVal) {
        this.increasingVal = increasingVal;
    }

    //default object to attach the class with the maincode class
    GL gl;

    final int textureIndex = 7;

    public Bullet(GL gl, float x, float y, float increasingVal) {
        this.gl = gl;
        this.increasingVal = increasingVal;
        xWorld = x;
        yWorld = y;

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

    public void drawBullet(GL gl) {

        gl.glEnable(GL.GL_BLEND);
        gl.glBindTexture(GL.GL_TEXTURE_2D, textureIndex);	// Turn Blending On

        //gl.glColor3f(1, 1, 0);
        gl.glPushMatrix();

        gl.glTranslated((xWorld), (yWorld), 1);
        xWorld += increasingVal;
        yWorld += increasingVal;
        gl.glScaled(scale, scale, 1);
        gl.glBegin(GL.GL_QUADS);
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
        c.drawCirclie(gl, xWorld , yWorld);

    }
    public void resolveColision(Object c2) {
        //Manar: resolve collesion between healthy ,enemy and power up and change the liveScore & powerScore vars
        if (c2 instanceof Enemy) {
            if (detectCollision(((Enemy) c2).c)) {
                MainCode.destroy((Enemy) c2);
                PowerUp p = new PowerUp(gl, ((Enemy) c2).getXWorld(), ((Enemy) c2).getYWorld(), 0.01f);
                MainCode.presentsList.add(p);
            }
        }

    }

    public boolean detectCollision(Collision c2) {
        double offset = 0.01;
        double r = (c.r - offset) + (c2.r - offset);

        return (Math.abs(c.x - c2.x) <= r) && (Math.abs(c.y - c2.y) <= r);

    }

}
