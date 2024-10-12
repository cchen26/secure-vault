package com.cchen26.securevault.dto.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

/**
 * @author Chao
 * @version 1.0
 * @email chaochen234@gmail.com
 * @since 2024-10-05
 */

public interface IDocument {
    Long getId();
    void setId(Long id);
    @JsonProperty("documentId")
    String getDocument_Id();
    void setDocument_Id(String documentId);
    String getName();
    void setName(String name);
    String getDescription();
    void setDescription(String description);
    String getUri();
    void setUri(String uri);
    String getIcon();
    void setIcon(String icon);
    long getSize();
    void setSize(long size);
    @JsonProperty("formattedSize")
    String getFormatted_Size();
    void setFormatted_Size(String formattedSize);
    String getExtension();
    void setExtension(String extension);
    @JsonProperty("referenceId")
    String getReference_Id();
    void setReference_Id(String referenceId);
    @JsonProperty("createdAt")
    LocalDateTime getCreated_At();
    void setCreated_At(LocalDateTime createdAt);
    LocalDateTime getUpdated_At();
    @JsonProperty("updatedAt")
    void setUpdated_At(LocalDateTime updatedAt);
    @JsonProperty("ownerName")
    String getOwner_Name();
    void setOwner_Name(String ownerName);
    @JsonProperty("ownerEmail")
    String getOwner_Email();
    void setOwner_Email(String ownerEmail);
    @JsonProperty("ownerPhone")
    String getOwner_Phone();
    void setOwner_Phone(String ownerPhone);
    @JsonProperty("ownerLastLogin")
    LocalDateTime getOwner_Last_Login();
    void setOwner_Last_Login(LocalDateTime ownerLastLogin);
    @JsonProperty("updaterName")
    String getUpdater_Name();
    void setUpdater_Name(String updaterName);
}