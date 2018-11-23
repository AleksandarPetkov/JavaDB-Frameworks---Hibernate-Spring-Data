package productshop.service;

import productshop.domain.dtos.ProductInRangeDto;
import productshop.domain.dtos.ProductSeedDto;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {

    void seedProducts(ProductSeedDto[] productSeedDtos);

    List<ProductInRangeDto> productsInRange(BigDecimal more, BigDecimal less);
}
