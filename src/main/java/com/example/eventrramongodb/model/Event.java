package com.example.eventrramongodb.model;

import com.example.eventrramongodb.model.Enumerations.Status;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Document(collection = "events")
public class Event {

    @Id
    private String id;

    private Status status;

    private String time;

    private String type;

    private LocalDate date;

    private String description;

    private String locationId;

    private String adminId;

    private String clientId;

    private List<String> bandIds;

    private List<String> photographerIds;

    private List<String> cateringIds;

    public Event(String time, LocalDate date, String locationId, String type,
                 String description, String clientId, List<String> bandIds,
                 List<String> cateringIds, List<String> photographerIds,
                 String adminId, Status status) {
        this.time = time;
        this.date = date;
        this.locationId = locationId;
        this.type = type;
        this.description = description;
        this.status = status;
        this.clientId = clientId;
        this.bandIds = new ArrayList<>();
        this.cateringIds = new ArrayList<>();
        this.photographerIds = new ArrayList<>();
        this.adminId = adminId;
    }

    public Event() {
    }

    //Getters and setters
    public void setStatus(Status status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public Status getStatus() {
        return status;
    }

    public String getTime() {
        return time;
    }

    public String getType() {
        return type;
    }

    public List<String> getBandIds() {
        return bandIds;
    }

    public List<String> getPhotographerIds() {
        return photographerIds;
    }

    public List<String> getCateringIds() {
        return cateringIds;
    }

    public String getLocationId() {
        return locationId;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public String getAdminId() {
        return adminId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setBandIds(List<String> bandIds) {
        this.bandIds = bandIds;
    }

    public void setPhotographerIds(List<String> photographerIds) {
        this.photographerIds = photographerIds;
    }

    public void setCateringIds(List<String> cateringIds) {
        this.cateringIds = cateringIds;
    }


}
