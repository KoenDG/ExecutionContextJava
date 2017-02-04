package controllers;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import akka.actor.ActorRef;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import scala.compat.java8.FutureConverters;
import services.LongRunningProcess;

import static akka.pattern.Patterns.ask;

/**
 * Made into a demo for this question.
 * Disclaimer: could not get this to work. See HomeController2 for working solution.
 */
@Singleton
public class HomeController extends Controller {

//    private final ActorRef configuredActor;
//
//    /**
//     * Constructor.
//     * @param configuredActor The ActorRef which we're going to use to get the context we defined for Akka.
//     */
//    @Inject
//    public HomeController(@Named("longcalculation-context") final ActorRef configuredActor) {
//       this.configuredActor = configuredActor;
//    }

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
//        // Doesn't work, immediately returns {"cancelled":false,"done":false,"completedExceptionally":false,"numberOfDependents":0}
//        final CompletionStage<Object> integerPromise = FutureConverters.toJava(ask(this.configuredActor, CompletableFuture.supplyAsync(() -> LongRunningProcess.run(10000L)), 15000));
//
//        // Doesn't work asynchronously
//        final CompletionStage<Object> integerPromise2 = FutureConverters.toJava(ask(this.configuredActor, LongRunningProcess.run(10000L), 15000));


        // Doesnt work, immediately returns {"empty":true,"traversableAgain":true}
//        final CompletableFuture<Integer> supplyAsync = CompletableFuture.supplyAsync(() -> LongRunningProcess.run(10000L));
//        final CompletionStage<Object> integerPromise = FutureConverters.toJava(ask(this.configuredActor, FutureConverters.toScala(supplyAsync), 15000));


        // Old code
//        final CompletionStage<Integer> integerPromise = CompletableFuture.supplyAsync(() -> LongRunningProcess.run(10000L), (Executor) myExecutionContext);
//
//        final CompletionStage<Integer> integerPromise2 = CompletableFuture.supplyAsync(() -> LongRunningProcess.run(10000L), (Executor) myExecutionContext);

//        return integerPromise.thenApplyAsync(x -> ok(Json.toJson(x)));
        return (CompletionStage<Result>) TODO;
    }
}
