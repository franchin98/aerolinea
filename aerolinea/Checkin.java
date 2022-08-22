package aerolinea;

import java.util.Scanner;

public class Checkin {

    private static Scanner teclado = new Scanner(System.in);

    public static void main(String[] args) {

	System.out.println("Bienvenido a Aerolineas Argentinas.");
	int CantidadPasajero = 192;

	Vuelo vuelo = new Vuelo("Buenos Aires", "Bariloche", CantidadPasajero);

	int opcion = 0;

	do {
	    opcion = seleccionarOpcion();
	    switch (opcion) {
	    case 1:
		System.out.println(vuelo.toString());
		registrarPasajero(vuelo);
		break;
	    case 2:
		System.out.println(vuelo.toString());
		elegirAsiento(vuelo);
		break;
	    case 3:
		System.out.println(vuelo.toString());
		listarPasajeros(vuelo);
		break;
	    case 9:
		System.out.println("\n----------------------------");
		System.out.println("HA SALIDO DEL SISTEMA.");
		System.out.println("----------------------------");
		break;
	    default:
		System.out.println("Opción Invalida");
		break;
	    }
	} while (opcion != 9);

    }

    private static int seleccionarOpcion() {

	int opcionSeleccionada = 0;

	System.out.println("\n************************");
	System.out.println("Menu de opciones\n");
	System.out.println("1 - Registrar pasajero");
	System.out.println("2 - Elegir Asiento");
	System.out.println("3 - Listar pasajeros");
	System.out.println("9 - Salir");
	System.out.println("************************");
	System.out.print("Ingrese una opción: ");
	opcionSeleccionada = teclado.nextInt();

	return opcionSeleccionada;
    }

    private static void registrarPasajero(Vuelo actual) {
	/*
	 * Interfaz de usuario encargada de cargar un pasajero al vuelo. Debe ingresar
	 * los datos necesarios e informar por pantalla el resultado de la operación
	 */

	String nombre, apellido;
	int dni;

	System.out.println("\n*****************************************");
	System.out.println("\tREGISTRO DE PASAJEROS");
	System.out.println("*****************************************");

	System.out.print("\nINGRESE EL DNI DEL PASAJERO: ");
	dni = teclado.nextInt();

	teclado.nextLine();
	System.out.print("INGRESE EL NOMBRE: ");
	nombre = teclado.nextLine();

	System.out.print("INGRESE EL APELLIDO: ");
	apellido = teclado.nextLine();

	Pasajero nuevoPasajero = new Pasajero(dni, nombre, apellido);

	if (actual.agregarPasajero(nuevoPasajero)) {
	    System.out.println("\n*********************************************");
	    System.out.println("\tPASAJERO AGREGADO CON ÉXITO.");
	    System.out.println("*********************************************");
	} else {
	    System.out.println("\n*********************************************");
	    System.out.println("\tNO SE PUEDE AGREGAR AL PASAJERO." + "\n\t\tLISTA LLENA.");
	    System.out.println("*********************************************");
	}

    }

    private static void elegirAsiento(Vuelo actual) {
	/*
	 * Interfaz de usuario encargada de gestionar la asignación de asientos en el
	 * vuelo. Debe permitir el ingreso de los datos y mostrar por pantalla el
	 * resultado de la operación
	 */

	int dni, fila, columna;
	Pasajero pasajeroASentar;

	System.out.println("\n********************************");
	System.out.println("\tUBICAR PASAJEROS");
	System.out.println("********************************");

	System.out.println();

	System.out.print("INGRESE EL DNI DEL PASAJERO: ");
	dni = teclado.nextInt();

	System.out.println();

	pasajeroASentar = actual.buscarPasajero(dni);

	if (pasajeroASentar != null) {
	    if (actual.pasajeroUbicado(pasajeroASentar) == false) {
		System.out.println(pasajeroASentar.toString());

		System.out.println();
		do {
		    System.out.print("INGRESE LA FILA EN DONDE SE UBICA AL PASAJERO: ");
		    fila = teclado.nextInt();

		    if (fila < 0 || fila > 31) {
			System.out.println("\nEL NÚMERO DE FILA DEBE SER UN NÚMERO COMPRENDIDO ENTRE 0 Y 31.");
		    }
		} while (fila < 0 || fila > 31);

		do {
		    System.out.print("INGRESE LA COLUMNA EN DONDE SE UBICA AL PASAJERO: ");
		    columna = teclado.nextInt();
		    if (columna < 0 || columna > 5)
			System.out.println("\nEL NÚMERO DE COLUMNA DEBE SER UN NÚMERO COMPRENDIDO ENTRE 0 Y 5");
		} while (columna < 0 || columna > 6);

		if (actual.asignarAsiento(pasajeroASentar, fila, columna)) {
		    System.out.println("\n------------------------------------------------------------------");
		    System.out
			    .println("PASAJERO UBICADO EXITOSAMENTE EN LA FILA " + fila + ", COLUMNA " + columna + ".");
		    System.out.println("------------------------------------------------------------------");
		} else {
		    System.out.println("\nNO SE PUEDE UBICAR AL PASAJERO EN ESTA UBICACIÓN."
			    + "\nVERIFIQUE LAS SIGUIENTES UBICACIONES DISPONIBLES:");
		    System.out.println();
		    actual.estadoDeLosAsientos();
		}
	    } else {
		System.out.println("\nEl " + pasajeroASentar + " ya se encuentra en un asiento del avión.");
	    }
	} else {
	    System.out.println("\nEL PASAJERO CON DNI " + dni + ", NO SE ENCUENTRA REGISTRADO.");
	}

    }

    /*
     * Se debe mostrar la lista de pasajeros registrados para éste vuelo ordenados
     * por DNI
     */
    private static void listarPasajeros(Vuelo actual) {
	actual.ordenarListaDePasajerosPorDNI();

	System.out.println();
	for (int i = 0; i < actual.getPasajeros().length; i++) {
	    if (actual.getPasajeros()[i] != null)
		System.out.println((i + 1) + ". " + actual.getPasajeros()[i].toString());
	    else
		break;
	}

    }
}
