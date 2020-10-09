package com.ofek324.utils.Handlers;

import com.ofek324.Entities.Objects.ActionOfService;
import com.ofek324.Entities.Objects.Flow;
import com.ofek324.Entities.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ratpack.handling.Context;

import static com.ofek324.utils.Handlers.JsonParser.objectToJson;

public class RouterHandler {
    private static Logger logger = LoggerFactory.getLogger(RouterHandler.class);

    static DBHandler dbHandler = new DBHandler();

    public static void insertNewAction(Context ctx) {

        ctx.getRequest().getBody().then(x -> {
            String res="";
            ActionOfService actionOfService = JsonParser.jsonToObject(x.getText(), ActionOfService.class);
            try {
                res = dbHandler.insertNewService(actionOfService);
                ctx.render(objectToJson(new Response(200, res, actionOfService)));
                logger.info("inserterd " + actionOfService.toString());
            } catch (Exception e) {
                e.printStackTrace();
                ctx.render(objectToJson(new Response(500, res, actionOfService)));
            }
        });
    }
    public static void insertNewFlow(Context ctx) {

        ctx.getRequest().getBody().then(x -> {
            String res="";
            Flow flow = JsonParser.jsonToObject(x.getText(), Flow.class);
            try {
                res = dbHandler.insertNewFlow(flow);
                ctx.render(objectToJson(new Response(200, res, flow)));
                logger.info("inserterd " + flow.toString());
            } catch (Exception e) {
                e.printStackTrace();
                ctx.render(objectToJson(new Response(500, res, flow)));
            }
        });
    }

    public static void allActions(Context ctx) {

        ctx.getRequest().getBody().then(x -> {
            try {
                ctx.render(dbHandler.getServicesCollectionData());
            } catch (Exception e) {
                e.printStackTrace();
                ctx.render(objectToJson(new Response(500, "couldnt fetch data",null)));
            }
        });
    }

    public static void allFlows(Context ctx) {

        ctx.getRequest().getBody().then(x -> {
            try {
                ctx.render(objectToJson(dbHandler.getFlowsCollectionData()));
            } catch (Exception e) {
                e.printStackTrace();
                ctx.render(objectToJson(new Response(500, "couldnt fetch data",null)));
            }
        });
    }



}
