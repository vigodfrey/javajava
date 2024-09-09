import java.util.*;

class Laptop {
    private final int ram;
    private final int hdd;
    private final String os;

    public Laptop(int ram, int hdd, String os) {
        this.ram = ram;
        this.hdd = hdd;
        this.os = os;
    }

    public int getRam() {
        return ram;
    }

    public int getHdd() {
        return hdd;
    }

    public String getOs() {
        return os;
    }

    public String getInfo() {
        return "ram: " + ram + ", hdd: " + hdd + ", os: " + os;
    }
}

class Store {

    private Set<Laptop> laptops = Set.of(
            new Laptop(16, 512, "Windows"),
            new Laptop(8, 1024, "Windows"),
            new Laptop(8, 256, "macOS"),
            new Laptop(32, 1024, "Linux"),
            new Laptop(16, 512, "macOS")
    );

    private List<String> os = List.of("Windows", "macOS", "Linux");

    public void filterLaptops() {
        Scanner scanner = new Scanner(System.in);
        Map<Integer, Integer> userCriteria = new HashMap<>();
        Set<Laptop> filteredLaptops = new HashSet<>(laptops);

        System.out.println("Введите цифру, соответствующую необходимому критерию:");
        System.out.println("1) ОЗУ (Минимум в ГБ)");
        System.out.println("2) Объем ЖД (Минимум в ГБ)");
        System.out.println("3) Операционная система (0 - Windows, 1 - macOS, 2 - Linux)");

        while (true) {
            System.out.print("Введите номер критерия (или 0 для завершения): ");
            int input = scanner.nextInt();
            if (input == 0) break;

            switch (input) {
                case 1:
                    System.out.print("Введите минимальное значение ОЗУ: ");
                    userCriteria.put(1, scanner.nextInt());
                    break;
                case 2:
                    System.out.print("Введите минимальное значение объема ЖД: ");
                    userCriteria.put(2, scanner.nextInt());
                    break;
                case 3:
                    System.out.print("Выберите операционную систему (0 - Windows, 1 - macOS, 2 - Linux): ");
                    userCriteria.put(3, scanner.nextInt());
                    break;
                default:
                    System.out.println("Некорректный ввод. Попробуйте снова.");
            }
        }

        if (userCriteria.isEmpty()) return;

        if (userCriteria.containsKey(1)) {
            filteredLaptops.removeIf(laptop -> laptop.getRam() < userCriteria.get(1));
        }

        if (userCriteria.containsKey(2)) {
            filteredLaptops.removeIf(laptop -> laptop.getHdd() < userCriteria.get(2));
        }

        if (userCriteria.containsKey(3)) {
            filteredLaptops.removeIf(laptop -> !laptop.getOs().equals(os.get(userCriteria.get(3))));
        }

        if (filteredLaptops.isEmpty()) {
            System.out.println("Нет ноутбуков, соответствующих заданным критериям.");
        } else {
            System.out.println("Ноутбуки, соответствующие заданным критериям:");
            for (Laptop laptop : filteredLaptops) {
                System.out.println(laptop.getInfo());
            }
        }
    }
}

public class Main {

    public static void main(String[] args) {
        Store store = new Store();
        store.filterLaptops();
    }
}
