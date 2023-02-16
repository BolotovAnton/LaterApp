package ru.yandex.practicum.laterApp.item;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class GetItemRequest {

    private long userId;
    private State state;
    private ContentType contentType;
    private Sort sort;
    private int limit;
    private List<String> tags;
}
