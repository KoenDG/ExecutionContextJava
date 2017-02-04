package services;

import akka.actor.ActorSystem;

/**
 * https://github.com/playframework/playframework/blob/master/documentation/manual/working/javaGuide/main/async/code/javaguide/async/controllers/MyExecutionContextImpl.java
 */
public class MyExecutionContextImpl extends CustomExecutionContext implements MyExecutionContext {

    /**
     * Constructor.
     *
     * @param actorSystem The actor system.
     */
    @javax.inject.Inject
    public MyExecutionContextImpl(final ActorSystem actorSystem) {
        // uses a custom thread pool defined in application.conf
        super(actorSystem, "akka.actor.longcalculation-context");
    }
}
