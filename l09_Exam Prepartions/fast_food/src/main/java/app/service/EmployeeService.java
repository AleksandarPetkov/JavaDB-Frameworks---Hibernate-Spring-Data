package app.service;

import java.io.IOException;

public interface EmployeeService {

    String readEmployees() throws IOException;

    String seedEmployee() throws IOException;
}
