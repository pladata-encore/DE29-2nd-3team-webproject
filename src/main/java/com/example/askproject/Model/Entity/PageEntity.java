package com.example.askproject.Model.Entity;

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
public class PageEntity extends BaseTimeEntity{
    @Id
    private String pageId;
    private String pageTitle;
    private String pageComment;
    @Column(name = "page_today_count", nullable = false)
    // @ColumnDefault(value = "0")
    private Long pageTodayCount = 0L; // 기본값으로 0 설정
}
