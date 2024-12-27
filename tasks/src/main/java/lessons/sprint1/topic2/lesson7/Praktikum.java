package lessons.sprint1.topic2.lesson7;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

class Praktikum {
    static class Book {
        private static Long idCounter = 0L;

        private Long id;
        private String name;
        private String author;

        public Book(String name, String author) {
            this.id = idCounter++;
            this.name = name;
            this.author = author;
        }

        @Override
        public String toString() {
            return "Book [" + id + "] (" + name + ", " + author + ")";
        }
    }

    public static void main(String[] args) throws Throwable {
        // с помощью MethodHandles создайте объект класса Book c произвольными аргументами
        // и выведите его в консоль

        // ваш код здесь
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        MethodType constructorSignature  = MethodType.methodType(void.class, String.class, String.class);
        MethodHandle bookConstructor  = lookup.findConstructor(Book.class, constructorSignature );

        Book book = (Book) bookConstructor.invokeExact("книга", "автор");

        System.out.println(book);
    }
}