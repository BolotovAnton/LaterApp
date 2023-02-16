package ru.yandex.practicum.laterApp.item;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.yandex.practicum.laterApp.item.model.Item;

import java.util.List;

interface ItemRepository extends JpaRepository<Item, Long> {

    List<Item> findAllByUserId(long userId);

    Item findItemByResolvedUrl(String resolvedUrl);
}