package com.nextspring.modules.category;

import java.util.UUID;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class CategoryController {
	private final CategoryRepository categoryRepository;

	@MutationMapping
	CategoryEntity addCategory(@Argument CategoryInput input) {
		CategoryEntity categoryEntity = new CategoryEntity(UUID.randomUUID(), input.name());

		return categoryRepository.save(categoryEntity);
	}

	@QueryMapping
	Iterable<CategoryEntity> categories() {
		return categoryRepository.findAll();
	}

	@QueryMapping
	CategoryEntity category(@Argument UUID id) {
		return categoryRepository.findById(id).orElseThrow();
	}

	@QueryMapping
	CategoryEntity updateCategory(@Argument UUID id) {
		return categoryRepository.findById(id).orElseThrow();
	}

	@MutationMapping
	void deleteCategory(@Argument UUID id) {
		categoryRepository.deleteById(id);
	}

	record CategoryInput(String name) {
	}
}
