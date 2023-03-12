package main.Players;
//Manar
import javax.media.opengl.GL;

public class PowerUp {

    float xPresent;
    float yPresent;
    float scale = 0.08f;
    float speed = 0.0f;
    int powerScore = 1;
    int powerUpTextureIndex = 8;
    GL gl;
    public Collision c = new Collision(xPresent,yPresent,0.08f);
    public PowerUp(GL gl, float xPresent, float yPresent, float spead) {
        this.gl = gl;
        this.xPresent = xPresent;
        this.yPresent = yPresent;
        this.speed = spead;
    }
    public int getPowerScore() {
        return powerScore;
    }

    public void setPowerScore(int powerScore) {
        this.powerScore = powerScore;
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

    public int getPowerUpTextureIndex() {
        return powerUpTextureIndex;
    }

    public void setPowerUpTextureIndex(int powerUpTextureIndex) {
        this.powerUpTextureIndex = powerUpTextureIndex;
    }

    public GL getGl() {
        return gl;
    }

    public void setGl(GL gl) {
        this.gl = gl;
    }
    public void drawPresent(GL gl) {
        gl.glEnable(GL.GL_BLEND);
        gl.glBindTexture(GL.GL_TEXTURE_2D, powerUpTextureIndex);	// Turn Blending On
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

    public void movePresent() {
        yPresent -= 0.009;
    }
}
