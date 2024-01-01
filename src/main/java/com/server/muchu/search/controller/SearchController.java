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

    // ML 서버가 존재할 때의 코드 -> 현재는 ML 서버를 내렸기 때문에 /depreciated 로 매핑
    @GetMapping("/depreciated")
    public String searchPageDepreciated(@RequestParam(name = "keyword") String keyword,
                             @RequestParam(name = "page", defaultValue = "1") int page,
                             Model model) {

        int clusterNum = searchService.getClusterNumByKeyword(keyword);

        searchService.pageSettingByClusterNum(clusterNum, model, page, keyword);

        model.addAttribute("serverExist", true);

        return "search/searchResult";

    }

    @GetMapping("")
    public String searchPage(@RequestParam(name = "keyword") String keyword,
                             @RequestParam(name = "page", defaultValue = "1") int page,
                             Model model) {

        searchService.pageSettingByClusterNum(1, model, page, keyword);

        model.addAttribute("serverExist", false);

        return "search/searchResult";

    }




}
