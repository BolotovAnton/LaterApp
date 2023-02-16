//package ru.yandex.practicum.laterApp.item;
//
//import org.springframework.stereotype.Repository;
//import ru.yandex.practicum.laterApp.item.model.Item;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Repository
//public class InMemoryItemRepository implements ItemRepository{
//
//    private final List<Item> items = new ArrayList<>();
//
//    private static long generateId;
//
//    @Override
//    public List<Item> findByUserId(long userId) {
//        return items.stream().filter(x -> x.getUserId() == userId).collect(Collectors.toList());
//    }
//
//    @Override
//    public Item save(Item item) {
//        item.setId(++generateId);
//        items.add(item);
//        return item;
//    }
//
//    @Override
//    public void deleteByUserIdAndItemId(long userId, long itemId) {
//        items.removeIf(item -> item.getId() == itemId && item.getUserId() == userId);
//    }
//}
