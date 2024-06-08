              /*package Clinica2;

import java.io.*;
import java.util.*;

public class AddressBook {
    private Map<String, String> citas;

    public AddressBook() {
        this.citas = new HashMap<>();
    }

    public void load(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                	citas.put(parts[0], parts[1]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void save(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Map.Entry<String, String> entry : citas.entrySet()) {
                writer.write(entry.getKey() + "," + entry.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void list() {
        System.out.println("Citas:");
        for (Map.Entry<String, String> entry : citas.entrySet()) {
            System.out.println("doctor:" + entry.getKey() + ":" + entry.getValue());
           
        }
    }

    public void create(String citasClinica, String name) {
    	citas.put(citasClinica, name);
    }

    public void delete(String citasClinica) {
    	citas.remove(citasClinica);
    }

    public static void main(String[] args) {
        AddressBook addressBook = new AddressBook();
        addressBook.load("citas.txt");

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println(" \n alta doctor y alta pasiente :");
            System.out.println("1. pasiente nuevo");
            System.out.println("2. datosDoctor");
            System.out.println("3. crear cita");
            System.out.println("4. **Salir");
            System.out.print("crear pasiente:" );
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
              
                case 1:
                    System.out.print("nombre pasiente ");
                    String pasienteNuevo = scanner.nextLine();
                    System.out.print("padecimiento ");
                    String name = scanner.nextLine();
                    System.out.print("numeroID pasiente: ");
                    String pasienteNuevo1 = scanner.nextLine();
                    addressBook.create(pasienteNuevo1, name);
                    break;
                case 2:
                	 System.out.print("nombre doctor ");
                     String datosDoctor = scanner.nextLine();
                     System.out.print("especialidad");
                     String name1 = scanner.nextLine();
                     System.out.print("numeroID de doctor: ");
                     String datosDoctor1 = scanner.nextLine();
                     addressBook.create(datosDoctor, name1);
                    break;
                case 3:
                	 System.out.print("identificadorUnico ");
                     String identificadorUnico = scanner.nextLine();
                     System.out.print("fecha y hora");
                     String name11 = scanner.nextLine();
                     System.out.print("motivo de cita: ");
                     String identificadorUnico1 = scanner.nextLine();
                     addressBook.create(identificadorUnico, name11);
                    break;
                case 4:
                    addressBook.list();
               
                    return;
                default:
                    System.out.println("Opción inválida. Inténtalo de nuevo.");
            }
        }
    }
}          */
