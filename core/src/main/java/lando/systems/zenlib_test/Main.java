package lando.systems.zenlib_test;

import lando.systems.zenlib_test.screens.TestScreen;
import zendo.games.zenlib.ZenAssets;
import zendo.games.zenlib.ZenConfig;
import zendo.games.zenlib.ZenMain;
import zendo.games.zenlib.screens.ZenScreen;
import zendo.games.zenlib.utils.Time;

public class Main extends ZenMain {

    public static final ZenConfig config = new ZenConfig("Zenlib Test", 1280, 720);

    public static Main game;

    public Main() {
        super(config);
        Main.game = this;
    }

    @Override
    public ZenAssets createAssets() {
        return new Assets();
    }

    @Override
    public ZenScreen<Assets> createStartScreen() {
        return new TestScreen();
    }

    @Override
    public void render() {
        super.render();

        screen.update(Time.delta);
        screen.render(zenAssets.batch);
    }

    @Override
    public void dispose() {
        screen.dispose();
        super.dispose();
    }
}
