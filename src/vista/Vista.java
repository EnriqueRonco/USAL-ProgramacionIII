/*
Enrique Mesonero Ronco DNI:52417500V
 */
package vista;

import com.coti.tools.Esdia;
import controlador.Controlador;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import modelo.Datos_equipo;
import modelo.Equipo;
import modelo.Jornada;
import modelo.Jugadora;
import modelo.LigaFem;
import modelo.Partido;

/**
 *
 * @author Enrique
 */
public class Vista {

    Controlador c = new Controlador();

    public void runMenu(String menu) throws IOException, ClassNotFoundException {
        c.leerFicheroBinario();
        boolean fin = false;

        do {
            String opc = Esdia.readString(menu);
            switch (opc) {
                case "1" -> {
                    subMenu1();
                }

                case "2" -> {
                    subMenu2();
                }

                case "3" -> {
                    subMenu3();
                }

                case "4" -> {
                    subMenu4();
                }

                case "5" -> {
                    subMenu5();
                }

                case "q" -> {
                    guardarEnFicheroBinario();
                    fin = Esdia.yesOrNo("Desea Salir?");
                }

                default -> {
                    System.out.println("\n--Opcion no valida--\n");
                }
            }
        } while (!fin);
    }

    private void subMenu1() throws IOException {

        boolean fin1 = false;
        do {
            String opc1 = Esdia.readString(
                    "1.1.Iniciar temporada\n"
                    + "1.2.Cargar jornadas\n"
                    + "1.3.Cargar equipos\n"
                    + "1.4.Cargar jugadoras\n"
                    + "1.q.Volver al menu principal\n"
                    + "Introducir seleccion:\n");

            switch (opc1) {
                case "1" -> {
                    String temporada = "desconocido";

                    boolean finTemporada = false;
                    do {
                        temporada = Esdia.readString("Introducir temporada\n: ");
                        if (7 != temporada.length()) {
                            System.out.println("\n--Formato de temporada no valido (Formato valido:yyyy/yy)--\n");
                        } else {
                            char esp = temporada.charAt(4);

                            if (esp != '/' || 7 != temporada.length()) {
                                System.out.println("\n--Formato de temporada no valido (Formato valido:yyyy/yy)--\n");
                            } else {
                                c.guardarTemporada(temporada);
                                finTemporada = true;
                            }
                        }
                    } while (!finTemporada);
                }

                case "2" -> {
                    if (c.obtenerTemporada() == null) {
                        System.out.println("Por favor, introduzca temporada antes de seleccionar esta opcion\n");
                    } else {
                        System.out.println("Temporada " + c.obtenerTemporada());
                        c.cargarPartidos();
                    }
                }

                case "3" -> {
                    if (c.obtenerTemporada() == null) {
                        System.out.println("Por favor, introduzca temporada antes de seleccionar esta opcion\n");
                    } else {
                        System.out.println("Temporada " + c.obtenerTemporada());
                        c.cargarEquipos();
                    }
                }

                case "4" -> {
                    if (c.obtenerTemporada() == null) {
                        System.out.println("Por favor, introduzca temporada antes de seleccionar esta opcion\n");
                    } else if (c.cogerEquipo() == null || c.cogerEquipo().isEmpty()) {
                        System.out.println("Por favor, introduzca equipos antes de seleccionar esta opcion\n");
                    } else {
                        System.out.println("Temporada " + c.obtenerTemporada());
                        c.cargarJugadoras();
                    }
                }

                case "q" -> {
                    fin1 = true;
                }

                default -> {
                    System.out.println("\n--Opcion seleccionada no valida--\n");
                }
            }
        } while (!fin1);
    }

    private void subMenu2() {
        if (c.cogerEquipo() == null || c.cogerEquipo().isEmpty()) {
            System.out.println("Introduzca primero los equipos");
        } else {
            if (c.cogerJugadoras() == null || c.cogerJugadoras().isEmpty()) {
                System.out.println("Introduzca primero las jugadoras");
            } else {
                boolean fin2 = false, fine = false;

                do {
                    String opcE = seleccioneEquipo();

                    if (opcE.equals("NoVale")) {
                        System.out.println("\n--Valor introducido no valido");
                    } else {
                        if (!opcE.equals("salir")) {

                            do {
                                String opc = Esdia.readString(
                                        "1.1.Modificar datos de una jugadora\n"
                                        + "1.2.Eliminar una jugadora\n"
                                        + "1.3.Aniadir jugadora\n"
                                        + "1.q.Volver al menu anterior\n"
                                        + "Introduzca opcion: ");

                                switch (opc) {
                                    case "1" -> {
                                        int i = 1;
                                        for (Jugadora j : c.seleccionarEquipo(opcE)) {
                                            System.out.println(
                                                    i
                                                    + "." + j.getNombre()
                                                    + " " + j.getPosicion()
                                                    + " " + j.getDorsal()
                                                    + " " + j.getNacimineto().cogerFecha()
                                                    + " " + j.getNacimineto().getCiudad()
                                                    + " " + j.getNacimineto().getProvincia()
                                                    + " " + j.getNacionalidad()
                                                    + " " + j.getAltura()
                                                    + "\n");
                                            i++;
                                        }
                                        modificarJugadoras(opcE);
                                    }

                                    case "2" -> {
                                        int i = 1;
                                        for (Jugadora j : c.seleccionarEquipo(opcE)) {
                                            System.out.println(
                                                    i
                                                    + "." + j.getNombre()
                                                    + " " + j.getPosicion()
                                                    + " " + j.getDorsal()
                                                    + " " + j.getNacimineto().cogerFecha()
                                                    + " " + j.getNacimineto().getCiudad()
                                                    + " " + j.getNacimineto().getProvincia()
                                                    + " " + j.getNacionalidad()
                                                    + " " + j.getAltura()
                                                    + "\n");
                                            i++;
                                        }
                                        eliminarJugadora(opcE);
                                    }

                                    case "3" -> {
                                        aniadirJugadora(opcE);
                                    }

                                    case "q" -> {
                                        fin2 = true;
                                    }

                                    default -> {
                                        System.out.println("\n--Opcion seleccionada no vailda--\n");
                                    }
                                }
                            } while (!fin2);
                        } else {
                            fine = true;
                        }
                    }
                } while (!fine);
            }
        }

    }

    private void subMenu3() throws IOException {

        if (c.cogerJornadas().isEmpty()) {
            System.out.println("Cargar primero las jornadas\n");
        } else if (c.cogerEquipo().isEmpty()) {
            System.out.println("Cargar primero los equipos\n");
        } else {

            boolean fin3 = false;
            do {
                String opc = Esdia.readString(
                        "3.1.Leer los resultados de la jornada\n"
                        + "3.2.Modificar fecha de una jornada\n"
                        + "3.3.Modificar fecha u hora de un partido\n"
                        + "3.4.Mostral los resultados de la jornada\n"
                        + "3.5.Mostrar la clasificacion de la jornada\n"
                        + "3.q.Salir\n"
                        + "Introducir opcion: ");
                switch (opc) {

                    case "1" -> {
                        cargarJornada();
                    }

                    case "2" -> {
                        modificarFechaJornada();
                    }

                    case "3" -> {
                        modificarFechaPartido();
                    }

                    case "4" -> {
                        mostrarResultados();
                    }

                    case "5" -> {
                        mostrarClasificacion();
                    }

                    case "q" -> {
                        fin3 = true;
                    }

                    default -> {
                        System.out.println("Opcion escogida no valida");
                    }
                }
            } while (!fin3);
        }
    }

    private void subMenu4() {
        if (c.cogerEquipo() == null || c.cogerEquipo().isEmpty()) {
            System.out.println("Introduzca primero los equipos");
        } else {
            if (c.cogerJugadoras() == null || c.cogerJugadoras().isEmpty()) {
                System.out.println("Introduzca primero las jugadoras");
            } else {

                boolean finSubMenu4 = false;

                do {
                    String opc = Esdia.readString(
                            "4.1.Mostrar jugadoras de un equipo\n"
                            + "4.2.Mostrar equipos\n"
                            + "4.3.Relacion de jugadoras\n"
                            + "4.q.Salir"
                    );

                    switch (opc) {
                        case "1" -> {
                            mostrarJugadorasOrdenadas();
                        }

                        case "2" -> {
                            mostrarEquiposOrdenadosPorTelefono();
                        }

                        case "3" -> {
                            mostrarRelacionDeJugadoras();
                        }

                        case "q" -> {
                            finSubMenu4 = true;
                        }
                    }
                } while (!finSubMenu4);
            }
        }
    }

    private void subMenu5() throws FileNotFoundException {
        if (c.cogerJornadas().isEmpty() || c.cogerJornadas() == null) {
            System.out.println("Introduzca primero las jornadas");
        } else {
            if (c.cogerEquipo().isEmpty() || c.cogerEquipo() == null) {
                System.out.println("Introduzca primero los equipos");
            } else {
                if (c.cogerJugadoras().isEmpty() || c.cogerJugadoras() == null) {
                    System.out.println("Introduzca primero las jugadoras");
                } else {
                    boolean finSubMenu5 = false;

                    do {

                        String opc = Esdia.readString("5.1.Jugadoras de un equipo\n"
                                + "5.2.Relacion de equipos\n"
                                + "5.3.Clasificacion de una jornada\n"
                                + "5.q.Volver al menu principal: "
                        );

                        switch (opc) {
                            case "1" -> {
                                guardarJugadorasEquipo();
                            }

                            case "2" -> {
                                imprimirEquipos();
                            }

                            case "3" -> {
                                clasificacionJornadaHTML();
                            }

                            case "q" -> {
                                finSubMenu5 = true;
                            }

                            default -> {
                                System.out.println("\n--Valor introducido no valido--\n");
                            }
                        }
                    } while (!finSubMenu5);
                }
            }
        }
    }

    private String seleccioneEquipo() {
        ArrayList<Equipo> equipos = c.cogerEquipo();
        int i = 1;
        for (Equipo e : equipos) {
            System.out.println(i + "." + e.getNombreEquipoSinAcentos());
            i++;
        }

        int eq = Esdia.readInt(
                "Seleccione equipo para modificar datos de una jugadora: \n"
                + "Pulse -1 para salir "
        );

        if (eq == -1) {
            return "salir";
        } else {
            if (eq < 1 || eq > c.cogerEquipo().size()) {
                return "NoVale";
            } else {
                String eq1 = "";
                eq1 += eq;
                return eq1;
            }
        }
    }

    private void modificarJugadoras(String opcE) {
        boolean finModJu = false;
        do {
            int opcJ = Esdia.readInt("Seleccione jugadora: ");

            if (opcJ < 1 || opcJ > c.seleccionarEquipo(opcE).size()) {
                System.out.println("\n--Valor introducido no valido--\n");
            } else {
                String opcJs = "";
                opcJs += opcJ;
                c.modificarJugadoras(opcE, opcJs);
                finModJu = true;
            }
        } while (!finModJu);
    }

    private void eliminarJugadora(String opcE) {
        boolean finJu = false;
        do {
            int opcJ = Esdia.readInt("Seleccione jugadora a eliminar: ");
            int tam = c.seleccionarEquipo(opcE).size();
            if (opcJ < 1 || opcJ > tam) {
                System.out.println("\n--Valor no valido--\n");

            } else {
                String opcJu = "";
                opcJu += opcJ;
                c.eliminarJugadora(opcE, opcJu);
                tam--;
                finJu = true;
            }
        } while (!finJu);

    }

    private void aniadirJugadora(String opcE) {
        c.aniadirJugadora(opcE);
    }

    private void cargarJornada() throws IOException {

        boolean finJornada = false;

        do {
            int opcJornada = Esdia.readInt("Seleccione una jornada(Pulse -1 para salir): ");

            if (opcJornada == -1) {
                System.out.println("Salir");
                finJornada = true;
            } else {
                if (opcJornada < 1 || opcJornada > c.cogerJornadas().size()) {
                    System.out.println("\n--Valor introducido no valido--\n");
                } else {
                    c.cargarJornada(opcJornada);
                    finJornada = true;
                }
            }
        } while (!finJornada);
    }

    private void mostrarResultados() {
        boolean finR = false;

        do {
            int opcJ = Esdia.readInt("Seleccione jornada: ");

            if (opcJ < 1 || opcJ > c.cogerJornadas().size()) {
                System.out.println("\n--Valor introducido no valido");
            } else {
                Jornada j = c.cogerJornadas().get(opcJ - 1);
                System.out.println("Equipo local " + " Equipo visitante " + " Puntos local " + " Puntos visitante\n");
                for (Partido p : j.getPartidos()) {

                    System.out.println(
                            " " + p.getEquipoLocal()
                            + " " + p.getEquipoVisitante()
                            + " " + p.getPuntosLocal()
                            + " " + p.getPuntosVisitante()
                    );
                }
                finR = true;
            }
        } while (!finR);
    }

    private void mostrarClasificacion() {
        boolean finJ = false;

        do {
            int numJor = Esdia.readInt("Introduzca numero de jornada: ");
            ArrayList<Jornada> jornadas = c.cogerJornadas();
            if (numJor < 1 || numJor > jornadas.size()) {
                System.out.println("\n--Valor introducido no valido--\n");
            } else {

                Jornada j = jornadas.get(numJor - 1);

                System.out.println("Jornada" + j.getClasificacion().size());

                ArrayList<Datos_equipo> clasOrdenada = c.ordenarClasificacion(j);
                for (Datos_equipo deOr : clasOrdenada) {
                    System.out.println(
                            deOr.getNombreEquipo()
                            + " PJ: " + deOr.getPJ()
                            + " PG: " + deOr.getPG()
                            + " PP: " + deOr.getPP()
                            + " PF: " + deOr.getPF()
                            + " PC: " + deOr.getPC()
                            + " PTS: " + deOr.getPuntosClasificacion()
                            + "\n");
                }

                finJ = true;
            }
        } while (!finJ);
    }

    private void modificarFechaJornada() {
        c.modificarFechaJornada();
    }

    private void modificarFechaPartido() {
        c.modificarFechaPartido();
    }

    private void mostrarJugadorasOrdenadas() {
        boolean salirOpcEquipo = false;
        int opcEquipo = 0;

        ArrayList<Equipo> equipos = c.cogerEquipo();

        int i = 1;
        for (Equipo e : equipos) {
            System.out.println(i + "." + e.getNombreEquipo() + "\n");
            i++;
        }

        do {
            opcEquipo = Esdia.readInt("Seleccione equipo: ");
            if (opcEquipo < 1 || opcEquipo > c.cogerEquipo().size()) {
                System.out.println("\n--Valor seleccionado no valido--\n");
            } else {
                salirOpcEquipo = true;
            }
        } while (!salirOpcEquipo);

        ArrayList<Jugadora> jugadoras = c.obtenerJugadorasOrdenadas(opcEquipo);

        for (Jugadora j : jugadoras) {
            System.out.println(
                    j.getNombre()
                    + " " + j.getPosicion()
                    + " " + j.getDorsal()
                    + " " + j.getNacimineto().cogerFecha()
                    + " " + j.getNacimineto().getCiudad()
                    + " " + j.getNacimineto().getProvincia()
                    + " " + j.getNacionalidad()
                    + " " + j.getAltura()
            );
        }
    }

    private void mostrarEquiposOrdenadosPorTelefono() {
        ArrayList<Equipo> equipos = c.getEquiposOrdenados();

        for (Equipo e : equipos) {
            System.out.printf("%s\t %s\t %d\t %s\t %s\n",
                    e.getNombreEquipo(), e.getDireccion(), e.getTelefono(), e.getWeb(), e.getEmail());
        }
    }

    private void mostrarRelacionDeJugadoras() {
        ArrayList<Equipo> equipos = c.cogerEquipo();
        int i = 1;

        for (Equipo e : equipos) {
            System.out.println(i + "." + e.getNombreEquipo() + "\n");
            i++;
        }
        boolean finSeleccionEquipo = false;
        boolean finSeleccionLetra = false;

        do {
            int opc = Esdia.readInt("Seleccione equipo: ");

            if (opc < 1 || opc > equipos.size()) {
                System.out.println("\n--Seleccion no valida--\n");
            } else {
                do {
                    String letraS = Esdia.readString("Seleccione letra: ");

                    if (letraS.length() != 1) {
                        System.out.println("\n--Introduzca solo una letra--\n");
                    } else {
                        char letra = letraS.charAt(0);
                        if (!((letra >= 'a' && letra <= 'z') || (letra >= 'A' && letra <= 'Z') || letra == ' ')) {
                            System.out.println("\n--Por favor, introduzca una letra");
                        } else {

                            ArrayList<Jugadora> jugadoras = c.cogerJugadoraPorLetra(opc, letra);

                            for (Jugadora j : jugadoras) {
                                System.out.println(
                                        j.getNombre()
                                        + " " + j.getPosicion()
                                        + " " + j.getDorsal()
                                        + " " + j.getNacimineto().cogerFecha()
                                        + " " + j.getNacimineto().getCiudad()
                                        + " " + j.getNacimineto().getProvincia()
                                        + " " + j.getNacionalidad()
                                        + " " + j.getAltura()
                                );
                            }
                            finSeleccionLetra = true;
                        }
                    }

                } while (!finSeleccionLetra);
                finSeleccionEquipo = true;
            }
        } while (!finSeleccionEquipo);
    }

    private void guardarJugadorasEquipo() throws FileNotFoundException {
        ArrayList<Equipo> equipos = c.cogerEquipo();
        int i = 1;

        for (Equipo e : equipos) {
            System.out.println(i + "." + e.getNombreEquipo() + "\n");
            i++;
        }
        boolean finSeleccionEquipo = false;

        do {
            int opc = Esdia.readInt("Seleccione equipo: ");

            if (opc < 1 || opc > equipos.size()) {
                System.out.println("\n--Seleccion no valida--\n");
            } else {
                c.guardarJugadorasEquipo(opc);
                finSeleccionEquipo = true;
            }
        } while (!finSeleccionEquipo);

    }

    private void imprimirEquipos() throws FileNotFoundException {
        c.imprimirEquipos();
    }

    private void clasificacionJornadaHTML() throws FileNotFoundException {
        boolean finHTML = false;
        do {
            int numJornada = Esdia.readInt("Introduzca numero de jornada: ");

            if (numJornada < 1 || numJornada > c.cogerJornadas().size()) {
                System.out.println("\n--Valor introducido no valido--\n");
            } else {
                c.clasificacionJornadaHTML(numJornada);
                finHTML = true;
            }
        } while (!finHTML);
    }

    private void guardarEnFicheroBinario() throws IOException {
        c.guardarEnFicheroBinario();
    }

}
