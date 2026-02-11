package com.assercraft.engine;

public final class TickLoop {
    public static final int TICKS_PER_SECOND = 20;
    private static final long NANOS_PER_TICK = 1_000_000_000L / TICKS_PER_SECOND;

    public void runTicks(int ticks, Runnable tickAction) {
        long next = System.nanoTime();
        for (int i = 0; i < ticks; i++) {
            tickAction.run();
            next += NANOS_PER_TICK;
            long sleepNanos = next - System.nanoTime();
            if (sleepNanos > 0) {
                try {
                    Thread.sleep(sleepNanos / 1_000_000L, (int) (sleepNanos % 1_000_000L));
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
        }
    }
}
