package com.YouTube.Backend.Controllers;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/fastapi")
public class FastApiController {

    private final RestTemplate restTemplate = new RestTemplate();
    private static final String FASTAPI_BASE_URL = "http://localhost:8000";

    // 1. Get Video Recommendations
    @PostMapping("/recommendations/")
    public ResponseEntity<String> getRecommendations(@RequestBody RecommendationRequest request) {
        // Change: Send a POST request to FastAPI
        String url = FASTAPI_BASE_URL + "/recommendations/";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<RecommendationRequest> entity = new HttpEntity<>(request, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);
        return ResponseEntity.ok(response.getBody());
    }

    // 2. Moderate Video Content
    @PostMapping("/moderation/")
    public ResponseEntity<String> moderateVideo(@RequestBody ModerationRequest request) {
        String url = FASTAPI_BASE_URL + "/moderation/";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<ModerationRequest> entity = new HttpEntity<>(request, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);
        return ResponseEntity.ok(response.getBody());
    }

    // 3. Fetch Ad Targeting
    @PostMapping("/ad-targeting/")
    public ResponseEntity<String> getAds(@RequestBody AdTargetingRequest request) {
        String url = FASTAPI_BASE_URL + "/ad-targeting/";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<AdTargetingRequest> entity = new HttpEntity<>(request, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);
        return ResponseEntity.ok(response.getBody());
    }

    // Request Body for Recommendations
    public static class RecommendationRequest {
        private int user_id;  // changed to match FastAPI
        private int video_id; // changed to match FastAPI
        private float watch_time; // changed to match FastAPI

        // Getters and setters
        public int getUser_id() { return user_id; }
        public void setUser_id(int user_id) { this.user_id = user_id; }
        public int getVideo_id() { return video_id; }
        public void setVideo_id(int video_id) { this.video_id = video_id; }
        public float getWatch_time() { return watch_time; }
        public void setWatch_time(float watch_time) { this.watch_time = watch_time; }
    }

    // Request Body for Moderation
    public static class ModerationRequest {
        private int video_id; // changed to match FastAPI
        private String video_content;

        // Getters and setters
        public int getVideo_id() { return video_id; }
        public void setVideo_id(int video_id) { this.video_id = video_id; }
        public String getVideo_content() { return video_content; }
        public void setVideo_content(String video_content) { this.video_content = video_content; }
    }

    // Request Body for Ad Targeting
    public static class AdTargetingRequest {
        private int user_id;  // changed to match FastAPI
        private int age;
        private String location;
        private List<String> interests;

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
