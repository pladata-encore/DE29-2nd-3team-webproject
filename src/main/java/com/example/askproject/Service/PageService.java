package com.example.askproject.Service;

import java.util.List;

import com.example.askproject.Model.DTO.PageDTO;

public interface PageService {
    public void insertPage(PageDTO pageDTO)throws Exception;

    public void deletePage(String pageId)throws Exception;

    public void updatePage(PageDTO pageDTO)throws Exception;

    public List<PageDTO> findAllPage()throws Exception;

    public PageDTO findByPageId(String pageId)throws Exception;

    public List<String> findByPageIdContaining(String keyword)throws Exception;

    public void increasePageCount(String pageId)throws Exception;
}

