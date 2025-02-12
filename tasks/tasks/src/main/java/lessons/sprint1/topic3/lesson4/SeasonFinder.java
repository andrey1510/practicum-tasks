package lessons.sprint1.topic3.lesson4;

public class SeasonFinder {

    public static String getSeason(int month) {
        return switch (month) {
            case 1, 2, 12 -> "Зима";
            case 3, 4, 5 -> "Весна";
            case 6, 7, 8 -> "Лето";
            case 9, 10, 11 -> "Осень";
            default -> {
                String message = "Неверный номер - ";
                if (month < 1) message += "номер не может меньше 1.";
                else if (month > 12) message += "номер не может больше 12.";
                yield message;
            }
        };
    }

    public static void main(String[] args) {
        System.out.println(getSeason(1));
        System.out.println(getSeason(4));
        System.out.println(getSeason(7));
        System.out.println(getSeason(10));
        System.out.println(getSeason(0));
        System.out.println(getSeason(13));
    }
}
