package com.ofek324.actors;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import com.ofek324.Entities.RequestExecution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.ofek324.utils.Consts.MIDDLEWARE_ACTOR_NAME;

public class RequestsActor extends AbstractActor {
    private final ActorRef middlewareActor = MainActorSystem.ACTOR_SYSTEM.getActorRefByName(MIDDLEWARE_ACTOR_NAME);
    private static Logger logger = LoggerFactory.getLogger(RequestsActor.class);


    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(RequestExecution.class,this::dealWithRequest)
                .match(String.class,this::dealWithRequest)
                .build();
    }

    private void dealWithRequest(String s) {
        logger.info(s);
        logger.info("=======================================");
        middlewareActor.tell("me why", getSelf());
    }
//
    private void dealWithRequest(RequestExecution requestExecution) {
        middlewareActor.tell(new RequestExecution(), getSelf());

    }
}
