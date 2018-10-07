package com.sasya.repositories;

import com.sasya.models.Register;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegisterDAO extends CrudRepository<Register, Integer> {

}
