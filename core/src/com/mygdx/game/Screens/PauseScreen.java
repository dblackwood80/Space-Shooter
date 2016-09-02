package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.mygdx.game.AssetsLoader;
import com.mygdx.game.MainGame;

/**
 * Created by 11 on 5/31/2016.
 */
public class PauseScreen implements Screen {

    private MainGame game;
    private PlayScreen playScreen;
    /*Skin skin;
    Stage stage;
    Pixmap pixmap;
    //SpriteBatch batch;

    FreeTypeFontGenerator generator;
    FreeTypeFontGenerator.FreeTypeFontParameter parameter;
    BitmapFont font96;
    */
    public PauseScreen(MainGame g) {
        //create();
        this.game=g;
        this.playScreen = new PlayScreen(game);
    }
/*
    public void create(){
        //Music and Sound Effects
        //audioResources = new AssetsLoader();

        //batch = new SpriteBatch();
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        game = new MainGame();


        // A skin can be loaded via JSON or defined programmatically, either is fine. Using a skin is optional but strongly
        // recommended solely for the convenience of getting a texture, region, etc as a drawable, tinted drawable, etc.
        skin = new Skin(Gdx.files.internal("ui_data/uiskin.json"));
        // Generate a 1x1 white texture and store it in the skin named "white".
        // START COMMENT OUT
        pixmap = new Pixmap(400, 200, Format.RGBA8888);
        pixmap.setColor(Color.GRAY);
        pixmap.fill();

        skin.add("white", new Texture(pixmap));
// END COMMENT OUT
        generator = new FreeTypeFontGenerator(Gdx.files.internal("Fonts/BattleFont.TTF"));
        parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 96;

        font96 = generator.generateFont(parameter);
        //font16.getData().setScale(4);
        skin.add("default",font96);

        // Configure a TextButtonStyle and name it "default". Skin resources are stored by type, so this doesn't overwrite the font.
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = skin.newDrawable("white", Color.DARK_GRAY);
        textButtonStyle.down = skin.newDrawable("white", Color.DARK_GRAY);
        textButtonStyle.checked = skin.newDrawable("white", Color.BLUE);
        textButtonStyle.over = skin.newDrawable("white", Color.LIGHT_GRAY);

        textButtonStyle.font = skin.getFont("default");

        skin.add("default", textButtonStyle);

        // Create a button with the "default" TextButtonStyle. A 3rd parameter can be used to specify a name other than "default".
        final TextButton playButton=new TextButton("RESUME",textButtonStyle);
        final TextButton optionsButton=new TextButton("OPTIONS",textButtonStyle);
        final TextButton exitButton=new TextButton("EXIT",textButtonStyle);

        Gdx.app.log("Heights", playButton.getHeight() + " " + optionsButton.getHeight() + " " + exitButton.getHeight());
        Gdx.app.log("Widths", playButton.getWidth() + " " + optionsButton.getWidth() + " " + exitButton.getWidth());

        playButton.setPosition(Gdx.graphics.getWidth() / 2 - playButton.getWidth() / 2, Gdx.graphics.getHeight() / 2 + 2 * playButton.getHeight());
        optionsButton.setPosition(Gdx.graphics.getWidth() / 2 - optionsButton.getWidth() / 2, Gdx.graphics.getHeight() / 2);
        exitButton.setPosition(Gdx.graphics.getWidth() / 2 - exitButton.getWidth() / 2, Gdx.graphics.getHeight() / 2 - 2 * exitButton.getHeight());

        // Add actors to stage
        stage.addActor(playButton);
        stage.addActor(optionsButton);
        stage.addActor(exitButton);

        // Add a listener to the button. ChangeListener is fired when the button's checked state changes, eg when clicked,
        // Button#setChecked() is called, via a key press, etc. If the event.cancel() is called, the checked state will be reverted.
        // ClickListener could have been used, but would only fire when clicked. Also, canceling a ClickListener event won't
        // revert the checked state.
        playButton.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                Gdx.app.log("Button", "Pressed");
                //textButton.setText("Starting new game")
                AssetsLoader.menuMusic.stop();
                dispose();
                game.setScreen( new PlayScreen(game));
            }
        });

        optionsButton.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                Gdx.app.log("Button", "Pressed");
                //textButton.setText("Starting new game");
                dispose();
                game.setScreen( new OptionsScreen(game));
            }
        });

        exitButton.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                Gdx.app.log("Button", "Pressed");
                Gdx.app.exit();
            }
        });

    }
*/
    //// TODO: 5/31/2016 Complete Pause Screen

    public void update() {
        if (Gdx.input.justTouched()) {
            Gdx.app.log("Still", "Here");
            dispose();
            game.setScreen(playScreen);

        }
    }


    @Override
    public void show() {
        Gdx.app.log(getClass().getName(), "In show");
    }

    @Override
    public void render(float delta) {
        update();
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
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
