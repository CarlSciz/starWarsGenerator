package com.swgen.starWarsGenerator.HTTPAPI;

import org.json.JSONArray;
import org.json.JSONObject;


import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;

import java.net.URL;



public class swAPI {
    private static HttpsURLConnection conn;

    public static void main(String[] args) {

        BufferedReader reader;
        String line;
        StringBuffer responseContent = new StringBuffer();

        try{
            URL url = new URL("https://swapi.dev/api/people/1");
            conn = (HttpsURLConnection) url.openConnection();

            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);

            int status = conn.getResponseCode();
            if (status > 299) {
                reader = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
                while((line = reader.readLine()) != null) {
                    responseContent.append(line);
                }
                reader.close();
            } else {
                reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while((line = reader.readLine()) != null) {
                    responseContent.append(line);
                }
                reader.close();
            }
            System.out.println(responseContent.toString());
           // parse(responseContent.toString());
            System.out.println(status);
        } catch (MalformedURLException e){
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }
    }
      /*
    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("https://swapi.dev/api/people/1")).build();
    client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
            .thenApply(HttpResponse::body)
            .thenAccept(System.out::println)
            .join();



    public static String parse(String responseBody) {
        JSONArray albums = new JSONArray(responseBody);
        for(int i = 0; i < albums.length(); i++) {
            JSONObject album = albums.getJSONObject(i);
            String id = album.getString("name");
            String userId = album.getString("hair_color");
            String title = album.getString("eye_color");
            System.out.println(id + " " + title + " " + userId);
        }
        return null;
    }

       */
}