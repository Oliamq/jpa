package com.cibertec;

import java.time.LocalDate;
import java.util.Scanner;

import com.cibertec.sysmovie.util.JPAUtil;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import org.h2.tools.Server;

import com.cibertec.sysmovie.model.clientes;
import com.cibertec.sysmovie.model.alquileres;
import com.cibertec.sysmovie.model.peliculas;
import com.cibertec.sysmovie.model.detalle_alquiler;
import com.cibertec.sysmovie.model.DetalleAlquilerId;
import com.cibertec.sysmovie.model.EstadoAlquiler;

/**
 * Hello world!
 */
public class App {
    private static Server h2Server;

    private static Server iniciarServidorH2() {
        try {
            Server webServer = Server.createWebServer("-web", "-webAllowOthers", "-webPort", "8082").start();
            System.out.println(" H2 Web Console disponible en: http://localhost:8082");
            System.out.println("Conéctate con:");
            System.out.println("JDBC URL: jdbc:h2:mem:BD2_MITMA");
            System.out.println("Usuario: oliver");
            System.out.println("Contraseña: (dejar vacío)");
            return webServer;
        } catch (java.sql.SQLException e) {
            System.err.println("Error al iniciar el servidor H2");
            e.printStackTrace();
            return null;
        }
    }

    private static void pausar(Scanner scanner) {
        System.out.print("Presiona ENTER para continuar...");
        scanner.nextLine();
    }

    private static void stopH2Server() {
        if (h2Server != null) {
            h2Server.stop();
        }
    }

    public static void main(String[] args) {
        try {
            // 1. Iniciar el servidor H2 para poder acceder a la consola web
            Server webServer = iniciarServidorH2();
            Scanner scanner = new Scanner(System.in);

            // Obtenemos un EntityManager
            EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();

            // --- CRUD ---
            // Teoría: Una Transacción es una operación "todo o nada". O se ejecutan
            // todas las operaciones dentro de ella, o no se ejecuta ninguna.
            // Es esencial para mantener la integridad de los datos.
            EntityTransaction tx = em.getTransaction();

            // CREATE
            // CREATE - Insertar 3 clientes
            tx.begin();
            System.out.println("\n--- Insertando 3 clientes de prueba ---");

            clientes cliente1 = new clientes("Alex", "alex_12@gmail.com");
            clientes cliente2 = new clientes("Brenda", "brenda23@hotmail.com");
            clientes cliente3 = new clientes("Carlos", "carlos.dev@gmail.com");
            peliculas pelicula1 = new peliculas("ScaryMovie","Comedia",5);
            peliculas pelicula2 = new peliculas("Senior de los anillos","Accion",8);
            peliculas pelicula3 = new peliculas("Venom","Accion",10);

            em.persist(cliente1);
            em.persist(cliente2);
            em.persist(cliente3);
            em.persist(pelicula1);
            em.persist(pelicula2);
            em.persist(pelicula3);
            tx.commit();

            System.out.println("Clientes creados:");
            System.out.println(" - ID " + cliente1.getId_cliente() + ": " + cliente1.getNombre());
            System.out.println(" - ID " + cliente2.getId_cliente() + ": " + cliente2.getNombre());
            System.out.println(" - ID " + cliente3.getId_cliente() + ": " + cliente3.getNombre());

            System.out.println("Peliculas creados:");
            System.out.println(" - ID " + pelicula1.getId_pelicula() + ": " + pelicula1.getTitulo());
            System.out.println(" - ID " + pelicula2.getId_pelicula() + ": " + pelicula2.getTitulo());
            System.out.println(" - ID " + pelicula3.getId_pelicula() + ": " + pelicula3.getTitulo());


            // PAUSA PARA VER LA BD
            pausar(scanner);
            int opcion;
            do {
            mostrarMenu();
            opcion = scanner.nextInt();
            scanner.nextLine(); // limpiar buffer

            switch (opcion) {
                case 1:
                    listarClientes(em);
                    break;
                case 2:
                    listarPeliculas(em);
                    break;
                case 3:
                    registrarCliente(em, scanner);
                    break;
                case 4:
                    registrarAlquiler(em, scanner);
                    break;
                case 5:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
                }

                if (opcion != 5) pausar(scanner);
            } while (opcion != 5);



        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Cierra la conexión a la BD y el servidor H2
            JPAUtil.shutdown();
            stopH2Server();
            System.out.println("\n>>> APLICACIÓN FINALIZADA <<<");
        }
    }


    private static void listarClientes(EntityManager em) {  
        System.out.println("\n\n--- Lista de Clientes ---");
        em.createQuery("FROM clientes", clientes.class)
            .getResultList()
            .forEach(c -> System.out.println(c.getId_cliente() + ": " + c.getNombre()));
    }

    private static void listarPeliculas(EntityManager em) { 
        System.out.println("\n\n--- Lista de Películas ---");
        em.createQuery("FROM peliculas", peliculas.class)
            .getResultList()
            .forEach(p -> System.out.println(p.getId_pelicula() + ": " + p.getTitulo()));
    }

    private static void registrarCliente(EntityManager em, Scanner scanner) {  

        System.out.print("Nombre del cliente: ");
        String nombre = scanner.nextLine();
        System.out.print("Email del cliente: ");
        String email = scanner.nextLine();

        EntityTransaction tx = em.getTransaction();

        
        tx.begin();
        clientes nuevoCliente = new clientes(nombre, email);
        em.persist(nuevoCliente);
        tx.commit();

        System.out.println("Cliente registrado con ID: " + nuevoCliente.getId_cliente());
        pausar(scanner);
    }

    private static void registrarAlquiler(EntityManager em, Scanner scanner) {
        listarClientes(em);
        System.out.print("Ingrese ID del cliente: ");
        int idCliente = scanner.nextInt();

        listarPeliculas(em);
        System.out.print("Ingrese ID de la película: ");
        int idPelicula = scanner.nextInt();

        clientes cliente = em.find(clientes.class, idCliente);
        peliculas pelicula = em.find(peliculas.class, idPelicula);

        if (cliente == null || pelicula == null) {
            System.out.println("Cliente o Película no encontrados.");
            return;
        }

        alquileres alquiler = new alquileres(
            LocalDate.now(), // fecha actual
            cliente,
            2, 
            EstadoAlquiler.ACTIVO 
            );
        detalle_alquiler detalle = new detalle_alquiler(alquiler,pelicula,1 );

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(alquiler);
        em.persist(detalle);
        tx.commit();

        System.out.println("Alquiler registrado exitosamente.\n\n");

        pausar(scanner);
    }

    private static void mostrarMenu() {
        System.out.print("\u001B[H\u001B[2J");
        System.out.flush();
        System.out.println("\n--- MENÚ SISTEMA DE ALQUILER ---");
        System.out.println("1. Listar clientes");
        System.out.println("2. Listar películas");
        System.out.println("3. Registrar nuevo cliente");
        System.out.println("4. Registrar nuevo alquiler");
        System.out.println("5. Salir");

        System.out.print("Seleccione una opción: ");
    }
}

