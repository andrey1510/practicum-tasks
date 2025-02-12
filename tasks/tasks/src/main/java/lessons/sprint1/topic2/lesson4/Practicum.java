package lessons.sprint1.topic2.lesson4;

import java.util.function.Predicate;

class Practicum {

    enum Position {
        DIRECTOR, MANAGER, DEVELOPER
    }

    static class Employee {
        private final Position position;
        private final String name;
        private final int experienceYears;
        private final String department;

        public Employee(Position position, String name,
                        int experienceYears, String department) {
            this.position = position;
            this.name = name;
            this.experienceYears = experienceYears;
            this.department = department;
        }

        public Position getPosition() {
            return position;
        }

        public String getName() {
            return name;
        }

        public int getExperienceYears() {
            return experienceYears;
        }

        public String getDepartment() {
            return department;
        }
    }

    public static Predicate<Employee> createNewDeveloperPredicate(String department) {
        return ((Predicate<Employee>) empl1 -> empl1.getDepartment().equals(department))
            .and(empl -> empl.getPosition() == Position.DEVELOPER)
            .and(empl -> empl.getExperienceYears() < 1);  // ваш код тут
    }

    public static void main(String[] args) {
        // Создайте несколько сотрудников с разными атрибутами
        // Проверьте корректность предиката
        Employee empl1 = new Employee(Position.DEVELOPER, "John", 0, "dep2");
        Employee empl2 = new Employee(Position.MANAGER, "Jack", 0, "dep1");
        Employee empl3 = new Employee(Position.DEVELOPER, "Jake", 3, "dep1");
        Employee empl4 = new Employee(Position.DEVELOPER, "Sam", 0, "dep1");
        Employee empl5 = new Employee(Position.DEVELOPER, "Jeff", 4, "dep6");

        Predicate<Employee> predicate = createNewDeveloperPredicate("dep1");
        System.out.println(predicate.test(empl1));
        System.out.println(predicate.test(empl2));
        System.out.println(predicate.test(empl3));
        System.out.println(predicate.test(empl4));
        System.out.println(predicate.test(empl5));
    }

}
