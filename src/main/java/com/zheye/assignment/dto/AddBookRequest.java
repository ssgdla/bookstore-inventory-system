package com.zheye.assignment.dto;

import lombok.Data;

@Data
public class AddBookRequest {

    private String title;

    private String author;

    private String isbn;

    private String price;

    private Long quantity;

}
