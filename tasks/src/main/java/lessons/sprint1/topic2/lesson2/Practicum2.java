package lessons.sprint1.topic2.lesson2;

import java.util.Comparator;

class Practicum2 {

    static class Person {
        private String name;
        private int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }

        @Override
        public String toString() {
            return name + ", age = " + age;
        }
    }

    public static void main(String[] args) {
        Comparator<Person> ageComparator = (p1, p2) -> p1.getAge() - p2.getAge(); // ваш код тут

        Person p1 = new Person("Иван", 20);
        Person p2 = new Person("Василий", 35);
        System.out.println(ageComparator.compare(p1, p2)); // < 0
        System.out.println(ageComparator.compare(p2, p1)); // > 0
        System.out.println(ageComparator.compare(p1, p2)); // == 0
    }
}
