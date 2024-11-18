package com.cobook.CoBook_BE.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@Document(collection = "Demo")
public class Space {
    @Id
    public String sid;
    public String sname;
    public String tag;
    public boolean approvalRequired;
}
