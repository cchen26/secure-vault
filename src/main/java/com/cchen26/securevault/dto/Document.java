package com.cchen26.securevault.dto;

import lombok.*;

import java.time.LocalDateTime;

/**
 * @author Chao
 * @version 1.0
 * @email chaochen234@gmail.com
 * @since 2024-10-05
 */

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Document {
    private Long id;
    private String documentId;
    private String name;
    private String description;
    private String uri;
    private long size;
    private String formattedSize;
    private String icon;
    private String extension;
    private String referenceId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String ownerName;
    private String ownerEmail;
    private String ownerPhone;
    private String ownerLastLogin;
    private String updaterName;
}