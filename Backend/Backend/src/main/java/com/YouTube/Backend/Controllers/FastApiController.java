package com.YouTube.Backend.Controllers;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * FastApiController - Handles communication with the FastAPI service.
 * This controller sends HTTP requests to the FastAPI server for specific functionalities:
 * 1. Fetching video recommendations.
 * 2. Moderating video content.
 * 3. Fetching ad targeting data.
 *
 * Each API call is routed through this controller to ensure centralized handling
 * of FastAPI requests, maintaining clean code and separation of concerns.
 */
@RestController
@RequestMapping("/fastapi")
public class FastApiController {

    // RestTemplate instance to make HTTP calls to external services.
    private final RestTemplate restTemplate = new RestTemplate();

    // Base URL for FastAPI server; update this if the FastAPI server address changes.
    private static final String FASTAPI_BASE_URL = "http://localhost:8000";

    /**
     * Sends a POST request to the FastAPI /recommendations/ endpoint
     * to fetch video recommendations for a specific user and video.
     *
     * @param request The request body containing user_id, video_id, and watch_time.
     * @return The response from the FastAPI server as a JSON string.
     */
    @PostMapping("/recommendations/")
    public ResponseEntity<String> getRecommendations(@RequestBody RecommendationRequest request) {
        // Construct the FastAPI endpoint URL
        String url = FASTAPI_BASE_URL + "/recommendations/";
        
        // Create HTTP headers to specify JSON content type
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        // Create the HTTP entity combining headers and request body
        HttpEntity<RecommendationRequest> entity = new HttpEntity<>(request, headers);
        
        // Make the POST request to the FastAPI endpoint
        ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);
        
        // Return the FastAPI response to the caller
        return ResponseEntity.ok(response.getBody());
    }

    /**
     * Sends a POST request to the FastAPI /moderation/ endpoint
     * to moderate the content of a specific video.
     *
     * @param request The request body containing video_id and video_content.
     * @return The response from the FastAPI server as a JSON string.
     */
    @PostMapping("/moderation/")
    public ResponseEntity<String> moderateVideo(@RequestBody ModerationRequest request) {
        // Construct the FastAPI endpoint URL
        String url = FASTAPI_BASE_URL + "/moderation/";
        
        // Create HTTP headers to specify JSON content type
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        // Create the HTTP entity combining headers and request body
        HttpEntity<ModerationRequest> entity = new HttpEntity<>(request, headers);
        
        // Make the POST request to the FastAPI endpoint
        ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);
        
        // Return the FastAPI response to the caller
        return ResponseEntity.ok(response.getBody());
    }

    /**
     * Sends a POST request to the FastAPI /ad-targeting/ endpoint
     * to fetch personalized ad targeting data for a user.
     *
     * @param request The request body containing user_id, age, location, and interests.
     * @return The response from the FastAPI server as a JSON string.
     */
    @PostMapping("/ad-targeting/")
    public ResponseEntity<String> getAds(@RequestBody AdTargetingRequest request) {
        // Construct the FastAPI endpoint URL
        String url = FASTAPI_BASE_URL + "/ad-targeting/";
        
        // Create HTTP headers to specify JSON content type
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        // Create the HTTP entity combining headers and request body
        HttpEntity<AdTargetingRequest> entity = new HttpEntity<>(request, headers);
        
        // Make the POST request to the FastAPI endpoint
        ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);
        
        // Return the FastAPI response to the caller
        return ResponseEntity.ok(response.getBody());
    }

    // Inner class to represent the request body for recommendations API
    public static class RecommendationRequest {
        private int user_id;  // Unique identifier for the user
        private int video_id; // Unique identifier for the video
        private float watch_time; // Watch time in seconds

        // Getters and setters
        public int getUser_id() { return user_id; }
        public void setUser_id(int user_id) { this.user_id = user_id; }
        public int getVideo_id() { return video_id; }
        public void setVideo_id(int video_id) { this.video_id = video_id; }
        public float getWatch_time() { return watch_time; }
        public void setWatch_time(float watch_time) { this.watch_time = watch_time; }
    }

    // Inner class to represent the request body for moderation API
    public static class ModerationRequest {
        private int video_id; // Unique identifier for the video
        private String video_content; // Video content in string format for moderation analysis

        // Getters and setters
        public int getVideo_id() { return video_id; }
        public void setVideo_id(int video_id) { this.video_id = video_id; }
        public String getVideo_content() { return video_content; }
        public void setVideo_content(String video_content) { this.video_content = video_content; }
    }

    // Inner class to represent the request body for ad targeting API
    public static class AdTargetingRequest {
        private int user_id;  // Unique identifier for the user
        private int age; // User's age
        private String location; // User's geographical location
        private List<String> interests; // List of user's interests

        // Getters and setters
        public int getUser_id() { return user_id; }
        public void setUser_id(int user_id) { this.user_id = user_id; }
        public int getAge() { return age; }
        public void setAge(int age) { this.age = age; }
        public String getLocation() { return location; }
        public void setLocation(String location) { this.location = location; }
        public List<String> getInterests() { return interests; }
        public void setInterests(List<String> interests) { this.interests = interests; }
    }
}
