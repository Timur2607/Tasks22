import java.util.ArrayList;
import java.util.List;

// Задача 1: Singleton для подключения к базе данных
class DatabaseConnection {
    private static DatabaseConnection instance;

    private DatabaseConnection() {
        System.out.println("Подключение к базе данных создано.");
    }

    public static DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    public void connect() {
        System.out.println("Подключение к базе данных выполнено.");
    }
}

// Задача 2: Singleton для системы логирования
class Logger {
    private static Logger instance;
    private final List<String> logs;

    private Logger() {
        logs = new ArrayList<>();
    }

    public static Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    public void log(String message) {
        logs.add(message);
    }

    public void printLogs() {
        System.out.println("Логи системы:");
        for (String log : logs) {
            System.out.println(log);
        }
    }
}

// Задача 3: Enum для статусов заказа
enum OrderStatus {
    NEW,
    IN_PROGRESS,
    DELIVERED,
    CANCELLED
}

class Order {
    private String orderId;
    private OrderStatus status;

    public Order(String orderId) {
        this.orderId = orderId;
        this.status = OrderStatus.NEW; // По умолчанию статус NEW
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void changeStatus(OrderStatus newStatus) {
        if (status == OrderStatus.DELIVERED) {
            System.out.println("Нельзя изменить статус доставленного заказа.");
            return;
        }
        if (status == OrderStatus.CANCELLED) {
            System.out.println("Нельзя изменить статус отмененного заказа.");
            return;
        }
        this.status = newStatus;
        System.out.println("Статус заказа " + orderId + " изменен на " + status);
    }

    public void printStatus() {
        System.out.println("Текущий статус заказа " + orderId + ": " + status);
    }
}

// Задача 4: Enum для сезонов года
enum Season {
    WINTER,
    SPRING,
    SUMMER,
    AUTUMN
}

class SeasonTranslator {
    public static String translateSeason(Season season) {
        switch (season) {
            case WINTER:
                return "Зима";
            case SPRING:
                return "Весна";
            case SUMMER:
                return "Лето";
            case AUTUMN:
                return "Осень";
            default:
                return "Неизвестный сезон";
        }
    }
}

// Главный класс для выполнения всех задач
public class Main {
    public static void main(String[] args) {
        // Задача 1: Тестирование DatabaseConnection
        System.out.println("Задача 1: Singleton для подключения к базе данных");
        DatabaseConnection db1 = DatabaseConnection.getInstance();
        DatabaseConnection db2 = DatabaseConnection.getInstance();
        db1.connect();
        System.out.println("db1 и db2 ссылаются на один объект: " + (db1 == db2));

        // Задача 2: Тестирование Logger
        System.out.println("\nЗадача 2: Singleton для системы логирования");
        Logger logger = Logger.getInstance();
        logger.log("Первое сообщение в логе.");
        logger.log("Второе сообщение в логе.");
        logger.printLogs();

        // Задача 3: Тестирование Order и OrderStatus
        System.out.println("\nЗадача 3: Enum для статусов заказа");
        Order order = new Order("12345");
        order.printStatus();
        order.changeStatus(OrderStatus.IN_PROGRESS);
        order.changeStatus(OrderStatus.DELIVERED);
        order.changeStatus(OrderStatus.CANCELLED); // Это изменение не должно быть разрешено.
        order.printStatus();

        // Задача 4: Тестирование Season и SeasonTranslator
        System.out.println("\nЗадача 4: Enum для сезонов года");
        System.out.println("Перевод WINTER: " + SeasonTranslator.translateSeason(Season.WINTER));
        System.out.println("Перевод SPRING: " + SeasonTranslator.translateSeason(Season.SPRING));
        System.out.println("Перевод SUMMER: " + SeasonTranslator.translateSeason(Season.SUMMER));
        System.out.println("Перевод AUTUMN: " + SeasonTranslator.translateSeason(Season.AUTUMN));
    }
}