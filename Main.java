import java.io.*;
import java.util.*;

class Computer {
    private String processor;
    private int ram; 
    private int storage; 

    public Computer(String processor, int ram, int storage) {
        this.processor = processor;
        this.ram = ram;
        this.storage = storage;
    }

    @Override
    public String toString() {
        return "Computer [Processor=" + processor + ", RAM=" + ram + "GB, Storage=" + storage + "GB]";
    }
}

class ComputerBuilder {
    private String processor;
    private int ram;
    private int storage;

    public ComputerBuilder setProcessor(String processor) {
        this.processor = processor;
        return this;
    }

    public ComputerBuilder setRAM(int ram) {
        this.ram = ram;
        return this;
    }

    public ComputerBuilder setStorage(int storage) {
        this.storage = storage;
        return this;
    }

    public Computer build() {
        return new Computer(processor, ram, storage);
    }
}

class ComputerSingleton {
    private static ComputerSingleton instance;
    private List<Computer> computers;

    private ComputerSingleton() {
        computers = new ArrayList<>();
    }

    public static ComputerSingleton getInstance() {
        if (instance == null) {
            instance = new ComputerSingleton();
        }
        return instance;
    }

    public void addComputer(Computer computer) {
        computers.add(computer);
    }

    public List<Computer> getComputers() {
        return computers;
    }
}

public class Main {
    public static void main(String[] args) {
        // interfaz básica de texto
        Scanner scanner = new Scanner(System.in);
        ComputerSingleton computerManager = ComputerSingleton.getInstance();

        while (true) {
            System.out.println("Configurador de Computadoras Personalizadas");
            System.out.print("Ingrese el procesador: ");
            String processor = scanner.nextLine();
            System.out.print("Ingrese la RAM (GB): ");
            int ram = scanner.nextInt();
            System.out.print("Ingrese el almacenamiento (GB): ");
            int storage = scanner.nextInt();
            scanner.nextLine(); // Consumir la nueva línea

            Computer computer = new ComputerBuilder()
                    .setProcessor(processor)
                    .setRAM(ram)
                    .setStorage(storage)
                    .build();

            computerManager.addComputer(computer);

            System.out.print("¿Desea agregar otra computadora? (s/n): ");
            String option = scanner.nextLine();
            if (!option.equalsIgnoreCase("s")) {
                break;
            }
        }

        System.out.println("\nComputadoras configuradas:");
        for (Computer comp : computerManager.getComputers()) {
            System.out.println(comp);
        }
    }
}
