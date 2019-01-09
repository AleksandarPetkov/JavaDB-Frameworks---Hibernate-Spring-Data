package mostwantedtest.service;

import java.io.IOException;

public interface BranchService {
    String readJsonFile() throws IOException;
    String seedBranches() throws IOException;
}
