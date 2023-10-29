package lando.systems.zenlib_test.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.kotcrab.vis.ui.widget.VisTextButton;
import lando.systems.zenlib_test.Assets;
import lando.systems.zenlib_test.Main;
import zendo.games.zenlib.assets.ZenPatch;
import zendo.games.zenlib.screens.ZenScreen;

public class TestScreen2 extends ZenScreen<Assets> {

    public TestScreen2() {
        super(Assets.class);

        // override the default 'ScreenViewport'
        int screenWidth = Main.config.window.width / 2;
        int screenHeight = Main.config.window.height / 2;
        this.viewport = new StretchViewport(screenWidth, screenHeight, worldCamera);
        this.viewport.apply(true);

        Gdx.input.setInputProcessor(uiStage);
    }

    @Override
    protected void initializeUI() {
        super.initializeUI();

        var button = new VisTextButton("Switch to screen 1");
        button.setPosition(uiStage.getWidth() - button.getWidth() - 100, 100);
        button.getStyle().up = ZenPatch.glass_active.ninePatchDrawable;
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Main.game.setScreen(new TestScreen1());
            }
        });
        uiStage.addActor(button);
    }

    @Override
    public void update(float dt) {
        super.update(dt);
        // TODO - update screen specific things
    }

    @Override
    public void render(SpriteBatch batch) {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);

        batch.setProjectionMatrix(worldCamera.combined);
        batch.begin();
        {
            var image = assets.gdx;
            var scale = 1 / 4f;
            var imageWidth = scale * image.getWidth();
            var imageHeight = scale * image.getHeight();
            batch.draw(image,
                (worldCamera.viewportWidth - imageWidth) / 2f,
                (worldCamera.viewportHeight - imageHeight) / 2f,
                imageWidth, imageHeight);
        }
        batch.end();
        uiStage.draw();
    }

}
