package com.revature.service;

import com.revature.models.MostPopulatedCategories;
import com.revature.repo.MostPopulatedCategoriesDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MostPopulatedCategoriesService {

    private MostPopulatedCategoriesDAO mostPopulatedCategoriesDAO;
    private Logger logger = LoggerFactory.getLogger("Category Service Logger");

    @Autowired
    public MostPopulatedCategoriesService(MostPopulatedCategoriesDAO mostPopulatedCategoriesDAO) {
        this.mostPopulatedCategoriesDAO = mostPopulatedCategoriesDAO;
    }

    public List<MostPopulatedCategories> getAllCategories() {
        List<MostPopulatedCategories> catList = mostPopulatedCategoriesDAO.findAll();
        if (catList == null) {
            logger.error("The list of categories could not be retrieved");
            return null;
        }else if (catList.isEmpty()){
            logger.info("The retrieved list of categories was empty");
        }else{
            logger.info("The category list was successfully retrieved");
        }
        return catList;
    }

    public MostPopulatedCategories getCategory(int id){
        if (mostPopulatedCategoriesDAO.existsById(id)) {
            Optional<MostPopulatedCategories> mpc = mostPopulatedCategoriesDAO.findById(id);
            if (mpc.isPresent()) {
                logger.info("The desired category was successfully found and returned");
                return mpc.get();
            }else{
                logger.error("The category exists, but there was an issue retrieving it");
            }
        }

        logger.error("There is no category associated with the given ID");
        return null;
    }
/*
    public int saveCategory(MostPopulatedCategories mpc){
        try {
            mostPopulatedCategoriesDAO.save(mpc);
        }catch(Exception e){
            e.printStackTrace();
            return 1;
        }
        return 0;
    }
*/
    public int saveCategories(ArrayList<MostPopulatedCategories> mpcs){
        int returnValue = 1;
        //allows us to save multiple categories at a time
        if (mpcs != null && !mpcs.isEmpty()) {
            try {
                for (MostPopulatedCategories mpc : mpcs){
                    mostPopulatedCategoriesDAO.save(mpc);
                }
                returnValue = 0;
                logger.info("The categories were successfully saved");
            } catch (Exception e) {
                logger.error("The categories could not be saved");
                e.printStackTrace();
            }
        }else{
            if (mpcs == null) {
                logger.error("A list of categories was not provided");
            }else{
                logger.error("The provided list was empty");
            }
        }

        return returnValue;
    }

    public boolean deleteCategory(int id){
        boolean wasDeleted = false;
        MostPopulatedCategories mpc = getCategory(id);
        if (mpc != null) {
            try {
                mostPopulatedCategoriesDAO.delete(mpc);
                logger.info("The category was successfully deleted");
                wasDeleted = true;
            } catch (Exception e) {
                logger.error("The category could not be deleted");
                e.printStackTrace();
                return false;
            }
        }else{
            logger.error("There is no category to delete");
        }

        logger.info("The category was successfully deleted");
        return wasDeleted;
    }
}
