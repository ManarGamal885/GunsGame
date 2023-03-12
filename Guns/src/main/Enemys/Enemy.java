package main.Enemys;


import main.Keys.HandleKeys;
import javax.media.opengl.GL;
import main.MainCode;
import main.Players.Collision;
import main.Players.PowerUp;
import main.Players.Bullet;



public class Enemy  {
    
    //Enemy setting
    float xWorld;
    float yWorld;
    public float scale = 0.1f;
    public float speed;
    float angle;
    float startAngle = 90;
    
    //Keyboard orders
    HandleKeys key;
    public Collision c= new Collision(xWorld*speed*scale,yWorld*speed*scale,0.09f);
    
    //default object to attach the class with the maincode class
    GL gl;
    
    final int textureIndex = 6;
    
    public Enemy(GL  gl, HandleKeys key, float x, float y)
    {
       this.key = key;
       this.gl = gl;
       
       xWorld = x;
       yWorld = y;
       speed = 0.05f;
       angle = 0; 
       
    }
    
    public Enemy(GL  gl, HandleKeys key)
    {
       this.key = key;
       this.gl = gl;
       
       xWorld = -100f;
       yWorld = 100f;
       speed = 0.05f;
       angle = 0; 
       
    }
    
    public void setXWorld(float x)
    {
        xWorld = x;
    }
    
    public float getXWorld()
    {
        return this.xWorld;
    }
    
    public void setYWorld(float y)
    {
        yWorld = y;
    }
    
    public float getYWorld()
    {
        return this.yWorld;
    }
    
    public void drawSprite(GL gl)
    {
        
        
        gl.glEnable(GL.GL_BLEND);
        gl.glBindTexture(GL.GL_TEXTURE_2D, textureIndex);	// Turn Blending On
        
        //gl.glColor3f(1, 1, 0);
        gl.glPushMatrix();
        
        gl.glTranslated((xWorld * scale) * speed, (yWorld * scale) * speed, 1);
        gl.glRotatef(angle , 0, 0, 1);
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
        c.drawCirclie(gl,xWorld*speed*scale,yWorld*speed*scale);

        
    }
    
    
    public void move()
    {
        // sin(45) and cos(45)  = factor
        float factor =(float) (Math.sqrt(2)/2);
        
        
        if((key.isKeyPressed(key.UP))&&(key.isKeyPressed(key.LEFT)))
        {
            angle = 135 - startAngle;
            xWorld  -= factor;
            yWorld  += factor;
        }
        
        else if((key.isKeyPressed(key.UP))&&(key.isKeyPressed(key.RIGHT)))
        {
            angle = 45 - startAngle;
            xWorld += factor;
            yWorld  += factor;
        }
        else if((key.isKeyPressed(key.DOWN))&&(key.isKeyPressed(key.LEFT)))
        {
            angle = 225 - startAngle ;
            xWorld -= factor;
            yWorld  -= factor;
        }
        
        else if((key.isKeyPressed(key.DOWN))&&(key.isKeyPressed(key.RIGHT)))
        {
            angle = 315 - startAngle;
            xWorld += factor;
            yWorld -= factor;
        }
        else if(key.isKeyPressed(key.UP))
        {
            yWorld++; 
            angle = 90 - startAngle;
            //System.out.println("UP" );
        }
        else if(key.isKeyPressed(key.DOWN))
        {
            yWorld--;
            angle =  270 - startAngle;
            //System.out.println("DOWN" );
        }
        else if(key.isKeyPressed(key.LEFT))
        {
            xWorld--;
            angle = 180 - startAngle  ;
        }
        else if(key.isKeyPressed(key.RIGHT))
        {
            xWorld++;
            angle = 0 - startAngle ;
            
        }
        else 
        {
             angle = 0;
        }
       
    }
    
            
    
}
