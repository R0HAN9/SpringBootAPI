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
    private static final String FASTAPI_BASE_URL = "http://localhost:8000";

    // Constructor injection for RestTemplate
    public FastApiController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @PostMapping("/recommendations")
    public ResponseEntity<?> getRecommendations(@RequestBody @Valid RecommendationRequest request) {
        String url = FASTAPI_BASE_URL + "/recommendations";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<RecommendationRequest> entity = new HttpEntity<>(request, headers);

        try {
            ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);
            return ResponseEntity.ok(response.getBody());
        } catch (HttpClientErrorException e) {
            return ResponseEntity.status(e.getStatusCode())
                    .body(Map.of("error", "FastAPI Client Error", "message", e.getResponseBodyAsString()));
        } catch (HttpServerErrorException e) {
            return ResponseEntity.status(e.getStatusCode())
                    .body(Map.of("error", "FastAPI Server Error", "message", e.getResponseBodyAsString()));
        } catch (ResourceAccessException e) {
            return ResponseEntity.status(503).body(Map.of("error", "Service Unavailable", "message", "FastAPI unreachable"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("error", "Internal Server Error", "message", e.getMessage()));
        }
    }

    @PostMapping("/moderation")
    public ResponseEntity<?> moderateContent(@RequestBody @Valid ModerationRequest request) {
        String url = FASTAPI_BASE_URL + "/moderation";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<ModerationRequest> entity = new HttpEntity<>(request, headers);

        try {
            ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);
            return ResponseEntity.ok(response.getBody());
        } catch (HttpClientErrorException e) {
            return ResponseEntity.status(e.getStatusCode())
                    .body(Map.of("error", "FastAPI Client Error", "message", e.getResponseBodyAsString()));
        } catch (HttpServerErrorException e) {
            return ResponseEntity.status(e.getStatusCode())
                    .body(Map.of("error", "FastAPI Server Error", "message", e.getResponseBodyAsString()));
        } catch (ResourceAccessException e) {
            return ResponseEntity.status(503).body(Map.of("error", "Service Unavailable", "message", "FastAPI unreachable"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("error", "Internal Server Error", "message", e.getMessage()));
        }
    }

    @PostMapping("/ad-targeting")
    public ResponseEntity<?> getAdTargeting(@RequestBody @Valid AdTargetingRequest request) {
        String url = FASTAPI_BASE_URL + "/ad-targeting";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<AdTargetingRequest> entity = new HttpEntity<>(request, headers);

        try {
            ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);
            return ResponseEntity.ok(response.getBody());
        } catch (HttpClientErrorException e) {
            return ResponseEntity.status(e.getStatusCode())
                    .body(Map.of("error", "FastAPI Client Error", "message", e.getResponseBodyAsString()));
        } catch (HttpServerErrorException e) {
            return ResponseEntity.status(e.getStatusCode())
                    .body(Map.of("error", "FastAPI Server Error", "message", e.getResponseBodyAsString()));
        } catch (ResourceAccessException e) {
            return ResponseEntity.status(503).body(Map.of("error", "Service Unavailable", "message", "FastAPI unreachable"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("error", "Internal Server Error", "message", e.getMessage()));
        }
    }

    // Inner classes for request payloads
    public static class RecommendationRequest {
        @NotNull
        private String userId;

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
        private String content;

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
        private String userId;
        private String context;

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
