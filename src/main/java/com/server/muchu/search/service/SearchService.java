package com.server.muchu.search.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.server.muchu.search.entity.Meme;
import com.server.muchu.search.repository.SearchRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

import static com.server.muchu.search.config.SearchConfig.*;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class SearchService {

    @Value("${mlServer.ip}")
    private String requestIP;
    @Value("${mlServer.port}")
    private String requestPort;

    private final SearchRepository searchRepository;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @SneakyThrows
    public int getClusterNumByKeyword(String keyword) {

        HttpHeaders requestHeader = new HttpHeaders();
        requestHeader.setContentType(MediaType.APPLICATION_JSON);

        JSONObject requestBody = new JSONObject();
        requestBody.put("word", keyword);

        HttpEntity<String> entity = new HttpEntity<>(requestBody.toString(), requestHeader);

        String url = "http://" + requestIP + ":" + requestPort + "/k-means";
        String response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class).getBody();
        Map<String, String> responseToMap = objectMapper.readValue(response, new TypeReference<HashMap<String, String>>() {});

        return Integer.parseInt(responseToMap.getOrDefault("clusterNum", "0"));
    }


    public void pageSettingByClusterNum(int clusterNum, Model model, int page, String keyword) {

        Pageable pageable = PageRequest.of(page - 1, PAGE_SIZE, Sort.by("id").ascending());

        Page<Meme> memePage;

        if (clusterNum == 0)
            memePage = searchRepository.getMemePageByClusterNum(1, pageable);
        else
            memePage = searchRepository.getMemePageByClusterNum(clusterNum, pageable);

        model.addAttribute("totalPageNum", memePage.getTotalPages());
        model.addAttribute("presentPageNum", page);
        model.addAttribute("contents", memePage.getContent());
        model.addAttribute("keyword", keyword);
        model.addAttribute("existKeyword", clusterNum != 0);

    }



}
