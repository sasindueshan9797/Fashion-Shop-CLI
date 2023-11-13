import java.util.*;

class Example {
    public static Scanner input = new Scanner(System.in);

    // public static String[] orderIds = { "ODR#00001", "ODR#00002", "ODR#00003", "ODR#00004", "ODR#00005", "ODR#00006",
    //         "ODR#00007", "ODR#00008" };
    // public static String[] customerIds = { "0776198410", "0703859852", "0769854321", "0776198410", "0703859852",
    //         "0775544336", "0703859852", "0712002200" };
    // public static String[] sizes = { "XS", "XL", "XXL", "M", "M", "S", "XXL", "M" };
    // public static String[] qtys = { "3", "4", "1", "3", "6", "2", "1", "3" };
    // public static String[] orderStatus = { "processing", "delivering", "delivered", "delivering", "processing",
    //         "delivered", "processing", "delivered" };

    public static String[] orderIds = new String[0];
    public static String[] customerIds = new String[0];
    public static String[] sizes = new String[0];
    public static String[] qtys = new String[0];
    public static String[] orderStatus = new String[0];

    public static final int PROCESSING = 0;
    public static final int DELIVERING = 1;
    public static final int DELIVERED = 2;

    public static void main(String[] args) {
        mainMenu();
    }

    public static void mainMenu() {
        Scanner input = new Scanner(System.in);

        System.out.println("\t /$$$$$$$$                 /$$       /$$                            /$$$$$$  /$$");
        System.out.println("\t| $$_____/                | $$      |__/                           /$$__  $$| $$");
        System.out.println(
                "\t| $$    /$$$$$$   /$$$$$$$| $$$$$$$  /$$  /$$$$$$  /$$$$$$$       | $$  \\__/| $$$$$$$   /$$$$$$   /$$$$$$");
        System.out.println(
                "\t| $$$$$|____  $$ /$$_____/| $$__  $$| $$ /$$__  $$| $$__  $$      |  $$$$$$ | $$__  $$ /$$__  $$ /$$__  $$");
        System.out.println(
                "\t| $$__/ /$$$$$$$|  $$$$$$ | $$  \\ $$| $$| $$  \\ $$| $$  \\ $$       \\____  $$| $$  \\ $$| $$  \\ $$| $$  \\ $$");
        System.out.println(
                "\t| $$   /$$__  $$ \\____  $$| $$  | $$| $$| $$  | $$| $$  | $$       /$$  \\ $$| $$  | $$| $$  | $$| $$  | $$");
        System.out.println(
                "\t| $$  |  $$$$$$$ /$$$$$$$/| $$  | $$| $$|  $$$$$$/| $$  | $$      |  $$$$$$/| $$  | $$|  $$$$$$/| $$$$$$$/");
        System.out.println(
                "\t|__/   \\_______/|_______/ |__/  |__/|__/ \\______/ |__/  |__/       \\______/ |__/  |__/ \\______/ | $$____/");
        System.out.println(
                "\t                                                                                                | $$");
        System.out.println(
                "\t                                                                                                | $$");
        System.out.println(
                "\t                                                                                                |__/");
        System.out.println(
                "    ------------------------------------------------------------------------------------------------------------------\n");

        System.out.println(
                "\t\t[1] Place Order\t\t\t\t[2]Search Customer\n\n\t\t[3] Search Order\t\t\t[4] View Reports\n\n\t\t[5] Change Order Status\t\t\t[6] Delete Order\n\n");

        System.out.print("\t\tInput Option : ");
        int option = input.nextInt();

        switch (option) {
            case 1:
                clearConsole();
                placeOrder();
                break;

            case 2:
                clearConsole();
                searchCustomer();
                break;

            case 3:
                clearConsole();
                searchOrder();
                break;

            case 4:
                clearConsole();
                viewReports();
                break;

            case 5:
                clearConsole();
                changeStatus();
                break;

            case 6:
                clearConsole();
                deleteOrder();
                break;

            default:
                System.out.println("Invalid Input... Check the available options and Run again...");
        }
    }

    // +++++++++Utilities+++++++++++++++
    public final static void clearConsole() {
        try {
            final String os = System.getProperty("os.name");
            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (final Exception e) {
            e.printStackTrace();
            // Handle any exceptions.
        }
    }

    public static String idGenerator() {
        if (orderIds.length > 0) {
            int id = Integer.parseInt(orderIds[orderIds.length - 1].split("[#]")[1]);
            id++;

            return String.format("ODR#%05d", id);
        }

        return "ODR#00001";
    }

    public static boolean isValidNumber(String num) {

        if (num.length() == 10) {
            if (num.startsWith("07") || num.startsWith("011")) {
                return true;
            }

            return false;
        }
        return false;
    }

    public static boolean isExistingCustomer(String value) {
        for (int i = 0; i < customerIds.length; i++) {
            if (value.equals(customerIds[i])) {
                return true;
            }
        }
        return false;
    }

    public static int isExistingOrder(String value) {
        for (int i = 0; i < orderIds.length; i++) {
            if (value.equals(orderIds[i])) {
                return i;
            }
        }
        return -1;
    }

    public static void printOrderDetails(int index) {
        double amount = priceCalculator(sizes[index], qtys[index]);

        System.out.println("\nPhone Number : " + customerIds[index]);
        System.out.println("Size         : " + sizes[index]);
        System.out.println("Qty          : " + qtys[index]);
        System.out.println("Amount       : " + amount);
        System.out.println("Status       : " + orderStatus[index]);
    }

    public static double priceCalculator(String size, String quantity) {
        double amount = 1;
        double qty = Double.parseDouble(quantity);

        switch (size) {
            case "XS":
                amount *= 600 * qty;
                break;
            case "S":
                amount *= 800 * qty;
                break;
            case "M":
                amount *= 900 * qty;
                break;
            case "L":
                amount *= 1000 * qty;
                break;
            case "XL":
                amount *= 1100 * qty;
                break;
            case "XXL":
                amount *= 1200 * qty;
                break;
            default:
                System.out.println("Invalid Input...");
        }

        return amount;
    }

    public static String orderStatusSelector(int num) {

        switch (num) {
            case 0:
                return "PROCESSING";

            case 1:
                return "DELIVERING";

            case 2:
                return "DELIVERED";

            default:
                return null;
        }

    }

    public static String[] extendArray(String[] br) {

        String[] temp = new String[br.length + 1];

        for (int i = 0; i < br.length; i++) {
            temp[i] = br[i];
        }
        return temp;
    }

    public static String[] removeItems(String[] ar, int index) {

        String[] temp = new String[ar.length - 1];

        for (int i = 0; i < index; i++) {
            temp[i] = ar[i];
        }

        for (int i = index; i < temp.length; i++) {
            temp[i] = ar[i + 1];
        }

        ar = temp;

        return ar;
    }

    public static boolean search(String[] ar, String key) {

        for (int i = 0; i < ar.length; i++) {
            if (ar[i].equals(key)) {
                return true;
            }
        }

        return false;
    }

    public static String[] removeDuplicates(String[] ar) {
        String[] br = new String[0];

        for (int i = 0; i < ar.length; i++) {

            if (!search(br, ar[i])) {

                String[] temp = new String[br.length + 1];

                for (int j = 0; j < br.length; j++) {
                    temp[j] = br[j];
                }

                temp[br.length] = ar[i];
                br = temp;
            }
        }

        return br;
    }

    public static int getIndex(String[] ar, String key) {

        for (int i = 0; i < ar.length; i++) {
            if (ar[i].equals(key)) {
                return i;
            }
        }

        return 0;
    }

    public static String[] copyArray(String[] arr) {
        String[] temp = new String[arr.length];

        for (int i = 0; i < arr.length; i++) {
            temp[i] = arr[i];
        }

        return temp;
    }

    // +++++++++Place Order+++++++++++++++
    public static void placeOrder() {

        System.out.println("\t _____  _                   ____          _  ");
        System.out.println("\t|  __ \\| |                 / __ \\        | |");
        System.out.println("\t| |__) | | __ _  ___ ___  | |  | |_ __ __| | ___ _ __ ");
        System.out.println("\t|  ___/| |/ _` |/ __/ _ \\ | |  | | '__/ _` |/ _ \\ '__|");
        System.out.println("\t| |    | | (_| | (_|  __/ | |__| | | | (_| |  __/ |");
        System.out.println("\t|_|    |_|\\__,_|\\___\\___|  \\____/|_|  \\__,_|\\___|_|");
        System.out.println("    ------------------------------------------------------------");

        String orderID = idGenerator();
        System.out.println("\n\nEnter Order ID : " + orderID);

        String number;
        L1: do {
            System.out.print("\nEnter Customer Phone Number : ");
            number = input.next();

            if (!isValidNumber(number)) {
                System.out.println("\n\t\tInvalid Phone Number... Try again...");
                do {
                    System.out.print("\nDo you want to enter phone number again (Y/N) ?");
                    String choice = input.next().toLowerCase();

                    if (choice.equals("y")) {
                        continue L1;
                    } else if (choice.equals("n")) {
                        mainMenu();
                        return;
                    }
                    System.out.println("\n\t\tInvalid...");
                } while (true);
            }

            break;
        } while (true);

        String size;
        L2: do {
            System.out.print("\nEnter T-Shirt Size (XS/S/M/L/XL/XXL) : ");
            size = input.next().toUpperCase();

            if (size.equals("XS") || size.equals("S") || size.equals("M") || size.equals("L") || size.equals("XL")
                    || size.equals("XXL")) {
                break L2;
            }

            System.out.println("\n\t\tInvalid Size... Try again...");
            do {
                System.out.print("\nDo you want to enter size again (Y/N) ?");
                String choice = input.next().toLowerCase();

                if (choice.equals("y")) {
                    continue L2;
                } else if (choice.equals("n")) {
                    mainMenu();
                    return;
                }
                System.out.println("\n\t\tInvalid...");
            } while (true);
        } while (true);

        String qty;
        L3: do {
            System.out.print("\nEnter Quanity : ");
            qty = input.next();

            if (Integer.parseInt(qty) <= 0) {
                System.out.println("\n\t\tInvalid Quantity... Try again...");

                do {
                    System.out.print("\nDo you want to enter quantity again (Y/N) ?");
                    String choice = input.next().toLowerCase();

                    if (choice.equals("y")) {
                        continue L3;
                    } else if (choice.equals("n")) {
                        mainMenu();
                        return;
                    }
                    System.out.println("\n\t\tInvalid...");
                } while (true);
            }

            break;
        } while (true);

        double amount = priceCalculator(size, qty);
        System.out.println("\nAmount : " + amount);

        do {
            System.out.print("\n\nDo you want to place this Order (Y/N) ? : ");
            String order_choice = input.next().toLowerCase();

            if (order_choice.equals("y")) {

                orderIds = extendArray(orderIds);
                orderIds[orderIds.length - 1] = orderID;

                customerIds = extendArray(customerIds);
                customerIds[orderIds.length - 1] = number;

                sizes = extendArray(sizes);
                sizes[orderIds.length - 1] = size;

                qtys = extendArray(qtys);
                qtys[orderIds.length - 1] = qty;

                String order_status = orderStatusSelector(PROCESSING);
                orderStatus = extendArray(orderStatus);
                orderStatus[orderStatus.length - 1] = order_status;

                System.out.println("\n\t\tOrder Placed...!");

                do {
                    System.out.print("\nDo you want to place another order (Y/N) ? : ");
                    String choice = input.next().toLowerCase();

                    if (choice.equals("y")) {
                        clearConsole();
                        placeOrder();
                        return;
                    } else if (choice.equals("n")) {
                        clearConsole();
                        mainMenu();
                        return;
                    }
                    System.out.println("\n\t\tInvalid...");
                } while (true);

            } else if (order_choice.equals("N")) {
                do {
                    System.out.print("\nDo you want to place another order (Y/N) ? : ");
                    String choice = input.next().toLowerCase();

                    if (choice.equals("y")) {
                        clearConsole();
                        placeOrder();
                        return;
                    } else if (choice.equals("n")) {
                        clearConsole();
                        mainMenu();
                        return;
                    }

                    System.out.println("\n\t\tInvalid...");
                } while (true);
            }
            System.out.println("\n\t\tInvalid...");
        } while (true);

    }

    // +++++++++Search Customer+++++++++++++++
    public static void searchCustomer() {
        System.out.println("\t\t  _____                     _        _____          _  ");
        System.out.println("\t\t / ____|                   | |      / ____|        | |");
        System.out.println("\t\t| (___   ___  __ _ _ __ ___| |__   | |    _   _ ___| |_ ___  _ __ ___   ___ _ __");
        System.out.println(
                "\t\t \\___ \\ / _ \\/ _` | '__/ __| '_ \\  | |   | | | / __| __/ _ \\| '_ ` _ \\ / _ \\ '__|");
        System.out.println("\t\t ____) |  __/ (_| | | | (__| | | | | |___| |_| \\__ \\ || (_) | | | | | |  __/ |");
        System.out
                .println("\t\t|_____/ \\___|\\__,_|_|  \\___|_| |_|  \\_____\\__,_|___/\\__\\___/|_| |_| |_|\\___|_|");
        System.out.println(
                "             ------------------------------------------------------------------------------------");

        System.out.print("\n\nEnter Customer Phone Number : ");
        String number = input.next();

        int total = 0;

        if (isValidNumber(number) & isExistingCustomer(number)) {

            System.out.println("\t\t+---------+----------+---------------+");
            System.out.println("\t\t|   Size  |    Qty   |     Amount    |");
            System.out.println("\t\t+---------+----------+---------------+");
            System.out.println("\t\t|         |          |               |");

            for (int i = 0; i < customerIds.length; i++) {

                if (number.equals(customerIds[i])) {
                    double amount = priceCalculator(sizes[i], qtys[i]);
                    System.out.println(
                            "\t\t|   " + sizes[i] + "     |     " + qtys[i] + "    |     " + amount + "      |");
                    System.out.println("\t\t|         |          |               |");
                    total += amount;
                }
            }

            System.out.println("\t\t+---------+----------+---------------+");
            System.out.println("\t\t|   Total Amount     |     " + total + "      |");
            System.out.println("\t\t+---------+----------+---------------+");

            do {
                System.out.print("\nDo you want to search another customer report (Y/N) ? : ");
                String choice = input.next().toLowerCase();

                if (choice.equals("y")) {
                    clearConsole();
                    searchCustomer();
                    return;
                } else if (choice.equals("n")) {
                    clearConsole();
                    mainMenu();
                    return;
                }

                System.out.println("\n\t\tInvalid...");
            } while (true);

        }

        System.out.println("\n\t\tInvalid input...");
        do {
            System.out.print("\nDo you want to search another customer report (Y/N) ? : ");
            String choice = input.next().toLowerCase();

            if (choice.equals("y")) {
                clearConsole();
                searchCustomer();
                return;
            } else if (choice.equals("n")) {
                clearConsole();
                mainMenu();
                return;
            }

            System.out.println("\n\t\tInvalid...");
        } while (true);
    }

    // +++++++++Search Order+++++++++++++++
    public static void searchOrder() {
        System.out.println("\t  _____                     _        ____          _ ");
        System.out.println("\t / ____|                   | |      / __ \\        | |");
        System.out.println("\t| (___   ___  __ _ _ __ ___| |__   | |  | |_ __ __| | ___ _ __ ");
        System.out.println("\t \\___ \\ / _ \\/ _` | '__/ __| '_ \\  | |  | | '__/ _` |/ _ \\ '__|");
        System.out.println("\t ____) |  __/ (_| | | | (__| | | | | |__| | | | (_| |  __/ |");
        System.out.println("\t|_____/ \\___|\\__,_|_|  \\___|_| |_|  \\____/|_|  \\__,_|\\___|_|");
        System.out.println(
                "    --------------------------------------------------------------------");

        System.out.print("\nEnter order ID : ");
        String id = input.next();

        int index = isExistingOrder(id);
        if (index >= 0) {

            printOrderDetails(index);

            do {
                System.out.print("\nDo you want to search another order (Y/N) ? : ");
                String choice = input.next().toLowerCase();

                if (choice.equals("y")) {
                    clearConsole();
                    searchOrder();
                    return;
                } else if (choice.equals("n")) {
                    clearConsole();
                    mainMenu();
                    return;
                }

                System.out.println("\n\t\tInvalid...");
            } while (true);

        }

        System.out.println("\n\t\tInvalid input...");
        do {
            System.out.print("\nDo you want to search another order (Y/N) ? : ");
            String choice = input.next().toLowerCase();

            if (choice.equals("y")) {
                clearConsole();
                searchOrder();
                return;
            } else if (choice.equals("n")) {
                clearConsole();
                mainMenu();
                return;
            }

            System.out.println("\n\t\tInvalid...");
        } while (true);
    }

    // +++++++++Delete Order+++++++++++++++
    public static void deleteOrder() {

        System.out.println("\t _____       _      _          ____          _ ");
        System.out.println("\t|  __ \\     | |    | |        / __ \\        | |");
        System.out.println("\t| |  | | ___| | ___| |_ ___  | |  | |_ __ __| | ___ _ __ ");
        System.out.println("\t| |  | |/ _ \\ |/ _ \\ __/ _ \\ | |  | | '__/ _` |/ _ \\ '__|");
        System.out.println("\t| |__| |  __/ |  __/ ||  __/ | |__| | | | (_| |  __/ |");
        System.out.println("\t|_____/ \\___|_|\\___|\\__\\___|  \\____/|_|  \\__,_|\\___|_|");
        System.out.println(
                "    --------------------------------------------------------------------");
        System.out.print("\nEnter order ID : ");
        String id = input.next();

        int index = isExistingOrder(id);

        if (index >= 0) {
            printOrderDetails(index);

            System.out.print("\nDo you want to delete this order (Y/N) ? : ");
            String choice = input.next().toLowerCase();

            if (choice.equals("y")) {

                orderIds = removeItems(orderIds, index);
                customerIds = removeItems(customerIds, index);
                sizes = removeItems(sizes, index);
                qtys = removeItems(qtys, index);
                orderStatus = removeItems(orderStatus, index);

                System.out.println("\n\t\tOrder Deleted..!");

                do {
                    System.out.print("\nDo you want to delete another order (Y/N) ? : ");
                    String yn = input.next().toLowerCase();

                    if (yn.equals("y")) {
                        clearConsole();
                        deleteOrder();
                        return;

                    } else if (yn.equals("n")) {
                        clearConsole();
                        mainMenu();
                        return;
                    }

                    System.out.println("\n\t\tInvalid...");
                } while (true);

            } else if (choice.equals("n")) {

                do {
                    System.out.print("\nDo you want to delete another order (Y/N) ? : ");
                    String yn = input.next().toLowerCase();

                    if (yn.equals("y")) {
                        clearConsole();
                        deleteOrder();
                        return;

                    } else if (yn.equals("n")) {
                        clearConsole();
                        mainMenu();
                        return;
                    }

                    System.out.println("\n\t\tInvalid...");
                } while (true);
            }
        }

        System.out.println("\n\t\tInvalid input...");

        do {
            System.out.print("\nDo you want to delete another order (Y/N) ? : ");
            String choice = input.next().toLowerCase();

            if (choice.equals("y")) {
                clearConsole();
                deleteOrder();
                return;
            } else if (choice.equals("n")) {
                clearConsole();
                mainMenu();
                return;
            }

            System.out.println("\n\t\tInvalid...");
        } while (true);
    }

    // +++++++++View Reports+++++++++++++++
    public static void viewReports() {
        System.out.println("\t _____                       _ ");
        System.out.println("\t|  __ \\                     | |");
        System.out.println("\t| |__) |___ _ __   ___  _ __| |_ ___");
        System.out.println("\t|  _  // _ \\ '_ \\ / _ \\| '__| __/ __|");
        System.out.println("\t| | \\ \\  __/ |_) | (_) | |  | |_\\__ \\");
        System.out.println("\t|_|  \\_\\___| .__/ \\___/|_|   \\__|___/");
        System.out.println("\t           | |");
        System.out.println("\t           |_|");
        System.out.println(
                "    ------------------------------------------");

        System.out.println("\n\t[1] Customer Reports\n\n\t[2] Item Reports\n\n\t[3] Order Reports");
        System.out.print("\n\nEnter Option : ");
        int option = input.nextInt();

        switch (option) {
            case 1:
                clearConsole();
                customerReports();
                break;

            case 2:
                clearConsole();
                itemReports();
                break;

            case 3:
                clearConsole();
                orderReports();
                break;

            default:
                System.out.print("\n\tInvalid input...\n\nDo you want to enter option again (y / n) : ");
                String choice = input.next().toLowerCase();

                if (choice.equals("y")) {
                    clearConsole();
                    viewReports();
                    return;
                }

                clearConsole();
                mainMenu();
                return;

        }
    }

    public static void customerReports() {
        System.out.println("\t  _____          _                              _____                       _ ");
        System.out.println("\t / ____|        | |                            |  __ \\                     | |");
        System.out.println("\t| |    _   _ ___| |_ ___  _ __ ___   ___ _ __  | |__) |___ _ __   ___  _ __| |_ ___");
        System.out.println(
                "\t| |   | | | / __| __/ _ \\| '_ ` _ \\ / _ \\ '__| |  _  // _ \\ '_ \\ / _ \\| '__| __/ __|");
        System.out.println(
                "\t| |___| |_| \\__ \\ || (_) | | | | | |  __/ |    | | \\ \\  __/ |_) | (_) | |  | |_\\__ \\");
        System.out.println(
                "\t \\_____\\__,_|___/\\__\\___/|_| |_| |_|\\___|_|    |_|  \\_\\___| .__/ \\___/|_|   \\__|___/");
        System.out.println("\t                                                          | |");
        System.out.println("\t                                                          |_|");
        System.out.println(
                "    ------------------------------------------------------------------------------------------");

        System.out.println("\n\t[1] Best in Customers\n\n\t[2] View Customer\n\n\t[3] All Customer Report");
        System.out.print("\n\nEnter Option : ");
        int option = input.nextInt();

        switch (option) {
            case 1:
                clearConsole();
                bestCustomers();
                break;

            case 2:
                clearConsole();
                viewCustomers();
                break;

            case 3:
                clearConsole();
                allCustomers();
                break;

            default:
                System.out.print("\n\tInvalid input...\n\nDo you want to enter option again (y / n) : ");
                String choice = input.next().toLowerCase();

                if (choice.equals("y")) {
                    clearConsole();
                    customerReports();
                    return;
                }

                clearConsole();
                viewReports();
                return;

        }
    }

    public static void itemReports() {
        System.out.println("\t _____ _                   _____                       _ ");
        System.out.println("\t|_   _| |                 |  __ \\                     | |");
        System.out.println("\t  | | | |_ ___ _ __ ___   | |__) |___ _ __   ___  _ __| |_ ___ ");
        System.out.println("\t  | | | __/ _ \\ '_ ` _ \\  |  _  // _ \\ '_ \\ / _ \\| '__| __/ __|");
        System.out.println("\t _| |_| ||  __/ | | | | | | | \\ \\  __/ |_) | (_) | |  | |_\\__ \\");
        System.out.println("\t|_____|\\__\\___|_| |_| |_| |_|  \\_\\___| .__/ \\___/|_|   \\__|___/");
        System.out.println("\t                                     | |");
        System.out.println("\t                                     |_|");
        System.out.println(
                "    --------------------------------------------------------------------");

        System.out.println(
                "\n\t[1] Best Selling Categories Sorted By QTY\n\n\t[2] Best Selling Categories Sorted By Amount");
        System.out.print("\n\nEnter Option : ");
        int option = input.nextInt();

        switch (option) {
            case 1:
                clearConsole();
                itemsByQty();
                break;

            case 2:
                clearConsole();
                itemsByAmount();
                break;

            default:
                System.out.print("\n\tInvalid input...\n\nDo you want to enter option again (y / n) : ");
                String choice = input.next().toLowerCase();

                if (choice.equals("y")) {
                    clearConsole();
                    itemReports();
                    return;
                }

                clearConsole();
                viewReports();
                return;

        }
    }

    public static void orderReports() {
        System.out.println("\t  ____          _             _____                       _ ");
        System.out.println("\t / __ \\        | |           |  __ \\                     | |");
        System.out.println("\t| |  | |_ __ __| | ___ _ __  | |__) |___ _ __   ___  _ __| |_ ___ ");
        System.out.println("\t| |  | | '__/ _` |/ _ \\ '__| |  _  // _ \\ '_ \\ / _ \\| '__| __/ __|");
        System.out.println("\t| |__| | | | (_| |  __/ |    | | \\ \\  __/ |_) | (_) | |  | |_\\__ \\");
        System.out.println("\t \\____/|_|  \\__,_|\\___|_|    |_|  \\_\\___| .__/ \\___/|_|   \\__|___/");
        System.out.println("\t                                        | |");
        System.out.println("\t                                        |_|");
        System.out.println(
                "    -----------------------------------------------------------------------");

        System.out.println(
                "\n\t[1] All Orders\n\n\t[2] Orders By Amount");
        System.out.print("\n\nEnter Option : ");
        int option = input.nextInt();

        switch (option) {
            case 1:
                clearConsole();
                allOrders();
                break;

            case 2:
                clearConsole();
                ordersByAmount();
                break;

            default:
                System.out.print("\n\tInvalid input...\n\nDo you want to enter option again (y / n) : ");
                String choice = input.next().toLowerCase();

                if (choice.equals("y")) {
                    clearConsole();
                    orderReports();
                    return;
                }

                clearConsole();
                viewReports();
                return;

        }
    }

    public static void bestCustomers() {

        System.out.println("\t ____            _     _____          _____          _ ");
        System.out.println("\t|  _ \\          | |   |_   _|        / ____|        | |");
        System.out.println("\t| |_) | ___  ___| |_    | |  _ __   | |    _   _ ___| |_ ___  _ __ ___   ___ _ __ ___ ");
        System.out.println(
                "\t|  _ < / _ \\/ __| __|   | | | '_ \\  | |   | | | / __| __/ _ \\| '_ ` _ \\ / _ \\ '__/ __|");
        System.out.println(
                "\t| |_) |  __/\\__ \\ |_   _| |_| | | | | |___| |_| \\__ \\ || (_) | | | | | |  __/ |  \\__ \\");
        System.out.println(
                "\t|____/ \\___||___/\\__| |_____|_| |_|  \\_____\\__,_|___/\\__\\___/|_| |_| |_|\\___|_|  |___/");
        System.out.println(
                "    -------------------------------------------------------------------------------------------\n\n");

        String[] customers = removeDuplicates(customerIds);
        double[] totalAmount = new double[customers.length];
        int[] totalQtys = new int[customers.length];

        System.out.println("\t\t+---------------+---------+---------------+");
        System.out.printf("\t\t|%-15s|%-9s|%-15s|%n", " Customer ID", " All Qty", " Total Amount");
        System.out.println("\t\t+---------------+---------+---------------+");

        // Adding values to local arrays accordi to unique customer Array
        for (int i = 0; i < customers.length; i++) {

            for (int j = 0; j < customerIds.length; j++) {

                if (customers[i].equals(customerIds[j])) {

                    double amount = priceCalculator(sizes[j], qtys[j]);

                    totalAmount[i] += amount;
                    totalQtys[i] += Integer.parseInt(qtys[j]);
                }
            }
        }

        // sorting according to the Total Amount
        for (int i = 1; i < totalAmount.length; i++) {
            for (int j = 1; j < totalAmount.length; j++) {

                if (totalAmount[j - 1] < totalAmount[j]) {

                    double t = totalAmount[j - 1];
                    totalAmount[j - 1] = totalAmount[j];
                    totalAmount[j] = t;

                    int q = totalQtys[j - 1];
                    totalQtys[j - 1] = totalQtys[j];
                    totalQtys[j] = q;

                    String c = customers[j - 1];
                    customers[j - 1] = customers[j];
                    customers[j] = c;
                }
            }
        }

        // printing Arrays
        for (int i = 0; i < customers.length; i++) {
            System.out.printf("\t\t|%-15s|%-9s|%-15s|%n", "", "", "", "", "", "", "", "");
            System.out.printf("\t\t|%-15s|  %-7s|%13.2f  |%n", customers[i], "  " + totalQtys[i], totalAmount[i]);
        }
        System.out.println("\t\t+---------------+---------+---------------+");

        System.out.print("\nTo access the Main Menu, please enter 0 : ");
        int choice = input.nextInt();

        if (choice == 0) {
            clearConsole();
            mainMenu();
            return;
        } else {
            clearConsole();
            bestCustomers();
            return;
        }
    }

    public static void viewCustomers() {
        System.out.println("\t__      ___                  _____          _ ");
        System.out.println("\t\\ \\    / (_)                / ____|        | |");
        System.out.println("\t \\ \\  / / _  _____      __ | |    _   _ ___| |_ ___  _ __ ___   ___ _ __ ___ ");
        System.out.println("\t  \\ \\/ / | |/ _ \\ \\ /\\ / / | |   | | | / __| __/ _ \\| '_ ` _ \\ / _ \\ '__/ __|");
        System.out.println("\t   \\  /  | |  __/\\ V  V /  | |___| |_| \\__ \\ || (_) | | | | | |  __/ |  \\__ \\");
        System.out.println("\t    \\/   |_|\\___| \\_/\\_/    \\_____\\__,_|___/\\__\\___/|_| |_| |_|\\___|_|  |___/");
        System.out.println(
                "\t------------------------------------------------------------------------------\n\n");

        String[] customers = removeDuplicates(customerIds);
        double[] totalAmount = new double[customers.length];
        int[] totalQtys = new int[customers.length];

        System.out.println("\t\t+---------------+---------+---------------+");
        System.out.printf("\t\t|%-15s|%-9s|%-15s|%n", " Customer ID", " All Qty", " Total Amount");
        System.out.println("\t\t+---------------+---------+---------------+");

        // Adding values to local arrays accordi to unique customer Array
        for (int i = 0; i < customers.length; i++) {

            for (int j = 0; j < customerIds.length; j++) {

                if (customers[i].equals(customerIds[j])) {

                    double amount = priceCalculator(sizes[j], qtys[j]);

                    totalAmount[i] += amount;
                    totalQtys[i] += Integer.parseInt(qtys[j]);
                }
            }
            System.out.printf("\t\t|%-15s|%-9s|%-15s|%n", "", "", "", "", "", "", "", "");
            System.out.printf("\t\t|%-15s|  %-7s|%13.2f  |%n", customers[i], "  " + totalQtys[i], totalAmount[i]);
        }
        System.out.println("\t\t+---------------+---------+---------------+");

        System.out.print("\nTo access the Main Menu, please enter 0 : ");
        int choice = input.nextInt();

        if (choice == 0) {
            clearConsole();
            mainMenu();
            return;
        } else {
            clearConsole();
            viewCustomers();
            return;
        }

    }

    public static void allCustomers() {
        System.out.println(
                "\t          _ _    _____          _                              _____                       _ ");
        System.out.println(
                "\t    /\\   | | |  / ____|        | |                            |  __ \\                     | |");
        System.out.println(
                "\t   /  \\  | | | | |    _   _ ___| |_ ___  _ __ ___   ___ _ __  | |__) |___ _ __   ___  _ __| |_ ");
        System.out.println(
                "\t  / /\\ \\ | | | | |   | | | / __| __/ _ \\| '_ ` _ \\ / _ \\ '__| |  _  // _ \\ '_ \\ / _ \\| '__| __|");
        System.out.println(
                "\t / ____ \\| | | | |___| |_| \\__ \\ || (_) | | | | | |  __/ |    | | \\ \\  __/ |_) | (_) | |  | |");
        System.out.println(
                "\t/_/    \\_\\_|_|  \\_____\\__,_|___/\\__\\___/|_| |_| |_|\\___|_|    |_|  \\_\\___| .__/ \\___/|_|   \\__|");
        System.out.println("\t                                                                         | |");
        System.out.println("\t                                                                         |_|");
        System.out.println(
                "     ---------------------------------------------------------------------------------------------------\n\n");

        String[] customers = removeDuplicates(customerIds);

        System.out.println("\t\t+---------------+-------+-------+-------+-------+-------+-------+---------------+");
        System.out.printf("\t\t|%-15s|%-7s|%-7s|%-7s|%-7s|%-7s|%-7s|%-15s|%n", " Phone Number", "  XS", "   S", "   M",
                "   L", "  XL", "  XXL", " Total Amount");
        System.out.println("\t\t+---------------+-------+-------+-------+-------+-------+-------+---------------+");

        // Adding values to local arrays accordi to unique customer Array
        for (int i = 0; i < customers.length; i++) {
            int[] tempSizes = new int[6];
            String[] size = { "XS", "S", "M", "L", "XL", "XXL" };

            for (int j = 0; j < customerIds.length; j++) {

                if (customers[i].equals(customerIds[j])) {

                    switch (sizes[j]) {
                        case "XS":
                            tempSizes[0] += Integer.parseInt(qtys[j]);
                            break;
                        case "S":
                            tempSizes[1] += Integer.parseInt(qtys[j]);
                            break;
                        case "M":
                            tempSizes[2] += Integer.parseInt(qtys[j]);
                            break;
                        case "L":
                            tempSizes[3] += Integer.parseInt(qtys[j]);
                            break;
                        case "XL":
                            tempSizes[4] += Integer.parseInt(qtys[j]);
                            break;
                        case "XXL":
                            tempSizes[5] += Integer.parseInt(qtys[j]);
                            break;
                    }

                }
            }
            double total = 0;

            for (int j = 0; j < tempSizes.length; j++) {
                total += (size[j].equals("XS") ? priceCalculator("XS", String.valueOf(tempSizes[j]))
                        : size[j].equals("S") ? priceCalculator("S", String.valueOf(tempSizes[j]))
                                : size[j].equals("M") ? priceCalculator("M", String.valueOf(tempSizes[j]))
                                        : size[j].equals("L") ? priceCalculator("L", String.valueOf(tempSizes[j]))
                                                : size[j].equals("XL")
                                                        ? priceCalculator("XL", String.valueOf(tempSizes[j]))
                                                        : priceCalculator("XXL", String.valueOf(tempSizes[j])));
            }

            System.out.printf("\t\t|%15s|%7s|%7s|%7s|%7s|%7s|%7s|%15s|%n", "", "", "", "", "", "", "", "");
            System.out.printf("\t\t|%-15s|%-7s|%-7s|%-7s|%-7s|%-7s|%-7s|%13.2f  |%n", customers[i], "  " + tempSizes[0],
                    "  " + tempSizes[1], "  " + tempSizes[2], "  " + tempSizes[3], "  " + tempSizes[4],
                    "  " + tempSizes[5], total);

        }

        System.out.println("\t\t+---------------+-------+-------+-------+-------+-------+-------+---------------+");

        System.out.print("\nTo access the Main Menu, please enter 0 : ");
        int choice = input.nextInt();

        if (choice == 0) {
            clearConsole();
            mainMenu();
            return;
        } else {
            clearConsole();
            allCustomers();
            return;
        }
    }

    public static void itemsByQty() {

        System.out.println("\t  _____            _           _   ____           ____ _________     __");
        System.out.println("\t / ____|          | |         | | |  _ \\         / __ \\__   __\\ \\   / /");
        System.out.println("\t| (___   ___  _ __| |_ ___  __| | | |_) |_   _  | |  | | | |   \\ \\_/ /");
        System.out.println("\t \\___ \\ / _ \\| '__| __/ _ \\/ _` | |  _ <| | | | | |  | | | |    \\   /");
        System.out.println("\t ____) | (_) | |  | ||  __/ (_| | | |_) | |_| | | |__| | | |     | |");
        System.out.println("\t|_____/ \\___/|_|   \\__\\___|\\__,_| |____/ \\__, |  \\___\\_\\ |_|     |_|");
        System.out.println("\t                                          __/ |");
        System.out.println("\t                                         |___/");
        System.out.println("    ------------------------------------------------------------------------------\n\n");

        String[] size = { "XS", "S", "M", "L", "XL", "XXL" };
        double[] totalAmount = new double[size.length];
        int[] totalQtys = new int[size.length];

        System.out.println("\t\t+----------+---------+---------------+");
        System.out.printf("\t\t|%-10s|%-9s|%-15s|%n", " Size", " Qty", " Total Amount");
        System.out.println("\t\t+----------+---------+---------------+");

        // Adding values to local arrays accordi to unique customer Array
        for (int i = 0; i < size.length; i++) {

            for (int j = 0; j < sizes.length; j++) {

                if (size[i].equals(sizes[j])) {

                    double amount = priceCalculator(sizes[j], qtys[j]);

                    totalAmount[i] += amount;
                    totalQtys[i] += Integer.parseInt(qtys[j]);
                }
            }
        }

        // sorting according to the Total Amount
        for (int i = 1; i < totalQtys.length; i++) {
            for (int j = 1; j < totalQtys.length; j++) {

                if (totalQtys[j - 1] < totalQtys[j]) {

                    double t = totalAmount[j - 1];
                    totalAmount[j - 1] = totalAmount[j];
                    totalAmount[j] = t;

                    int q = totalQtys[j - 1];
                    totalQtys[j - 1] = totalQtys[j];
                    totalQtys[j] = q;

                    String s = size[j - 1];
                    size[j - 1] = size[j];
                    size[j] = s;
                }
            }
        }

        // printing Arrays
        for (int i = 0; i < size.length; i++) {
            System.out.printf("\t\t|%-10s|%-9s|%-15s|%n", "", "", "", "", "", "", "", "");
            System.out.printf("\t\t| %-9s|  %-7s|%13.2f  |%n", size[i], "  " + totalQtys[i], totalAmount[i]);
        }
        System.out.println("\t\t+----------+---------+---------------+");

        System.out.print("\nTo access the Main Menu, please enter 0 : ");
        int choice = input.nextInt();

        if (choice == 0) {
            clearConsole();
            mainMenu();
            return;
        } else {
            clearConsole();
            itemsByQty();
            return;
        }
    }

    public static void itemsByAmount() {
        System.out
                .println("\t  _____            _           _   ____                                               _ ");
        System.out.println(
                "\t / ____|          | |         | | |  _ \\            /\\                               | |");
        System.out.println(
                "\t| (___   ___  _ __| |_ ___  __| | | |_) |_   _     /  \\   _ __ ___   ___  _   _ _ __ | |_ ");
        System.out.println(
                "\t \\___ \\ / _ \\| '__| __/ _ \\/ _` | |  _ <| | | |   / /\\ \\ | '_ ` _ \\ / _ \\| | | | '_ \\| __|");
        System.out.println(
                "\t ____) | (_) | |  | ||  __/ (_| | | |_) | |_| |  / ____ \\| | | | | | (_) | |_| | | | | |_ ");
        System.out.println(
                "\t|_____/ \\___/|_|   \\__\\___|\\__,_| |____/ \\__, | /_/    \\_\\_| |_| |_|\\___/ \\__,_|_| |_|\\__|");
        System.out.println("\t                                          __/ |");
        System.out.println("\t                                         |___/");
        System.out.println(
                "      -----------------------------------------------------------------------------------------------\n\n");

        String[] size = { "XS", "S", "M", "L", "XL", "XXL" };
        double[] totalAmount = new double[size.length];
        int[] totalQtys = new int[size.length];

        System.out.println("\t\t+----------+---------+---------------+");
        System.out.printf("\t\t|%-10s|%-9s|%-15s|%n", " Size", " Qty", " Total Amount");
        System.out.println("\t\t+----------+---------+---------------+");

        // Adding values to local arrays accordi to unique customer Array
        for (int i = 0; i < size.length; i++) {

            for (int j = 0; j < sizes.length; j++) {

                if (size[i].equals(sizes[j])) {

                    double amount = priceCalculator(sizes[j], qtys[j]);

                    totalAmount[i] += amount;
                    totalQtys[i] += Integer.parseInt(qtys[j]);
                }
            }
        }

        // sorting according to the Total Amount
        for (int i = 1; i < totalQtys.length; i++) {
            for (int j = 1; j < totalQtys.length; j++) {

                if (totalAmount[j - 1] < totalAmount[j]) {

                    double t = totalAmount[j - 1];
                    totalAmount[j - 1] = totalAmount[j];
                    totalAmount[j] = t;

                    int q = totalQtys[j - 1];
                    totalQtys[j - 1] = totalQtys[j];
                    totalQtys[j] = q;

                    String s = size[j - 1];
                    size[j - 1] = size[j];
                    size[j] = s;
                }
            }
        }

        // printing Arrays
        for (int i = 0; i < size.length; i++) {
            System.out.printf("\t\t|%-10s|%-9s|%-15s|%n", "", "", "", "", "", "", "", "");
            System.out.printf("\t\t| %-9s|  %-7s|%13.2f  |%n", size[i], "  " + totalQtys[i], totalAmount[i]);
        }
        System.out.println("\t\t+----------+---------+---------------+");

        System.out.print("\nTo access the Main Menu, please enter 0 : ");
        int choice = input.nextInt();

        if (choice == 0) {
            clearConsole();
            mainMenu();
            return;
        } else {
            clearConsole();
            itemsByAmount();
            return;
        }
    }

    public static void allOrders() {
        System.out.println("\t__      ___                  ____          _    ");
        System.out.println("\t\\ \\    / (_)                / __ \\        | |");
        System.out.println("\t \\ \\  / / _  _____      __ | |  | |_ __ __| | ___ _ __ ___ ");
        System.out.println("\t  \\ \\/ / | |/ _ \\ \\ /\\ / / | |  | | '__/ _` |/ _ \\ '__/ __|");
        System.out.println("\t   \\  /  | |  __/\\ V  V /  | |__| | | | (_| |  __/ |  \\__ \\");
        System.out.println("\t    \\/   |_|\\___| \\_/\\_/    \\____/|_|  \\__,_|\\___|_|  |___/");
        System.out.println(
                "      ------------------------------------------------------------------\n\n");
        String[] temp_orderId = copyArray(orderIds);
        String[] temp_custId = copyArray(customerIds);
        String[] temp_qty = copyArray(qtys);
        String[] temp_sizes = copyArray(sizes);
        String[] temp_status = copyArray(orderStatus);

        // sorting temporary order id array
        for (int i = 1; i < temp_orderId.length; i++) {
            for (int j = 1; j < temp_orderId.length; j++) {

                if (Integer.parseInt(temp_orderId[j - 1].split("[#]")[1]) < Integer
                        .parseInt(temp_orderId[j].split("[#]")[1])) {

                    String order = temp_orderId[j - 1];
                    temp_orderId[j - 1] = temp_orderId[j];
                    temp_orderId[j] = order;

                    String cust = temp_custId[j - 1];
                    temp_custId[j - 1] = temp_custId[j];
                    temp_custId[j] = cust;

                    String qty = temp_qty[j - 1];
                    temp_qty[j - 1] = temp_qty[j];
                    temp_qty[j] = qty;

                    String size = temp_sizes[j - 1];
                    temp_sizes[j - 1] = temp_sizes[j];
                    temp_sizes[j] = size;

                    String status = temp_status[j - 1];
                    temp_status[j - 1] = temp_status[j];
                    temp_status[j] = status;
                }
            }
        }

        System.out.println("\t\t+--------------+---------------+--------+----------+---------------+---------------+");
        System.out.printf("\t\t|%-14s|%-15s|%-8s|%-10s|%-15s|%-15s|%n", "   Order ID", "  Customer ID", "  Size",
                "   QTY", "    Amount", "    Status");
        System.out.println("\t\t+--------------+---------------+--------+----------+---------------+---------------+");

        for (int i = 0; i < temp_orderId.length; i++) {
            System.out.printf("\t\t|%-14s|%-15s|%-8s|%-10s|%-15s|%-15s|%n", "", "", "", "", "", "");
            double total = priceCalculator(temp_sizes[i], temp_qty[i]);
            System.out.printf("\t\t|%-14s|%-15s|%-8s|%-10s|%11.2f    |%-15s|%n", temp_orderId[i], "  " + temp_custId[i],
                    "   " + temp_sizes[i], "    " + temp_qty[i], total, "  " + temp_status[i]);
        }

        System.out
                .println("\t\t+--------------+---------------+--------+----------+---------------+---------------+\n");

        System.out.print("\nTo access the Main Menu, please enter 0 : ");
        int choice = input.nextInt();

        if (choice == 0) {
            clearConsole();
            mainMenu();
            return;
        } else {
            clearConsole();
            allOrders();
            return;
        }
    }

    public static void ordersByAmount() {
        System.out.println("\t  ____          _                 ____                                               _ ");
        System.out.println(
                "\t / __ \\        | |               |  _ \\            /\\                               | | ");
        System.out
                .println("\t| |  | |_ __ __| | ___ _ __ ___  | |_) |_   _     /  \\   _ __ ___   ___  _   _ _ __ | |");
        System.out.println(
                "\t| |  | | '__/ _` |/ _ \\ '__/ __| |  _ <| | | |   / /\\ \\ | '_ ` _ \\ / _ \\| | | | '_ \\| __|");
        System.out.println(
                "\t| |__| | | | (_| |  __/ |  \\__ \\ | |_) | |_| |  / ____ \\| | | | | | (_) | |_| | | | | |");
        System.out.println(
                "\t \\____/|_|  \\__,_|\\___|_|  |___/ |____/ \\__, | /_/    \\_\\_| |_| |_|\\___/ \\__,_|_| |_|\\__|");
        System.out.println("\t                                         __/ |");
        System.out.println("\t                                        |___/");
        System.out.println(
                "      --------------------------------------------------------------------------------------------\n\n");

        String[] temp_orderId = copyArray(orderIds);
        String[] temp_custId = copyArray(customerIds);
        String[] temp_qty = copyArray(qtys);
        String[] temp_sizes = copyArray(sizes);
        String[] temp_status = copyArray(orderStatus);

        // sorting temporary order id array
        for (int i = 1; i < temp_orderId.length; i++) {

            for (int j = 1; j < temp_orderId.length; j++) {

                if (priceCalculator(temp_sizes[j - 1], temp_qty[j - 1]) < priceCalculator(temp_sizes[j], temp_qty[j])) {

                    String order = temp_orderId[j - 1];
                    temp_orderId[j - 1] = temp_orderId[j];
                    temp_orderId[j] = order;

                    String cust = temp_custId[j - 1];
                    temp_custId[j - 1] = temp_custId[j];
                    temp_custId[j] = cust;

                    String qty = temp_qty[j - 1];
                    temp_qty[j - 1] = temp_qty[j];
                    temp_qty[j] = qty;

                    String size = temp_sizes[j - 1];
                    temp_sizes[j - 1] = temp_sizes[j];
                    temp_sizes[j] = size;

                    String status = temp_status[j - 1];
                    temp_status[j - 1] = temp_status[j];
                    temp_status[j] = status;
                }
            }
        }

        System.out.println("\t\t+--------------+---------------+--------+----------+---------------+---------------+");
        System.out.printf("\t\t|%-14s|%-15s|%-8s|%-10s|%-15s|%-15s|%n", "   Order ID", "  Customer ID", "  Size",
                "   QTY", "    Amount", "    Status");
        System.out.println("\t\t+--------------+---------------+--------+----------+---------------+---------------+");

        for (int i = 0; i < temp_orderId.length; i++) {
            System.out.printf("\t\t|%-14s|%-15s|%-8s|%-10s|%-15s|%-15s|%n", "", "", "", "", "", "");
            double total = priceCalculator(temp_sizes[i], temp_qty[i]);
            System.out.printf("\t\t|%-14s|%-15s|%-8s|%-10s|%11.2f    |%-15s|%n", temp_orderId[i], "  " + temp_custId[i],
                    "   " + temp_sizes[i], "    " + temp_qty[i], total, "  " + temp_status[i]);
        }

        System.out
                .println("\t\t+--------------+---------------+--------+----------+---------------+---------------+\n");

        System.out.print("\nTo access the Main Menu, please enter 0 : ");
        int choice = input.nextInt();

        if (choice == 0) {
            clearConsole();
            mainMenu();
            return;
        } else {
            clearConsole();
            ordersByAmount();
            return;
        }
    }

    public static void changeStatus() {
        System.out.println("\t  ____          _              _____ _        _ ");
        System.out.println("\t / __ \\        | |            / ____| |      | |");
        System.out.println("\t| |  | |_ __ __| | ___ _ __  | (___ | |_ __ _| |_ _   _ ___ ");
        System.out.println("\t| |  | | '__/ _` |/ _ \\ '__|  \\___ \\| __/ _` | __| | | / __|");
        System.out.println("\t| |__| | | | (_| |  __/ |     ____) | || (_| | |_| |_| \\__ \\");
        System.out.println("\t \\____/|_|  \\__,_|\\___|_|    |_____/ \\__\\__,_|\\__|\\__,_|___/");
        System.out.println(
                "      ------------------------------------------------------------------\n\n");

        System.out.print("Enter Order ID : ");
        String id = input.next();

        int index = getIndex(orderIds, id);

        String current_status = orderStatus[index].toUpperCase();

        if (search(orderIds, id)) {

            printOrderDetails(index);

            if (current_status.equals("DELIVERED")) {
                System.out.println("\n\t\tCan't change this order status, Order already delivered");
                do {
                    System.out.print("\n\nDo you want to change another order status? (y/n) : ");
                    String choice4 = input.next().toLowerCase();

                    if (choice4.equals("y")) {
                        clearConsole();
                        changeStatus();
                        return;
                    } else if (choice4.equals("n")) {
                        clearConsole();
                        mainMenu();
                        return;
                    }
                    System.out.println("\n\t\tInvalid...");
                } while (true);
            } else {
                do {
                    System.out.print("\n\nDo you want to change this order status? (y/n) : ");
                    String choice = input.next().toLowerCase();

                    if (choice.equals("y")) {

                        switch (current_status) {
                            case "PROCESSING":
                                System.out.println("\n\t\t[1] Order Delivering\n\n\t\t[2] Order Delivered");

                                L2: do {
                                    System.out.print("\n\nEnter option : ");
                                    int option = input.nextInt();

                                    switch (option) {
                                        case 1:
                                            orderStatus[index] = orderStatusSelector(DELIVERING).toLowerCase();
                                            System.out.println("\n\n\t\tStatus Updated..!");
                                            do {
                                                System.out
                                                        .print("\n\nDo you want to change another order status? (y/n) : ");
                                                String choice1 = input.next().toLowerCase();

                                                if (choice1.equals("y")) {
                                                    clearConsole();
                                                    changeStatus();
                                                    return;
                                                } else if (choice1.equals("n")) {
                                                    clearConsole();
                                                    mainMenu();
                                                    return;
                                                }
                                                System.out.println("\n\t\tInvalid...");
                                            } while (true);

                                        case 2:
                                            orderStatus[index] = orderStatusSelector(DELIVERED).toLowerCase();
                                            System.out.println("\n\n\t\tStatus Updated..!");
                                            do {
                                                System.out
                                                        .print("\n\nDo you want to change another order status? (y/n) : ");
                                                String choice2 = input.next().toLowerCase();

                                                if (choice2.equals("y")) {
                                                    clearConsole();
                                                    changeStatus();
                                                    return;
                                                } else if (choice2.equals("n")) {
                                                    clearConsole();
                                                    mainMenu();
                                                    return;
                                                }

                                                System.out.println("\n\t\tInvalid...");
                                            } while (true);

                                        default:
                                            System.out.print("\n\n\t\tInvalid..!");
                                            continue L2;
                                    }
                                } while (true);

                            case "DELIVERING":
                                System.out.println("\n\t\t[1] Order Delivered");

                                L2: do {
                                    System.out.print("\n\nEnter option : ");
                                    int option = input.nextInt();

                                    switch (option) {
                                        case 1:
                                            orderStatus[index] = orderStatusSelector(DELIVERED).toLowerCase();
                                            System.out.println("\n\n\t\tStatus Updated..!");
                                            do {
                                                System.out
                                                        .print("\n\nDo you want to change another order status? (y/n) : ");
                                                String choice3 = input.next().toLowerCase();

                                                if (choice3.equals("y")) {
                                                    clearConsole();
                                                    changeStatus();
                                                    return;
                                                } else if (choice3.equals("n")) {
                                                    clearConsole();
                                                    mainMenu();
                                                    return;
                                                }
                                                System.out.println("\n\t\tInvalid...");
                                            } while (true);

                                        default:
                                            System.out.print("\n\n\t\tInvalid..!");
                                            continue L2;
                                    }
                                } while (true);
                        }
                    } else if (choice.equals("n")) {
                        do {
                            System.out.print("\n\nDo you want to change another order status? (y/n) : ");
                            String choice5 = input.next().toLowerCase();

                            if (choice5.equals("y")) {
                                clearConsole();
                                changeStatus();
                                return;
                            } else if (choice5.equals("n")) {
                                clearConsole();
                                mainMenu();
                                return;
                            }
                            System.out.println("\n\t\tInvalid...");
                        } while (true);
                    }
                    System.out.println("\t\tInvalid...");
                } while (true);
            }
        }

        System.out.println("\n\t\tInvalid ID...");

        do {
            System.out.print("\n\nDo you want to change another order status? (y/n) : ");
            String choice = input.next().toLowerCase();

            if (choice.equals("y")) {
                clearConsole();
                changeStatus();
                return;
            } else if (choice.equals("n")) {
                clearConsole();
                mainMenu();
                return;
            }

            System.out.println("\n\t\tInvalid...");
        } while (true);

    }

}
