package productshop.web.controllers;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;
import productshop.domain.dtos.CategorySeedDto;
import productshop.domain.dtos.ProductInRangeDto;
import productshop.domain.dtos.ProductSeedDto;
import productshop.domain.dtos.UserSeedDto;
import productshop.service.CategoryService;
import productshop.service.ProductService;
import productshop.service.UserService;
import productshop.util.FileIOUtil;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@Controller
public class ProductShopController implements CommandLineRunner {

    private final static String USER_FILE_PATH = "C:\\Users\\HP\\Downloads\\JsonProcessing-master\\src\\main\\resources\\files\\users.json";
    private final static String CATEGORIES_FILE_PATH = "C:\\Users\\HP\\Downloads\\JsonProcessing-master\\src\\main\\resources\\files\\categories.json";
    private final static String PRODUCTS_FILE_PATH = "C:\\Users\\HP\\Downloads\\JsonProcessing-master\\src\\main\\resources\\files\\products.json";

    private final UserService userService;
    private final CategoryService categoryService;
    private final ProductService productService;
    private final FileIOUtil fileIOUtil;
    private final Gson gson;

    @Autowired
    public ProductShopController(UserService userService, CategoryService categoryService, ProductService productService, FileIOUtil fileIOUtil, Gson gson) {
        this.userService = userService;
        this.categoryService = categoryService;
        this.productService = productService;
        this.fileIOUtil = fileIOUtil;
        this.gson = gson;
    }

    @Override
    public void run(String... args) throws Exception {
//        this.seedUsers();
//        this.seedCategories();
//        this.seedProducts();

        this.productsInRange();
    }

    private void seedUsers() throws IOException {
        String usersFileContent = this.fileIOUtil.readFile(USER_FILE_PATH);

        UserSeedDto[] userSeedDtos = this.gson.fromJson(usersFileContent, UserSeedDto[].class);

        this.userService.seedUsers(userSeedDtos);
    }

    private void seedCategories() throws IOException {
        String categoriesFileContent = this.fileIOUtil.readFile(CATEGORIES_FILE_PATH);

        CategorySeedDto[] categorySeedDtos = this.gson.fromJson(categoriesFileContent, CategorySeedDto[].class);

        this.categoryService.seedCategories(categorySeedDtos);
    }

    private void seedProducts() throws IOException {
        String productsFileContent = this.fileIOUtil.readFile(PRODUCTS_FILE_PATH);

        ProductSeedDto[] productSeedDtos = this.gson.fromJson(productsFileContent, ProductSeedDto[].class);

        this.productService.seedProducts(productSeedDtos);
    }

    private void productsInRange() throws IOException {
        List<ProductInRangeDto> productInRangeDtos = this.productService.productsInRange(BigDecimal.valueOf(500), BigDecimal.valueOf(1000));

        String productsInRangeJson = this.gson.toJson(productInRangeDtos);

        File file = new File("C:\\Users\\HP\\Downloads\\JsonProcessing-master\\src\\main\\resources\\files\\output\\products-in-range.json");

        FileWriter writer = new FileWriter(file);
        writer.write(productsInRangeJson);
        writer.close();
    }

    private void successfullySoldProducts(){

    };
}
