package lessons.sprint1.topic3.lesson7;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public class StudentRecord {

    public record StudentResult(String name, double averageScore) {}

    public static void printBestResult(Collection<StudentResult> studentResults) {
        studentResults.stream()
            .max(Comparator.comparingDouble(StudentResult::averageScore))
            .ifPresent(bestStudent -> System.out.printf("Студент с наивысшим средним баллом: %s, %.2f%n",
                bestStudent.name(), bestStudent.averageScore()));

    }

    public static void main(String[] args) {
        Collection<StudentResult> students = List.of(
            new StudentResult("Иван Иванов", 16.1),
            new StudentResult("Кирилл Васильев", 12.2),
            new StudentResult("Алексей Петров", 13.3)
        );
        printBestResult(students);
    }

}
