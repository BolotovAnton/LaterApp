package ru.yandex.practicum.laterApp.item;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.laterApp.item.dto.ItemDto;

import java.util.List;

@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {
    private final ItemServiceImpl itemService;

    @GetMapping
    public List<ItemDto> get(@RequestHeader("X-Later-User-Id") long userId,
                             @RequestParam(defaultValue = "unread") String state,
                             @RequestParam(defaultValue = "all") String contentType,
                             @RequestParam(defaultValue = "newest") String sort,
                             @RequestParam(defaultValue = "10") int limit,
                             @RequestParam(required = false) List<String> tags)  {
        GetItemRequest getItemRequest = new GetItemRequest(
                userId,
                State.valueOf(state),
                ContentType.valueOf(contentType),
                Sort.valueOf(sort),
                limit,
                tags
        );
        return itemService.getItems(getItemRequest);
    }

    @PostMapping
    public ItemDto add(@RequestHeader("X-Later-User-Id") long userId,
                       @RequestBody ItemDto itemDto) {
        return itemService.addNewItem(userId, itemDto);
    }

    @DeleteMapping("/{itemId}")
    public void deleteItem(@PathVariable long itemId) {
        itemService.deleteItem(itemId);
    }
}