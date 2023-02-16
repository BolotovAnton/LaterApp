package ru.yandex.practicum.laterApp.item.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemDto {

    private Long id;

    private String name;

    private Long userId;

    private String url;

    private String resolvedUrl;

    private String mimeType;

    private String title;

    private boolean hasImage;

    private boolean hasVideo;

    private Instant dateResolved;

    private Set<String> tags = new HashSet<>();

    private boolean unread;
}
