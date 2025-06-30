// FastApiService.java - Service layer for FastAPI integration

package com.YouTube.Backend.services;

import com.YouTube.Backend.models.AdTargetingRequest;
import com.YouTube.Backend.models.ModerationRequest;
import com.YouTube.Backend.models.RecommendationRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class FastApiService {

    private static final Logger logger = LoggerFactory.getLogger(FastApiService.class);
    private final RestTemplate restTemplate;

    @Value("${fastapi.base.url:http://localhost:8000}")
    private String fastApiBaseUrl;

    public FastApiService() {
        this.restTemplate = new RestTemplate();
    }

    /**
     * Check if FastAPI service is healthy
     */
    public ResponseEntity<String> checkHealth() {
        try {
            String url = fastApiBaseUrl + "/health";
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            logger.info("FastAPI health check successful");
            return response;
        } catch (RestClientException e) {
            logger.error("FastAPI health check failed: {}", e.getMessage());
            throw e;
        }
    }

    /**
     * Get video recommendations from FastAPI
     */
    public ResponseEntity<String> getRecommendations(RecommendationRequest request) {
        try {
            String url = fastApiBaseUrl + "/recommendations/";
            HttpHeaders headers = createHeaders();
            HttpEntity<RecommendationRequest> entity = new HttpEntity<>(request, headers);
            
            ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);
            logger.info("Recommendation request successful for user_id: {}", request.getUser_id());
            return response;
            
        } catch (RestClientException e) {
            logger.error("Recommendation request failed: {}", e.getMessage());
            throw e;
        }
    }

    /**
     * Moderate video content via FastAPI
     */
    public ResponseEntity<String> moderateContent(ModerationRequest request) {
        try {
            String url = fastApiBaseUrl + "/moderation/";
            HttpHeaders headers = createHeaders();
            HttpEntity<ModerationRequest> entity = new HttpEntity<>(request, headers);
            
            ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);
            logger.info("Moderation request successful for video_id: {}", request.getVideo_id());
            return response;
            
        } catch (RestClientException e) {
            logger.error("Moderation request failed: {}", e.getMessage());
            throw e;
        }
    }

    /**
     * Get ad targeting recommendations from FastAPI
     */
    public ResponseEntity<String> getAdTargeting(AdTargetingRequest request) {
        try {
            String url = fastApiBaseUrl + "/ad-targeting/";
            HttpHeaders headers = createHeaders();
            HttpEntity<AdTargetingRequest> entity = new HttpEntity<>(request, headers);
            
            ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);
            logger.info("Ad targeting request successful for user_id: {}", request.getUser_id());
            return response;
            
        } catch (RestClientException e) {
            logger.error("Ad targeting request failed: {}", e.getMessage());
            throw e;
        }
    }

    /**
     * Create HTTP headers for requests
     */
    private HttpHeaders createHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
}
