package com.example.board.dto;

import javax.xml.crypto.Data;
@lombok.Data
public class BoardDto {
    private int no;
    private String title;
    private String content;
    private String writer;
    private String regDate;
    private int count;

}
