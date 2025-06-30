# YouTube Backend Service

A robust Spring Boot microservice that provides RESTful API endpoints for integrating with FastAPI ML services. This service acts as a gateway for video recommendations, content moderation, and ad targeting functionality.

## 🚀 Features

- **ML Service Integration**: Seamless integration with FastAPI ML microservice
- **RESTful APIs**: Clean REST endpoints for frontend consumption
- **Input Validation**: Comprehensive request validation using Bean Validation
- **Error Handling**: Robust error handling with detailed error responses
- **Health Monitoring**: Service health checks and FastAPI service monitoring
- **CORS Support**: Cross-origin resource sharing for web applications
- **Logging**: Comprehensive logging for debugging and monitoring
- **Production Ready**: Docker support and production configurations

## 📋 Prerequisites

- Java 17+
- Maven 3.6+
- FastAPI ML Service running (dependency)

## 🛠️ Installation

### 1. Clone the Repository
```bash
git clone <repository-url>
cd springboot-backend
```

### 2. Install Dependencies
```bash
mvn clean install
```

### 3. Configure Application
Edit `src/main/resources/application.properties`:
```properties
# FastAPI Service URL
fastapi.base.url=http://localhost:8000

# Server Configuration
server.port=8080
```

## 🏃‍♂️ Running the Service

### Development Mode
```bash
mvn spring-boot:run
```

### Production Mode
```bash
# Build JAR
mvn clean package

# Run JAR
java -jar target/Backend-0.0.1-SNAPSHOT.jar
```

### Using Docker
```bash
# Build the image
docker build -t youtube-backend .

# Run the container
docker run -p 8080:8080 youtube-backend
```

## 📡 API Endpoints

### Base URL
```
http://localhost:8080/api/ml
```

### Health Check
- **GET** `/api/ml/health`
- **Response**:
```json
{
  "status": "FastAPI service is healthy",
  "fastapi_response": "..."
}
```

### Machine Learning Services

#### Video Recommendations
- **POST** `/api/ml/recommendations`
- **Request Body**:
```json
{
  "user_id": 123,
  "video_id": 456,
  "watch_time": 120.5
}
```
- **Response**:
```json
{
  "recommended_video": 789,
  "user_id": 123,
  "confidence": 0.95
}
```

#### Content Moderation
- **POST** `/api/ml/moderation`
- **Request Body**:
```json
{
  "video_id": 456,
  "video_content": "Video content description"
}
```
- **Response**:
```json
{
  "moderation_result": 1,
  "video_id": 456,
  "content_length": 25,
  "is_safe": true
}
```

#### Ad Targeting
- **POST** `/api/ml/ad-targeting`
- **Request Body**:
```json
{
  "user_id": 123,
  "age": 25,
  "location": "New York",
  "interests": ["technology", "gaming", "music"]
}
```
- **Response**:
```json
{
  "recommended_ad": 1,
  "user_id": 123,
  "should_target": true,
  "user_profile": {
    "age": 25,
    "location": "New York",
    "interests_count": 3
  }
}
```

## 📁 Project Structure

```
src/
└── main/
    └── java/
        └── com/
            └── YouTube/
                └── Backend/
                    ├── BackendApplication.java           # Main application class
                    ├── controllers/
                    │   └── FastApiController.java        # REST controller
                    ├── models/                           # Request/Response models
                    │   ├── RecommendationRequest.java
                    │   ├── ModerationRequest.java
                    │   └── AdTargetingRequest.java
                    └── services/
                        └── FastApiService.java           # Business logic layer
```

## 🔧 Configuration

### Application Properties
```properties
# Server Configuration
server.port=8080
server.servlet.context-path=/
spring.application.name=YouTube Backend Service

# FastAPI Service Configuration
fastapi.base.url=http://localhost:8000

# Logging Configuration
logging.level.com.YouTube.Backend=INFO
logging.level.org.springframework.web.client=DEBUG

# HTTP Client Configuration
spring.http.client.timeout=10000

# Actuator Configuration
management.endpoints.web.exposure.include=health,info
management.endpoint.health.show-details=always

# CORS Configuration
spring.web.cors.allowed-origins=http://localhost:3000,http://localhost:8080
spring.web.cors.allowed-methods=GET,POST,PUT,DELETE,OPTIONS
spring.web.cors.allowed-headers=*
spring.web.cors.allow-credentials=true
```

### Maven Dependencies
Key dependencies in `pom.xml`:
```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-validation</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-actuator</artifactId>
    </dependency>
    <dependency>
        <groupId>com.fasterxml.jackson.core</groupId>
        <artifactId>jackson-databind</artifactId>
    </dependency>
</dependencies>
```

## 🧪 Testing

### Unit Tests
```bash
mvn test
```

### Integration Tests
```bash
mvn verify
```

### Manual API Testing
```bash
# Test health endpoint
curl http://localhost:8080/api/ml/health

# Test recommendation endpoint
curl -X POST "http://localhost:8080/api/ml/recommendations" \
  -H "Content-Type: application/json" \
  -d '{"user_id": 1, "video_id": 2, "watch_time": 120.5}'
```

## 🔍 Error Handling

The service provides comprehensive error handling:

### Validation Errors (400 Bad Request)
```json
{
  "error": "Invalid user_id or video_id"
}
```

### Service Unavailable (503 Service Unavailable)
```json
{
  "error": "Recommendation service unavailable",
  "message": "Connection refused"
}
```

### Internal Server Error (500 Internal Server Error)
```json
{
  "error": "Internal server error"
}
```

## 📊 Monitoring & Health Checks

### Health Endpoints
- `/actuator/health` - Application health status
- `/api/ml/health` - FastAPI service connectivity check

### Logging
- Request/response logging
- Error tracking with stack traces
- Service communication logging
- Performance metrics

## 🔧 Service Integration

### FastAPI ML Service Communication
The service communicates with FastAPI ML service using:
- **RestTemplate**: HTTP client for service calls
- **Circuit Breaker Pattern**: Resilient service communication
- **Retry Mechanism**: Automatic retry on transient failures
- **Timeout Configuration**: Configurable request timeouts

### Request Flow
1. Client sends request to Spring Boot endpoint
2. Request validation using Bean Validation
3. Transform request to FastAPI format
4. Call FastAPI ML service
5. Handle response and errors
6. Return formatted response to client

## 🚀 Deployment

### Local Development
1. Start FastAPI ML service on port 8000
2. Start Spring Boot service on port 8080
3. Services will auto-discover each other

### Production Deployment
```bash
# Using Docker Compose
version: '3.8'
services:
  fastapi-ml:
    build: ./fastapi-ml-service
    ports:
      - "8000:8000"
  
  springboot-backend:
    build: ./springboot-backend
    ports:
      - "8080:8080"
    environment:
      - FASTAPI_BASE_URL=http://fastapi-ml:8000
    depends_on:
      - fastapi-ml
```

### Environment Variables
```bash
# FastAPI service URL
FASTAPI_BASE_URL=http://fastapi-service:8000

# Server configuration
SERVER_PORT=8080

# Logging level
LOGGING_LEVEL_ROOT=INFO
```

## 🔒 Security Considerations

- Input validation on all endpoints
- CORS configuration for frontend integration
- Error message sanitization
- Request timeout configuration
- Service-to-service authentication (implement as needed)

## 🤝 Integration Points

### Frontend Integration
The service provides clean REST APIs for:
- Single Page Applications (React, Angular, Vue.js)
- Mobile applications
- Third-party integrations

### Microservice Architecture
- Service discovery ready
- Load balancer compatible
- Containerized deployment
- Health check endpoints for orchestration

## 📝 Development Guidelines

### Adding New ML Features
1. Create request/response models in `models/` package
2. Add service method in `FastApiService`
3. Create controller endpoint in `FastApiController`
4. Add input validation
5. Update tests and documentation

### Code Style
- Follow Spring Boot best practices
- Use dependency injection
- Implement proper error handling
- Add comprehensive logging

## 📈 Performance Considerations

- Connection pooling for HTTP clients
- Async processing where appropriate
- Response caching for static data
- Database connection optimization
- Resource monitoring and alerting


## 🆘 Support

For issues and questions:
- Create an issue in the GitHub repository
- Contact the development team
- Check application logs for detailed error information
- Verify FastAPI ML service connectivity

## 🔄 Version History

- **v1.0.0**: Initial release with ML service integration
- RESTful API endpoints for recommendations, moderation, and ad targeting
- Comprehensive error handling and logging
- Docker support and production configurations
