package ru.yandex.practicum.laterApp.item;

import ru.yandex.practicum.laterApp.item.dto.ItemDto;

import java.util.List;

public interface ItemService {

    List<ItemDto> getItems(GetItemRequest getItemRequest);

    ItemDto addNewItem(Long userId, ItemDto itemDto);

    void deleteItem(long itemId);
}
