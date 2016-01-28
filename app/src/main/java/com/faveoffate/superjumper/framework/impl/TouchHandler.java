package com.faveoffate.superjumper.framework.impl;

import android.view.View.OnTouchListener;

import com.faveoffate.superjumper.framework.Input;

import java.util.List;

public interface TouchHandler extends OnTouchListener {
    public boolean isTouchDown(int pointer);
    public int getTouchX(int pointer);
    public int getTouchY(int pointer);
    public List<Input.TouchEvent> getTouchEvents();
}
