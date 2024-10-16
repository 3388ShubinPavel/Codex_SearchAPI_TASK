package com.codex_task.codex.repository;

import com.codex_task.codex.contenhandle.ElasticDoc;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ElasticDocumentRepo extends ElasticsearchRepository<ElasticDoc, Long> {

    @Query("{\"bool\": {\"should\": ["
            + "{\"match\": {\"title\": \"?0\"}},"
            + "{\"match\": {\"content\": \"?0\"}}"
            + "]}}")
    List<ElasticDoc> searchBySubstring(String substring);
}
