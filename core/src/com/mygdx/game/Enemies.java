package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by 11 on 5/29/2016.
 */
public class Enemies {
    public Vector2 enemiesPosition = new Vector2(0, 0);
    public Vector2 enemiesVelocity = new Vector2(0, 0);

    public Enemies(Vector2 sentPosition, Vector2 sentVelocity) {
        enemiesPosition = new Vector2(sentPosition.x, sentPosition.y);
        enemiesVelocity = new Vector2(sentVelocity.x, sentVelocity.y);
    }

    public void Update() {
        enemiesPosition.y -= enemiesVelocity.y;
    }
}
