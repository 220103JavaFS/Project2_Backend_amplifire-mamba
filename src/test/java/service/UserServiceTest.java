package service;

import com.revature.models.User;
import com.revature.models.UserDTO;
import com.revature.repo.MostPopulatedCategoriesDAO;
import com.revature.repo.UserDAO;
import com.revature.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest {
    private UserService testInstance;

    @Mock
    private UserDAO mockedDAO;
    private User testUser = new User();

    @BeforeEach
    public void setUp() throws NoSuchAlgorithmException {
        //Creating the testuser
        testUser.setUserId(4);
        testUser.setUsername("SuperRandomMan");
        testUser.setPassword("ItThatShallNotBeGuessed");
        testUser.setFirstName("random");
        testUser.setLastName("man");
        testUser.setEmail("rando@anywhere.com");

        //Tells Mockito to scan for any Mock objects
        MockitoAnnotations.openMocks(this);

        //Create login service with the mock userDao
        testInstance = new UserService(mockedDAO);

        List<User> lst = new ArrayList<User>() {{
            add(testUser);
        } };

        Optional<User> temp = Optional.ofNullable(testUser);

        //when userDao object calls selectUserByUsername during the login function, return the test user created above.
        Mockito.when(mockedDAO.findAll()).thenReturn(lst);
        Mockito.when(mockedDAO.findById(4)).thenReturn(Optional.ofNullable(testUser));
        Mockito.when(mockedDAO.save(testUser)).thenReturn(null);
        //Mockito.when(mockedDAO.delete(testUser)).thenReturn(null);
        Mockito.when(mockedDAO.findByUsername("SuperRandomMan")).thenReturn(temp);
    }

    //=======================================================================================
    //public List<User> getAllUsers() {return userDAO.findAll();}

    @Test
    @Order(1)
    public void getAllUsersTest() throws NoSuchAlgorithmException {
        List<User> lst = new ArrayList<User>() {{
            add(testUser);
        } };
        assertEquals(lst, testInstance.getAllUsers());
    }

    //=======================================================================================

    @Test
    @Order(2)
    public void getUserTestValid() throws NoSuchAlgorithmException{
        assertEquals(testUser, testInstance.getUser(4));
    }

    @Test
    @Order(3)
    public void getUserTestInvalid() throws NoSuchAlgorithmException{
        User temp = new User();
        assertEquals(temp, testInstance.getUser(7));
    }

    //=======================================================================================

    @Test
    @Order(4)
    public void saveUserTest() throws NoSuchAlgorithmException{
        assertEquals(0, testInstance.saveUser(testUser));
    }

    //=======================================================================================

//    @Test
//    @Order(5)
//    public void deleteUserTest() throws NoSuchAlgorithmException{
//        assert?(testInstance.deleteUser(4));
//    }

    //=======================================================================================

    @Test
    @Order(6)
    public void getUserByUsernameTestValid() throws NoSuchAlgorithmException{
        UserDTO temp = new UserDTO();
        temp.setUsername("SuperRandomMan");
        temp.setPassword("ItThatShallNotBeGuessed");
        assertEquals(testUser, testInstance.getUserByUsername(temp));
    }
}
