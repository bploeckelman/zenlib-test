package lando.systems.zenlib_test.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import lando.systems.zenlib_test.Main;
import lando.systems.zenlib_test.physics.Boundary;
import lando.systems.zenlib_test.physics.PhysicsBall;
import zendo.games.zenlib.physics.Collidable;
import zendo.games.zenlib.physics.Influencer;
import zendo.games.zenlib.physics.PhysicsSystem;
import zendo.games.zenlib.screens.ZenScreen;

import static lando.systems.zenlib_test.Main.game;

public class TestPhysicsScreen extends ZenScreen {

    private PhysicsSystem physicsSystem;
    private Array<Collidable> bounds;
    private Array<PhysicsBall> balls;
    private Array<Collidable> collidables;
    private Array<Influencer> influencers;

    public TestPhysicsScreen() {
        // override the default 'ScreenViewport'
        int screenWidth = Main.config.window.width / 2;
        int screenHeight = Main.config.window.height / 2;
        this.viewport = new StretchViewport(screenWidth, screenHeight, worldCamera);
        this.viewport.apply(true);

        physicsSystem = new PhysicsSystem(new Rectangle(viewport.getScreenX(), viewport.getScreenY(), viewport.getScreenWidth(), viewport.getScreenHeight()));
        collidables = new Array<>();
        influencers = new Array<>();
        balls = new Array<>();
        bounds = new Array<>();
        bounds.add(new Boundary(0, 0, 0, screenHeight));
        bounds.add(new Boundary(0, screenHeight, screenWidth, screenHeight));
        bounds.add(new Boundary(screenWidth, screenHeight, screenWidth, 0));
        bounds.add(new Boundary(screenWidth, 0, 0, 0));

        bounds.add(new Boundary(screenWidth/2f, screenHeight* 2f /5f, screenWidth/2f, screenHeight * 3 / 5f));

        collidables.addAll(bounds);

        for (int i = 0; i < 40; i++) {
            balls.add(new PhysicsBall(new Vector2(MathUtils.random(0f, screenWidth), MathUtils.random(0f, screenHeight)),
                new Vector2(MathUtils.random(-100, 100f), MathUtils.random(-100, 100f))));
        }
        collidables.addAll(balls);
    }

    @Override
    public void update(float dt) {
        super.update(dt);
        if (Gdx.input.justTouched()){
            game.setScreen(new TestScreen1());
        }
        for (PhysicsBall b : balls) {
            b.getVelocity().y -= 60f * dt;
        }
        physicsSystem.update(dt,collidables, influencers );
    }

    @Override
    public void render(SpriteBatch batch) {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);

        batch.setProjectionMatrix(worldCamera.combined);
        batch.begin();
        {
            for (Collidable c : collidables) {
                c.renderDebug(game.assets.shapes);
            }

        }
        batch.end();
    }
}
