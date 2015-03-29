package com.polarbirds.darkness.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.polarbirds.darkness.DarknessGame;
import com.polarbirds.darkness.GameWorld;
import com.polarbirds.darkness.input.FPSCameraController;

/**
 * Created by Kristian Rekstad on 04.03.2015.
 */
public class GameScreen implements Screen {

    public DarknessGame game;
    GameWorld world;
    public FPSCameraController cameraController; //FirstPersonCameraController cameraController;
    public PerspectiveCamera playerCamera;
    private ShapeRenderer shapeRenderer;


    public GameScreen(DarknessGame game) {
        this.game = game;
    }

    @Override
    public void show() {
        playerCamera = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        playerCamera.translate(0, 1.8f, 0f); //Fixme
        playerCamera.near = 0.1f;
        playerCamera.far = 20f;
        //playerCamera.lookAt(0,0,0);
        playerCamera.update(true);

        if (world == null) world = new GameWorld(this);
        cameraController = new FPSCameraController(playerCamera); //new FirstPersonCameraController(playerCamera);
        DarknessGame.INPUT_MULTIPLEXER.addProcessor(cameraController);

        shapeRenderer = new ShapeRenderer();
    }

    @Override
    public void render(float delta) {
        cameraController.update(delta);
        world.update(delta);

        world.render();


        shapeRenderer.setProjectionMatrix(playerCamera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
            shapeRenderer.line(-100f, 0, 0, 100, 0, 0, Color.RED, Color.PINK);
            shapeRenderer.line(0f, -100f, 0, 0f, 100f, 0, Color.GREEN, Color.YELLOW);
            shapeRenderer.line(0f, 0f, -100f, 0f, 0f, 100f, Color.BLUE, Color.CYAN);
        shapeRenderer.end();
    }

    @Override
    public void resize(int width, int height) {
        playerCamera.viewportWidth = width;
        playerCamera.viewportHeight = height;
        playerCamera.update(true);

        world.resize(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        DarknessGame.INPUT_MULTIPLEXER.removeProcessor(cameraController);
    }

    @Override
    public void dispose() {
        if (world != null) {world.dispose(); world=null;}
    }
}
