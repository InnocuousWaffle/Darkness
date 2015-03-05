package com.polarbirds.darkness.gameobject;

import com.badlogic.gdx.physics.bullet.collision.btCollisionObject;
import com.badlogic.gdx.utils.Disposable;
import com.polarbirds.darkness.graphics.RenderableObject;

/**
 * Created by Kristian Rekstad on 05.03.2015.
 */
public abstract class GameObject implements RenderableObject, Disposable{

    public int health;
    public float speed;
    public btCollisionObject collisionObject;

    public GameObject(){
        health = 100;
        speed = 5;
    }


    @Override
    public void dispose() {
        if (collisionObject != null) collisionObject.dispose();
    }

    public boolean hurt(int amount){
        health -= amount;
        return health <= 0;
    }

    public abstract void update(float deltaTime);

}
