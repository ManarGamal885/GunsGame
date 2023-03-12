package main;

import main.Enemys.Enemy;
import main.Keys.HandleKeys;
import Textures.AnimListener;
import Textures.TextureReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.glu.GLU;
import main.Players.Bullet;
import main.Players.Collision;
import main.Players.Player;
import main.Players.PowerUp;
import main.Players.Healthy;
import main.Players.healthBar;
import main.Enemys.Enemy;
import main.Players.specialGift;
import main.Players.specialBullet;

//this calss is used to run the game in infinite loop using AnimListener
//display, init, reshape and displayChanged  are abtract method from GLeventLisener into AnimListener class
// this class is attached with Gameplay to run the code
public class MainCode extends AnimListener {

    //deault Objects
    public double minX = 0;
    public double maxX = 100;
    double minY = 0;
    double maxY = 100;
    int specialbulletsNumber = 3;
    GL gl;
    GLCanvas canvas;
    //Manar: adding power up image index and increasing val
    int imageIdx = 10;
    float increasingVal;

    //texture setting
    String textureNames[] = {"shuttle.png", "shuttle.png", "shuttle.png", "shuttle.png", "back2.jpg", "24.png", "bullet.png", "present.png", "healthy.png", "powerup1.png", "powerup2.png", "powerup3.png", "powerup4.png", "powerup5.png", "powerup6.png", "gameover.png", "specialPresent.png", "fire.png"};

    TextureReader.Texture texture[] = new TextureReader.Texture[textureNames.length];
    int textures[] = new int[textureNames.length];

    int timeCountePresent, timeCounteHealthy, timeCountSpecial,
            frameCountPresent, frameCountHealthy, frameCountSpecial;
    public static int timeCountspecialAttack = 0, frameCountspecialAtttack = 0;
    public static boolean hittedSpecial;
    //Key setting
    HandleKeys key = new HandleKeys();
    //player setting
    Player player = new Player(gl, key);

    //Manar: handling the healthbar
    healthBar bar = new healthBar(imageIdx, 0.8f, 0.9f);

    public static ArrayList<Bullet> bullets = new ArrayList<>();
    public static ArrayList<specialBullet> specialbullets = new ArrayList<>();
    //Enemy setting
    public static ArrayList<Enemy> enemyList = new ArrayList<>();
    public static int stage1 = 5;

    //Manar: power up and healthy lists
    public static ArrayList<PowerUp> presentsList = new ArrayList<>();
    public static ArrayList<specialGift> specialList = new ArrayList<>();
    public static ArrayList<Healthy> healthyList = new ArrayList<>();

    @Override
    public void init(GLAutoDrawable glad) {

        gl = glad.getGL();
        gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);    //This Will Clear The Background Color To Black
        gl.glOrtho(minX, maxX, minY, maxY, 0, 0);
        gl.glEnable(GL.GL_TEXTURE_2D);  // Enable Texture Mapping
        gl.glBlendFunc(GL.GL_SRC_ALPHA, GL.GL_ONE_MINUS_SRC_ALPHA);
        readTexture(textureNames, textures, texture);
        for (int i = 0; i < stage1; i++) {
            createEnemy(20 * i, 20 * i);
        }
        gl.glLoadIdentity();

    }

    //Run the code in this method
    @Override
    public void display(GLAutoDrawable glad) {

        gl = glad.getGL();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);

        //Manar: arranging the time for healthy and power up per frames
        //Draw the background
        drawBackground(gl);
        timePerFrame();
        //Draw healthy bar
        //Manar: getting power up and live score from player; calling in display to get the new value of them every time displayed is called
        int liveScore = player.liveScore;
        int playerPowerUp = player.powerScore;
        boolean specialHitted = player.specialHitted;

        HealthBarCheck(liveScore);
        bar.drawHealthyBar(gl, imageIdx);

        //Draw the player
        player.drawPlayer(gl);
        player.move(maxX, maxY);
        drawBullet(playerPowerUp, specialHitted);
        System.out.println(specialHitted);
        //Draw enemies
        drawEnemy(stage1);
        //Draw presents
        //Manar: drawing and delaying the powerup and healthy
        checkForTime();
        //Draw Healthy
        System.out.println(frameCountspecialAtttack);
        

    }

    public void setCanvas(GLCanvas canvas) {
        canvas.addKeyListener(key);
        this.canvas = canvas;
    }

    public GLCanvas getCanvas() {
        return this.canvas;
    }

    //Manar: handling the image with livescore
    public void HealthBarCheck(int liveScore) {
        if (liveScore == 5) {
            imageIdx = 10;
        } else if (liveScore == 4) {
            imageIdx = 11;
        } else if (liveScore == 3) {
            imageIdx = 12;
        } else if (liveScore == 2) {
            imageIdx = 13;
        } else if (liveScore == 1) {
            imageIdx = 14;
        } else if (liveScore == 0) {
            imageIdx = 15;
            drawGameOver(gl);
            //and Stop the game
        }
    }

    public void drawGameOver(GL gl) {
        gl.glEnable(GL.GL_BLEND);
        gl.glBindTexture(GL.GL_TEXTURE_2D, textures[15]);	// Turn Blending On
        //gl.glColor3f(0, 0f, 0f);
        gl.glPushMatrix();
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
    }

    public void drawBackground(GL gl) {
        gl.glEnable(GL.GL_BLEND);
        gl.glBindTexture(GL.GL_TEXTURE_2D, textures[4]);	// Turn Blending On
        //gl.glColor3f(0, 0f, 0f);
        gl.glPushMatrix();
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
    }

    public void timePerFrame() {
        frameCountPresent++;
        frameCountHealthy++;
        frameCountSpecial++;
        if (hittedSpecial) {
            frameCountspecialAtttack++;
        }
        if (frameCountPresent == 60) {
            timeCountePresent++;
            frameCountPresent = 0;
        }

        if (frameCountHealthy == 60) {
            timeCounteHealthy++;
            frameCountHealthy = 0;
        }
        if (frameCountSpecial == 60) {
            timeCountSpecial++;
            frameCountSpecial = 0;
        }
    }

    public void checkForTime() {
        if ((timeCountePresent) == 3 && timeCountePresent != 0) {
            fillPresentsArray();
            timeCountePresent = 0;
        }
        drawPresent();
        destroyPresent();
        if ((timeCountSpecial) == 6 && timeCountSpecial != 0) {
            fillSpecialArray();
            timeCountSpecial = 0;
        }
        drawSpecial();
        destroySpecial();
        if (timeCountspecialAttack >= 7) {
            destroySpecial();
            drawPresent();
        }
        if ((timeCounteHealthy) == 5 && timeCounteHealthy != 0) {
            fillHealthyArray();
            timeCounteHealthy = 0;
        }
        if ((timeCounteHealthy) == 5 && timeCounteHealthy != 0) {
            fillHealthyArray();
            timeCounteHealthy = 0;
        }
        drawHealthy();
        destroyHealthy();

    }

    public void drawEnemy(int stage1) {

        for (int i = 0; i < enemyList.size(); i++) {
            enemyList.get(i).setXWorld(enemyList.get(i).getXWorld());
            enemyList.get(i).setYWorld(enemyList.get(i).getYWorld());
            enemyList.get(i).drawSprite(gl);
            player.resolveColision(enemyList.get(i));
        }
    }

    public void drawBullet(int playerPowerUp, boolean specialHitted) {
        //Manar: normal attack, need delay
           float bulletX = player.getXWorld() * player.scale * player.speed;
           float bulletY = player.getYWorld() * player.scale * player.speed;
        if (!specialHitted) {
          
            if (key.isKeyPressed(key.SPACE)) {
               
                if (playerPowerUp == 1) {

                    Bullet bullet1 = new Bullet(gl, bulletX, bulletY, 0);
                    bullets.add(bullet1);

                } else if (playerPowerUp == 2) {
                    bulletX -= 0.03f;
                    Bullet bullet1 = new Bullet(gl, bulletX, bulletY, 0);
                    bullets.add(bullet1);
                    bulletX += 0.05f;
                    Bullet bullet2 = new Bullet(gl, bulletX, bulletY, 0);
                    bullets.add(bullet2);
                } else if (playerPowerUp >= 3) {
                    Bullet bullet1 = new Bullet(gl, bulletX, bulletY, 0);
                    bullets.add(bullet1);
                    Bullet bullet2 = new Bullet(gl, bulletX, bulletY, 0.005f);
                    bullets.add(bullet2);

                    Bullet bullet3 = new Bullet(gl, bulletX, bulletY, -0.005f);
                    bullets.add(bullet3);
                }

            }
            for (int i = 0; i < bullets.size(); i++) {
                bullets.get(i).setYWorld(bullets.get(i).getYWorld() + bullets.get(i).speed);
                bullets.get(i).drawBullet(gl);
                if (bullets.get(i).getYWorld() > maxY) {
                    destroy(bullets.get(i));
                }
            }
        } else {
            
           
            if (frameCountspecialAtttack <= 100) {
                 if (key.isKeyPressed(key.SPACE)) {
       
                    specialBullet bullet1 = new specialBullet(gl, bulletX, bulletY, 0);
                    specialbullets.add(bullet1);

                }
                for (int i = 0; i < specialbullets.size(); i++) {
                    specialbullets.get(i).setyWorld(specialbullets.get(i).getyWorld() + specialbullets.get(i).speed);
                    specialbullets.get(i).drawBullet(gl);
                    if (specialbullets.get(i).getxWorld() > maxY) {
                        destroy(specialbullets.get(i));
                    }
                    for (int j = 0; j < enemyList.size(); j++) {
                        specialbullets.get(i).resolveColision(enemyList.get(j));
                    }
                    
                }
            } else {

                if (key.isKeyPressed(key.SPACE)) {
       
                    Bullet bullet1 = new Bullet(gl, bulletX, bulletY, 0);
                    bullets.add(bullet1);

                }

                for (int i = 0; i < bullets.size(); i++) {
                    bullets.get(i).setYWorld(bullets.get(i).getYWorld() + bullets.get(i).speed);
                    bullets.get(i).drawBullet(gl);
                    if (bullets.get(i).getYWorld() > maxY) {
                        destroy(bullets.get(i));
                    }
                }
//                frameCountspecialAtttack=0;
            }

        }

    }

    public void destroyPresent() {
        if (presentsList.size() > 0) {
            for (int i = 0; i < presentsList.size(); i++) {
                if (presentsList.get(i).getyPresent() < minY - 1) {
                    destroy(presentsList.get(i));
                }
            }
        }
    }

    public void destroySpecial() {
        if (specialList.size() > 0) {
            for (int i = 0; i < specialList.size(); i++) {
                if (specialList.get(i).getyPresent() < minY - 1) {
                    destroy(specialList.get(i));
                }
            }
        }
    }

    public void destroyHealthy() {
        if (healthyList.size() > 0) {
            for (int i = 0; i < healthyList.size(); i++) {
                if (healthyList.get(i).getyHealthy() < minY - 1) {
                    destroy(healthyList.get(i));
                }
            }
        }
    }

    public void drawPresent() {
        if (presentsList.size() > 0) {
            for (int i = 0; i < presentsList.size(); i++) {
                presentsList.get(i).drawPresent(gl);
                presentsList.get(i).movePresent();
                player.resolveColision(presentsList.get(i));
            }
        }
    }

    public void drawSpecial() {
        if (presentsList.size() > 0) {
            for (int i = 0; i < specialList.size(); i++) {
                specialList.get(i).drawSpecialPresent(gl);
                specialList.get(i).moveSpecial();
                player.resolveColision(specialList.get(i));
            }
        }
    }

    public void drawHealthy() {

        if (healthyList.size() > 0) {
            for (int i = 0; i < healthyList.size(); i++) {
                healthyList.get(i).drawHealthy(gl);
                healthyList.get(i).moveHealthy();
                player.resolveColision(healthyList.get(i));
            }
        }
    }

    public static void destroy(Object ob) {
        if (ob instanceof Enemy) {
            enemyList.remove(ob);
        }
        if (ob instanceof Bullet) {
            bullets.remove(ob);
        }
        if (ob instanceof PowerUp) {
            System.out.println("destroyed");
            presentsList.remove(ob);
        }
        if (ob instanceof Healthy) {
            healthyList.remove(ob);
        }
        if (ob instanceof specialGift) {
            specialList.remove(ob);
        }
        if (ob instanceof specialBullet) {
            specialbullets.remove(ob);
        }
    }

    public void createEnemy(float x, float y) {
        Enemy enemy = new Enemy(gl, key, x, y);
        enemyList.add(enemy);
    }

    public void createPresent(float x, float y, float spead) {
        PowerUp present = new PowerUp(gl, x, y, spead);
        presentsList.add(present);
    }

    public void createSpecial(float x, float y, float spead) {
        specialGift special = new specialGift(gl, x, y, spead);
        specialList.add(special);
    }

    public void createHealthy(float x, float y, float spead) {
        Healthy healthy = new Healthy(gl, x, y, spead);
        healthyList.add(healthy);
    }

    //Set the texture and read it 
    public void readTexture(String[] textureNames, int[] textureContainer, TextureReader.Texture texture[]) {

        gl.glGenTextures(textureNames.length, textureContainer, 0);

        for (int i = 0; i < textureNames.length; i++) {
            try {

                if (textureNames[i] == "back1.jpg" || textureNames[i] == "24.png") {
                    String pathName = "src/Assets/Photos/";
                    texture[i] = TextureReader.readTexture(pathName + textureNames[i], true);

                } else {
                    String pathName = "src/PlayerAssets/man/";
                    texture[i] = TextureReader.readTexture(pathName + textureNames[i], true);

                }

                gl.glBindTexture(GL.GL_TEXTURE_2D, textures[i]);

//                mipmapsFromPNG(gl, new GLU(), texture[i]);
                new GLU().gluBuild2DMipmaps(
                        GL.GL_TEXTURE_2D,
                        GL.GL_RGBA, // Internal Texel Format,
                        texture[i].getWidth(), texture[i].getHeight(),
                        GL.GL_RGBA, // External format from image,
                        GL.GL_UNSIGNED_BYTE,
                        texture[i].getPixels() // Imagedata
                );

            } catch (IOException e) {
                System.out.println(e);
                e.printStackTrace();
            }
        }
    }

    @Override
    public void reshape(GLAutoDrawable glad, int i, int i1, int i2, int i3) {
        //useless method
    }

    @Override
    public void displayChanged(GLAutoDrawable glad, boolean bln, boolean bln1) {
        //useless method
    }

    public void fillPresentsArray() {
        int max = 1;
        int min = -1;
        float speed = 0.01f;
        float positiony = 1f;
        float positionx = (float) (Math.random() * (max - (min))) + (min);
        createPresent(positionx, positiony, speed);
        speed += 0.5f;

    }

    public void fillSpecialArray() {
        int max = 1;
        int min = -1;
        float speed = 0.01f;
        float positiony = 1f;
        float positionx = (float) (Math.random() * (max - (min))) + (min);
        createSpecial(positionx, positiony, speed);
        speed += 0.5f;
    }

    public void fillHealthyArray() {
        int max = 1;
        int min = -1;
        float speed = 0.01f;
        float positiony = 1f;
        float positionx = (float) (Math.random() * (max - (min))) + (min);
        createHealthy(positionx, positiony, speed);
        speed += 0.5f;
        positiony += 0.5;
    }

}
