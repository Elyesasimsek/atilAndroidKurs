package com.example.survivorbird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.Random;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class SurvivorBird extends ApplicationAdapter {
    private SpriteBatch batch;
    private Texture background, bird, ufo, ufo2, ufo3;
    float birdx = 0, birdy = 0, ufoy = 0;
    int gameState = 0;
    float gravity = 0.3f, velocity = 0f, ufoVelocity = 10;
    int screenHeight, mainCharacterSpeed;
    float birdSizex,birdSizey, ufoSizex, ufoSizey;
    Random random;
    int score = 0, scoredUfo = 0;
    BitmapFont font, font2;

    ShapeRenderer shapeRenderer;
    Circle birdCircle;
    Circle[]  ufoCirsles;
    Circle[]  ufoCirsles2;
    Circle[]  ufoCirsles3;

    int numberOfUfos = 4;
    float[] ufox = new float[numberOfUfos];
    float distance = 0;
    float[] ufoOffSet = new float[numberOfUfos];
    float[] ufoOffSet2 = new float[numberOfUfos];
    float[] ufoOffSet3 = new float[numberOfUfos];

    @Override
    public void create() {
        batch = new SpriteBatch();
        background = new Texture("background.png");
        bird = new Texture("fly.png");
        ufo = new Texture("ufo.png");
        ufo2 = new Texture("ufo.png");
        ufo3 = new Texture("ufo.png");

        distance = Gdx.graphics.getWidth() / 2;
        random = new Random();

        birdSizex = Gdx.graphics.getWidth() /15;
        birdSizey = Gdx.graphics.getHeight() / 10;
        birdx = Gdx.graphics.getWidth() / 7;
        birdy = Gdx.graphics.getHeight() / 2;
        ufoSizex = Gdx.graphics.getWidth() /15;
        ufoSizey = Gdx.graphics.getHeight() / 10;

        shapeRenderer = new ShapeRenderer();

        birdCircle = new Circle();
        ufoCirsles = new Circle[numberOfUfos];
        ufoCirsles2 = new Circle[numberOfUfos];
        ufoCirsles3 = new Circle[numberOfUfos];

        font = new BitmapFont();
        font.setColor(Color.WHITE);
        font.getData().setScale(4);

        font2 = new BitmapFont();
        font2.setColor(Color.WHITE);
        font2.getData().setScale(6);

        screenHeight =Gdx.graphics.getHeight();
        mainCharacterSpeed = screenHeight/90;

        for (int i = 0;i < numberOfUfos;i++){

            ufoOffSet[i] = (random.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() - 200);
            ufoOffSet2[i] = (random.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() - 200);
            ufoOffSet3[i] = (random.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() - 200);

            ufox[i] = Gdx.graphics.getWidth() - ufo.getWidth() / 2 + i * distance;

            ufoCirsles[i] = new Circle();
            ufoCirsles2[i] = new Circle();
            ufoCirsles3[i] = new Circle();
        }
    }

    @Override
    public void render() {
        batch.begin();
        batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        if (gameState == 1){
            if (ufox[scoredUfo] < Gdx.graphics.getWidth() / 7){
                score++;
                if (scoredUfo < numberOfUfos - 1){
                    scoredUfo++;
                }else {
                    scoredUfo = 0;
                }
            }

            if (Gdx.input.justTouched()){
                velocity = -mainCharacterSpeed;
            }

            for (int i = 0;i < numberOfUfos;i++){
                if (ufox[i] < -ufo.getWidth() /15){
                    ufox[i] = ufox[i] + numberOfUfos * distance;

                    ufoOffSet[i] = (random.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() - 200);
                    ufoOffSet2[i] = (random.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() - 200);
                    ufoOffSet3[i] = (random.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() - 200);
                }else {
                    ufox[i] = ufox[i] - ufoVelocity;
                }

                batch.draw(ufo, ufox[i], Gdx.graphics.getHeight() / 2 + ufoOffSet[i], ufoSizex, ufoSizey);
                batch.draw(ufo2, ufox[i], Gdx.graphics.getHeight() / 2 + ufoOffSet2[i], ufoSizex, ufoSizey);
                batch.draw(ufo3, ufox[i], Gdx.graphics.getHeight() / 2 + ufoOffSet3[i], ufoSizex, ufoSizey);

                ufoCirsles[i] = new Circle(ufox[i] + ufoSizex / 2.7f, Gdx.graphics.getHeight() / 2 + ufoOffSet[i] + ufoSizey / 2.7f, ufoSizex / 2.7f);
                ufoCirsles2[i] = new Circle(ufox[i] + ufoSizex / 2.7f, Gdx.graphics.getHeight() / 2 + ufoOffSet2[i] + ufoSizey / 2.7f, ufoSizex / 2.7f);
                ufoCirsles3[i] = new Circle(ufox[i] + ufoSizex / 2.7f, Gdx.graphics.getHeight() / 2 + ufoOffSet3[i] + ufoSizey / 2.7f, ufoSizex / 2.7f);
            }

            if (birdy > 0 && birdy < 650){
                velocity = velocity + gravity;
                birdy -= velocity;
            }else {
                gameState = 2;
            }

        } else if (gameState == 0) {
            if (Gdx.input.justTouched()){
                gameState = 1;
            }
        } else if (gameState == 2) {
            font2.draw(batch, "Game Over! Top To Play Again!", 100, Gdx.graphics.getHeight()/ 2);

            if (Gdx.input.justTouched()){
                gameState = 1;
                birdy = Gdx.graphics.getHeight() / 2;

                for (int i = 0;i < numberOfUfos;i++){

                    ufoOffSet[i] = (random.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() - 200);
                    ufoOffSet2[i] = (random.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() - 200);
                    ufoOffSet3[i] = (random.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() - 200);

                    ufox[i] = Gdx.graphics.getWidth() - ufo.getWidth() / 2 + i * distance;

                    ufoCirsles[i] = new Circle();
                    ufoCirsles2[i] = new Circle();
                    ufoCirsles3[i] = new Circle();
                }

                velocity = 0;
                scoredUfo = 0;
                score = 0;
            }
        }

        batch.draw(bird, birdx, birdy, birdSizex, birdSizey);
        font.draw(batch, String.valueOf(score), 100, 200);
        batch.end();
        birdCircle.set(birdx + birdSizex / 2.7f, birdy + birdSizey / 2.7f, birdSizex / 2.7f);
  /*      shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.circle(birdCircle.x, birdCircle.y, birdCircle.radius);*/


        for (int i = 0;i < numberOfUfos;i++){
            /*shapeRenderer.circle(ufoCirsles[i].x, ufoCirsles[i].y, ufoCirsles[i].radius);
            //shapeRenderer.circle(ufoCirsles2[i].x, ufoCirsles2[i].y, ufoCirsles2[i].radius);
            shapeRenderer.circle(ufoCirsles3[i].x, ufoCirsles3[i].y, ufoCirsles3[i].radius);*/

            if (Intersector.overlaps(birdCircle, ufoCirsles[i]) || Intersector.overlaps(birdCircle, ufoCirsles2[i]) || Intersector.overlaps(birdCircle, ufoCirsles3[i])){
                gameState = 2;
            }
        }
    //    shapeRenderer.end();
    }

    @Override
    public void dispose() {

    }
}
