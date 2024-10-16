package com.codex_task.codex.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DocSearchRequest {
    private String querystring;
}
