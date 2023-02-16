package ru.yandex.practicum.laterApp.item.metadata;

import java.time.Instant;

public interface UrlMetaDataRetriever {

    UrlMetaData retrieve(String url);

    interface UrlMetaData {
        String getNormalUrl();
        String getResolvedUrl();
        String getMimeType();
        String getTitle();
        boolean isHasImage();
        boolean isHasVideo();
        Instant getDateResolved();
    }
}
