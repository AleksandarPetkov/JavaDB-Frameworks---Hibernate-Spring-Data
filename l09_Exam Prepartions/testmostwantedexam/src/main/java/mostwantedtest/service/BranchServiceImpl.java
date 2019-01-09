package mostwantedtest.service;

import com.google.gson.Gson;
import mostwantedtest.domain.dtos.json.SeedBranchesDTO;
import mostwantedtest.domain.entities.Branch;
import mostwantedtest.repository.BranchRepository;
import mostwantedtest.util.FileUtil;
import mostwantedtest.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class BranchServiceImpl implements BranchService {
    private static final String BRANCH_GSON_FILE_PATH = "D:\\SoftUni\\JavaDB-Frameworks---Hibernate-Spring-Data\\l09_Exam Prepartions\\testmostwantedexam\\src\\main\\resources\\files\\json\\branches.json";

    private final BranchRepository branchRepository;
    private final FileUtil fIleUtil;
    private final ValidationUtil validatorUtil;
    private final ModelMapper modelMapper;
    private final Gson gson;

    @Autowired
    public BranchServiceImpl(BranchRepository branchRepository, FileUtil fIleUtil, ValidationUtil validatorUtil, ModelMapper modelMapper, Gson gson) {
        this.branchRepository = branchRepository;
        this.fIleUtil = fIleUtil;
        this.validatorUtil = validatorUtil;
        this.modelMapper = modelMapper;
        this.gson = gson;
    }


    @Override
    public String readJsonFile() throws IOException {
        return this.fIleUtil.readFile(BRANCH_GSON_FILE_PATH);
    }

    @Override
    public String seedBranches() throws IOException {
        StringBuilder sb = new StringBuilder();
        String readBranchContent = readJsonFile();

        SeedBranchesDTO[] seedBranches = this.gson.fromJson(readBranchContent, SeedBranchesDTO[].class);
        for (SeedBranchesDTO seedBranch : seedBranches) {
            if (!this.validatorUtil.isValid(seedBranch)){
                sb.append("Error: Incorrect Data!").append(System.lineSeparator());
                continue;
            }

            Branch branch = this.modelMapper.map(seedBranch, Branch.class);

            this.branchRepository.saveAndFlush(branch);
            sb.append(String.format("Successfully imported Branch - %s.",seedBranch.getName()))
                    .append(System.lineSeparator());
        }
        return sb.toString();
    }
}
