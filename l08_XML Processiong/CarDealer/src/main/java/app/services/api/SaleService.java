package app.services.api;

import app.dtos.views.SaleView;
import app.entities.Sale;

import java.util.List;

public interface SaleService {
    void save(Sale sale);

    void insertRandomData();

    List<SaleView> getAllSaleDetails();
}
