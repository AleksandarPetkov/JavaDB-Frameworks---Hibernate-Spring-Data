package app.services.api;

import app.dtos.bindings.SupplierDto;
import app.dtos.views.SupplierView;
import app.entities.Supplier;

import java.util.List;

public interface SupplierService {
    void save(Supplier supplier);

    void saveAll(List<SupplierDto> suppliers);

    List<SupplierView> getAllLocalSuppliers();
}
