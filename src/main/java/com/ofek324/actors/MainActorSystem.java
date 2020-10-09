package com.ofek324.actors;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

import static com.ofek324.utils.Consts.*;

public enum  MainActorSystem {
    ACTOR_SYSTEM;

    private ActorSystem actorSystem ;

    MainActorSystem() {
        actorSystem = ActorSystem.create(CLOUFSYS_ACTOR_NAME);
    }

    public static void initActors() {
        ActorSystem actorSystem = ACTOR_SYSTEM.getActorSystem();
        actorSystem.actorOf(Props.create(MiddlewareActor.class), MIDDLEWARE_ACTOR_NAME);
        actorSystem.actorOf(Props.create(RequestsActor.class), REQUESTS_ACTOR_NAME);
    }

    public ActorSystem getActorSystem() {
        return actorSystem;
    }

    public ActorRef getActorRefByName(String actorName) {
        return getActorSystem().actorFor("user/"+actorName);
    }
}