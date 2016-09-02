package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.Screens.MenuScreen;

public class MainGame extends Game {
	public SpriteBatch batch;
    public BitmapFont font;

	@Override
	public void create () {
		batch = new SpriteBatch();
        font = new BitmapFont();
		AssetsLoader.load();
		setScreen(new MenuScreen(this));
	}

	@Override
	public void render () {

		super.render();
	}
}
