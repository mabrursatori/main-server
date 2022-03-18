package com.mabrur.server.doa.repos;

import com.mabrur.server.doa.entities.Category;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface CategoryRepo extends PagingAndSortingRepository<Category, Long> {
    
}
