package app.services.impl;

import app.dtos.bindings.SupplierDto;
import app.dtos.views.SupplierView;
import app.entities.Supplier;
import app.repositories.SupplierRepository;
import app.services.api.SupplierService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class SupplierServiceImpl implements SupplierService {

    private SupplierRepository supplierRepository;
    private ModelMapper mapper;

    @Autowired
    public SupplierServiceImpl(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
        this.mapper = new ModelMapper();
    }

    @Override
    public void save(Supplier supplier) {
        this.supplierRepository.save(supplier);
    }

    @Override
    public void saveAll(List<SupplierDto> suppliers) {
        for (SupplierDto supplierDto : suppliers) {
            Supplier supplier = this.mapper.map(supplierDto, Supplier.class);
            this.save(supplier);
        }
    }

    @Override
    public List<SupplierView> getAllLocalSuppliers() {
        List<Supplier> suppliers = this.supplierRepository.getAllLocalSuppliers();
        List<SupplierView> supplierViews = new ArrayList<>();
        for (Supplier supplier : suppliers) {
            SupplierView supplierView = this.mapper.map(supplier, SupplierView.class);
            supplierView.setPartsCount(supplier.getParts().size());
            supplierViews.add(supplierView);
        }
        return supplierViews;
    }
}
