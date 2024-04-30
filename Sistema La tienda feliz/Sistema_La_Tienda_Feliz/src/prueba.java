import java.util.Scanner;

public class prueba {
    // Declarar la matriz de productos y una variable para contar el número de productos
    static String[][] productos = new String[100][4];
    static int numProductos = 0;
    static int codigoProducto = 1000; // Código base para los productos

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean continuar = true;

        System.out.println("<-----Sistema de Gestión de Inventario La Tienda Feliz----->");

        while (continuar) {
            Menu();
            int opt = sc.nextInt();

            switch (opt) {
                case 1:
                    AgregarProducto(sc); 
                    break;
                case 2:
                    ActualizarProducto(sc); 
                case 3:
                    BuscarProducto(sc); 
                    break;
                case 4:
                    EliminarProducto(sc); 
                    break;
                case 5:
                    MostrarInventario(); 
                    break;
                case 6:
                    continuar = false;
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, elija una opción válida.");
            }
        }

        System.out.println("¡Gracias por usar el sistema de gestión de inventario!");
        sc.close();
    }

    public static void Menu() {
        System.out.println("\n<----------Menú Principal---------->");
        System.out.println("1. Agregar producto");
        System.out.println("2. Actualizar producto");
        System.out.println("3. Buscar producto");
        System.out.println("4. Eliminar producto");
        System.out.println("5. Mostrar inventario");
        System.out.println("6. Salir");
        System.out.print("\n¿Qué desea realizar?: ");
    }

    public static void AgregarProducto(Scanner sc) {
        if (numProductos < 100) {
            System.out.print("Ingrese el nombre del producto: ");
            String nombre = sc.next();
            System.out.print("Ingrese el precio del producto: ");
            String precio = sc.next();
            System.out.print("Ingrese la cantidad disponible del producto: ");
            String cantidad = sc.next();

            // Generar codigo unico
            String codigo = "P" + codigoProducto;

            productos[numProductos][0] = codigo;
            productos[numProductos][1] = nombre;
            productos[numProductos][2] = precio;
            productos[numProductos][3] = cantidad;

            numProductos++;
            codigoProducto++;

            System.out.println("¡Producto agregado correctamente!");
        } else {
            System.out.println("El inventario está lleno. No se pueden agregar más productos.");
        }
    }

    public static void ActualizarProducto(Scanner sc) {
        if (numProductos == 0) {
            System.out.println("No hay productos en el inventario para actualizar.");
            return;
        }

        //pedir al usuario el codigo del producto
        System.out.print("Ingrese el código del producto que desea actualizar: ");
        String codigoProducto = sc.next();

        // Buscar producto en el inventario
        int indiceProducto = buscarProductoPorCodigo(codigoProducto, 0);

        if (indiceProducto == -1) {
            System.out.println("El producto con el código " + codigoProducto + " no se encontró en el inventario.");
            return;
        }

        // Solicitar al usuario los nuevos datos para el producto seleccionado
        System.out.print("Ingrese el nuevo nombre del producto: ");
        String nombre = sc.next();
        System.out.print("Ingrese el nuevo precio del producto: ");
        String precio = sc.next();
        System.out.print("Ingrese la nueva cantidad disponible del producto: ");
        String cantidad = sc.next();

        // Actualizar los datos del producto en la matriz de productos
        productos[indiceProducto][1] = nombre;
        productos[indiceProducto][2] = precio;
        productos[indiceProducto][3] = cantidad;

        System.out.println("¡Producto actualizado correctamente!");
    }

    public static void BuscarProducto(Scanner sc) {
        if (numProductos == 0) {
            System.out.println("No hay productos en el inventario para buscar.");
            return;
        }

        // Solicitar al usuario el código del producto que desea buscar
        System.out.print("Ingrese el código del producto que desea buscar: ");
        String codigoProducto = sc.next();

        // Buscar el producto en el inventario
        int indiceProducto = buscarProductoPorCodigo(codigoProducto, 0);

        if (indiceProducto == -1) {
            System.out.println("El producto con el código " + codigoProducto + " no se encontró en el inventario.");
        } else {
            System.out.println("Producto encontrado:");
            System.out.println("Código: " + productos[indiceProducto][0]);
            System.out.println("Nombre: " + productos[indiceProducto][1]);
            System.out.println("Precio: " + productos[indiceProducto][2]);
            System.out.println("Cantidad: " + productos[indiceProducto][3]);
        }
    }

    public static void EliminarProducto(Scanner sc) {
        if (numProductos == 0) {
            System.out.println("No hay productos en el inventario para eliminar.");
            return;
        }

        // Solicitar al usuario el código del producto que desea eliminar
        System.out.print("Ingrese el código del producto que desea eliminar: ");
        String codigoProducto = sc.next();

        // Buscar el producto en el inventario
        int indiceProducto = buscarProductoPorCodigo(codigoProducto, 0);

        if (indiceProducto == -1) {
            System.out.println("El producto con el código " + codigoProducto + " no se encontró en el inventario.");
        } else {
            // Eliminar el producto del inventario moviendo los productos restantes hacia arriba
            for (int i = indiceProducto; i < numProductos - 1; i++) {
                productos[i] = productos[i + 1];
            }
            numProductos--;

            System.out.println("¡Producto eliminado correctamente!");
        }
    }

    public static void MostrarInventario() {
        if (numProductos == 0) {
            System.out.println("No hay productos en el inventario.");
            return;
        }

        System.out.println("\n--- Inventario ---");
        for (int i = 0; i < numProductos; i++) {
            System.out.println("Código: " + productos[i][0]);
            System.out.println("Nombre: " + productos[i][1]);
            System.out.println("Precio: " + productos[i][2]);
            System.out.println("Cantidad: " + productos[i][3]);
            System.out.println("------------------");
        }
    }

    public static int buscarProductoPorCodigo(String codigo, int indiceActual) {
        // Caso base: si llegamos al final de la matriz de productos y no se encuentra el código
        if (indiceActual >= numProductos) {
            return -1;
        }

        // Comparar el código del producto en el índice actual con el código buscado
        if (productos[indiceActual][0].equals(codigo)) {
            return indiceActual; // Producto encontrado
        }

        // Llamada recursiva para buscar en la siguiente fila de productos
        return buscarProductoPorCodigo(codigo, indiceActual + 1);
    }


}
