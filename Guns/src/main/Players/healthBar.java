package main.Players;
//Manar
import javax.media.opengl.GL;

public class healthBar {

    int barIdx;
    float xBar;
    float yBar;
    float scaley = 0.03f;
    float scalex = 0.15f;
    float speed = 0.00f;

    public int getBarIdx() {
        return barIdx;
    }

    public void setBarIdx(int barIdx) {
        this.barIdx = barIdx;
    }

    public healthBar(int barIdx, float xBar, float yBar) {
        this.barIdx = barIdx;
        this.xBar = xBar;
        this.yBar = yBar;
    }

    public void drawHealthyBar(GL gl, int index) {
        gl.glEnable(GL.GL_BLEND);
        gl.glBindTexture(GL.GL_TEXTURE_2D, index);	// Turn Blending On
        gl.glPushMatrix();
        gl.glTranslated(xBar, yBar, 1);
        gl.glScaled(scalex, scaley, 1);
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
    }

    public float getxBar() {
        return xBar;
    }

    public void setxBar(float xBar) {
        this.xBar = xBar;
    }

    public float getyBar() {
        return yBar;
    }

    public void setyBar(float yBar) {
        this.yBar = yBar;
    }
}
