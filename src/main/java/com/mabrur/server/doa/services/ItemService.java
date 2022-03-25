package com.mabrur.server.doa.services;

import java.util.Optional;

import com.mabrur.server.doa.entities.Item;
import com.mabrur.server.doa.repos.ItemRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

    @Autowired
    private ItemRepo itemRepo;

    public Item save(Item item) {

        if (item.getId() != null) {
            Item currentItem = itemRepo.findById(item.getId()).get();
            currentItem.setTitle(item.getTitle());
            currentItem.setContentArabic(item.getContentArabic());
            currentItem.setContentIndo(item.getContentIndo());
            currentItem.setDescription(item.getDescription());
            currentItem.setCategory(item.getCategory());
            item = currentItem;
        }

        return itemRepo.save(item);
    }

    public Iterable<Item> findAll() {
        return itemRepo.findAll();
    }

    public Optional<Item> findOne(Long id) {
        return itemRepo.findById(id);
    }

    public void deleteById(long id) {
        itemRepo.deleteById(id);
    }

}
