package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;

/**
 * Created by 11 on 5/31/2016.
 */
public class AssetsLoader {
    public static Music menuMusic;
    public static Music playMusic;
    public static Music playerLaser;

    public static Preferences preferences;

    public AssetsLoader() {

    }

    public static void setHighScore(int score) {
        preferences.putInteger("HighScore", score);
        preferences.flush();
    }

    public static int getHighScore() {
        return preferences.getInteger("HighScore");
    }

    public static void load() {
        menuMusic = Gdx.audio.newMusic(Gdx.files.internal("Music/Menu_Screen_Music.ogg"));
        playMusic = Gdx.audio.newMusic(Gdx.files.internal("Music/Play_Screen_Music.ogg"));
        playerLaser = Gdx.audio.newMusic(Gdx.files.internal("Sound_Effects/Player_Laser.ogg"));

        preferences = Gdx.app.getPreferences("SpaceShooter");

        if (!preferences.contains("HighScore")) {
            preferences.putInteger("HighScore", 0);
        }
    }
}
