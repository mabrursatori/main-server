package com.mabrur.server.doa.controllers;

import com.mabrur.server.doa.entities.Item;
import com.mabrur.server.doa.repos.ItemRepo;
import com.mabrur.server.doa.services.ItemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/doa/api/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping
    public Iterable<Item> findAll() {
        return itemService.findAll();
    }

    @PostMapping
    public Item create(@RequestBody Item item) {
        return itemService.save(item);
    }

}
