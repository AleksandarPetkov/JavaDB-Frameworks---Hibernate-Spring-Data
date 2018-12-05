package app.services.impl;

import app.dtos.bindings.PartDto;
import app.entities.Part;
import app.entities.Supplier;
import app.repositories.PartRepository;
import app.repositories.SupplierRepository;
import app.services.api.PartService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Random;

@Service
@Transactional
public class PartServiceImpl implements PartService {

    private PartRepository partRepository;
    private SupplierRepository supplierRepository;
    private ModelMapper mapper;

    @Autowired
    public PartServiceImpl(PartRepository partRepository, SupplierRepository supplierRepository) {
        this.partRepository = partRepository;
        this.supplierRepository = supplierRepository;
        this.mapper = new ModelMapper();
    }

    @Override
    public void save(Part part) {
        this.partRepository.save(part);
    }

    @Override
    public void saveAll(List<PartDto> parts) {
        Random random = new Random();
        List<Supplier> allSuppliers = this.supplierRepository.findAll();
        for (PartDto partDto : parts) {
            Part part = this.mapper.map(partDto, Part.class);
            int randomIndex = random.nextInt(allSuppliers.size());
            part.setSupplier(allSuppliers.get(randomIndex));
            this.save(part);
        }
    }
}
