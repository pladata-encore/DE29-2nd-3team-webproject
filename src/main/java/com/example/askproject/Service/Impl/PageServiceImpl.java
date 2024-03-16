package com.example.askproject.Service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.time.LocalTime;
import com.example.askproject.Model.DAO.PageDAO;
import com.example.askproject.Model.DTO.PageDTO;
import com.example.askproject.Model.Entity.PageEntity;
import com.example.askproject.Service.PageService;

@Service
public class PageServiceImpl implements PageService {

    @Autowired
    private PageDAO pageDAO;

    @Override
    public PageDTO findByPageId(String pageId) {
        PageDTO pageDTO = new PageDTO();
        PageEntity pageEntity = pageDAO.findByPageId(pageId);
        pageDTO.setPageComment(pageEntity.getPageComment());
        pageDTO.setPageId(pageEntity.getPageId());
        pageDTO.setPageTitle(pageEntity.getPageTitle());
        pageDTO.setPageTodayCount(pageEntity.getPageTodayCount());
        return pageDTO;
    }

    @Override
    public void deletePage(String pageId) {
        pageDAO.deletePage(pageId);
    }

    @Override
    public List<PageDTO> findAllPage() {
        List<PageEntity> entities = pageDAO.findAllPage();
        List<PageDTO> dtos = new ArrayList<>();
        for (PageEntity pageEntity : entities) {
            PageDTO pageDTO = new PageDTO();
            pageDTO.setPageComment(pageEntity.getPageComment());
            pageDTO.setPageId(pageEntity.getPageId());
            pageDTO.setPageTitle(pageEntity.getPageTitle());
            pageDTO.setPageTodayCount(pageEntity.getPageTodayCount());
            dtos.add(pageDTO);
        }
        return dtos;
    }

    @Override
    public void insertPage(PageDTO pageDTO) {
        PageEntity pageEntity = new PageEntity();
        pageEntity.setPageComment(pageDTO.getPageComment());
        pageEntity.setPageId(pageDTO.getPageId());
        pageEntity.setPageTitle(pageDTO.getPageTitle());
        pageEntity.setPageTodayCount(pageDTO.getPageTodayCount());
        pageDAO.insertPage(pageEntity);
    }

    @Override
    public void updatePage(PageDTO pageDTO) {
        PageEntity pageEntity = pageDAO.findByPageId(pageDTO.getPageId());
        pageEntity.setPageComment(pageDTO.getPageComment());
        pageEntity.setPageTitle(pageDTO.getPageTitle());
        pageDAO.updatePage(pageEntity);
    }

    @Override
    public List<String> findByPageIdContaining(String keyword) {
        List<PageEntity> entities = pageDAO.findByPageIdContaining(keyword);
        List<String> pageIds = new ArrayList<>();
        for (PageEntity entity : entities) {
            pageIds.add(entity.getPageId());
        }
        return pageIds;
    }

    @Override
    public void increasePageCount(String pageId) {
        PageEntity entity = pageDAO.findByPageId(pageId);
        Long count = entity.getPageTodayCount();
        if (count == null) {
            count = 0L;
        }
        count++;
        entity.setPageTodayCount(count);
        pageDAO.updatePage(entity);
    }


    @Scheduled(cron = "0 0 0 * * *")
    public void resetToday() {
        pageDAO.resetPageTodayCount();
    }

}
