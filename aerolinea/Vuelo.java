package aerolinea;

public class Vuelo {

    private final int CANTIDAD_DE_FILAS = 32;
    private final int CANTIDAD_DE_ASIENTOS_POR_FILA = 6;
    private String origen;
    private String destino;
    private Pasajero[] pasajeros;
    private Pasajero[][] asientos;
    private int cantidadPasajeros = 0;
    private int asientosLibres = 192;
    private int asientosOcupados = 0;

    public Vuelo(String origen, String destino, int cantidadPasajero) {
	this.origen = origen;
	this.destino = destino;

	pasajeros = new Pasajero[cantidadPasajero];

	asientos = new Pasajero[CANTIDAD_DE_FILAS][CANTIDAD_DE_ASIENTOS_POR_FILA];
    }

    /*
     * Incorpora un nuevo pasajero a la lista de pasajeros del vuelo. Devuelve true
     * si se pudo agregar o false si la capacidad del vuelo se encuentra completa.
     */
    public boolean agregarPasajero(Pasajero pasajero) {
	for (int i = 0; i < pasajeros.length; i++) {
	    if (pasajeros[i] == null) {
		pasajeros[i] = pasajero;
		cantidadPasajeros++;
		return true;
	    }
	}
	return false;
    }

    public boolean pasajeroUbicado(Pasajero pasajeroSeleccionado) {
	boolean encontrado = false;
	for (int i = 0; i < asientos.length; i++) {
	    for (int j = 0; j < asientos[i].length; j++) {
		if (asientos[i][j] != null)
		    encontrado = asientos[i][j] == pasajeroSeleccionado ? true : encontrado;
	    }
	}

	return encontrado;
    }

    /*
     * Verifica si el asiento indicado se encuentra disponible.
     */
    public boolean verificarDisponibilidadAsiento(int fila, int columna) {
	return asientos[fila][columna] == null;
    }

    /*
     * Busca un pasajero en la lista de pasajeros a partir del DNI. Si no lo
     * encuentra devuelve null.
     */
    public Pasajero buscarPasajero(int dni) {
	int i = 0;
	while (i < pasajeros.length && pasajeros[i] != null) {
	    if (pasajeros[i].getDni() == dni) {
		return pasajeros[i];
	    }
	    i++;
	}
	return null;
    }

    /*
     * Asigna el asiento al pasajero recibido por parámetro. Devuelve true si lo
     * pudo asignar o false en caso que el asiento no se encuentre disponible.
     */
    public boolean asignarAsiento(Pasajero pasajero, int fila, int columna) {
	if (pasajeroUbicado(pasajero) == false) {
	    if (verificarDisponibilidadAsiento(fila, columna)) {
		asientos[fila][columna] = pasajero;
		asientosOcupados++;
		asientosLibres--;
		return true;
	    }
	}
	return false;
    }

    /*
     * Ordena la lista de pasajeros por DNI.
     */
    public void ordenarListaDePasajerosPorDNI() {
	Pasajero aux = null;
	for (int i = (pasajeros.length - 1); i >= 0; i--) {
	    for (int k = 0; k < i; k++) {
		if (pasajeros[k] == null || pasajeros[k + 1] == null) {
		    break;
		}
		if (pasajeros[k].getDni() > pasajeros[k + 1].getDni()) {
		    aux = pasajeros[k];
		    pasajeros[k] = pasajeros[k + 1];
		    pasajeros[k + 1] = aux;
		}
	    }
	}
    }

    public Pasajero[] getPasajeros() {
	return pasajeros;
    }

    public void estadoDeLosAsientos() {
	for (int i = 0; i < asientos.length; i++) {
	    for (int j = 0; j < asientos[i].length; j++) {
		if (asientos[i][j] != null) {
		    System.out.print("O ");
		} else {
		    System.out.print("L ");
		}
	    }
	    System.out.println();
	}
    }

    public String toString() {
	return "Origen: " + origen + ". Destino: " + destino + ".\nCantidad de pasajeros: " + cantidadPasajeros
		+ ".\nAsientos libres: " + asientosLibres + ".\nAsientos ocupados: " + asientosOcupados;
    }

    public String getOrigen() {
	return origen;
    }

    public void setOrigen(String origen) {
	this.origen = origen;
    }

    public String getDestino() {
	return destino;
    }

    public void setDestino(String destino) {
	this.destino = destino;
    }

}
