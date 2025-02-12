package lessons.sprint1.topic9.lesson5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class User {
    private final String name;
    private final int age;

    User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public boolean isValidAge() {
        return age >= 18 && age <= 60;
    }

    public boolean hasValidName() {
        return name != null && !name.trim().isEmpty();
    }
}

public class UserProfileTest {

    // Параметризованный тест, который получает тестовые данные из метода provideUsers
    @ParameterizedTest
    @MethodSource("provideUsers")
    void testUserProfile(User user) {
        Assertions.assertTrue(user.hasValidName(), "Имя не должно быть пустым или иметь значение null");
        Assertions.assertTrue(user.isValidAge(), "Возраст должен быть от 18 до 60");
    }

    private static Stream<User> provideUsers() {
        // Верните тестовые данные: несколько пользователей с корректным именем и возрастом
        return Stream.of(
            new User("Ivan", 18),
            new User("Pavel", 34),
            new User("Victor", 42),
            new User("Kirill", 21)
        );
    }
}
