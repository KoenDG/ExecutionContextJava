package services;

import akka.actor.ActorSystem;
import scala.concurrent.ExecutionContext;
import scala.concurrent.ExecutionContextExecutor;

/**
 * https://github.com/playframework/playframework/blob/master/framework/src/play/src/main/java/play/libs/concurrent/CustomExecutionContext.java
 */
public abstract class CustomExecutionContext implements ExecutionContextExecutor {

    /** The executioncontext we'll be using. */
    private final ExecutionContext executionContext;

    /**
     * Copied from link.
     * @param actorSystem The actorsystem.
     * @param name The name of my execution context in my akka.conf
     */
    public CustomExecutionContext(final ActorSystem actorSystem, final String name) {
        this.executionContext = actorSystem.dispatchers().lookup(name);
    }

    @Override
    public ExecutionContext prepare() {
        return executionContext.prepare();
    }

    @Override
    public void execute(final Runnable command) {
        executionContext.execute(command);
    }

    @Override
    public void reportFailure(final Throwable cause) {
        executionContext.reportFailure(cause);
    }
}
