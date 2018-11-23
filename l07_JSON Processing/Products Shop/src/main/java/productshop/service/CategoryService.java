package productshop.service;

import productshop.domain.dtos.CategorySeedDto;

public interface CategoryService {

    void seedCategories(CategorySeedDto[] categorySeedDtos);
}
