package ru.yandex.practicum.laterApp.item;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.laterApp.item.dto.ItemDto;
import ru.yandex.practicum.laterApp.item.dto.ItemMapper;
import ru.yandex.practicum.laterApp.item.metadata.UrlMetaDataRetriever;
import ru.yandex.practicum.laterApp.item.model.Item;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService{

    private final ItemRepository itemRepository;

    private final UrlMetaDataRetriever urlMetaDataRetriever;

    public List<ItemDto> getItems(GetItemRequest getItemRequest) {
        return null;
    }

    public ItemDto addNewItem(Long userId, ItemDto itemDto) {
        itemDto.setUserId(userId);
        Item item = ItemMapper.toItem(itemDto);
        UrlMetaDataRetriever.UrlMetaData urlMetadata = urlMetaDataRetriever.retrieve(item.getUrl());
        Item itemWithMetaData = ItemMapper.setMetaData(item, urlMetadata);
        if (itemRepository.findAllByUserId(userId).stream().noneMatch(i -> i.getResolvedUrl().equals(itemWithMetaData.getResolvedUrl()))) {
            itemRepository.save(itemWithMetaData);
            return ItemMapper.toDto(itemWithMetaData);
        } else {
            Item existItem = itemRepository.findItemByResolvedUrl(itemWithMetaData.getResolvedUrl());
            existItem.setTags(itemWithMetaData.getTags());
            return ItemMapper.toDto(itemRepository.save(existItem));
        }
    }

    public void deleteItem(long itemId) {
        itemRepository.deleteById(itemId);
    }
}
