package lando.systems.zenlib_test.screens;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import lando.systems.zenlib_test.Assets;
import lando.systems.zenlib_test.Main;
import zendo.games.zenlib.screens.ZenScreen;

public class TestScreen extends ZenScreen<Assets> {

    public TestScreen() {
        super(Assets.class);

        // override the default 'ScreenViewport'
        int screenWidth = Main.config.window.width / 4;
        int screenHeight = Main.config.window.height / 4;
        this.viewport = new StretchViewport(screenWidth, screenHeight, worldCamera);
        this.viewport.apply(true);
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
    }

}
