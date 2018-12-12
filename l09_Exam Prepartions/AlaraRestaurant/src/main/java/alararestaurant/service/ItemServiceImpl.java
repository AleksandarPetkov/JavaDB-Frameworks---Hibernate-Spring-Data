package alararestaurant.service;

import alararestaurant.domain.dtos.ItemsSeedDto;
import alararestaurant.domain.entities.Category;
import alararestaurant.domain.entities.Item;
import alararestaurant.repository.CategoryRepository;
import alararestaurant.repository.ItemRepository;
import alararestaurant.util.FileUtil;
import alararestaurant.util.ValidationUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ItemServiceImpl implements ItemService {
    private static final String ITEMS_FILE_PATH = "C:\\Users\\HP\\Desktop\\Alara Restaurant_Skeleton\\AlaraRestaurant\\src\\main\\resources\\files\\items.json";

    private final ItemRepository itemRepository;
    private final CategoryRepository categoryRepository;
    private final FileUtil fIleUtil;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;
    private final Gson gson;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository, CategoryRepository categoryRepository, FileUtil fIleUtil, ValidationUtil validationUtil, ModelMapper modelMapper, Gson gson) {
        this.itemRepository = itemRepository;
        this.categoryRepository = categoryRepository;
        this.fIleUtil = fIleUtil;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.gson = gson;
    }

    @Override
    public Boolean itemsAreImported() {
        return this.itemRepository.count() > 0;
    }

    @Override
    public String readItemsJsonFile() throws IOException {
        return this.fIleUtil.readFile(ITEMS_FILE_PATH);
    }

    @Override
    public String importItems(String items) {
        StringBuilder sb = new StringBuilder();

        ItemsSeedDto[] itemsSeedDtos = this.gson.fromJson(items,ItemsSeedDto[].class);
        for (ItemsSeedDto itemSeedDto : itemsSeedDtos) {
            if (!this.validationUtil.isValid(itemSeedDto)){
                sb.append("Invalid data format.").append(System.lineSeparator());
                continue;
            }

            if(this.itemRepository.findByName(itemSeedDto.getName()).orElse(null) != null){
                sb.append("Invalid data format.").append(System.lineSeparator());
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
            sb.append(String.format("Record %s successfully imported.", itemEntity.getName())).append(System.lineSeparator());
        }

        return sb.toString();
    }
}
