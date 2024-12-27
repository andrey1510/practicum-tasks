package lessons.sprint1.topic2.lesson4;

import java.io.IOException;
import java.io.OutputStream;

class Practicum2 {

    interface Printer {
        void print(String input) throws IOException;

        static Printer outputStreamPrinter(OutputStream os) {
            return (input) -> os.write(input.getBytes());
        }
    }

    public static void main(String[] args) throws IOException {
        Printer outputStreamPrinter = Printer.outputStreamPrinter(System.out);
        outputStreamPrinter.print("Hello, world!"); // Hello, world!
    }
}
