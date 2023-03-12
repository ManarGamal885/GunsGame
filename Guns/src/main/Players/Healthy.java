package main.Players;
//Manar
import javax.media.opengl.GL;

public class Healthy {

    float xHealthy;
    float yHealthy;
    float scale = 0.04f;
    float speed = 0.00f;
    boolean hitted;
    int healthyTextureIndex = 9;
    GL gl;

    public Collision c = new Collision(xHealthy, yHealthy, 0.05f);

    public Healthy(GL gl, float xHealthy, float yHealthy, float speed) {
        this.xHealthy = xHealthy;
        this.yHealthy = yHealthy;
    }

    public float getxHealthy() {
        return xHealthy;
    }

    public void setxHealthy(float xHealthy) {
        this.xHealthy = xHealthy;
    }

    public float getyHealthy() {
        return yHealthy;
    }

    public void setyHealthy(float yHealthy) {
        this.yHealthy = yHealthy;
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


    public void drawHealthy(GL gl) {
        gl.glEnable(GL.GL_BLEND);
        gl.glBindTexture(GL.GL_TEXTURE_2D, healthyTextureIndex);	// Turn Blending On
        gl.glPushMatrix();
        gl.glTranslated(xHealthy, yHealthy, 1);
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
        c.drawCirclie(gl, xHealthy, yHealthy);
    }

    public void moveHealthy() {
        yHealthy -= 0.01;
    }
}
