package com.javaexpress.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.javaexpress.models.Category;
import com.javaexpress.repository.CategoryRepository;

@Service
public class CategoryService {
	
	
	@Autowired
	private CategoryRepository categoryRepository;

	public Category createCategory(Category category) {
		category.setBarCode(UUID.randomUUID().toString());
		return categoryRepository.save(category);
	}

	public Category fetchCategory(Integer categoryId) {
		
		return categoryRepository.findById(categoryId).orElseThrow(()->
		new RuntimeException("Category Not exists in db"));
	}
	

	public Category updateCategory(Category category, Integer categoryId) {
		Category dbCategory = fetchCategory(categoryId);
		dbCategory.setName(category.getName());
		return categoryRepository.save(dbCategory);
	}

	public void deleteCategory(Integer categoryId) {
		Category dbCategory = fetchCategory(categoryId);
		categoryRepository.delete(dbCategory);
		
	}
	
	
	

}
