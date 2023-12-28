package com.server.muchu.search.controller;

import com.server.muchu.search.service.SearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequestMapping("/search")
@RequiredArgsConstructor
public class SearchController {

    private final SearchService searchService;

    @GetMapping
    public String searchPage(@RequestParam(name = "keyword") String keyword,
                             @RequestParam(name = "page", defaultValue = "1") int page,
                             Model model) {

        int clusterNum = searchService.getClusterNumByKeyword(keyword);

        searchService.pageSettingByClusterNum(clusterNum, model, page, keyword);

        return "search/searchResult";

    }


}
