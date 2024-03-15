package com.example.askproject.Model.Entity;

import org.hibernate.annotations.ColumnDefault;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "page")
public class PageEntity {
    @Id
    private String pageId;
    private String pageTitle;
    private String pageComment;
    private Long pageTodayCount = 0L; // 기본값으로 0 설정
}
