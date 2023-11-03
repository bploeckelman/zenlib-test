package lando.systems.zenlib_test.physics;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import space.earlygrey.shapedrawer.ShapeDrawer;
import zendo.games.zenlib.physics.Collidable;
import zendo.games.zenlib.physics.CollisionShape;
import zendo.games.zenlib.physics.CollisionShapeCircle;

public class PhysicsBall implements Collidable {

    private final Rectangle collisionBounds;
    private final CollisionShapeCircle collisionShape;


    public Vector2 pos;
    public Vector2 vel;
    public float size;

    public PhysicsBall(Vector2 position, Vector2 velocity) {
        this.pos = new Vector2(position);
        this.vel = new Vector2(velocity);
        this.size = MathUtils.random(5f,50f);

        this.collisionBounds = new Rectangle(pos.x - size/2f, pos.y - size /2f, size, size);
        this.collisionShape = new CollisionShapeCircle(size /2f, pos.x, pos.y);

    }

    @Override
    public void renderDebug(ShapeDrawer shapes) {
        shapes.filledCircle(collisionShape.center, collisionShape.radius, Color.MAGENTA);
    }

    @Override
    public float getFriction() {
        return 1f;
    }

    @Override
    public float getMass() {
        return 10;
    }

    @Override
    public Vector2 getVelocity() {
        return vel;
    }

    @Override
    public void setVelocity(Vector2 newVel) {
        setVelocity(newVel.x, newVel.y);
    }

    @Override
    public void setVelocity(float x, float y) {
        vel.set(x, y);
    }

    @Override
    public Vector2 getPosition() {
        return pos;
    }

    @Override
    public void setPosition(float x, float y) {
        collisionShape.center.set(x, y);
        collisionBounds.setPosition(x - size/2f, y - size/2f);
        pos.set(x, y);
    }

    @Override
    public void setPosition(Vector2 newPos) {
        setPosition(newPos.x,newPos.y);
    }

    @Override
    public Rectangle getCollisionBounds() {
        return collisionBounds;
    }

    @Override
    public CollisionShape getCollisionShape() {
        return collisionShape;
    }

    @Override
    public void collidedWith(Collidable object) {

    }

    @Override
    public boolean shouldCollideWith(Collidable object) {
        return true;
    }
}
