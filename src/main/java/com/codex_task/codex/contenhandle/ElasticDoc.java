package com.codex_task.codex.contenhandle;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "documents")
public class ElasticDoc {

    @Id
    private Long id;
    private String title;
    private String content;
}
