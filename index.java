                     package Clinica2;


	import java.io.*;
	import java.util.*;

	public class index {
	    private Map<String, Doctor> doctores;
	    private Map<String, Paciente> pacientes;
	    private Map<String, Cita> citas;
	    private Set<String> administradores;

	    public index () {
	        this.doctores = new HashMap<>();
	        this.pacientes = new HashMap<>();
	        this.citas = new HashMap<>();
	        this.administradores = new HashSet<>(Arrays.asList("admin")); // Agregar identificadores de administradores
	    }

	    public void load(String filename) {
	        File file = new File(filename);
	        if (!file.exists()) {
	            System.out.println("El archivo no existe. Se creará un nuevo archivo al guardar los datos.");
	            return;
	        }
	        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
	            String line;
	            while ((line = reader.readLine()) != null) {
	                String[] parts = line.split(",");
	                if (parts.length == 3) {
	                	
	                    // Asumimos que el archivo tiene un formato específico que permite distinguir entre doctores y pacientes
	                    if (parts[0].startsWith("D")) {
	                        doctores.put(parts[0], new Doctor(parts[0], parts[1], parts[2]));
	                    } else if (parts[0].startsWith("P")) {
	                        pacientes.put(parts[0], new Paciente(parts[0], parts[1]));
	                    }
	                }  else if (parts.length == 5) { // Cambiado a 5 para incluir todos los campos de Cita
	                    citas.put(parts[0], new Cita(parts[0], parts[1], parts[2], parts[3], parts[4]));
	                }
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	    public void save(String filename) {
	        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
	            for (Doctor doctor : doctores.values()) {
	                writer.write(doctor.toCSV());
	                writer.newLine();
	            }
	            for (Paciente paciente : pacientes.values()) {
	                writer.write(paciente.toCSV());
	                writer.newLine();
	            }
	            for (Cita cita : citas.values()) {
	                writer.write(cita.toCSV());
	                writer.newLine();
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	    public void list() {
	        System.out.println("Doctores:");
	        for (Doctor doctor : doctores.values()) {
	            System.out.println(doctor);
	        }
	        System.out.println("\nPacientes:");
	        for (Paciente paciente : pacientes.values()) {
	            System.out.println(paciente);
	        }
	        System.out.println("\nCitas:");
	        for (Cita cita : citas.values()) {
	            System.out.println(cita);
	        }
	    }

	    public void createDoctor(String id, String nombre, String especialidad) {
	        doctores.put(id, new Doctor(id, nombre, especialidad));
	    }

	    public void createPaciente(String id, String nombre) {
	        pacientes.put(id, new Paciente(id, nombre));
	    }

	    public void createCita(String id, String doctorId, String pacienteId, String fechaHora, String motivo) {
	        if (doctores.containsKey(doctorId) && pacientes.containsKey(pacienteId)) {
	            citas.put(id, new Cita(id, doctorId, pacienteId, fechaHora, motivo));
	        } else {
	            System.out.println("Error: Doctor o paciente no encontrado.");
	        }
	    }

	    public boolean autenticarAdministrador(String id) {
	        return administradores.contains(id);
	    }

	    public static void main(String[] args) {
	        Consultorio consultorio = new Consultorio();
	        consultorio.load("datos.csv");
	        
	        Scanner scanner = new Scanner(System.in);
	        String adminId = "";
	        boolean accesoPermitido = false;
	        
	  
	        while (true) {
	            System.out.println("\nOpciones del consultorio de citas:");
	            System.out.println("1. Alta de doctor");
	            System.out.println("2. Alta de paciente");
	            System.out.println("3. Crear cita");
	            System.out.println("4. ***Listar datos");
	            System.out.println("5. *******Salir");
	            System.out.print("Seleccione una opción: ");
	            int choice = scanner.nextInt();
	            scanner.nextLine();

	            switch (choice) {
	                case 1:
	                    System.out.print("ID del doctor: ");
	                    String doctorId = scanner.nextLine();
	                    System.out.print("Nombre completo del doctor: ");
	                    String doctorNombre = scanner.nextLine();
	                    System.out.print("Especialidad: ");
	                    String especialidad = scanner.nextLine();
	                    consultorio.createDoctor(doctorId, doctorNombre, especialidad);
	                    break;
	                case 2:
	                    System.out.print("ID del paciente: ");
	                    String pacienteId = scanner.nextLine();
	                   System.out.print("Nombre completo del paciente: ");
	                    String pacienteNombre = scanner.nextLine();
	                    consultorio.createPaciente(pacienteId, pacienteNombre);
	                    break;
	                case 3:
	                    System.out.print("ID de la cita: ");
	                    String citaId = scanner.nextLine();
	                    System.out.print("ID del doctor: ");
	                    String doctorIdCita = scanner.nextLine();
	                    System.out.print("ID del paciente: ");
	                    String pacienteIdCita = scanner.nextLine();
	                    System.out.print("Fecha y hora de la cita: ");
	                    String fechaHora = scanner.nextLine();
	                    System.out.print("Motivo de cita: ");
	                    String motivo = scanner.nextLine();
	                    consultorio.createCita(citaId, doctorIdCita, pacienteIdCita, fechaHora, motivo);
	                    break;
	                case 4:
	                    consultorio.list();
	                    break;
	                case 5:
	                    consultorio.save("datos.txt");
	                    System.out.println("Datos guardados. Saliendo...");
	                    return;
	                default:
	                    System.out.println("Opción inválida. Inténtelo de nuevo.");
	            }
	        }
	    }
	}

	class Doctor {
	    private String id;
	    private String nombre;
	    private String especialidad;

	    public Doctor(String id, String nombre, String especialidad) {
	        this.id = id;
	        this.nombre = nombre;
	        this.especialidad = especialidad;
	    }

	    public String toCSV() {
	        return id + "," + nombre + "," + especialidad;
	    }

	    @Override
	    public String toString() {
	        return "ID: " + id + ", Nombre: " + nombre + ", Especialidad: " + especialidad;
	    }
	}

	class Paciente {
	    private String id;
	    private String nombre;

	    public Paciente(String id, String nombre) {
	        this.id = id;
	        this.nombre = nombre;
	    }

	    public String toCSV() {
	        return id + "," + nombre;
	    }

	    @Override
	    public String toString() {
	        return "ID: " + id + ", Nombre: " + nombre;
	    }
	}

	class Cita {
	    private String id;
	    private String doctorId;
	    private String pacienteId;
	    private String fechaHora;
	    private String motivo;

	    public Cita(String id, String doctorId, String pacienteId, String fechaHora, String motivo) {
	        this.id = id;
	        this.doctorId = doctorId;
	        this.pacienteId = pacienteId;
	        this.fechaHora = fechaHora;
	        this.motivo = motivo;
	    }

	    Cita(String part, String part0, String part1, String part2) {
	        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
	    }

	    public String toCSV() {
	        return id + "," + doctorId + "," + pacienteId + "," + fechaHora + "," + motivo;
	    }

	    @Override
	    public String toString() {
	     return "ID: " + id + ", Doctor ID: " + doctorId + ", Paciente ID: " + pacienteId + ", Fecha y Hora: " + fechaHora + ", Motivo: " + motivo ;}
	}
	                      
	
	
	
	
	