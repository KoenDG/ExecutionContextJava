package services;

import play.Logger;

/**
 * Demo utility class.
 */
public class LongRunningProcess {

    /**
     * Sleep for the given amount of time, in milliseconds.
     * @param sleepTime The time in milliseconds.
     * @return The number 1.
     */
    public static int run(final Long sleepTime) {
        Logger.info("Starting LongRunningProcess.");
        try {
            Thread.sleep(sleepTime);
        } catch (final InterruptedException e) {
            e.printStackTrace();
        }
        Logger.info("Stopping LongRunningProcess.");
        return 1;
    }
}
