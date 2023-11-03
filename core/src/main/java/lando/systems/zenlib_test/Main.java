package lando.systems.zenlib_test;

import lando.systems.zenlib_test.screens.TestScreen1;
import zendo.games.zenlib.ZenConfig;
import zendo.games.zenlib.ZenMain;
import zendo.games.zenlib.screens.ZenScreen;

public class Main extends ZenMain<Assets> {

    public static final ZenConfig config = new ZenConfig("Zenlib Test", 1280, 720);

    public static Main game;

    public Main() {
        super(config);
        Main.game = this;
    }

    @Override
    public Assets createAssets() {
        return new Assets();
    }

    @Override
    public ZenScreen createStartScreen() {
        return new TestScreen1();
    }

}
