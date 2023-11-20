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

        // api를 통해 keyword를 넘기고, ML 관련 결과값을 받아옴
        // 여기서는 군집 번호를 받아왔다고 생각(아직 ML 파트 구현이 안된 관계로)
        int clusterNum = searchService.getClusterNumByKeyword(keyword);

        // 띄워줄 페이지
        searchService.pageSettingByClusterNum(clusterNum, model, page);

        return "search/searchResult";

    }


}
