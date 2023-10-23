package lando.systems.zenlib_test;

import com.badlogic.gdx.graphics.Texture;
import zendo.games.zenlib.ZenAssets;

public class Assets extends ZenAssets {

    public Texture gdx;

    @Override
    public void loadManagerAssets() {
        mgr.load("libgdx.png", Texture.class);
    }

    @Override
    public void initCachedAssets() {
        gdx = mgr.get("libgdx.png", Texture.class);
    }

}
