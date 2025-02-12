package lessons.sprint1.topic9.lesson6;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UrlValidationTest {

    @TestFactory // Добавьте аннотацию для запуска динамического теста
    Stream<DynamicTest> testUrlsFromFiles() throws Exception {
        Path urlDirectory = Paths.get("src/test/resources/urls");

        return Files.list(urlDirectory)
            .filter(Files::isRegularFile)
            .map(path -> DynamicTest.dynamicTest("Проверка URL из файла: " + path.getFileName(),
                () -> {
                    String url = Files.readString(path).trim();
                    assertTrue(url.startsWith("http"), "URL должен начинаться с 'http': " + url);
                }));
    }
}