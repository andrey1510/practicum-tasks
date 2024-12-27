package lessons.sprint1.topic7.lesson4;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertTrue;


class ResourceManagementTest {

    private static DatabaseConnection database;

    @BeforeAll
    static void setUpAll() {
        database = new DatabaseConnection();
        // Установите схему базы данных
    }

    @BeforeEach
    void setUp() {
        // Откройте соединение с базой данных перед всеми тестами
        // Подготовьте тестовые данные перед каждым тестом
    }

    @Test
    void testInsertData() {
        database.insertData("Test data");
        assertTrue(database.hasData("Test data"));
    }

    @AfterEach
    void tearDown() {
        // Откатите транзакции после каждого теста
        // Закройте соединение с базой после всех тестов
    }

    @AfterAll
    static void tearDownAll() {
        // Очистите схему базы данных
    }
}

class DatabaseConnection {
    void addSchema() {
        System.out.println("Схема установлена");
    }

    void connect() {
        System.out.println("Соединение с базой данных установлено");
    }

    void disconnect() {
        System.out.println("Соединение с базой данных закрыто");
    }

    void rollbackTransaction() {
        System.out.println("Транзакции откатаны");
    }

    void prepareTestData() {
        System.out.println("Подготовка тестовых данных");
    }

    boolean hasData(String string) {
        return Objects.equals(string, "Test data");
    }

    void insertData(String string) {
        System.out.println("Данные: " + string + "были добавлены в базу");
    }

    void clearSchema(){
        System.out.println("Cхема очищена");
    }
}