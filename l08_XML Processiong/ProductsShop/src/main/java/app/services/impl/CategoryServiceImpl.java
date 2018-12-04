package app.services.impl;

import app.dto.binding.CategoryDto;
import app.dto.view.CategoryView;
import app.entities.Category;
import app.repositories.CategoryRepository;
import app.services.api.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;
    private ModelMapper mapper;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
        this.mapper = new ModelMapper();
    }

    @Override
    public void save(CategoryDto categoryDto) {
        Category category = this.mapper.map(categoryDto, Category.class);
        this.categoryRepository.saveAndFlush(category);
    }

    @Override
    public void saveAll(List<CategoryDto> categories) {
        for (CategoryDto category : categories) {
            this.save(category);
        }
    }

    @Override
    public List<CategoryView> getCategoriesByProductsCount() {
        return this.categoryRepository.getCategoriesByProductsCount();
    }
}
