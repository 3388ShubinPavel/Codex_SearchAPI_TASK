package com.codex_task.codex.service;

import com.codex_task.codex.contenhandle.Document;
import com.codex_task.codex.contenhandle.ElasticDoc;
import com.codex_task.codex.controller.JsonReader;
import com.codex_task.codex.dto.SearchResult;
import com.codex_task.codex.repository.DocumentRepo;
import com.codex_task.codex.repository.ElasticDocumentRepo;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DocumentService {

    @Autowired
    private DocumentRepo documentRepo;

    @Autowired
    private ElasticDocumentRepo elasticDocumentRepo;

    @Autowired
    private JsonReader jsonReader;


    @PostConstruct
    public void loadContentFromJson() {
        if (documentRepo.count() == 0) {
            String filePath = "C:\\My Stuff\\Projects\\codex\\src\\main\\resources\\static\\mocks.json";
            List<Document> contents = jsonReader.readContentFromJson(filePath);

            if (contents != null) {
                documentRepo.saveAll(contents);
                for (Document document : contents) {
                    ElasticDoc elasticDoc = new ElasticDoc(document.getId(), document.getTitle(), document.getContent());
                    elasticDocumentRepo.save(elasticDoc);
                }
            }
        }
    }

    public Document saveDocumentToDatabase(Document document) {
        Document savedDocument = documentRepo.save(document);

        ElasticDoc elasticDoc = new ElasticDoc(savedDocument.getId(), savedDocument.getTitle(), savedDocument.getContent());
        elasticDocumentRepo.save(elasticDoc);

        return savedDocument;
    }

    public Document updateDocument(Long documentId, Document updatedDocument) {
        Document existingDocument = documentRepo.findById(documentId)
                .orElseThrow(() -> new RuntimeException("Document not found"));

        existingDocument.setTitle(updatedDocument.getTitle());
        existingDocument.setContent(updatedDocument.getContent());

        Document savedDocument = documentRepo.save(existingDocument);

        ElasticDoc elasticDoc = new ElasticDoc(savedDocument.getId(), savedDocument.getTitle(), savedDocument.getContent());
        elasticDocumentRepo.save(elasticDoc);

        return savedDocument;
    }

    public List<SearchResult> searchDocuments(String query){
        List<ElasticDoc> foundDocuments = elasticDocumentRepo.searchBySubstring(query);

        return foundDocuments.stream()
                .flatMap(doc -> {
                    List<SearchResult> results = new ArrayList<>();
                    if (doc.getTitle().toLowerCase().contains(query.toLowerCase())){
                        results.add(new SearchResult(doc.getId(), "title", doc.getTitle()));
                    }
                    if (doc.getContent().toLowerCase().contains(query.toLowerCase())) {
                        results.add(new SearchResult(doc.getId(), "content", doc.getContent()));
                    }
                    return results.stream();
                })
                .collect(Collectors.toList());
    }
}
