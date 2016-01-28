package com.faveoffate.superjumper.framework.impl;

import com.faveoffate.superjumper.framework.Game;
import com.faveoffate.superjumper.framework.Screen;

public abstract class GLScreen extends Screen {
    protected final GLGraphics glGraphics;
    protected final GLGame glGame;
    
    public GLScreen(Game game) {
        super(game);
        glGame = (GLGame)game;
        glGraphics = ((GLGame)game).getGLGraphics();
    }

}
