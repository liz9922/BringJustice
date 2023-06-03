package com.example.bringjustice;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URL;

public class API {
    public static void main(String[] args) {
        try {
            // Provide the URL of the JSON file
            String jsonUrl = "https://api.fbi.gov/@wanted?pageSize=2&page=2&sort_on=modified&sort_order=desc";
            ObjectMapper objectMapper = new ObjectMapper();

            // Read the JSON data from the provided URL
            JsonNode rootNode = objectMapper.readTree(new URL(jsonUrl));
            JsonNode itemsNode = rootNode.get("items");

            // Loop through the items and extract the information for each person
            for (JsonNode itemNode : itemsNode) {
                String name = itemNode.get("title").asText();
                String description = itemNode.get("description").asText();
                String url = itemNode.get("url").asText();
                // Extract other desired fields accordingly

                // Output the information
                System.out.println("Name: " + name);
                System.out.println("Description: " + description);
                System.out.println("URL: " + url);
                System.out.println("------------------------------------");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}