// FastApiController.java - Controller for FastAPI integration

package com.YouTube.Backend.controllers;

import com.YouTube.Backend.models.AdTargetingRequest;
import com.YouTube.Backend.models.ModerationRequest;
import com.YouTube.Backend.models.RecommendationRequest;
import com.YouTube.Backend.services.FastApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;

import java.util.Map;

@RestController
@RequestMapping("/api/ml")
@CrossOrigin(origins = "*") // Configure as needed for your frontend
public class FastApiController {

    private static final Logger logger = LoggerFactory.getLogger(FastApiController.class);
    
    @Autowired
    private FastApiService fastApiService;

    /**
     * Health check endpoint for FastAPI integration
     */
    @GetMapping("/health")
    public ResponseEntity<Object> checkFastApiHealth() {
        try {
            ResponseEntity<String> response = fastApiService.checkHealth();
            return ResponseEntity.ok(Map.of(
                "status", "FastAPI service is healthy",
                "fastapi_response", response.getBody()
            ));
        } catch (RestClientException e) {
            logger.error("FastAPI service health check failed: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                    .body(Map.of(
                        "status", "FastAPI service unavailable",
                        "error", e.getMessage()
                    ));
        }
    }

    /**
     * Get video recommendations
     */
    @PostMapping("/recommendations")
    public ResponseEntity<Object> getRecommendations(@RequestBody RecommendationRequest request) {
        try {
            // Validate request
            if (request.getUser_id() <= 0 || request.getVideo_id() <= 0) {
                return ResponseEntity.badRequest()
                        .body(Map.of("error", "Invalid user_id or video_id"));
            }

            ResponseEntity<String> response = fastApiService.getRecommendations(request);
            return ResponseEntity.ok(response.getBody());
            
        } catch (RestClientException e) {
            logger.error("Recommendation request failed: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                    .body(Map.of(
                        "error", "Recommendation service unavailable",
                        "message", e.getMessage()
                    ));
        } catch (Exception e) {
            logger.error("Unexpected error in recommendations: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Internal server error"));
        }
    }

    /**
     * Moderate video content
     */
    @PostMapping("/moderation")
    public ResponseEntity<Object> moderateVideo(@RequestBody ModerationRequest request) {
        try {
            // Validate request
            if (request.getVideo_id() <= 0) {
                return ResponseEntity.badRequest()
                        .body(Map.of("error", "Invalid video_id"));
            }

            ResponseEntity<String> response = fastApiService.moderateContent(request);
            return ResponseEntity.ok(response.getBody());
            
        } catch (RestClientException e) {
            logger.error("Moderation request failed: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                    .body(Map.of(
                        "error", "Moderation service unavailable",
                        "message", e.getMessage()
                    ));
        } catch (Exception e) {
            logger.error("Unexpected error in moderation: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Internal server error"));
        }
    }

    /**
     * Get ad targeting recommendations
     */
    @PostMapping("/ad-targeting")
    public ResponseEntity<Object> getAdTargeting(@RequestBody AdTargetingRequest request) {
        try {
            // Validate request
            if (request.getUser_id() <= 0 || request.getAge() <= 0) {
                return ResponseEntity.badRequest()
                        .body(Map.of("error", "Invalid user_id or age"));
            }

            ResponseEntity<String> response = fastApiService.getAdTargeting(request);
            return ResponseEntity.ok(response.getBody());
            
        } catch (RestClientException e) {
            logger.error("Ad targeting request failed: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                    .body(Map.of(
                        "error", "Ad targeting service unavailable",
                        "message", e.getMessage()
                    ));
        } catch (Exception e) {
            logger.error("Unexpected error in ad targeting: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Internal server error"));
        }
    }
}
