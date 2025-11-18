import java.util.*;

public class PetAdoptionSystem {
    public static void register(Scanner sc) {
        System.out.print("Enter username: ");
        String username = sc.nextLine();
        System.out.print("Enter password: ");
        String password = sc.nextLine();
        System.out.print("Enter role (admin/volunteer): ");
        String role = sc.nextLine();
        FileHandler.saveUser(username, password, role);
        System.out.println("Registration successful!");
    }

    public static boolean login(Scanner sc) {
        System.out.print("Username: ");
        String username = sc.nextLine();
        System.out.print("Password: ");
        String password = sc.nextLine();
        if (FileHandler.checkLogin(username, password)) {
            System.out.println("Login successful!");
            return true;
        }
        System.out.println("Login failed.");
        return false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        AdoptionManager manager = new AdoptionManager();

        System.out.println("--- Welcome to Pet Adoption System ---");

        while (true) {
            System.out.println("\n1. Register");
            System.out.println("2. Login");
            System.out.println("0. Exit");
            System.out.print("Choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            if (choice == 1) {
                register(sc);
            } else if (choice == 2) {
                if (login(sc)) break;
            } else if (choice == 0) {
                return;
            } else {
                System.out.println("Invalid option.");
            }
        }

        while (true) {
            System.out.println("\n--- Pet Adoption Management ---");
            System.out.println("1. Add Pet");
            System.out.println("2. Remove Pet");
            System.out.println("3. Add Adopter");
            System.out.println("4. Process Adoption");
            System.out.println("5. List Pets");
            System.out.println("6. Foster Care");
            System.out.println("7. Make Donation");
            System.out.println("0. Logout");
            System.out.print("Enter choice: ");
            int ch = sc.nextInt();
            sc.nextLine();

            switch (ch) {
                case 1:
                    System.out.print("Pet type (Dog/Cat): ");
                    String type = sc.nextLine();
                    System.out.print("Pet name: ");
                    String name = sc.nextLine();
                    System.out.print("Health record: ");
                    String health = sc.nextLine();
                    if (type.equalsIgnoreCase("Dog"))
                        manager.addPet(new Dog(name, health));
                    else if (type.equalsIgnoreCase("Cat"))
                        manager.addPet(new Cat(name, health));
                    else
                        System.out.println("Invalid pet type.");
                    break;

                case 2:
                    System.out.print("Enter pet name to remove: ");
                    String removeName = sc.nextLine();
                    manager.removePet(removeName);
                    break;

                case 3:
                    System.out.print("Adopter name: ");
                    String adopterName = sc.nextLine();
                    System.out.print("Contact: ");
                    String contact = sc.nextLine();
                    manager.addAdopter(new Adopter(adopterName, contact));
                    break;

                case 4:
                    System.out.print("Pet name to adopt: ");
                    String petName = sc.nextLine();
                    System.out.print("Adopter name: ");
                    String adopter = sc.nextLine();
                    manager.processAdoption(petName, adopter);
                    break;

                case 5:
                    manager.listPets();
                    break;

                case 6:
                    System.out.print("Pet name for foster care: ");
                    String fosterPet = sc.nextLine();
                    System.out.println(FosterCare.assignFosterHome(new Pet(fosterPet, "Unknown", "Healthy")));
                    break;

                case 7:
                    System.out.print("Donor name: ");
                    String donor = sc.nextLine();
                    System.out.print("Donation amount: ");
                    double amount = sc.nextDouble();
                    Donation.donate(donor, amount);
                    break;

                case 0:
                    System.out.println("Logged out.");
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
