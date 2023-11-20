package com.server.muchu.search.service;

import com.server.muchu.search.entity.Meme;
import com.server.muchu.search.repository.SearchRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import static com.server.muchu.search.config.SearchConfig.*;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class SearchService {

    private final SearchRepository searchRepository;

    public int getClusterNumByKeyword(String keyword) {

        return 1;

    }


    public void pageSettingByClusterNum(int clusterNum, Model model, int page) {

        Pageable pageable = PageRequest.of(page - 1, PAGE_SIZE, Sort.by("id").ascending());

        Page<Meme> memePage = searchRepository.getMemePageByClusterNum(clusterNum, pageable);

        model.addAttribute("totalPageNum", memePage.getTotalPages());
        model.addAttribute("presentPageNum", page);
        model.addAttribute("contents", memePage.getContent());

    }



}
