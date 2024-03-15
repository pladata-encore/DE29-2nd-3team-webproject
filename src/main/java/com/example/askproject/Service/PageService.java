package com.example.askproject.Service;

import java.util.List;

import com.example.askproject.Model.DTO.PageDTO;

public interface PageService {
    public void insertPage(PageDTO pageDTO);

    public void deletePage(String pageId);

    public void updatePage(PageDTO pageDTO);

    public List<PageDTO> findAllPage();

    public PageDTO findByPageId(String pageId);

    public List<String> findByPageIdContaining(String keyword);

    public void increasePageCount(String pageId);
}
