package lessons.sprint1.topic8.lesson2;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void testGetUserName() {
        // Настройте поведение mock-объекта userRepository для метода findUserNameById
        // При вызове метода findUserNameById(1) должно возвращаться значение "Иван Васильевич"
        when(userRepository.findUserNameById(1)).thenReturn("Иван Васильевич");

        String result = userService.getUserNameById(1);

        assertEquals("Иван Васильевич", result);

        // Убедитесь, что метод findUserNameById(1) был вызван ровно 1 раз
        verify(userRepository, times(1)).findUserNameById(1); 
    }
}
