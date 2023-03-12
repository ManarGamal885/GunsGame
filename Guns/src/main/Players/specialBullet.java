/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.Players;

import javax.media.opengl.GL;
import main.Enemys.Enemy;
import main.MainCode;

/**
 *
 * @author msii
 */
public class specialBullet {

    float xWorld;
    float yWorld;
    float scale = 0.09f;
    public float speed = 0.02f;
    GL gl;

    final int textureIndex = 18;

     public specialBullet(GL gl, float x, float y, float increasingVal) {
        this.gl = gl;
        xWorld = x;
        yWorld = y;

    }
    public Collision c = new Collision(xWorld, yWorld, 0.09f);

    public float getxWorld() {
        return xWorld;
    }

    public void setxWorld(float xWorld) {
        this.xWorld = xWorld;
    }

    public float getyWorld() {
        return yWorld;
    }

    public void setyWorld(float yWorld) {
        this.yWorld = yWorld;
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public Collision getC() {
        return c;
    }

    public void setC(Collision c) {
        this.c = c;
    }
    public void drawBullet(GL gl) {

        gl.glEnable(GL.GL_BLEND);
        gl.glBindTexture(GL.GL_TEXTURE_2D, textureIndex);	// Turn Blending On

        //gl.glColor3f(1, 1, 0);
        gl.glPushMatrix();

        gl.glTranslated((xWorld), (yWorld), 1);
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
