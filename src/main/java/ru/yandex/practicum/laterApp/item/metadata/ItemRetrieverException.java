package ru.yandex.practicum.laterApp.item.metadata;

import ru.yandex.practicum.laterApp.common.LaterApplicationException;

public class ItemRetrieverException extends LaterApplicationException {
    public ItemRetrieverException(String message) {
        super(message);
    }

    public ItemRetrieverException(String message, Throwable cause) {
        super(message, cause);
    }
}