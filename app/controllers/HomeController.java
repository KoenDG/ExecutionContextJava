package controllers;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.Executor;

import javax.inject.Inject;
import javax.inject.Singleton;

import akka.actor.ActorSystem;
import services.LongRunningProcess;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import scala.concurrent.ExecutionContext;

/**
 * Made into a demo for this question.
 */
@Singleton
public class HomeController extends Controller {

    private final ActorSystem actorSystem;

    /**
     * Constructor.
     * @param actorSystem The actorsystem which we're going to use to get the context we defined for Akka.
     */
    @Inject
    public HomeController(final ActorSystem actorSystem) {
       this.actorSystem = actorSystem;
    }

    /**
     * Scenario 1: A single Completeable Future.
     * @return The result.
     */
    public CompletionStage<Result> async() {
        final CompletionStage<Integer> integerPromise = CompletableFuture.supplyAsync(() ->
                LongRunningProcess.run(10000L));

        return integerPromise.thenApplyAsync(x -> ok(Json.toJson(x)));
    }

    /**
     * Scenario 2: two at once.
     * @return The result.
     */
    public CompletionStage<Result> async2() {
        final CompletionStage<Integer> integerPromise = CompletableFuture.supplyAsync(() -> LongRunningProcess.run(10000L));

        final CompletionStage<Integer> integerPromise2 = CompletableFuture.supplyAsync(() -> LongRunningProcess.run(10000L));

        return integerPromise.thenApplyAsync(x -> ok(Json.toJson(x)));
    }

    /**
     * Scenario 3: Using our custom defined actor.
     * @return asdfasdf
     */
    public CompletionStage<Result> async3() {
        // Does not work if I use play.akka.actor.my-context
        // PLay documentation recommended I use play.akka instead of akka.*, but that did not work
        // https://www.playframework.com/documentation/2.5.x/ConfigFile
        final ExecutionContext myExecutionContext = this.actorSystem.dispatchers().lookup("akka.actor.my-context");

        final CompletionStage<Integer> integerPromise = CompletableFuture.supplyAsync(() -> LongRunningProcess.run(10000L), (Executor) myExecutionContext);

        final CompletionStage<Integer> integerPromise2 = CompletableFuture.supplyAsync(() -> LongRunningProcess.run(10000L), (Executor) myExecutionContext);

        return integerPromise.thenApplyAsync(x -> ok(Json.toJson(x)));
    }
}
