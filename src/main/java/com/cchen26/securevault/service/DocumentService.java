package com.cchen26.securevault.service;

import com.cchen26.securevault.dto.Document;
import com.cchen26.securevault.dto.api.IDocument;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;
import java.util.List;

/**
 * @author Chao
 * @version 1.0
 * @email chaochen234@gmail.com
 * @since 2024-10-05
 */

public interface DocumentService {
    Page<IDocument> getDocuments(int page, int size);
    Page<IDocument> getDocuments(int page, int size, String name);
    Collection<Document> saveDocuments(String userId, List<MultipartFile> documents);
    IDocument updateDocument(String documentId, String name, String description);
    void deleteDocument(String documentId);
    IDocument getDocumentByDocumentId(String documentId);
    Resource getResource(String documentName);
}