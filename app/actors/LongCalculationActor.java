package actors;

import akka.actor.UntypedActor;
import scala.concurrent.Future;

/**
 * https://www.playframework.com/documentation/2.5.x/JavaAkka#dependency-injecting-actors
 */
public class LongCalculationActor extends UntypedActor {

    @Override
    public void onReceive(final Object message) throws Exception {
        if (message instanceof Future<?>) {
            sender().tell(message, self());
        }
    }
}
