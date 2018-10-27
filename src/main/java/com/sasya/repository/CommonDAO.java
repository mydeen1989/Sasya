package com.sasya.repository;

import com.sasya.model.Category;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * UserDAO
 */
@Repository
@Transactional
public interface CommonDAO {

    public List<Category> getAllCategory();

}
