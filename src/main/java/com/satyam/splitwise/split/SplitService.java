package com.satyam.splitwise.split;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SplitService {

    private SplitRepository splitRepository;


    public SplitService(SplitRepository splitRepository) {
        this.splitRepository = splitRepository;
    }

    public List<SplitModel> saveAllSplits(List<SplitModel> splits){
        return splitRepository.saveAll(splits);
    }

}
