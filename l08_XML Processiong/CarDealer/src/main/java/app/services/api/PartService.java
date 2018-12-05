package app.services.api;

import app.dtos.bindings.PartDto;
import app.entities.Part;

import java.util.List;

public interface PartService {
    void save(Part part);

    void saveAll(List<PartDto> parts);
}
