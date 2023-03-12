/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.Players;

import javax.media.opengl.GL;

/**
 *
 * @author msii
 */
public class specialGift {

    float xPresent;
    float yPresent;
    float scale = 0.07f;
    float speed = 0.0f;
    int powerScore = 1;
    int SpecialpowerUpIndex = 17;
    GL gl;
    public Collision c = new Collision(xPresent, yPresent, 0.08f);

    public specialGift(GL gl, float xPresent, float yPresent, float spead) {
        this.gl = gl;
        this.xPresent = xPresent;
        this.yPresent = yPresent;
        this.speed = spead;
    }

    public float getxPresent() {
        return xPresent;
    }

    public void setxPresent(float xPresent) {
        this.xPresent = xPresent;
    }

    public float getyPresent() {
        return yPresent;
    }

    public void setyPresent(float yPresent) {
        this.yPresent = yPresent;
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

    public int getPowerScore() {
        return powerScore;
    }

    public void setPowerScore(int powerScore) {
        this.powerScore = powerScore;
    }

    public int getSpecialpowerUpIndex() {
        return SpecialpowerUpIndex;
    }

    public void setSpecialpowerUpIndex(int SpecialpowerUpIndex) {
        this.SpecialpowerUpIndex = SpecialpowerUpIndex;
    }
    
     public void drawSpecialPresent(GL gl) {
        gl.glEnable(GL.GL_BLEND);
        gl.glBindTexture(GL.GL_TEXTURE_2D, SpecialpowerUpIndex);	// Turn Blending On
        gl.glPushMatrix();
        gl.glTranslated(xPresent, yPresent, 1);
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
        c.drawCirclie(gl,xPresent,yPresent);
    }
      public void moveSpecial() {
        yPresent -= 0.009;
    }
}
