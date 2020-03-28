package hiberspring.service.impl;

import com.google.gson.Gson;
import hiberspring.common.GlobalConstants;
import hiberspring.domain.dtos.BranchSeedDto;
import hiberspring.domain.entities.Branch;
import hiberspring.domain.entities.Town;
import hiberspring.repository.BranchRepository;
import hiberspring.service.BranchService;
import hiberspring.service.TownService;
import hiberspring.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

@Service
public class BranchServiceImpl implements BranchService {


    private final BranchRepository branchRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final Gson gson;
    private final TownService townService;

    @Autowired
    public BranchServiceImpl(BranchRepository branchRepository, ModelMapper modelMapper, ValidationUtil validationUtil, Gson gson, TownService townService) {
        this.branchRepository = branchRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.gson = gson;
        this.townService = townService;
    }

    @Override
    public Boolean branchesAreImported() {
        return this.branchRepository.count() > 0;
    }

    @Override
    public String readBranchesJsonFile() throws IOException {
        return Files.readString(Path.of(GlobalConstants.BRANCHES_FILE_PATH));
    }

    @Override
    public String importBranches(String branchesFileContent) throws FileNotFoundException {

        StringBuilder sb = new StringBuilder();

        BranchSeedDto[] dtos = this.gson
                .fromJson(new FileReader(GlobalConstants.BRANCHES_FILE_PATH), BranchSeedDto[].class);

        Arrays.stream(dtos)
                .forEach(branchSeedDto -> {

                    if(this.validationUtil.isValid(branchSeedDto)){

                        if(this.branchRepository.findByName(branchSeedDto.getName())== null){

                            Branch branch = this.modelMapper
                                    .map(branchSeedDto, Branch.class);
                            Town town = this.townService.getTownByName(branchSeedDto.getTown());
                            branch.setTown(town);
                            sb.append(String.format(GlobalConstants.SUCCESSFUL_IMPORT_MESSAGE, branch.getClass().getSimpleName(),branch.getName()));
                            this.branchRepository.saveAndFlush(branch);
                        }else{
                            sb.append(GlobalConstants.IN_DB_MESSAGE);
                        }
                    }else{
                        sb.append(GlobalConstants.INCORRECT_DATA_MESSAGE);
                    }
                    sb.append(System.lineSeparator());
                });

        return sb.toString();
    }

    @Override
    public Branch getBranchByName(String name) {
        return this.branchRepository.findByName(name);
    }
}
