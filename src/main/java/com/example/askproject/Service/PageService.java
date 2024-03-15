package com.example.askproject.Service;

import java.util.List;

import com.example.askproject.Model.DTO.PageDTO;

import jakarta.servlet.http.HttpServletRequest;

public interface PageService {
    void insertPage(PageDTO pageDTO);

    void deletePage(String pageId);

    void updatePage(PageDTO pageDTO);

    List<PageDTO> findAllPage();

    PageDTO findByPageId(String pageId);

    List<String> findByPageIdContaining(String keyword);

    void increasePageCount(String pageId);

}
