package app.service;

import app.domain.dtos.ItemSeedDto;
import app.domain.entities.Category;
import app.domain.entities.Item;
import app.repository.CategoryRepository;
import app.repository.ItemRepository;
import app.util.FIleUtil;
import app.util.ValidatorUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;


@Service
public class ItemServiceImpl implements ItemService {
    private static final String EMPLOYEES_FILE_PATH = "D:\\fast_food\\src\\main\\resources\\json\\items.json";

    private final ItemRepository itemRepository;
    private final CategoryRepository categoryRepository;
    private final FIleUtil fIleUtil;
    private final ValidatorUtil validatorUtil;
    private final ModelMapper modelMapper;
    private final Gson gson;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository, CategoryRepository categoryRepository, FIleUtil fIleUtil, ValidatorUtil validatorUtil, ModelMapper modelMapper, Gson gson) {
        this.itemRepository = itemRepository;
        this.categoryRepository = categoryRepository;
        this.fIleUtil = fIleUtil;
        this.validatorUtil = validatorUtil;
        this.modelMapper = modelMapper;
        this.gson = gson;
    }

    @Override
    public String readEmployees() throws IOException {
        return this.fIleUtil.readFile(EMPLOYEES_FILE_PATH);
    }

    @Override
    public String seedItems() throws IOException {
        StringBuilder sb = new StringBuilder();

        ItemSeedDto[] itemSeedDtos = this.gson.fromJson(readEmployees(), ItemSeedDto[].class);

        for (ItemSeedDto itemSeedDto : itemSeedDtos) {
            if (!this.validatorUtil.isValid(itemSeedDto)) {
                sb.append("Error: Incorrect Data!").append(System.lineSeparator());
                continue;
            }

            if (this.itemRepository.findByName(itemSeedDto.getName()).orElse(null) != null) {
                sb.append("Error: Incorrect Data!").append(System.lineSeparator());
                continue;
            }

            Category categoryEntity = this.categoryRepository.findByName(itemSeedDto.getCategory()).orElse(null);
            if (categoryEntity == null) {
                categoryEntity = new Category();
                categoryEntity.setName(itemSeedDto.getCategory());

                this.categoryRepository.saveAndFlush(categoryEntity);
            }

            Item itemEntity = this.modelMapper.map(itemSeedDto, Item.class);
            itemEntity.setCategory(categoryEntity);

            this.itemRepository.saveAndFlush(itemEntity);
            sb.append(String.format("Record %s successfully imported!", itemEntity.getName())).append(System.lineSeparator());
        }

        return sb.toString();
    }
}
