package com.ofek324.server;

import akka.actor.ActorRef;
import com.ofek324.actors.MainActorSystem;
import com.ofek324.utils.Handlers.DBHandler;
import com.ofek324.utils.Handlers.RouterHandler;
import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ratpack.handling.RequestLogger;
import ratpack.http.MutableHeaders;
import ratpack.server.RatpackServer;

import static com.ofek324.actors.MainActorSystem.initActors;
import static com.ofek324.utils.Consts.PORT;
import static com.ofek324.utils.Consts.REQUESTS_ACTOR_NAME;

public class Main {
    private static Logger logger = LoggerFactory.getLogger(Main.class);
    static DBHandler dbHandler ;

    public static void main(String[] args) {
        String log4jConfPath = "./src/main/resources/log4j.properties";
        PropertyConfigurator.configure(log4jConfPath);

        logger.info("Configuration File Defined To Be :: ");

        try {
            dbHandler = new DBHandler();
            dbHandler.getMongoClient();
            initActors();
            logger.info("DB handler connected");
            ActorRef reqActor = MainActorSystem.ACTOR_SYSTEM.getActorRefByName(REQUESTS_ACTOR_NAME);

            RatpackServer.start(serverSpec -> serverSpec
                    .serverConfig(c -> c.port(PORT))
                    .handlers(chain -> chain
                            .all(RequestLogger.ncsa())
                            .all(ctx -> {
                                MutableHeaders headers = ctx.getResponse().getHeaders();
                                headers.set("Access-Control-Allow-Origin", "*");
                                headers.set("Access-Control-Allow-Headers", "x-requested-with, origin, content-type, accept");
                                ctx.next();
                            })
                            .get("", ctx -> ctx.render("hello, the api at /api"))
                            .get("dbname",ctx -> ctx.render(dbHandler.getDBName()))
                            .get("api/v0_1/conn/getAllActions", RouterHandler::allActions)
                            .get("api/v0_1/conn/getAllFlows", RouterHandler::allFlows)
                            .post("api/v0_1/conn/newAction", RouterHandler::insertNewAction)
                            .post("api/v0_1/conn/insertNewFlow", RouterHandler::insertNewFlow)
                            .get("dropconn", ctx -> dbHandler.dropConn())
                            .get("actors",context -> {
                                logger.info("==================================");
                                reqActor.tell("me whyyyy",ActorRef.noSender());
                                context.render("Actors Check started, refer to logs");
                            })
                    )
            );
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {System.out.println("Shutdown hook ran!"); dbHandler.dropConn();}));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

/*
    storyline -
    scenario 1 : user enters to publish a new service with its friends.
    step 1: creates its code and wraps according to documentation
    step 2: submitting his action to the cloud using /api/v0_1/conn/newAction
    step 3: asked by the web if this action is part of existing flow or new one
    condition 3.1: if new one - requests to api/v0_1/conn/insertNewFlow
    condition 3.2: if exists - 1) gets all flows using api/v0_1/conn/getAllFlows
                               2) adds to the relevant the new action including rearrangement of order - /TODO

    scenario 2: user chooses flow - TODO
    step 1: pulls the arguments and presents in form.
    step 2: on submitting request - takes the auth and args -> server /
    step 3: server adds the request to history and logs it with status.
    step 4:

 */