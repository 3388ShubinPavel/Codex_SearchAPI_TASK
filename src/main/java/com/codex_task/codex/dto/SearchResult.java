package com.codex_task.codex.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SearchResult {
    private Long id;
    private String title;
    private String content;
}
