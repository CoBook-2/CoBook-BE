package com.cobook.CoBook_BE.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Demo")
public class Book {

    @Id
    private String id;
    private String data;
    private String time;


    public void setId(String id) {
        this.id = id;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public String getData() {
        return data;
    }

    public String getTime() {
        return time;
    }
}
