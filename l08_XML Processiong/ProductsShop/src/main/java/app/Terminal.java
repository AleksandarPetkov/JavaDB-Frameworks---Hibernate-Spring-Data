package app;

import app.dto.binding.CategoryWrapperDto;
import app.dto.binding.ProductWrapperDto;
import app.dto.binding.UserWrapperDto;
import app.dto.view.*;
import app.services.api.CategoryService;
import app.services.api.ProductService;
import app.services.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.math.BigDecimal;
import java.util.List;

@Component
public class Terminal implements CommandLineRunner {

    private UserService userService;
    private ProductService productService;
    private CategoryService categoryService;

    @Autowired
    public Terminal(UserService userService, ProductService productService, CategoryService categoryService) {
        this.userService = userService;
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @Override
    public void run(String... args) throws Exception {
        //this.seedDatabase();

        //Problem 1
        //this.getProductsInPriceRangeWithNoBuyer();

        //Problem 2
        //this.getSuccessfullySoldProducts();

        //Problem 3
        //this.getCategoriesByProductsCount();

        //Problem 4
        //this.getUsersWithSoldProducts();

    }

    private void seedDatabase() throws IOException, JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(UserWrapperDto.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        InputStream inputStream = new FileInputStream("./src/main/resources/files/users.xml");
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        UserWrapperDto users = (UserWrapperDto) unmarshaller.unmarshal(reader);
        this.userService.saveAll(users.getUsers());

        jaxbContext = JAXBContext.newInstance(CategoryWrapperDto.class);
        unmarshaller = jaxbContext.createUnmarshaller();
        inputStream = new FileInputStream("./src/main/resources/files/categories.xml");
        reader = new BufferedReader(new InputStreamReader(inputStream));
        CategoryWrapperDto categories = (CategoryWrapperDto) unmarshaller.unmarshal(reader);
        this.categoryService.saveAll(categories.getCategories());

        jaxbContext = JAXBContext.newInstance(ProductWrapperDto.class);
        unmarshaller = jaxbContext.createUnmarshaller();
        inputStream = new FileInputStream("./src/main/resources/files/products.xml");
        reader = new BufferedReader(new InputStreamReader(inputStream));
        ProductWrapperDto products = (ProductWrapperDto) unmarshaller.unmarshal(reader);
        this.productService.saveAll(products.getProducts());
    }

    public void getProductsInPriceRangeWithNoBuyer() throws JAXBException {
        List<ProductView> products = this.productService.findAllInPriceRange(new BigDecimal(500), new BigDecimal(1000));
        ProductWrapperView productView = new ProductWrapperView();
        productView.setProducts(products);
        JAXBContext jaxbContext = JAXBContext.newInstance(ProductWrapperView.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(productView, new File("./src/main/resources/output/products.xml"));
    }

    public void getSuccessfullySoldProducts() throws JAXBException {
        List<UserView> users = this.userService.findAllUsersWithSoldProducts();
        UserWrapperView userView = new UserWrapperView();
        userView.setUsers(users);
        JAXBContext jaxbContext = JAXBContext.newInstance(UserWrapperView.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(userView, new File("./src/main/resources/output/users-sold-products.xml"));
    }

    private void getCategoriesByProductsCount() throws JAXBException {
        List<CategoryView> categories = this.categoryService.getCategoriesByProductsCount();
        CategoryWrapperView categoryView = new CategoryWrapperView();
        categoryView.setCategories(categories);
        JAXBContext jaxbContext = JAXBContext.newInstance(CategoryWrapperView.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(categoryView, new File("./src/main/resources/output/categories-products.xml"));
    }

    public void getUsersWithSoldProducts() throws IOException, JAXBException {
        UsersProductsWrapperView usersProductsView = this.userService.getUsersWithSoldProducts();
        JAXBContext jaxbContext = JAXBContext.newInstance(UsersProductsWrapperView.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(usersProductsView, new File("./src/main/resources/output/users-products.xml"));
    }
}
