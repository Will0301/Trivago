package com.trivago.locator.repository;

import com.trivago.locator.model.LocatorEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LocatorRepository extends MongoRepository<LocatorEntity, String> {

    Optional<LocatorEntity> findAllByCpf(String cpf);

    LocatorEntity findByCpf(String cpf);
    void deleteByCpf(String cpf);
}
