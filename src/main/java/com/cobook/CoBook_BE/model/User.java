package com.cobook.CoBook_BE.model;

import lombok.Getter;
import lombok.Setter;
//import org.springframework.data.annotation.Id;
//import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Setter
@Getter
//@Document(collection = "Demo")
public class User {
//    @Id
    public String uid;
    public String uname;
    public String pw;
    public String phone;
    public ArrayList<String> entered;
}

