package com.revature.repo;

import com.revature.models.MostPopulatedCategories;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MostPopulatedCategoriesDAO extends JpaRepository<MostPopulatedCategories, Integer> {

}