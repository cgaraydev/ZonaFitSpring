package camilo.zona_fit;

import camilo.zona_fit.modelo.Cliente;
import camilo.zona_fit.servicio.IClienteServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class ZonaFitApplication implements CommandLineRunner {

    @Autowired
    private IClienteServicio clienteServicio;

    private static final Logger logger = LoggerFactory.getLogger(ZonaFitApplication.class);

    String nl = System.lineSeparator();

    public static void main(String[] args) {

        logger.info("Iniciando la Aplicación");

        SpringApplication.run(ZonaFitApplication.class, args);

        logger.info("Finalizando la Aplicación");

    }

    @Override
    public void run(String... args) throws Exception {
        zonaFitApp();
    }

    private void zonaFitApp() {
        logger.info(nl + "Aplicación Zona Fit ejecutandose ..." + nl);

        var salir = false;
        var sc = new Scanner(System.in);
        while (!salir) {
            var opcion = mostrarMenu(sc);
            salir = ejecutarOpcion(sc, opcion);
            logger.info(nl);
        }

    }

    private boolean ejecutarOpcion(Scanner sc, int opcion) {
        var salir = false;
        switch (opcion) {
            case 1 -> {
                logger.info(nl + "Listado de Clientes" + nl);
                List<Cliente> clientes = clienteServicio.listarClientes();
                clientes.forEach(cliente -> logger.info(cliente.toString() + nl));
            }
        }
        return salir;
    }

    private Object mostrarMenu(Scanner sc) {
        logger.info(nl + """
                1. Listar Clientes
                2. Buscar Cliente por Id
                3. Guardar Cliente
                4. Modificar Cliente
                5. Eliminar Cliente
                6. Salir                
                Elija una opción:\s""");
        return Integer.parseInt(sc.nextLine());
    }
}
