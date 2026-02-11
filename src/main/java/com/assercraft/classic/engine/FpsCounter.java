package com.assercraft.classic.engine;

public final class FpsCounter {
    private long lastTime = System.nanoTime();
    private int frames;
    private int fps;

    public void frame() {
        frames++;
        long now = System.nanoTime();
        if (now - lastTime >= 1_000_000_000L) {
            fps = frames;
            frames = 0;
            lastTime = now;
        }
    }

    public int fps() {
        return fps;
    }
}
