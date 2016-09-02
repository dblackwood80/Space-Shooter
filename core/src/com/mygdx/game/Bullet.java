package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by 11 on 5/29/2016.
 */
public class Bullet {
    public Vector2 bulletPosition = new Vector2(0, 0);
    public Vector2 bulletVelocity = new Vector2(0, 0);

    public Bullet(Vector2 sentPosition, Vector2 sentVelocity) {
        bulletPosition = new Vector2(sentPosition.x, sentPosition.y);
        bulletVelocity = new Vector2(sentVelocity.x, sentVelocity.y);
    }

    public void Update() {
        bulletPosition.y += bulletVelocity.y;
    }
}
