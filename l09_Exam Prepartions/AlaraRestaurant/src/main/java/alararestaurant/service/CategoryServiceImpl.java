package alararestaurant.service;

import alararestaurant.domain.entities.Category;
import alararestaurant.domain.entities.Item;
import alararestaurant.repository.CategoryRepository;
import alararestaurant.util.FileUtil;
import alararestaurant.util.ValidationUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final FileUtil fIleUtil;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, FileUtil fIleUtil, ValidationUtil validationUtil, ModelMapper modelMapper, Gson gson) {
        this.categoryRepository = categoryRepository;
        this.fIleUtil = fIleUtil;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;

    }

    @Override
    public String exportCategoriesByCountOfItems() {
        StringBuilder sb = new StringBuilder();

        List<Category> categories = this.categoryRepository.categoriesByCountofItems();

        for (Category category : categories) {
            sb.append(String.format("Category: %s", category.getName())).append(System.lineSeparator());

            for (Item item : category.getItems()) {
                sb.append(String.format("--- Item Name: %s", item.getName())).append(System.lineSeparator());
                sb.append(String.format("--- Item Price: %.2f", item.getPrice())).append(System.lineSeparator());
                sb.append(System.lineSeparator());
                sb.append(System.lineSeparator());
            }
        }
        return sb.toString();
    }
}
