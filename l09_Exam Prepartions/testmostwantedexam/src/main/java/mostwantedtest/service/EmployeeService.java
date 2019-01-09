package mostwantedtest.service;

import java.io.IOException;

public interface EmployeeService {
    String readJsonFile() throws IOException;
    String seedEmployee() throws IOException;
}
