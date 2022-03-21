package com.mabrur.server.doa.repos;

import com.mabrur.server.doa.entities.Item;

import org.springframework.data.repository.CrudRepository;

public interface ItemRepo extends CrudRepository<Item, Long> {
    
}
