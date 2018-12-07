package app.service;

import java.io.IOException;

public interface ItemService {

    String readEmployees() throws IOException;

    String seedItems() throws IOException;
}
