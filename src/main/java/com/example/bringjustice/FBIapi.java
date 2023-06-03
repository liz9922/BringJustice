package com.example.bringjustice;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class FBIapi {
    private OkHttpClient client;
    private String baseURL = "https://api.fbi.gov/@wanted?";

    public FBIapi() {
        client = new OkHttpClient();
    }

    public ArrayList<WantedPeople> getAllWantedPeople() {
        String url = baseURL + "pageSize=20&page=1&sort_on=modified&sort_order=desc&poster_classification=terrorist";
        ArrayList<WantedPeople> w = new ArrayList<>();
        Request r = new Request.Builder()
                .url(url)
                .get()
                .build();
        try {
            Response response = client.newCall(r).execute();
            if (response.isSuccessful()) {
                String json = response.body().string();
                ObjectMapper o = new ObjectMapper();
                JsonNode jsonNode = o.readTree(json);
                JsonNode terrorist = jsonNode.get("items");

                for (JsonNode terror : terrorist) {
                    JsonNode nameNode = terror.get("title");
                    JsonNode imageNode = terror.get("images").get("large");
                    JsonNode descNode = terror.get("description");

                    WantedPeople t = new WantedPeople();
                    if (nameNode != null && !nameNode.isNull()) {
                        t.setTitle(nameNode.asText());
                    }
                    if (imageNode != null && !imageNode.isNull()) {
                        t.setImageURL(imageNode.asText());
                    } else {
                        t.setImageURL("Saknas url");
                    }
                    if (descNode != null && !descNode.isNull()) {
                        t.setDescription(descNode.asText());
                    }
                    w.add(t);
                }
            }
            return w;
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public ArrayList<WantedPeople> getAllWantedPeopleGPT() {
        String url = baseURL + "pageSize=20&page=1&sort_on=modified&sort_order=desc&poster_classification=terrorist";
        ArrayList<WantedPeople> wantedPeopleList = new ArrayList<>();
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                String json = response.body().string();
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonNode = objectMapper.readTree(json);
                JsonNode terrorists = jsonNode.get("items");

                for (JsonNode terrorist : terrorists) {
                    JsonNode nameNode = terrorist.get("title");
                    JsonNode imagesNode = terrorist.get("images");
                    JsonNode largeImageNode = null;
                    if (imagesNode.isArray()) {
                        for (JsonNode imageNode : imagesNode) {
                            if (imageNode.has("large")) {
                                largeImageNode = imageNode.get("large");
                                break;
                            }
                        }
                    } else {
                        largeImageNode = imagesNode.get("large");
                    }
                    JsonNode descNode = terrorist.get("description");

                    WantedPeople wantedPerson = new WantedPeople();
                    if (nameNode != null && !nameNode.isNull()) {
                        wantedPerson.setTitle(nameNode.asText());
                    }
                    if (largeImageNode != null && !largeImageNode.isNull()) {
                        wantedPerson.setImageURL(largeImageNode.asText());
                    } else {
                        wantedPerson.setImageURL("Saknas url");
                    }
                    if (descNode != null && !descNode.isNull()) {
                        wantedPerson.setDescription(descNode.asText());
                    }
                    wantedPeopleList.add(wantedPerson);
                }
            } else {
                // Handle unsuccessful response here if needed
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return wantedPeopleList;


    }
}
