package com.faveoffate.superjumper.thegame;

import com.faveoffate.superjumper.framework.Game;
import com.faveoffate.superjumper.framework.Input;
import com.faveoffate.superjumper.framework.gl.Camera2D;
import com.faveoffate.superjumper.framework.gl.SpriteBatcher;
import com.faveoffate.superjumper.framework.gl.Texture;
import com.faveoffate.superjumper.framework.gl.TextureRegion;
import com.faveoffate.superjumper.framework.impl.GLScreen;
import com.faveoffate.superjumper.framework.math.OverlapTester;
import com.faveoffate.superjumper.framework.math.Rectangle;
import com.faveoffate.superjumper.framework.math.Vector2;

import java.util.List;

import javax.microedition.khronos.opengles.GL10;

public class Help3Screen extends GLScreen {
    Camera2D guiCam;
    SpriteBatcher batcher;
    Rectangle nextBounds;
    Vector2 touchPoint;
    Texture helpImage;
    TextureRegion helpRegion;

    public Help3Screen(Game game) {
        super(game);
        guiCam = new Camera2D(glGraphics, 320, 480);
        nextBounds = new Rectangle(320 - 64, 0, 64, 64);
        touchPoint = new Vector2();
        batcher = new SpriteBatcher(glGraphics, 1);
    }
    @Override
    public void resume() {
        helpImage = new Texture(glGame, "help3.png" );
        helpRegion = new TextureRegion(helpImage, 0, 0, 320, 480);
    }
    @Override
    public void pause() {
        helpImage.dispose();
    }
    @Override
    public void update(float deltaTime) {
        List<Input.TouchEvent> touchEvents = game.getInput().getTouchEvents();
        game.getInput().getKeyEvents();
        int len = touchEvents.size();
        for(int i = 0; i < len; i++) {
            Input.TouchEvent event = touchEvents.get(i);
            touchPoint.set(event.x, event.y);
            guiCam.touchToWorld(touchPoint);
            if(event.type == Input.TouchEvent.TOUCH_UP) {
                if(OverlapTester.pointInRectangle(nextBounds, touchPoint)) {
                    Assets.playSound(Assets.clickSound);
                    game.setScreen(new Help4Screen(game));
                    return;
                }
            }
        }
    }
    @Override
    public void present(float deltaTime) {
        GL10 gl = glGraphics.getGL();
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        guiCam.setViewportAndMatrices();
        gl.glEnable(GL10.GL_TEXTURE_2D);
        batcher.beginBatch(helpImage);
        batcher.drawSprite(160, 240, 320, 480, helpRegion);
        batcher.endBatch();
        gl.glEnable(GL10.GL_BLEND);
        gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
        batcher.beginBatch(Assets.items);
        batcher.drawSprite(320 - 32, 32, -64, 64, Assets.arrow);
        batcher.endBatch();
        gl.glDisable(GL10.GL_BLEND);
    }
    @Override
    public void dispose() {
    }
}
