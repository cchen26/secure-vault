package com.cchen26.securevault.repository;

import com.cchen26.securevault.dto.api.IDocument;
import com.cchen26.securevault.entity.DocumentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.cchen26.securevault.constant.Constants.*;

/**
 * @author Chao
 * @version 1.0
 * @email chaochen234@gmail.com
 * @since 2024-10-05
 */

@Repository
public interface DocumentRepository extends JpaRepository<DocumentEntity, Long> {
    @Query(countQuery = SELECT_COUNT_DOCUMENTS_QUERY, value = SELECT_DOCUMENTS_QUERY, nativeQuery = true)
    Page<IDocument> findDocuments(Pageable pageable);

    @Query(countQuery = SELECT_COUNT_DOCUMENTS_BY_NAME_QUERY, value = SELECT_DOCUMENTS_BY_NAME_QUERY, nativeQuery = true)
    Page<IDocument> findDocumentsByName(@Param("documentName") String documentName, Pageable pageable);

    @Query(value = SELECT_DOCUMENT_QUERY, nativeQuery = true)
    Optional<IDocument> findDocumentByDocumentId(String documentId);

    Optional<DocumentEntity> findByDocumentId(String documentId);
}
