package com.example.askproject.Service;

import java.util.List;

import com.example.askproject.Model.DTO.PageDTO;

public interface PageService {
    public void insertPage(PageDTO pageDTO);
    public void deletePage(String pageId);
    public void updatePage(PageDTO pageDTO);
    public List<PageDTO> findAllPage();
<<<<<<< HEAD
    public PageDTO getPageById(Long pageId);
=======
    public PageDTO findByPageId(String pageId);
>>>>>>> 11b305363e8b78cf8f80edec82c3488e07b2ac3c
}
