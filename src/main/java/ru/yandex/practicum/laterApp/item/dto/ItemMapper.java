package ru.yandex.practicum.laterApp.item.dto;

import ru.yandex.practicum.laterApp.item.metadata.UrlMetaDataRetriever;
import ru.yandex.practicum.laterApp.item.model.Item;

import java.util.ArrayList;
import java.util.List;

public class ItemMapper {

    public static ItemDto toDto(Item item) {
        ItemDto itemDto = new ItemDto();
        itemDto.setId(item.getId());
        itemDto.setName(item.getName());
        itemDto.setUserId(item.getUserId());
        itemDto.setUrl(item.getUrl());
        itemDto.setTags(item.getTags());
        itemDto.setUnread(itemDto.isUnread());
        itemDto.setResolvedUrl(item.getResolvedUrl());
        itemDto.setMimeType(item.getMimeType());
        itemDto.setTitle(item.getTitle());
        itemDto.setHasImage(item.isHasImage());
        itemDto.setHasVideo(item.isHasVideo());
        itemDto.setDateResolved(item.getDateResolved());
        return itemDto;
    }

    public static Item toItem(ItemDto itemDto) {
        Item item = new Item();
        item.setName(itemDto.getName());
        item.setUserId(itemDto.getUserId());
        item.setUrl(itemDto.getUrl());
        item.setTags(itemDto.getTags());
        item.setUnread(itemDto.isUnread());
        return item;
    }

    public static List<ItemDto> toDto(List<Item> items) {
        List<ItemDto> itemDtoList = new ArrayList<>();
        for (Item item : items) {
            itemDtoList.add(ItemMapper.toDto(item));
        }
        return itemDtoList;
    }

    public static Item setMetaData(Item item, UrlMetaDataRetriever.UrlMetaData urlMetadata) {
        item.setUrl(urlMetadata.getNormalUrl());
        item.setResolvedUrl(urlMetadata.getResolvedUrl());
        item.setMimeType(urlMetadata.getMimeType());
        item.setTitle(urlMetadata.getTitle());
        item.setHasImage(urlMetadata.isHasImage());
        item.setHasVideo(urlMetadata.isHasVideo());
        item.setDateResolved(urlMetadata.getDateResolved());
        return item;
    }
}
