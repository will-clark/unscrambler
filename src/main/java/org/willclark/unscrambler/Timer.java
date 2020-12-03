package org.willclark.unscrambler;

public class Timer {
    
    private long start = -1;
    private long stop = -1;

    private Timer() {}

    public static Timer start() {
        Timer timer = new Timer();
        timer.start = System.currentTimeMillis();
        return timer;
    }

    public void stop() {
        stop = System.currentTimeMillis();
    }
    
    public String duration() {
        if (stop < 0) throw new IllegalStateException("duration() called prior to stopping the timer");
        long diff = Math.max(stop - start, 0);
        return String.format("It took %d milliseconds", diff);
    }

}