package app.services.impl;

import app.dto.binding.ProductDto;
import app.dto.view.ProductView;
import app.entities.Category;
import app.entities.Product;
import app.entities.User;
import app.repositories.CategoryRepository;
import app.repositories.ProductRepository;
import app.repositories.UserRepository;
import app.services.api.ProductService;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;
    private UserRepository userRepository;
    private CategoryRepository categoryRepository;
    private ModelMapper mapper;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, UserRepository userRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.mapper = new ModelMapper();
    }

    @Override
    public void save(ProductDto productDto) {
        Product product = this.mapper.map(productDto, Product.class);
        this.productRepository.saveAndFlush(product);
    }

    @Override
    public void saveAll(List<ProductDto> products) {
        Random random = new Random();
        List<User> allUsers = this.userRepository.findAll();
        List<Category> allCategories = this.categoryRepository.findAll();
        for (ProductDto productDto : products) {
            int sellerIndex = random.nextInt(allUsers.size());
            productDto.setSeller(allUsers.get(sellerIndex));
            int isSoldChance = random.nextInt(4);
            if (isSoldChance > 0) {
                int buyerIndex = random.nextInt(allUsers.size());
                productDto.setBuyer(allUsers.get(buyerIndex));
            }
            int categoryIndex = random.nextInt(allCategories.size());
            productDto.getCategories().add(allCategories.get(categoryIndex));
            for (int i = 0; i < 3; i++) {
                categoryIndex = random.nextInt(allCategories.size());
                if (categoryIndex % 2 == 0) {
                    productDto.getCategories().add(allCategories.get(categoryIndex));
                }
            }
            Product product = this.mapper.map(productDto, Product.class);
            for (Category cat : productDto.getCategories()) {
                cat.getProducts().add(product);
            }
            this.productRepository.saveAndFlush(product);
        }
    }

    @Override
    public List<ProductView> findAllInPriceRange(BigDecimal from, BigDecimal to) {
        List<Product> products = this.productRepository.findByPriceBetweenAndBuyerIdIsNullOrderByPrice(from, to);
        List<ProductView> productViews = new ArrayList<>();
        for (Product product : products) {
            ProductView productView = new ProductView();
            productView.setName(product.getName());
            productView.setPrice(product.getPrice());
            productView.setSellerName(product.getSeller().getLastName());
            productViews.add(productView);
        }
        return productViews;
    }
}
