package com.ofek324.actors;

import akka.actor.AbstractActor;
import com.ofek324.server.Main;
import com.ofek324.Entities.HTTPExecutor;
import com.ofek324.Entities.RequestExecution;
import org.jboss.netty.handler.codec.http.DefaultHttpRequest;
import org.jboss.netty.handler.codec.http.HttpRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.jboss.netty.handler.codec.http.HttpMethod.GET;
import static org.jboss.netty.handler.codec.http.HttpVersion.HTTP_1_0;

public class MiddlewareActor extends AbstractActor {
    private static Logger logger = LoggerFactory.getLogger(Main.class);

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(RequestExecution.class,this::dealWithRequest)
                .match(String.class,this::sendHttpRequest)
                .build();
    }

    private void sendHttpRequest(String s) {
        logger.info(s);
        HTTPExecutor httpExecutor = new HTTPExecutor();
        HttpRequest request = new DefaultHttpRequest(HTTP_1_0, GET, "/");
        httpExecutor.sendRequest(request);

    }

    private void dealWithRequest(RequestExecution requestExecution) {

    }
}
