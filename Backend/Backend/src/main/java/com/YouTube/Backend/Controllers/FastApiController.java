import org.springframework.http.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Map;

@RestController
@RequestMapping("/fastapi")
@Validated
public class FastApiController {

    private final RestTemplate restTemplate;
    private static final String FASTAPI_BASE_URL = "http://localhost:8000"; // Base URL for FastAPI services

    // Constructor for injecting RestTemplate dependency
    public FastApiController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * Endpoint to interact with FastAPI for getting video recommendations.
     * 
     * @param request The request payload containing user and video details.
     * @return Response from FastAPI or an error message if an exception occurs.
     */
    @PostMapping("/recommendations")
    public ResponseEntity<?> getRecommendations(@RequestBody @Valid RecommendationRequest request) {
        String url = FASTAPI_BASE_URL + "/recommendations"; // Target FastAPI endpoint
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON); // Setting JSON content type
        HttpEntity<RecommendationRequest> entity = new HttpEntity<>(request, headers); // Preparing request entity

        try {
            // Sending a POST request to the FastAPI endpoint
            ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);
            return ResponseEntity.ok(response.getBody()); // Returning the response body
        } catch (HttpClientErrorException e) {
            // Handling 4xx client-side errors
            return ResponseEntity.status(e.getStatusCode())
                    .body(Map.of("error", "FastAPI Client Error", "message", e.getResponseBodyAsString()));
        } catch (HttpServerErrorException e) {
            // Handling 5xx server-side errors
            return ResponseEntity.status(e.getStatusCode())
                    .body(Map.of("error", "FastAPI Server Error", "message", e.getResponseBodyAsString()));
        } catch (ResourceAccessException e) {
            // Handling connection-related issues
            return ResponseEntity.status(503).body(Map.of("error", "Service Unavailable", "message", "FastAPI unreachable"));
        } catch (Exception e) {
            // Handling any other unexpected exceptions
            return ResponseEntity.status(500).body(Map.of("error", "Internal Server Error", "message", e.getMessage()));
        }
    }

    /**
     * Endpoint to interact with FastAPI for content moderation.
     * 
     * @param request The request payload containing video content details.
     * @return Response from FastAPI or an error message if an exception occurs.
     */
    @PostMapping("/moderation")
    public ResponseEntity<?> moderateContent(@RequestBody @Valid ModerationRequest request) {
        String url = FASTAPI_BASE_URL + "/moderation"; // Target FastAPI endpoint
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON); // Setting JSON content type
        HttpEntity<ModerationRequest> entity = new HttpEntity<>(request, headers); // Preparing request entity

        try {
            // Sending a POST request to the FastAPI endpoint
            ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);
            return ResponseEntity.ok(response.getBody()); // Returning the response body
        } catch (HttpClientErrorException e) {
            // Handling 4xx client-side errors
            return ResponseEntity.status(e.getStatusCode())
                    .body(Map.of("error", "FastAPI Client Error", "message", e.getResponseBodyAsString()));
        } catch (HttpServerErrorException e) {
            // Handling 5xx server-side errors
            return ResponseEntity.status(e.getStatusCode())
                    .body(Map.of("error", "FastAPI Server Error", "message", e.getResponseBodyAsString()));
        } catch (ResourceAccessException e) {
            // Handling connection-related issues
            return ResponseEntity.status(503).body(Map.of("error", "Service Unavailable", "message", "FastAPI unreachable"));
        } catch (Exception e) {
            // Handling any other unexpected exceptions
            return ResponseEntity.status(500).body(Map.of("error", "Internal Server Error", "message", e.getMessage()));
        }
    }

    /**
     * Endpoint to interact with FastAPI for ad targeting.
     * 
     * @param request The request payload containing user and context details.
     * @return Response from FastAPI or an error message if an exception occurs.
     */
    @PostMapping("/ad-targeting")
    public ResponseEntity<?> getAdTargeting(@RequestBody @Valid AdTargetingRequest request) {
        String url = FASTAPI_BASE_URL + "/ad-targeting"; // Target FastAPI endpoint
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON); // Setting JSON content type
        HttpEntity<AdTargetingRequest> entity = new HttpEntity<>(request, headers); // Preparing request entity

        try {
            // Sending a POST request to the FastAPI endpoint
            ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);
            return ResponseEntity.ok(response.getBody()); // Returning the response body
        } catch (HttpClientErrorException e) {
            // Handling 4xx client-side errors
            return ResponseEntity.status(e.getStatusCode())
                    .body(Map.of("error", "FastAPI Client Error", "message", e.getResponseBodyAsString()));
        } catch (HttpServerErrorException e) {
            // Handling 5xx server-side errors
            return ResponseEntity.status(e.getStatusCode())
                    .body(Map.of("error", "FastAPI Server Error", "message", e.getResponseBodyAsString()));
        } catch (ResourceAccessException e) {
            // Handling connection-related issues
            return ResponseEntity.status(503).body(Map.of("error", "Service Unavailable", "message", "FastAPI unreachable"));
        } catch (Exception e) {
            // Handling any other unexpected exceptions
            return ResponseEntity.status(500).body(Map.of("error", "Internal Server Error", "message", e.getMessage()));
        }
    }

    // Inner classes for request payloads
    public static class RecommendationRequest {
        @NotNull
        private String userId; // User ID for the recommendation request

        // Getters and setters
        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }
    }

    public static class ModerationRequest {
        @NotNull
        private String content; // Video content to be moderated

        // Getters and setters
        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }

    public static class AdTargetingRequest {
        @NotNull
        private String userId; // User ID for ad targeting
        private String context; // Context of the ad targeting request

        // Getters and setters
        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getContext() {
            return context;
        }

        public void setContext(String context) {
            this.context = context;
        }
    }
}
