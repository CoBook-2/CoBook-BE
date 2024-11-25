package com.cobook.CoBook_BE.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class Receipt {
    String address;
    String category;
    Date date;
    String image;
    Date indate;
    boolean processed;
    String purchased;
    int totalCost;
    String writer;

    List<Item> items;
}
