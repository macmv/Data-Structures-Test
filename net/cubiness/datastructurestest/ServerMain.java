package net.cubiness.datastructurestest;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.Collection;
import java.util.HashMap;
import java.util.logging.Logger;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;


public class ServerMain {
    
    public static final Logger log = Logger.getLogger(ServerMain.class.getName());
    public static ExperimentStructures structures;

    public static void main(String[] args) throws Exception {
        log.info("Creating ExperimentStructures");
        structures = new ExperimentStructures();
        log.info("Creating server");
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/runExperiment", new MyHandler());
        server.setExecutor(null); // creates a default executor
        log.info("Starting server");
        server.start();
    }
    
    private static HashMap<String, String> parseQuery(String query) {
        HashMap<String, String> queryHash = new HashMap<>();
        for (String value : query.split("&")) {
            String[] data = value.split("=");
            if (data.length != 2) {
                return null;
            }
            queryHash.put(value.split("=")[0], value.split("=")[1]);
        }
        return queryHash;
    }
    
    static class MyHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {
            long startTime = System.nanoTime();
            String rawQuery = t.getRequestURI().getQuery();
            if (rawQuery == null) {
                t.sendResponseHeaders(400, 0);
                t.close();
                return;
            }
            HashMap<String, String> query = parseQuery(rawQuery);
            if (!(query.containsKey("class") &&
                    query.containsKey("length") &&
                    query.containsKey("user") &&
                    query.containsKey("passwd")) || query == null) {
                t.sendResponseHeaders(400, 0);
                t.close();
                return;
            }
            Collection<Account> testStruct = structures.getStructure(query.get("class"), Integer.parseInt(query.get("length")));
            ExperimentUtils.testSearch(testStruct, new Account(query.get("user"), query.get("passwd")));
            StringBuilder response = new StringBuilder();
            response.append("Your test took " + (float) (System.nanoTime() - startTime) / 1000000f + " seconds.\nData tested:\n");
            query.forEach((k, v) -> {
                response.append(k + " = " + v + "\n");
            });
            t.sendResponseHeaders(200, response.length());
            t.getResponseBody().write(response.toString().getBytes());
            t.close();
        }
    }

}
