import java.io.*;
import java.util.*;

class BackupData implements Serializable {
    String name;
    String data;

    BackupData(String name, String data) {
        this.name = name;
        this.data = data;
    }

    void display() {
        System.out.println("Name: " + name);
        System.out.println("Data: " + data);
    }
}

public class DataBackupSystem {

    static Scanner sc = new Scanner(System.in);
    static final String FILE_NAME = "backup.dat";

    public static void main(String[] args) {

        int choice;

        do {
            System.out.println("\n=== DATA BACKUP SYSTEM ===");
            System.out.println("1. Create Backup");
            System.out.println("2. Restore Backup");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1: createBackup(); break;
                case 2: restoreBackup(); break;
                case 3: System.out.println("Thank you!"); break;
                default: System.out.println("Invalid choice!");
            }

        } while (choice != 3);
    }

    static void createBackup() {
        try {
            System.out.print("Enter backup name: ");
            String name = sc.nextLine();
            System.out.print("Enter data: ");
            String data = sc.nextLine();

            BackupData backup = new BackupData(name, data);

            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME));
            oos.writeObject(backup);
            oos.close();

            System.out.println("✅ Backup saved!");
        } catch (Exception e) {
            System.out.println("Error saving backup!");
        }
    }

    static void restoreBackup() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME));
            BackupData backup = (BackupData) ois.readObject();
            ois.close();

            System.out.println("✅ Backup restored:");
            backup.display();
        } catch (Exception e) {
            System.out.println("No backup found!");
        }
    }
}