package com.revature.service;

import com.revature.models.MostPopulatedCategories;
import com.revature.repo.MostPopulatedCategoriesDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MostPopulatedCategoriesService {

    private MostPopulatedCategoriesDAO mostPopulatedCategoriesDAO;

    @Autowired
    public MostPopulatedCategoriesService(MostPopulatedCategoriesDAO mostPopulatedCategoriesDAO) {
        this.mostPopulatedCategoriesDAO = mostPopulatedCategoriesDAO;
    }

    public List<MostPopulatedCategories> getAllCategories() {
        return mostPopulatedCategoriesDAO.findAll();
    }

    public MostPopulatedCategories getCategory(int id){
        Optional<MostPopulatedCategories> mpc = mostPopulatedCategoriesDAO.findById(id);
        if(mpc.isPresent()){
            return mpc.get();
        }
        return new MostPopulatedCategories();
    }

    public int saveCategory(MostPopulatedCategories mpc){
        try {
            mostPopulatedCategoriesDAO.save(mpc);
        }catch(Exception e){
            e.printStackTrace();
            return 1;
        }
        return 0;
    }

    public int saveCategories(ArrayList<MostPopulatedCategories> mpcs){
        //allows us to save multiple categories at a time
        try {
            for (MostPopulatedCategories mpc : mpcs) mostPopulatedCategoriesDAO.save(mpc);
        }catch(Exception e){
            e.printStackTrace();
            return 1;
        }
        return 0;
    }

    public boolean deleteCategory(int id){
        try {
            MostPopulatedCategories mpc = getCategory(id);
            mostPopulatedCategoriesDAO.delete(mpc);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
