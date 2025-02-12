package workshop2.task3;

public class OrderProducer {
    private int orderNumber = 1;

    public String getOrder() {
        try {
            Thread.sleep(1_000);
            String order = "Order #" + orderNumber++;
            System.out.println("Клиент добавил: " + order);
            return order;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
