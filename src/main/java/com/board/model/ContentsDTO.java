package com.board.model;

import lombok.Data;

@Data
public class ContentsDTO {
    private int x;
    private int y;
    private int image_id;
    private String contents;
    private int user_id;
    private String description;
}
