package com.mygdx.game.Screens;

/**
 * Created by 11 on 5/30/2016.
 */
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.mygdx.game.AssetsLoader;
import com.mygdx.game.MainGame;

public class OptionsScreen implements Screen {
    private MainGame game;

    Skin skin;
    Stage stage;
    Slider volumeSlider;
    //Label volumePercentLabel;


    FreeTypeFontGenerator generator;
    FreeTypeFontParameter parameter;
    BitmapFont font96;

    //Music and Sound Effects
    MenuScreen menu;


    public OptionsScreen(MainGame g){
        create();
        this.game=g;
    }

    public OptionsScreen(){

        create();
    }
    public void create(){

        //menu = new MenuScreen();

        //Music and Sound Effects
        //menuMusic = Gdx.audio.newMusic(Gdx.files.internal("Music/Menu_Screen_Music.ogg"));
        //menuMusic.setLooping(true);
       // menuMusic.play();

        //batch = new SpriteBatch();
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        game = new MainGame();

        // A skin can be loaded via JSON or defined programmatically, either is fine. Using a skin is optional but strongly
        // recommended solely for the convenience of getting a texture, region, etc as a drawable, tinted drawable, etc.
        skin = new Skin(Gdx.files.internal("ui_data/uiskin.json"));
        // Generate a 1x1 white texture and store it in the skin named "white".
        Pixmap pixmap = new Pixmap(400, 200, Format.RGBA8888);
        pixmap.setColor(Color.GRAY);
        pixmap.fill();

        skin.add("white", new Texture(pixmap));

        generator = new FreeTypeFontGenerator(Gdx.files.internal("Fonts/BattleFont.TTF"));
        parameter = new FreeTypeFontParameter();
        parameter.size = 96;
        //parameter.shadowOffsetX = 20;

        font96 = generator.generateFont(parameter);
        skin.add("default",font96);

        // Configure a TextButtonStyle and name it "default". Skin resources are stored by type, so this doesn't overwrite the font.
        TextButtonStyle textButtonStyle = new TextButtonStyle();
        textButtonStyle.up = skin.newDrawable("white", Color.DARK_GRAY);
        textButtonStyle.down = skin.newDrawable("white", Color.DARK_GRAY);
        textButtonStyle.checked = skin.newDrawable("white", Color.BLUE);
        textButtonStyle.over = skin.newDrawable("white", Color.LIGHT_GRAY);

        textButtonStyle.font = skin.getFont("default");

        skin.add("default", textButtonStyle);
        volumeSlider = new Slider(0, 100, 1, false, skin);

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.background = skin.newDrawable("white", Color.DARK_GRAY);
        //labelStyle.
        labelStyle.font = skin.getFont("default");
        skin.add("white", labelStyle);
        //volumePercentLabel = new Label(volumeSlider.getPercent() + "%", skin);

        // Create a button with the "default" TextButtonStyle. A 3rd parameter can be used to specify a name other than "default".
        //final TextButton playButton=new TextButton("NEW",textButtonStyle);
        final TextButton optionsButton=new TextButton("BACK",textButtonStyle);
        final TextButton exitButton=new TextButton("EXIT",textButtonStyle);
        final Label lad = new Label((int)(1 - volumeSlider.getPercent()) * 100 + "%", labelStyle);

        Gdx.app.log("Heights", optionsButton.getHeight() + " " + exitButton.getHeight());
        Gdx.app.log("Widths", optionsButton.getWidth() + " " + exitButton.getWidth());

        //playButton.setPosition(Gdx.graphics.getWidth() / 2 - playButton.getWidth() / 2, Gdx.graphics.getHeight() / 2 + 2 * playButton.getHeight());
        optionsButton.setPosition(Gdx.graphics.getWidth() / 2 - optionsButton.getWidth() / 2, Gdx.graphics.getHeight() / 2);
        exitButton.setPosition(Gdx.graphics.getWidth() / 2 - exitButton.getWidth() / 2, Gdx.graphics.getHeight() / 2 - 2 * exitButton.getHeight());
        volumeSlider.setPosition(Gdx.graphics.getWidth() / 2 - volumeSlider.getWidth(), volumeSlider.getWidth());
        lad.setPosition(Gdx.graphics.getWidth() / 2 - volumeSlider.getWidth(), volumeSlider.getWidth() + lad.getHeight());

        // Add actors to stage
        //stage.addActor(playButton);
        stage.addActor(optionsButton);
        stage.addActor(exitButton);
        stage.addActor(volumeSlider);
        stage.addActor(lad);
        //stage
        volumeSlider.setSize(600, 50);
        volumeSlider.getStyle().knob.setMinHeight(volumeSlider.getHeight());


        // Add a listener to the button. ChangeListener is fired when the button's checked state changes, eg when clicked,
        // Button#setChecked() is called, via a key press, etc. If the event.cancel() is called, the checked state will be reverted.
        // ClickListener could have been used, but would only fire when clicked. Also, canceling a ClickListener event won't
        // revert the checked state.
        /*playButton.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                Gdx.app.log("Button", "Pressed");
                //textButton.setText("Starting new game");
                //game.setScreen( new PlayScreen(game));
                AssetsLoader.menuMusic.dispose();
                dispose();
                game.setScreen( new PlayScreen(game));
            }
        });
*/
        optionsButton.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                Gdx.app.log("Button", "Pressed");
                //textButton.setText("Starting new game");
                game.setScreen(new MenuScreen(game));
                dispose();
            }
        });

        exitButton.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                Gdx.app.log("Button", "Pressed");
                Gdx.app.exit();
            }
        });

        volumeSlider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.log("vol", "works");
                AssetsLoader.menuMusic.setVolume(1 - volumeSlider.getPercent());
                lad.setText((int)(100-(volumeSlider.getPercent() * 100)) + "");
            }
        });

    }

    public void render (float delta) {
        Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
        stage.setDebugAll(true);
        game.batch.begin();
        //volumeSlider.draw(game.batch, 1);
        game.batch.end();
    }

    @Override
    public void resize (int width, int height) {

        stage.getViewport().update(width, height, false);
    }

    @Override
    public void dispose () {
        stage.dispose();
        skin.dispose();
        generator.dispose();
        //AssetsLoader.menuMusic.dispose();
    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }
}
