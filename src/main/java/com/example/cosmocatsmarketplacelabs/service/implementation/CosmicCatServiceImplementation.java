package com.example.cosmocatsmarketplacelabs.service.implementation;

import com.example.cosmocatsmarketplacelabs.domain.CosmicCatDetails;
import com.example.cosmocatsmarketplacelabs.repository.CosmicCatRepository;
import com.example.cosmocatsmarketplacelabs.repository.entity.CosmicCatEntity;
import com.example.cosmocatsmarketplacelabs.repository.mapper.CosmicCatRepositoryMapper;
import com.example.cosmocatsmarketplacelabs.service.CosmicCatService;
import com.example.cosmocatsmarketplacelabs.service.exception.CosmicCatNofFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class CosmicCatServiceImplementation implements CosmicCatService {
    private final CosmicCatRepository cosmicCatRepository;
    private final CosmicCatRepositoryMapper cosmicCatRepositoryMapper;

    public CosmicCatServiceImplementation(CosmicCatRepository cosmicCatRepository, CosmicCatRepositoryMapper cosmicCatRepositoryMapper) {
        this.cosmicCatRepository = cosmicCatRepository;
        this.cosmicCatRepositoryMapper = cosmicCatRepositoryMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CosmicCatDetails> getAllCosmicCats() {
        return cosmicCatRepositoryMapper.toCosmicCatDetails(cosmicCatRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public CosmicCatDetails getCosmicCatByCosmicCatId(UUID cosmicCatId) {
        return cosmicCatRepositoryMapper.toCosmicCatDetails(cosmicCatRepository.findByNaturalId(cosmicCatId)
                .orElseThrow(() -> new CosmicCatNofFoundException(cosmicCatId)));
    }

    @Override
    @Transactional(propagation = Propagation.NESTED)
    public CosmicCatDetails saveCosmicCat(CosmicCatDetails cosmoCatDetails) {
        return cosmicCatRepositoryMapper.toCosmicCatDetails(cosmicCatRepository
                .save(cosmicCatRepositoryMapper.toCosmicCatEntity(cosmoCatDetails)));
    }

    @Override
    @Transactional(propagation = Propagation.NESTED)
    public CosmicCatDetails saveCosmicCat(UUID cosmicCatId, CosmicCatDetails cosmoCatDetails) {
        CosmicCatEntity oldCosmicCat = cosmicCatRepository.findByNaturalId(cosmicCatId)
                .orElseThrow(() -> new CosmicCatNofFoundException(cosmicCatId));
        oldCosmicCat.setCatName(cosmoCatDetails.getCatName());
        oldCosmicCat.setCatMail(cosmoCatDetails.getCatMail());
        oldCosmicCat.setRealName(cosmoCatDetails.getRealName());
        return cosmicCatRepositoryMapper.toCosmicCatDetails(cosmicCatRepository.save(oldCosmicCat));
    }

    @Override
    @Transactional
    public void deleteCosmicCat(UUID cosmicCatId) {
        cosmicCatRepository.deleteByNaturalId(cosmicCatId);
    }


}
