import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class GestorClientesComidas {
    public record Cliente(int id, String nombre, int telefono, String direccion) {}
    public record Comidas(int id, String nombre, String descripcion, int precio) {}

    private final List<Cliente> listaClientes;
    private final List<Comidas> listaComidas;
    private final Scanner scanner;

    public GestorClientesComidas() {
        this.listaClientes = new ArrayList<>();
        this.listaComidas = new ArrayList<>();
        this.scanner = new Scanner(System.in);
        inicializarListas();
    }

    private void inicializarListas() {
        listaClientes.add(new Cliente(1, "Juan Pérez", 123456789, "Calle 1 #123"));
        listaClientes.add(new Cliente(2, "María González", 987654321, "Avenida 2 #456"));

        listaComidas.add(new Comidas(1, "Paella", "Arroz con mariscos y azafrán", 1500));
        listaComidas.add(new Comidas(2, "Tacos al Pastor", "Tacos de cerdo marinado con piña", 800));
    }

    private void gestionarClientes() {
        boolean volver = false;
        while (!volver) {
            System.out.println("\n--- Gestión de Clientes ---");
            System.out.println("1. Crear cliente");
            System.out.println("2. Ver todos los clientes");
            System.out.println("3. Buscar cliente por ID");
            System.out.println("4. Actualizar cliente");
            System.out.println("5. Eliminar cliente");
            System.out.println("6. Volver al menú principal");
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1: crearClienteInteractivo(); break;
                case 2: mostrarTodosClientes(); break;
                case 3: buscarClientePorId(); break;
                case 4: actualizarClienteInteractivo(); break;
                case 5: eliminarClienteInteractivo(); break;
                case 6: volver = true; break;
                default: System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }

    private void gestionarComidas() {
        boolean volver = false;
        while (!volver) {
            System.out.println("\n--- Gestión de Comidas ---");
            System.out.println("1. Crear comida");
            System.out.println("2. Ver todas las comidas");
            System.out.println("3. Buscar comida por ID");
            System.out.println("4. Actualizar comida");
            System.out.println("5. Eliminar comida");
            System.out.println("6. Volver al menú principal");
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1: crearComidaInteractivo(); break;
                case 2: mostrarTodasComidas(); break;
                case 3: buscarComidaPorId(); break;
                case 4: actualizarComidaInteractivo(); break;
                case 5: eliminarComidaInteractivo(); break;
                case 6: volver = true; break;
                default: System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }

    private void crearClienteInteractivo() {
        System.out.print("Ingrese el ID del cliente: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer
        System.out.print("Ingrese el nombre del cliente: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese el teléfono del cliente: ");
        int telefono = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer
        System.out.print("Ingrese la dirección del cliente: ");
        String direccion = scanner.nextLine();
        Cliente nuevoCliente = new Cliente(id, nombre, telefono, direccion);
        crearCliente(nuevoCliente);
        System.out.println("Cliente creado exitosamente.");
    }

    private void mostrarTodosClientes() {
        List<Cliente> clientes = leerTodosClientes();
        if (clientes.isEmpty()) {
            System.out.println("No hay clientes registrados.");
        } else {
            System.out.println("Lista de clientes: ");
            clientes.forEach(System.out::println);
        }
    }

    private void buscarClientePorId() {
        System.out.print("Ingrese el ID del cliente a buscar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer

        Optional<Cliente> cliente = leerCliente(id);
        if (cliente.isPresent()) {
            System.out.println("Cliente encontrado: " + cliente.get());
        } else {
            System.out.println("Cliente no encontrado.");
        }
    }

    private void actualizarClienteInteractivo() {
        System.out.print("Ingrese el ID del cliente a actualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer

        Optional<Cliente> clienteExistente = leerCliente(id);
        if (clienteExistente.isPresent()) {
            System.out.print("Ingrese el nuevo nombre del cliente: ");
            String nombre = scanner.nextLine();
            System.out.print("Ingrese el nuevo teléfono del cliente: ");
            int telefono = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer
            System.out.print("Ingrese la nueva dirección del cliente: ");
            String direccion = scanner.nextLine();

            Cliente clienteActualizado = new Cliente(id, nombre, telefono, direccion);
            actualizarCliente(clienteActualizado);
            System.out.println("Cliente actualizado exitosamente.");
        } else {
            System.out.println("Cliente no encontrado.");
        }
    }

    private void eliminarClienteInteractivo() {
        System.out.print("Ingrese el ID del cliente a eliminar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer

        if (eliminarCliente(id)) {
            System.out.println("Cliente eliminado exitosamente.");
        } else {
            System.out.println("Cliente no encontrado.");
        }
    }

    private void crearComidaInteractivo() {
        System.out.print("Ingrese el ID de la comida: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer
        System.out.print("Ingrese el nombre de la comida: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese la descripción de la comida: ");
        String descripcion = scanner.nextLine();
        System.out.print("Ingrese el precio de la comida: ");
        int precio = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer

        Comidas nuevaComida = new Comidas(id, nombre, descripcion, precio);
        crearComida(nuevaComida);
        System.out.println("Comida creada exitosamente.");
    }

    private void mostrarTodasComidas() {
        List<Comidas> comidas = leerTodasComidas();
        if (comidas.isEmpty()) {
            System.out.println("No hay comidas registradas.");
        } else {
            System.out.println("Lista de comidas: ");
            comidas.forEach(System.out::println);
        }
    }

    private void buscarComidaPorId() {
        System.out.print("Ingrese el ID de la comida a buscar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer

        Optional<Comidas> comida = leerComida(id);
        if (comida.isPresent()) {
            System.out.println("Comida encontrada: " + comida.get());
        } else {
            System.out.println("Comida no encontrada.");
        }
    }

    private void actualizarComidaInteractivo() {
        System.out.print("Ingrese el ID de la comida a actualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer

        Optional<Comidas> comidaExistente = leerComida(id);
        if (comidaExistente.isPresent()) {
            System.out.print("Ingrese el nuevo nombre de la comida: ");
            String nombre = scanner.nextLine();
            System.out.print("Ingrese la nueva descripción de la comida: ");
            String descripcion = scanner.nextLine();
            System.out.print("Ingrese el nuevo precio de la comida: ");
            int precio = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            Comidas comidaActualizada = new Comidas(id, nombre, descripcion, precio);
            actualizarComida(comidaActualizada);
            System.out.println("Comida actualizada exitosamente.");
        } else {
            System.out.println("Comida no encontrada.");
        }
    }

    private void eliminarComidaInteractivo() {
        System.out.print("Ingrese el ID de la comida a eliminar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer

        if (eliminarComida(id)) {
            System.out.println("Comida eliminada exitosamente.");
        } else {
            System.out.println("Comida no encontrada.");
        }
    }

    // Métodos para gestionar clientes
    private void crearCliente(Cliente cliente) {
        listaClientes.add(cliente);
    }

    private List<Cliente> leerTodosClientes() {
        return new ArrayList<>(listaClientes);
    }

    private Optional<Cliente> leerCliente(int id) {
        return listaClientes.stream().filter(c -> c.id() == id).findFirst();
    }

    private void actualizarCliente(Cliente clienteActualizado) {
        for (int i = 0; i < listaClientes.size(); i++) {
            if (listaClientes.get(i).id() == clienteActualizado.id()) {
                listaClientes.set(i, clienteActualizado);
                break;
            }
        }
    }

    private boolean eliminarCliente(int id) {
        return listaClientes.removeIf(c -> c.id() == id);
    }

    // Métodos para gestionar comidas
    private void crearComida(Comidas comida) {
        listaComidas.add(comida);
    }

    private List<Comidas> leerTodasComidas() {
        return new ArrayList<>(listaComidas);
    }

    private Optional<Comidas> leerComida(int id) {
        return listaComidas.stream().filter(c -> c.id() == id).findFirst();
    }

    private void actualizarComida(Comidas comidaActualizada) {
        for (int i = 0; i < listaComidas.size(); i++) {
            if (listaComidas.get(i).id() == comidaActualizada.id()) {
                listaComidas.set(i, comidaActualizada);
                break;
            }
        }
    }

    private boolean eliminarComida(int id) {
        return listaComidas.removeIf(c -> c.id() == id);
    }

    public void iniciarInteraccion() {
        boolean salir = false;
        while (!salir) {
            System.out.println("\n--- Menú Principal ---");
            System.out.println("1. Gestionar Clientes");
            System.out.println("2. Gestionar Comidas");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1: gestionarClientes(); break;
                case 2: gestionarComidas(); break;
                case 3: salir = true; break;
                default: System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
        System.out.println("¡Gracias por usar el sistema!");
    }
}
