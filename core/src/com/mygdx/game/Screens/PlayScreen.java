package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.AssetsLoader;
import com.mygdx.game.Bullet;
import com.mygdx.game.Constants;
import com.mygdx.game.Enemies;
import com.mygdx.game.MainGame;

import java.util.ArrayList;

/**
 * Created by 11 on 5/28/2016.
 */
public class PlayScreen implements Screen {

    private MainGame game;

    private long initialTime;

    private float fireDelay;

    // Textures
    Texture backgroundTexture;
    Texture playerTexture;
    Texture enemyTexture;
    Texture bulletTexture;

    //Buttons
    TextureAtlas buttonAtlas;
    //TextureRegion playButtonId;
    TextureRegion pauseButtonId;
    Rectangle pauseButtonBounds;
    boolean isPaused;

    Vector2 playerPosition;
    Vector2 enemyPosition;

    Enemies enemies;
    ArrayList<Enemies> enemiesManager;
    float enemiesElapsedSeconds = 0;

    Bullet bullet;
    ArrayList<Bullet> bulletManager;

    private Rectangle enemyRectangle;
    private Rectangle bulletRectangle;


    private OrthographicCamera camera;
    private Viewport viewPort;

    boolean gameIsOver;
    int currentScore;

    FreeTypeFontGenerator generator;
    FreeTypeFontGenerator.FreeTypeFontParameter parameter;
    BitmapFont font64;
    String score;
    String highScore;

    public PlayScreen(MainGame game) {
        this.game = game;
//// TODO: 5/31/2016 Vertical parallax scrolling
        backgroundTexture = new Texture("Textures/Nebula.jpg");
        backgroundTexture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        playerTexture = new Texture("Textures/Player.png");
        enemyTexture = new Texture("Textures/Enemy1.png");
        bulletTexture = new Texture("Textures/Laserbeam.png");

        buttonAtlas = new TextureAtlas(Gdx.files.internal("UI_Buttons/SpaceShooterButtons.pack"));
        pauseButtonId = buttonAtlas.findRegion("Pause_Button");
        pauseButtonBounds = new Rectangle(Gdx.graphics.getWidth() - pauseButtonId.getRegionWidth() - 64, pauseButtonId.getRegionHeight() - 64, 128, 128);
        isPaused = new Boolean(false);
        Gdx.app.log(pauseButtonBounds.getX() + ", " + pauseButtonBounds.getY(), pauseButtonBounds.getWidth() + ", " + pauseButtonBounds.getHeight());

        playerPosition = new Vector2(Gdx.graphics.getWidth() / 2 - playerTexture.getWidth() /2, 32);
        enemyPosition = new Vector2(Gdx.graphics.getWidth() / 2 - enemyTexture.getWidth() / 2, Gdx.graphics.getHeight() - enemyTexture.getHeight());

        enemies = new Enemies(enemyPosition, enemyPosition);
        enemiesManager = new ArrayList<Enemies>();

        bullet = new Bullet(playerPosition, playerPosition);
        bulletManager = new ArrayList<Bullet>();

        bulletRectangle = new Rectangle(0, 0, 0, 0);
        enemyRectangle = new Rectangle(0, 0, 0, 0);

        gameIsOver = false;
        currentScore = 0;
        generator = new FreeTypeFontGenerator(Gdx.files.internal("Fonts/BattleFont.TTF"));
        parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 64;
        font64 = generator.generateFont(parameter);
        font64.getXHeight();

        score = "Score: 0" ;
        highScore = "High Score: " + AssetsLoader.getHighScore();

        //game.font.setColor(Color.BLACK);

        Gdx.app.log(enemyTexture.getWidth() + " ", bulletTexture.getWidth() + " ");
    }

    public void update(float delta) {
        // Keep track of time elapsed in seconds instead of nanoseconds
        long elapsedNanoseconds = TimeUtils.nanoTime() - initialTime;
        float elapsedSeconds = MathUtils.nanoToSec * elapsedNanoseconds;
        fireDelay -= delta;
        AssetsLoader.playMusic.setLooping(true);
        AssetsLoader.playMusic.play();


        enemiesElapsedSeconds += 1/60f;
        //Gdx.app.log("first time", "set time " + enemiesElapsedSeconds);

        // Reset spawn time of enemies
        if (enemiesElapsedSeconds > 5) {
            enemiesElapsedSeconds = 0;
            //Gdx.app.log("same class", "reset time " + enemiesElapsedSeconds);
        }

        float accelerometerInputX = -Gdx.input.getAccelerometerX();

        // Change player position in X-direction with accelerometer
        playerPosition.x += delta * accelerometerInputX * Constants.PLAYER_MOVEMENT_SPEED;

        // If screen is tapped, create and add new bullet(laser) to manager and fire laser
        if (Gdx.input.justTouched()) {
            Vector2 tmp = new Vector2(Gdx.input.getX(), Gdx.input.getY());
            Gdx.app.log("Point of touch", tmp.x + ", " + tmp.y);
            if (pauseButtonBounds.contains(tmp.x, tmp.y) && isPaused) {

            }
            if (pauseButtonBounds.contains(tmp.x, tmp.y)) {
                //isPaused = true;
                Gdx.app.log("Touch", "Pause screen call");
                //AssetsLoader.playMusic.stop();

                //game.setScreen(new PauseScreen(game));
            } else {
                if (fireDelay <= 0) {
                    AssetsLoader.playerLaser.play();
                    Bullet nextBullet = new Bullet(playerPosition, playerPosition);
                    bulletManager.add(nextBullet);
                    fireDelay += 0.6;
            }
        }
    }

        // Controls for desktop version go left
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            playerPosition.x -= delta * Constants.PLAYER_MOVEMENT_SPEED * 5; //MAYBE NOT 5
        }

        // Controls for desktop version go right
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            playerPosition.x += delta * Constants.PLAYER_MOVEMENT_SPEED * 5; //MAYBE NOT 5
        }

        // Spawn random number of enemies, between 1 and 10, in 5 second intervals
        if (MathUtils.random() < delta * 5 && enemiesManager.size() < MathUtils.random(1, 10) && enemiesElapsedSeconds > 4) {
            Enemies nextEnemy = new Enemies(new Vector2(MathUtils.random() * (Gdx.graphics.getWidth()-64), Gdx.graphics.getHeight() - enemyTexture.getHeight()), new Vector2(0, 5));
            enemiesManager.add(nextEnemy);
        }

        // Make sure that player doesn't go of screen
        playerInBounds();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        update(Gdx.graphics.getDeltaTime());

        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        if (!gameIsOver) {
            game.batch.draw(backgroundTexture, 0, 0);
            //game.batch.draw(pauseButtonId, Gdx.graphics.getWidth() - pauseButtonId.getRegionWidth() - 64, Gdx.graphics.getHeight() - pauseButtonId.getRegionHeight() - 64, 128, 128);
            font64.draw(game.batch, score, 25, Gdx.graphics.getHeight()-font64.getCapHeight());
            font64.draw(game.batch, highScore, 25, Gdx.graphics.getHeight()-(2 *font64.getCapHeight()) - 16);
            game.batch.draw(playerTexture, playerPosition.x, playerPosition.y);

            // Destroy bullets
            bulletRemove();

            //Destroy enemy objects
            enemyRemove();

            // Check if enemy is hit by bullet
            enemyHit();

            // Check if player is hit by enemy
            playerHit();
        } else if (gameIsOver) {
            //Gdx.gl.glClearColor(1, 0, 0, 1);
            //Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            if (currentScore > AssetsLoader.getHighScore()) {
                AssetsLoader.setHighScore(currentScore);
            }
            game.setScreen(new GameOverScreen(game));
            gameIsOver = false;
            AssetsLoader.playMusic.stop();
            //dispose(); // Crashes game when disposed of. 11505-11522/com.mygdx.game A/libc: Fatal signal 11 (SIGSEGV), code 1, fault addr 0x28 in tid 11522 (GLThread 10482)
            //game.batch.draw(backgroundTexture, 0, 0);
           // game.batch.draw(gameOverTexture, 0, 0);
        }

        game.batch.end();

    }

    private void playerInBounds() {
        if (playerPosition.x < 0) {
            playerPosition.x = 0;
        }
        if (playerPosition.x + playerTexture.getWidth() > backgroundTexture.getWidth()) {
            playerPosition.x = backgroundTexture.getWidth() - playerTexture.getWidth();
        }
    }

    public void enemyHit() {
        for (int i = 0; i < enemiesManager.size(); i++){
            for (int j = 0; j < bulletManager.size(); j++) {
                if (bulletManager.get(j).bulletPosition.dst(enemiesManager.get(i).enemiesPosition) < enemyTexture.getHeight() / 4 /*enemyTexture.getWidth() / 2*/) {
                    bulletManager.remove(j);
                    enemiesManager.remove(i);
                    currentScore++;
                    score = "score: " + currentScore;
                    Gdx.app.log("for loop", "Enemy " + i + " and bullet " + j + " removed");
                    break;
                }
            }
        }
    }

    public void playerHit() {
        for (int q = 0; q < enemiesManager.size(); q++) {
            if (enemiesManager.get(q).enemiesPosition.dst(playerPosition) < enemyTexture.getWidth()) {
                Gdx.app.log(" crash " + enemiesManager.get(q).enemiesPosition.dst(playerPosition), "player hit");
                enemiesManager.remove(q);
                gameIsOver = true;
            }
        }
    }

    public void enemyRemove() {
        int enemyCounter = 0;

        while(enemyCounter < enemiesManager.size()) {
            Enemies currentEnemies = enemiesManager.get(enemyCounter);
            enemyRectangle.set(currentEnemies.enemiesPosition.x, currentEnemies.enemiesPosition.y, enemyTexture.getWidth() + 128, enemyTexture.getHeight());
            currentEnemies.Update();
            if (currentEnemies.enemiesPosition.y > 0 && currentEnemies.enemiesPosition.y < backgroundTexture.getHeight()) {
                game.batch.draw(enemyTexture, currentEnemies.enemiesPosition.x, currentEnemies.enemiesPosition.y);
            } else if (currentEnemies.enemiesPosition.y < Gdx.graphics.getHeight() + enemyTexture.getHeight()){
                enemiesManager.remove(enemyCounter);
                Gdx.app.log("Main Game", "Removed enemy " + enemiesManager.size());
                if (enemiesManager.size() > 0) {
                    enemyCounter--;
                }
            }
            enemyCounter++;
        }
    }

    public void bulletRemove() {
        int bulletCounter = 0;

        while(bulletCounter < bulletManager.size()) {
            Bullet currentBullet = bulletManager.get(bulletCounter);
            bulletRectangle.set(currentBullet.bulletPosition.x, currentBullet.bulletPosition.y, bulletTexture.getWidth() + 128, bulletTexture.getHeight());
            currentBullet.Update();
            if (currentBullet.bulletPosition.y > 0 && currentBullet.bulletPosition.y < backgroundTexture.getHeight()) {
                game.batch.draw(bulletTexture, currentBullet.bulletPosition.x - 32, currentBullet.bulletPosition.y + 128);
            } else if (currentBullet.bulletPosition.y > Gdx.graphics.getHeight()){
                bulletManager.remove(bulletCounter);
                Gdx.app.log("Remove", "Bullet " + bulletCounter + " removed");
                if (bulletManager.size() > 0) {
                    bulletCounter--;
                }
            }
            bulletCounter++;
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        game.batch.dispose();
        game.font.dispose();
        backgroundTexture.dispose();
        playerTexture.dispose();
        enemyTexture.dispose();
        bulletTexture.dispose();
    }
}
