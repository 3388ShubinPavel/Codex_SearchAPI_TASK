package com.codex_task.codex.controller;

import com.codex_task.codex.contenhandle.Document;
import com.codex_task.codex.dto.DocSearchRequest;
import com.codex_task.codex.dto.SearchResult;
import com.codex_task.codex.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/document")
public class DocumentController {

    @Autowired
    private DocumentService documentService;

    @PostMapping
    public ResponseEntity<Document> saveDocument(@RequestBody Document documentRequest){

        Document savedDocument = documentService.saveDocumentToDatabase(documentRequest);
        return ResponseEntity.ok(savedDocument);
    }

    @PatchMapping
    public ResponseEntity<Document> patchDocument(@RequestBody PatchRequest request){
        Document updatedDocument = documentService.updateDocument(request.getDocumentId(), request.getDocument());
        return ResponseEntity.ok(updatedDocument);
    }

    @PostMapping("/search")
    public ResponseEntity<List<SearchResult>> searchDocuments(@RequestBody DocSearchRequest request){
        List<SearchResult> searchResults = documentService.searchDocuments(request.getQuerystring());
        return ResponseEntity.ok(searchResults);
    }
}

class PatchRequest {
    private Long documentId;
    private Document document;

    public Long getDocumentId() {
        return documentId;
    }

    public void setDocumentId(Long documentId) {
        this.documentId = documentId;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }
}



