// RecommendationRequest.java
package com.YouTube.Backend.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class RecommendationRequest {
    
    @NotNull
    @Positive
    @JsonProperty("user_id")
    private int user_id;
    
    @NotNull
    @Positive
    @JsonProperty("video_id")
    private int video_id;
    
    @JsonProperty("watch_time")
    private float watch_time;

    // Constructors
    public RecommendationRequest() {}

    public RecommendationRequest(int user_id, int video_id, float watch_time) {
        this.user_id = user_id;
        this.video_id = video_id;
        this.watch_time = watch_time;
    }

    // Getters and Setters
    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getVideo_id() {
        return video_id;
    }

    public void setVideo_id(int video_id) {
        this.video_id = video_id;
    }

    public float getWatch_time() {
        return watch_time;
    }

    public void setWatch_time(float watch_time) {
        this.watch_time = watch_time;
    }

    @Override
    public String toString() {
        return "RecommendationRequest{" +
                "user_id=" + user_id +
                ", video_id=" + video_id +
                ", watch_time=" + watch_time +
                '}';
    }
}

// ModerationRequest.java
package com.YouTube.Backend.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class ModerationRequest {
    
    @NotNull
    @Positive
    @JsonProperty("video_id")
    private int video_id;
    
    @JsonProperty("video_content")
    private String video_content;

    // Constructors
    public ModerationRequest() {}

    public ModerationRequest(int video_id, String video_content) {
        this.video_id = video_id;
        this.video_content = video_content;
    }

    // Getters and Setters
    public int getVideo_id() {
        return video_id;
    }

    public void setVideo_id(int video_id) {
        this.video_id = video_id;
    }

    public String getVideo_content() {
        return video_content;
    }

    public void setVideo_content(String video_content) {
        this.video_content = video_content;
    }

    @Override
    public String toString() {
        return "ModerationRequest{" +
                "video_id=" + video_id +
                ", video_content='" + video_content + '\'' +
                '}';
    }
}

// AdTargetingRequest.java
package com.YouTube.Backend.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

public class AdTargetingRequest {
    
    @NotNull
    @Positive
    @JsonProperty("user_id")
    private int user_id;
    
    @Positive
    @JsonProperty("age")
    private int age;
    
    @JsonProperty("location")
    private String location;
    
    @JsonProperty("interests")
    private List<String> interests;

    // Constructors
    public AdTargetingRequest() {}

    public AdTargetingRequest(int user_id, int age, String location, List<String> interests) {
        this.user_id = user_id;
        this.age = age;
        this.location = location;
        this.interests = interests;
    }

    // Getters and Setters
    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<String> getInterests() {
        return interests;
    }

    public void setInterests(List<String> interests) {
        this.interests = interests;
    }

    @Override
    public String toString() {
        return "AdTargetingRequest{" +
                "user_id=" + user_id +
                ", age=" + age +
                ", location='" + location + '\'' +
                ", interests=" + interests +
                '}';
    }
}
