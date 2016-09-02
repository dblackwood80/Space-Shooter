package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.MainGame;

/**
 * Created by 11 on 5/29/2016.
 */
public class GameOverScreen implements Screen{
    Texture gameOverTexture;
    private MainGame game;

    public GameOverScreen(MainGame game) {
        this.game = game;
        game.font.setColor(Color.BLACK);
        gameOverTexture = new Texture("Textures/Game_Over.png");
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        game.batch.draw(gameOverTexture, 0, 0);
        if (Gdx.input.justTouched()) {
            game.setScreen(new MenuScreen(game));
            dispose();
        }
        game.batch.end();
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

    }
}
