package com.codex_task.codex.repository;

import com.codex_task.codex.contenhandle.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepo extends JpaRepository<Document, Long> {
}
