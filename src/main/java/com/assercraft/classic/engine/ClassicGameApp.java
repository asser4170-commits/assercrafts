package com.assercraft.classic.engine;

import com.assercraft.classic.gfx.ShaderProgram;

import static org.lwjgl.glfw.GLFW.glfwSetWindowTitle;

public final class ClassicGameApp {
    private static final double TICK_RATE = 20.0;
    private static final double TICK_DT = 1.0 / TICK_RATE;

    public void run() {
        GameWindow window = new GameWindow();
        window.create(1280, 720, "AsserCraft Classic 1.0");

        ShaderProgram shader = new ShaderProgram("/shaders/basic.vert", "/shaders/basic.frag");
        FpsCounter fpsCounter = new FpsCounter();

        double accumulator = 0.0;
        long last = System.nanoTime();

        while (!window.shouldClose()) {
            long now = System.nanoTime();
            double frameTime = (now - last) / 1_000_000_000.0;
            last = now;
            accumulator += frameTime;

            while (accumulator >= TICK_DT) {
                updateFixedTick();
                accumulator -= TICK_DT;
            }

            render(window, shader);
            fpsCounter.frame();
            glfwSetWindowTitle(window.handle(), "AsserCraft Classic 1.0 | FPS " + fpsCounter.fps());
            window.swapAndPoll();
        }

        shader.destroy();
        window.destroy();
    }

    private void updateFixedTick() {
        // Phase 1 only: fixed step loop skeleton.
    }

    private void render(GameWindow window, ShaderProgram shader) {
        window.clear(0.42f, 0.65f, 0.92f);
        shader.use();
        // Phase 1 only: no world rendering yet.
    }
}
