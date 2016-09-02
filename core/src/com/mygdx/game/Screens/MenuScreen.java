package com.mygdx.game.Screens;

/**
 * Created by 11 on 5/30/2016.
 */
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.mygdx.game.AssetsLoader;
import com.mygdx.game.MainGame;

public class MenuScreen implements Screen {

    private MainGame game;

    Skin skin;
    Stage stage;
    Pixmap pixmap;
    //SpriteBatch batch;

    FreeTypeFontGenerator generator;
    FreeTypeFontParameter parameter;
    BitmapFont font96;

    boolean menuMusicPlaying;

    private OrthographicCamera camera;

    public MenuScreen(MainGame g){
        create();
        this.game=g;

    }

    public MenuScreen(){

        create();
    }
    public void create(){
        //Music and Sound Effects
        //audioResources = new AssetsLoader();

        //batch = new SpriteBatch();
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        game = new MainGame();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        //camera.position.set(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, 0);
        //camera.update();

        // A skin can be loaded via JSON or defined programmatically, either is fine. Using a skin is optional but strongly
        // recommended solely for the convenience of getting a texture, region, etc as a drawable, tinted drawable, etc.
        skin = new Skin(Gdx.files.internal("ui_data/uiskin.json"));
        // Generate a 1x1 white texture and store it in the skin named "white".
        /*
        pixmap = new Pixmap(400, 200, Format.RGBA8888);
        pixmap.setColor(Color.GRAY);
        pixmap.fill();

        skin.add("white", new Texture(pixmap));
*/
        generator = new FreeTypeFontGenerator(Gdx.files.internal("Fonts/BattleFont.TTF"));
        parameter = new FreeTypeFontParameter();
        parameter.size = 96;

        font96 = generator.generateFont(parameter);
        //font16.getData().setScale(4);
        skin.add("default",font96);

        // Configure a TextButtonStyle and name it "default". Skin resources are stored by type, so this doesn't overwrite the font.
        TextButtonStyle textButtonStyle = new TextButtonStyle();
        textButtonStyle.up = skin.newDrawable("white", Color.DARK_GRAY);
        textButtonStyle.down = skin.newDrawable("white", Color.DARK_GRAY);
        textButtonStyle.checked = skin.newDrawable("white", Color.BLUE);
        textButtonStyle.over = skin.newDrawable("white", Color.LIGHT_GRAY);

        textButtonStyle.font = skin.getFont("default");

        skin.add("default", textButtonStyle);

        // Create a button with the "default" TextButtonStyle. A 3rd parameter can be used to specify a name other than "default".
        final TextButton playButton=new TextButton("PLAY",textButtonStyle);
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

    public void update() {
        menuMusicPlaying = AssetsLoader.menuMusic.isPlaying();

        if (!menuMusicPlaying) {
           menuMusicPlaying = true;
            AssetsLoader.menuMusic.setLooping(true);
            AssetsLoader.menuMusic.play();
        }
    }

    public void render (float delta) {
        // update camera
        //camera.update();
       // game.batch.setProjectionMatrix(camera.combined);

        update();
        Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
        stage.setDebugAll(true);
    }

    @Override
    public void resize (int width, int height) {
    }

    @Override
    public void dispose () {
        stage.dispose();
        skin.dispose();
        generator.dispose();
        //pixmap.dispose();
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
