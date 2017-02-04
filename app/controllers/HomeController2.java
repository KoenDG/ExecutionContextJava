package controllers;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.Executor;

import play.core.j.HttpExecutionContext;
import play.mvc.Controller;
import play.mvc.Result;
import services.LongRunningProcess;
import services.MyExecutionContext;

/**
 * Second attempt.
 * Based on: https://github.com/playframework/playframework/blob/master/documentation/manual/working/javaGuide/main/async/code/javaguide/async/controllers/Application.java
 * @author kdegroot
 */
public class HomeController2 extends Controller {

    private final MyExecutionContext myExecutionContext;

    /**
     * Constructor.
     * @param myExecutionContext myExecutionContext.
     */
    @javax.inject.Inject
    public HomeController2(final MyExecutionContext myExecutionContext) {
        this.myExecutionContext = myExecutionContext;
    }

    /**
     * Trying this.
     * @return The result.
     */
    public CompletionStage<Result> async() {
        // Wrap an existing thread pool, using the context from the current thread
        final Executor myEc = HttpExecutionContext.fromThread(myExecutionContext);
        final CompletionStage<Integer> integerPromise = CompletableFuture.supplyAsync(() -> LongRunningProcess.run(10000L), myEc);

        // Why should I also provide the custom executor here? It is already part of the CompletionStage object so I would expect its execution to continue on the same thread.
        return integerPromise.thenApplyAsync(i -> ok("Got result: " + i));
    }
}
