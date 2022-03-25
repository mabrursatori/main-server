package com.mabrur.server.doa.controllers;

import com.mabrur.server.doa.dto.ItemFormData;
import com.mabrur.server.doa.entities.Item;
import com.mabrur.server.doa.repos.ItemRepo;
import com.mabrur.server.doa.services.ItemService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/doa/item")
public class APIItemController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public Iterable<Item> findAll() {
        return itemService.findAll();
    }

    @PostMapping("/save")
    public Item create(ItemFormData itemFormData) {

        Item item = modelMapper.map(itemFormData, Item.class);
        return item;
        // return itemService.save(item);
    }

    @PostMapping("/update/{id}")
    public ItemFormData update(@PathVariable("id") Long id, ItemFormData itemFormData) {

        Item item = modelMapper.map(itemFormData, Item.class);
        return itemFormData;
        // return itemService.save(item);
    }

}
