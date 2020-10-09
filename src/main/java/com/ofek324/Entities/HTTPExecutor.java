package com.ofek324.Entities;

import com.biasedbit.http.client.DefaultHttpClient;
import com.biasedbit.http.client.HttpClient;
import com.biasedbit.http.client.future.RequestFuture;
import com.biasedbit.http.client.processor.BodyAsStringProcessor;
import org.jboss.netty.handler.codec.http.HttpRequest;


public class HTTPExecutor {

    // Create & initialise the client
    HttpClient client = new DefaultHttpClient();

    public void sendRequest(HttpRequest request) {
        // Create & initialise the client
        client.init();
        RequestFuture<String> future = client.execute("localhost", 8080, request, new BodyAsStringProcessor());
        future.addListener((future1) -> {
            System.out.println(future1);
            if (future1.hasSuccessfulResponse()) System.out.println(future.getProcessedResult());
            client.terminate();
        });
    }

}
