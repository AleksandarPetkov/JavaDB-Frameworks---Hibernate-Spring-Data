package mostwantedtest.service;

import java.io.IOException;

public interface ClientService {
    String readJsonFile() throws IOException;
    String seedClients() throws IOException;
}
