package com.example.askproject.Model.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PageDTO {
    private String pageId;
    private String pageTitle;
    private String pageComment;
    private Long pageTodayCount;
}
