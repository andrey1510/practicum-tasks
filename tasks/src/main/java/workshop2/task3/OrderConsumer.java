package workshop2.task3;

public class OrderConsumer {
    public void processOrder(String order) {
        try {
            Thread.sleep(5_000); // Симуляция времени приготовления напитка
            System.out.println("Бариста  завершил приготовление: " + order);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

