package service;

import com.revature.models.MostPopulatedCategories;
import com.revature.repo.MostPopulatedCategoriesDAO;
import com.revature.service.MostPopulatedCategoriesService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MostPopulatedCategoriesServiceTest {

    @Mock
    private MostPopulatedCategoriesDAO mockedDAO;
    private MostPopulatedCategoriesService testInstance;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        testInstance = new MostPopulatedCategoriesService(mockedDAO);
        Mockito.when(mockedDAO.findAll()).thenReturn(new ArrayList<>());
        Mockito.when(mockedDAO.existsById(306)).thenReturn(true);
        Mockito.when(mockedDAO.findById(306)).thenReturn(java.util.Optional.of(new MostPopulatedCategories()));
        Mockito.when(mockedDAO.existsById(-1)).thenReturn(false);
        Mockito.when(mockedDAO.findById(-1)).thenReturn(null);
    }

    @Test
    void getAllCategories() {
        List<MostPopulatedCategories> testList = testInstance.getAllCategories();
        assertEquals(0, testList.size());
    }

    @Test
    void getCategory() {
        MostPopulatedCategories mpc = testInstance.getCategory(306);
        MostPopulatedCategories mpc2 = testInstance.getCategory(-1);

        assert (mpc != null && mpc2 == null);
    }
/*
    @Test
    void saveCategory() {
    }
*/
    @Test
    void saveCategories() {
        ArrayList<MostPopulatedCategories> testList = new ArrayList<>();
        int successValue1 = testInstance.saveCategories(testList);
        testList.add(new MostPopulatedCategories(0, "haha", 0));
        int successValue2 = testInstance.saveCategories(testList);

        assertEquals(1, successValue1);
        assertEquals(0, successValue2);
    }

    @Test
    void deleteCategory() {
        boolean wasDeleted1 = testInstance.deleteCategory(306);
        boolean wasDeleted2 = testInstance.deleteCategory(-1);

        assertTrue(wasDeleted1);
        assertFalse(wasDeleted2);
    }
}