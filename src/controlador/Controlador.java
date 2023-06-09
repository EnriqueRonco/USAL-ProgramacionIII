/*
Enrique Mesonero Ronco DNI:52417500V
 */
package controlador;

import com.coti.tools.Esdia;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import static java.lang.System.err;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import modelo.Datos_equipo;
import modelo.Equipo;
import modelo.Jornada;
import modelo.Jugadora;
import modelo.Jugadora.FechaNac;
import modelo.LigaFem;
import modelo.Partido;

/**
 *
 * @author Enrique
 */
public class Controlador {

    LigaFem lf = new LigaFem();

    public Controlador() {
    }

    //Submenu 1
    public void guardarTemporada(String temporada) {
        lf.setTemporada(temporada);
    }

    public String obtenerTemporada() {
        return lf.getTemporada();
    }

    public void cargarPartidos() throws IOException {
        lf.cargarPartidos();
    }

    public void cargarEquipos() throws IOException {
        lf.cargarEquipos();
    }

    public void cargarJugadoras() throws IOException {
        lf.cargarJugadoras();
    }

    public ArrayList<Equipo> cogerEquipo() {
        return lf.getEquipos();
    }

    public ArrayList<ArrayList> cogerJugadoras() {
        int i = 0, j = 0;

        for (ArrayList l : lf.getJugadoras()) {
            if (l == null) {
                j++;
            }
        }

        if (j == 0) {
            return lf.getJugadoras();
        } else {
            return null;
        }

    }

    public ArrayList<Jugadora> seleccionarEquipo(String opcE) {
        Equipo e = lf.getEquipos().get(Integer.parseInt(opcE) - 1);
        return e.getJugadoras();
    }

    public void modificarJugadoras(String opcE, String opcJ) {
        Equipo e = lf.getEquipos().get(Integer.parseInt(opcE) - 1);
        Jugadora j = e.getJugadoras().get(Integer.parseInt(opcJ) - 1);

        System.out.println(j.getNombre()
                + " 1." + j.getPosicion()
                + " 2.Dorsal: " + j.getDorsal()
                + " 3." + j.getNacimineto().cogerFecha()
                + " 4." + j.getNacimineto().getCiudad()
                + " 5." + j.getNacimineto().getProvincia()
                + " 6." + j.getNacionalidad()
                + " 7." + j.getAltura()
                + " q.Finalizar modificacion"
                + "\n");

        boolean fin = false;

        do {
            String opcJu = Esdia.readString("Que desea modificar?(Pulse q para salir):");

            switch (opcJu) {

                case "1" -> {
                    String opcPos = Esdia.readString("A que posicion desea cambiar?\n"
                            + "1.Base\n"
                            + "2.Escolta\n"
                            + "3.Alero\n"
                            + "4.Ala-pivot\n"
                            + "5.Pivot\n"
                            + "0.Desconocido\n"
                            + "q.Salir\n"
                            + "Introduzca seleccion: ");

                    boolean finPos = true;
                    do {
                        switch (opcPos) {
                            case "1" -> {
                                j.setPosicion("Base");
                            }
                            case "2" -> {
                                j.setPosicion("Escolta");
                            }
                            case "3" -> {
                                j.setPosicion("Alero");
                            }
                            case "4" -> {
                                j.setPosicion("Ala-pivot");
                            }
                            case "5" -> {
                                j.setPosicion("Pivot");
                            }
                            case "0" -> {
                                j.setPosicion("Desconocido");
                            }
                            default -> {
                                System.out.println("\n--Valor no valido--\n");
                                finPos = false;
                            }
                        }
                    } while (!finPos);
                }

                case "2" -> {
                    int numDor = Esdia.readInt("Introduzca valor para dorsal(Valor entero)");
                    j.setDorsal(numDor);
                }

                case "3" -> {
                    System.out.println("\n--Fecha de nacimiento--\n");
                    String fechNac = "";

                    boolean diaC = true;
                    do {
                        int dia = Esdia.readInt("Intriduzca dia(1-31): ");
                        if (dia < 1 || dia > 31) {
                            System.out.println("Lo siento, dia introducido no valido\n");
                            diaC = false;
                        } else {
                            if (dia < 10) {
                                fechNac += "0" + dia + "/";
                                diaC = true;
                            } else {
                                fechNac += dia + "/";
                                diaC = true;
                            }
                        }
                    } while (!diaC);

                    boolean mesC = true;
                    do {
                        int mes = Esdia.readInt("Introduzca mes (En valor numerico): ");
                        if (mes < 1 || mes > 12) {
                            System.out.println("Lo siento, mes introducido no valido\n");
                            mesC = false;
                        } else {
                            if (mes < 10) {
                                fechNac += "0" + mes + "/";
                                mesC = true;
                            } else {
                                fechNac += mes + "/";
                                mesC = true;
                            }
                        }
                    } while (!mesC);

                    boolean anioC = true;
                    do {
                        int anio = Esdia.readInt("Introduzca anio: ");
                        if (anio < 1 || anio > Calendar.getInstance().get(Calendar.YEAR)) {
                            System.out.println("Lo siento, anio introducido no valido\n");
                            anioC = false;
                        } else {
                            if (anio < 1000 && anio >= 100) {
                                fechNac += "0" + anio;
                                anioC = true;
                            } else if (anio < 100 && anio >= 10) {
                                fechNac += "00" + anio;
                                anioC = true;
                            } else if (anio < 10) {
                                fechNac += "000" + anio;
                                anioC = true;
                            } else {
                                fechNac += anio;
                                anioC = true;
                            }
                        }
                    } while (!anioC);

                    String fechaPasar[] = fechNac.split("/");
                    j.getNacimineto().setFecha(fechaPasar);
                    j.getNacimineto().cogerFecha();
                }

                case "4" -> {
                    String ciudad = Esdia.readString("Introduzca una ciudad: ");
                    j.getNacimineto().setCiudad(ciudad);
                }

                case "5" -> {
                    String provincia = Esdia.readString("Introduzca una provincia: ");
                    j.getNacimineto().setProvincia(provincia);
                }

                case "6" -> {
                    String nacionalidad = Esdia.readString("Introduzca un pais: ");
                    j.setNacionalidad(nacionalidad);
                }

                case "7" -> {
                    boolean finAlt = false;
                    do {

                        float altura = Esdia.readFloat("Introduzca altura en cm: ");

                        if (altura < 50 || altura > 250) {
                            System.out.println("\n--Altura no valida--\n");
                        } else {
                            j.setAltura(altura);
                            finAlt = true;
                        }
                    } while (!finAlt);
                }

                case "q" -> {
                    fin = true;
                }

                default -> {
                    System.out.println("\n--Valor introducido no valido--\n");
                }
            }
        } while (!fin);
    }

    public void eliminarJugadora(String opcE, String opcJ) {
        Equipo e = lf.getEquipos().get(Integer.parseInt(opcE) - 1);
        Jugadora j = e.getJugadoras().remove(Integer.parseInt(opcJ) - 1);

    }

    public void aniadirJugadora(String opcE) {
        Equipo e = lf.getEquipos().get(Integer.parseInt(opcE) - 1);

        String nombre = Esdia.readString("Introduzca nombre: ");

        String posicion = "";

        String opcPos = Esdia.readString("A que posicion desea posicionar?\n"
                + "1.Base\n"
                + "2.Escolta\n"
                + "3.Alero\n"
                + "4.Ala-pivot\n"
                + "5.Pivot\n"
                + "0.Desconocido\n"
                + "q.Salir\n"
                + "Introduzca seleccion: ");

        boolean finPos = true;
        do {
            switch (opcPos) {
                case "1" -> {
                    posicion = "Base";
                }
                case "2" -> {
                    posicion = "Escolta";
                }
                case "3" -> {
                    posicion = "Alero";
                }
                case "4" -> {
                    posicion = "Ala-pivot";
                }
                case "5" -> {
                    posicion = "Pivot";
                }
                case "0" -> {
                    posicion = "Desconocido";
                }
                default -> {
                    System.out.println("\n--Valor no valido--\n");
                    finPos = false;
                }
            }
        } while (!finPos);

        System.out.println("\n--Dorsal--\n");
        int dorsal = Esdia.readInt("Introduzca valor para dorsal(Valor entero)");

        System.out.println("\n--Fecha de nacimiento--\n");
        String fechNac = "";

        boolean diaC = true;
        do {
            int dia = Esdia.readInt("Intriduzca dia(1-31): ");
            if (dia < 1 || dia > 31) {
                System.out.println("Lo siento, dia introducido no valido\n");
                diaC = false;
            } else {
                if (dia < 10) {
                    fechNac += "0" + dia + "/";
                    diaC = true;
                } else {
                    fechNac += dia + "/";
                    diaC = true;
                }
            }
        } while (!diaC);

        boolean mesC = true;
        do {
            int mes = Esdia.readInt("Introduzca mes (En valor numerico): ");
            if (mes < 1 || mes > 12) {
                System.out.println("Lo siento, mes introducido no valido\n");
                mesC = false;
            } else {
                if (mes < 10) {
                    fechNac += "0" + mes + "/";
                    mesC = true;
                } else {
                    fechNac += mes + "/";
                    mesC = true;
                }
            }
        } while (!mesC);

        boolean anioC = true;
        do {
            int anio = Esdia.readInt("Introduzca anio: ");
            if (anio < 1 || anio > Calendar.getInstance().get(Calendar.YEAR)) {
                System.out.println("Lo siento, anio introducido no valido\n");
                anioC = false;
            } else {
                if (anio < 1000 && anio >= 100) {
                    fechNac += "0" + anio;
                    anioC = true;
                } else if (anio < 100 && anio >= 10) {
                    fechNac += "00" + anio;
                    anioC = true;
                } else if (anio < 10) {
                    fechNac += "000" + anio;
                    anioC = true;
                } else {
                    fechNac += anio;
                    anioC = true;
                }
            }
        } while (!anioC);

        String ciudad = Esdia.readString("Introduzca una ciudad: ");
        String provincia = Esdia.readString("Introduzca una provincia: ");

        String instanciaDatos = fechNac + ciudad + "(" + provincia;

        String nacionalidad = Esdia.readString("Introduzca un pais: ");

        boolean finAlt = false;

        float altura = 0.00f;
        do {

            String alt = Esdia.readString("Introduzca altura en cm: ");

            if (Float.parseFloat(alt) < 50 || Float.parseFloat(alt) > 250) {
                System.out.println("\n--Altura no valida--\n");
            } else {
                altura = Float.parseFloat(alt);
                finAlt = true;
            }
        } while (!finAlt);

        FechaNac fecha = new FechaNac(instanciaDatos);

        Jugadora j = new Jugadora(nombre, posicion, dorsal, fecha, nacionalidad, altura);
        e.getJugadoras().add(j);

    }

    public ArrayList<Jornada> cogerJornadas() {
        return lf.getJornadas();
    }

    public void cargarJornada(int opcJornada) throws IOException {
        lf.cargarJornada(opcJornada);
    }

    public void modificarFechaJornada() {
        boolean finFecha = false;

        do {
            int opcJornada = Esdia.readInt("Introduzca jornada: ");

            if (opcJornada < 1 || opcJornada > lf.getJornadas().size()) {
                System.out.println("\n--Valor introducido no valido--\n");
            } else {
                Jornada j = lf.getJornadas().get(opcJornada - 1);
                System.out.println("Fecha actual de la jornada " + opcJornada + " " + j.getFecha());

                int diaNuevo = 0;
                int mesNuevo = 0;
                int anioNuevo = 0;

                boolean finNuevoDia = false;
                do {

                    diaNuevo = Esdia.readInt("Introduzca nuevo dia: ");

                    if (diaNuevo < 1 || diaNuevo > 31) {
                        System.out.println("\n--Valor introducido no valido");
                    } else {
                        finNuevoDia = true;
                    }

                } while (!finNuevoDia);

                boolean finNuevoMes = false;
                do {

                    mesNuevo = Esdia.readInt("Introduzca nuevo mes: ");

                    if (mesNuevo < 1 || mesNuevo > 12) {
                        System.out.println("\n--Valor introducido no valido--\n");
                    } else {
                        finNuevoMes = true;
                    }
                } while (!finNuevoMes);

                boolean finNuevoAnio = false;
                do {
                    String temporada = lf.getTemporada();
                    String anios[] = temporada.split("/");
                    int anio1 = Integer.parseInt(anios[0]);
                    int anio2 = Integer.parseInt(anios[1]);

                    if ((anio1 - 2000) < 0) {
                        anio2 += 1900;
                    } else {
                        anio2 += 2000;
                    }

                    anioNuevo = Esdia.readInt("Introduzca nuevo anio: ");

                    if (anioNuevo < anio1 || anioNuevo > anio2) {
                        System.out.println("\n--Valor introducido no valido--\n");
                    } else {
                        finNuevoAnio = true;
                    }

                } while (!finNuevoAnio);

                if (diaNuevo < 10) {
                    if (mesNuevo < 10) {
                        String fechaNueva = "0" + diaNuevo + "/" + "0" + mesNuevo + "/" + anioNuevo;
                        j.setFecha(fechaNueva);
                    } else {
                        String fechaNueva = "0" + diaNuevo + "/" + mesNuevo + "/" + anioNuevo;
                        j.setFecha(fechaNueva);
                    }
                } else {
                    if (mesNuevo < 10) {
                        String fechaNueva = diaNuevo + "/" + "0" + mesNuevo + "/" + anioNuevo;
                        j.setFecha(fechaNueva);
                    } else {
                        String fechaNueva = diaNuevo + "/" + mesNuevo + "/" + anioNuevo;
                        j.setFecha(fechaNueva);
                    }

                }
                finFecha = true;
            }
        } while (!finFecha);

    }

    public void modificarFechaPartido() {
        boolean finFecha = false;

        do {
            int opcJornada = Esdia.readInt("Introduzca jornada: ");

            if (opcJornada < 1 || opcJornada > lf.getJornadas().size()) {
                System.out.println("\n--Valor introducido no valido--\n");
            } else {
                Jornada j = lf.getJornadas().get(opcJornada - 1);

                for (Partido p : j.getPartidos()) {
                    String fechaPArray[] = p.getFechaPartido();
                    String horaPArray[] = p.getHoraPartido();

                    String fechaP = fechaPArray[0] + "/" + fechaPArray[1] + "/" + fechaPArray[2];
                    String horaP = horaPArray[0] + ":" + horaPArray[1];

                    System.out.println(
                            p.getEquipoLocal() + "-"
                            + " " + p.getEquipoVisitante()
                            + " " + fechaP
                            + " " + horaP
                            + "\n"
                    );
                }
                boolean finSeleccionPartido = false;

                do {

                    int opcPartido = Esdia.readInt("Seleccione partido:");

                    if (opcPartido < 1 || opcPartido > j.getPartidos().size()) {
                        System.out.println("\n--Valor introducido no valido--\n");
                    } else {
                        Partido p = j.getPartidos().get(opcPartido - 1);

                        boolean finEleccion = false;
                        do {
                            String seleccion = Esdia.readString("Seleccione si quiere modificar fecha u hora, en caso de querer salir pulse q: ");

                            switch (seleccion) {
                                case "fecha" -> {
                                    String fechaNueva = "";

                                    if (opcJornada != (lf.getJornadas().size() - 1)) {
                                        String fechaJornada = j.getFecha();
                                        String fechaJornadaArray[] = fechaJornada.split("/");
                                        int dia = Integer.parseInt(fechaJornadaArray[0]);
                                        int mes = Integer.parseInt(fechaJornadaArray[1]);
                                        int anio = Integer.parseInt(fechaJornadaArray[2]);

                                        String fechaJornadaSiguiente = lf.getJornadas().get(opcJornada).getFecha();
                                        String fechaJornadaSiguienteArray[] = fechaJornadaSiguiente.split("/");
                                        int diaSig = Integer.parseInt(fechaJornadaSiguienteArray[0]);
                                        int mesSig = Integer.parseInt(fechaJornadaSiguienteArray[1]);
                                        int anioSig = Integer.parseInt(fechaJornadaSiguienteArray[2]);

                                        int diaNuevo = 0;
                                        int mesNuevo = 0;
                                        int anioNuevo = 0;

                                        boolean finNuevoDia = false;
                                        do {
                                            diaNuevo = Esdia.readInt("Seleccione nuevo dia para el partido: ");
                                            if (diaNuevo < dia || diaNuevo > diaSig) {
                                                System.out.println("\n--Valor introducido no valido--\n");
                                            } else {
                                                finNuevoDia = true;
                                            }
                                        } while (!finNuevoDia);

                                        boolean finNuevoMes = false;
                                        do {
                                            mesNuevo = Esdia.readInt("Seleccione nuevo mes para el partido: ");
                                            if (mesNuevo < mes || mesNuevo > mesSig) {
                                                System.out.println("\n--Valor introducido no valido--\n");
                                            } else {
                                                finNuevoMes = true;
                                            }
                                        } while (!finNuevoMes);

                                        boolean finNuevoAnio = false;
                                        do {
                                            anioNuevo = Esdia.readInt("Seleccione nuevo anio para el partido: ");
                                            if (anioNuevo < anio || anioNuevo > anioSig) {
                                                System.out.println("\n--Valor introducido no valido--\n");
                                            } else {
                                                finNuevoAnio = true;
                                            }

                                            if (diaNuevo < 10) {
                                                if (mesNuevo < 10) {
                                                    fechaNueva = "0" + diaNuevo + "/" + "0" + mesNuevo + "/" + anioNuevo;
                                                } else {
                                                    fechaNueva = "0" + diaNuevo + "/" + mesNuevo + "/" + anioNuevo;
                                                }
                                            } else {
                                                if (mesNuevo < 10) {
                                                    fechaNueva = diaNuevo + "/" + "0" + mesNuevo + "/" + anioNuevo;
                                                } else {
                                                    fechaNueva = diaNuevo + "/" + mesNuevo + "/" + anioNuevo;
                                                }

                                            }

                                        } while (!finNuevoAnio);

                                    } else {
                                        String fechaJornada = j.getFecha();
                                        String fechaJornadaArray[] = fechaJornada.split("/");
                                        int dia = Integer.parseInt(fechaJornadaArray[0]);
                                        int mes = Integer.parseInt(fechaJornadaArray[1]);
                                        int anio = Integer.parseInt(fechaJornadaArray[2]);

                                        int diaNuevo = 0;
                                        int mesNuevo = 0;
                                        int anioNuevo = 0;

                                        boolean finNuevoDia = false;
                                        do {
                                            diaNuevo = Esdia.readInt("Seleccione nuevo dia para el partido: ");
                                            if (diaNuevo < dia || diaNuevo > 31) {
                                                System.out.println("\n--Valor introducido no valido--\n");
                                            } else {
                                                finNuevoDia = true;
                                            }
                                        } while (!finNuevoDia);

                                        boolean finNuevoMes = false;
                                        do {
                                            mesNuevo = Esdia.readInt("Seleccione nuevo mes para el partido: ");
                                            if (mesNuevo < mes || mesNuevo > 12) {
                                                System.out.println("\n--Valor introducido no valido--\n");
                                            } else {
                                                finNuevoMes = true;
                                            }
                                        } while (!finNuevoMes);

                                        String temporada = lf.getTemporada();
                                        String anios[] = temporada.split("/");
                                        int anio1 = Integer.parseInt(anios[0]);
                                        int anio2 = Integer.parseInt(anios[1]);

                                        if ((anio1 - 2000) < 0) {
                                            anio2 += 1900;
                                        } else {
                                            anio2 += 2000;
                                        }

                                        boolean finNuevoAnio = false;
                                        do {
                                            anioNuevo = Esdia.readInt("Seleccione nuevo anio para el partido: ");
                                            if (anioNuevo < anio || anioNuevo > anio2) {
                                                System.out.println("\n--Valor introducido no valido--\n");
                                            } else {
                                                finNuevoAnio = true;
                                            }
                                        } while (!finNuevoAnio);

                                        if (diaNuevo < 10) {
                                            if (mesNuevo < 10) {
                                                fechaNueva = "0" + diaNuevo + "/" + "0" + mesNuevo + "/" + anioNuevo;
                                            } else {
                                                fechaNueva = "0" + diaNuevo + "/" + mesNuevo + "/" + anioNuevo;
                                            }
                                        } else {
                                            if (mesNuevo < 10) {
                                                fechaNueva = diaNuevo + "/" + "0" + mesNuevo + "/" + anioNuevo;
                                            } else {
                                                fechaNueva = diaNuevo + "/" + mesNuevo + "/" + anioNuevo;
                                            }

                                        }

                                    }
                                    p.setFechaPartido(fechaNueva.split("/"));
                                    finSeleccionPartido = true;
                                }

                                case "hora" -> {
                                    String nuevaHora = "";
                                    int hora = 0;

                                    boolean finHora = false;
                                    do {
                                        hora = Esdia.readInt("Seleccione nueva hora: ");

                                        if (hora < 0 || hora > 23) {
                                            System.out.println("\n--Hora introducida no valida");
                                        } else {
                                            finHora = true;
                                        }
                                    } while (!finHora);

                                    int minuto = 0;
                                    boolean finMinuto = false;
                                    do {
                                        minuto = Esdia.readInt("Seleccione nuevo minuto: ");

                                        if (minuto < 0 || minuto > 59) {
                                            System.out.println("\n--Minuto introducido no valido");
                                        } else {
                                            finMinuto = true;
                                        }
                                    } while (!finMinuto);

                                    String nuevaHoraPartido = "";

                                    if (hora < 10) {
                                        if (minuto < 10) {
                                            nuevaHoraPartido = "0" + hora + ":" + "0" + minuto;
                                        } else {
                                            nuevaHoraPartido = "0" + hora + ":" + minuto;
                                        }
                                    } else {
                                        if (minuto < 10) {
                                            nuevaHoraPartido = hora + ":" + "0" + minuto;
                                        } else {
                                            nuevaHoraPartido = hora + ":" + minuto;
                                        }
                                    }
                                    p.setHoraPartido(nuevaHoraPartido.split(":"));
                                    finSeleccionPartido = true;
                                }

                                case "q" -> {
                                    finEleccion = true;
                                    finSeleccionPartido = true;
                                }

                                default -> {
                                    System.out.println("\n--Por favor, seleccione fecha u hora--\n");
                                }
                            }
                        } while (!finEleccion);
                    }
                } while (!finSeleccionPartido);
                finFecha = true;
            }
        } while (!finFecha);
    }

    public ArrayList<Jugadora> obtenerJugadorasOrdenadas(int opc) {
        Equipo e = lf.getEquipos().get(opc - 1);

        ArrayList<Jugadora> jDesordenada = e.getJugadoras();
        ArrayList<Jugadora> jOrdenada = new ArrayList();

        float alturaMax = 0.0f;

        for (Jugadora j : jDesordenada) {
            if (j.getAltura() > alturaMax) {
                alturaMax = j.getAltura();
            }
        }

        for (int op = 0; op < jDesordenada.size(); op++) {

            Jugadora ju = jDesordenada.get(op);
            if (ju.getAltura() >= alturaMax) {
                jOrdenada.add(ju);
                jDesordenada.remove(op);
                op = 0;
            }

            if (op == jDesordenada.size() - 1) {
                alturaMax--;
                op = 0;
            }

            if (jDesordenada.isEmpty()) {
                break;
            }
        }

        Comparator<Jugadora> comparaPorPosicion = (Jugadora j1, Jugadora j2) -> j1.getPosicion().compareToIgnoreCase(j2.getPosicion());
        jOrdenada.sort(comparaPorPosicion);

        return jOrdenada;
    }

    public ArrayList<Equipo> getEquiposOrdenados() {
        ArrayList<Equipo> equipos = lf.getEquipos();
        ArrayList<Equipo> equiposOrdenados = new ArrayList();

        Collections.sort(equipos);

        return equipos;
    }

    public ArrayList<Jugadora> cogerJugadoraPorLetra(int opcE, char letra) {
        Equipo e = lf.getEquipos().get(opcE - 1);
        ArrayList<Jugadora> jugadoras = e.getJugadoras();
        ArrayList<Jugadora> jugadorasAEntregar = new ArrayList();

        for (Jugadora j : jugadoras) {
            if (j.getNombre().charAt(0) == letra || j.getNombre().charAt(0) == Character.toUpperCase(letra)) {
                jugadorasAEntregar.add(j);
            }

        }

        Comparator<Jugadora> comparaPorFecha = (Jugadora j1, Jugadora j2) -> j1.getNacimineto().cogerFecha().compareToIgnoreCase(j2.getNacimineto().cogerFecha());
        jugadorasAEntregar.sort(comparaPorFecha);

        return jugadorasAEntregar;
    }

    public void guardarJugadorasEquipo(int opc) throws FileNotFoundException {
        Equipo e = lf.getEquipos().get(opc - 1);
        ArrayList<Jugadora> jugadoras = e.getJugadoras();

        String ruta = System.getProperty("user.home")
                + File.separator + "Desktop"
                + File.separator + "LigFemBal"
                + File.separator + "fichsalida"
                + File.separator + e.getNombreEquipoSinAcentos() + ".enc";

        

        

        PrintWriter wr = new PrintWriter(ruta);

        for (Jugadora j : jugadoras) {
            wr.printf("%-25s %-10s %-3d %-10s %-20s %-15s %-15s %-3f \n",
                    j.getNombre(),
                    j.getPosicion(),
                    j.getDorsal(),
                    j.getNacimineto().cogerFecha(),
                    j.getNacimineto().getCiudad(),
                    j.getNacimineto().getProvincia(),
                    j.getNacionalidad(),
                    j.getAltura());
        }

        wr.close();
    }

    public void imprimirEquipos() throws FileNotFoundException {
        ArrayList<Equipo> equipos = getEquiposOrdenados();

        String ruta = System.getProperty("user.home")
                + File.separator + "Desktop"
                + File.separator + "LigFemBal"
                + File.separator + "fichsalida"
                + File.separator + "equipos.enc";
        
        PrintWriter wr = new PrintWriter(ruta);
        
        for(Equipo e : equipos){
            wr.printf("%-25s %-12d %-30s %-30s",
                    e.getNombreEquipo(),
                    e.getTelefono(),
                    e.getEmail(),
                    e.getWeb());
        }
        
        wr.close();
    }

    public void clasificacionJornadaHTML(int numJornada) throws FileNotFoundException {
        lf.clasificacionJornadaHTML(numJornada);
    }
    
    public ArrayList<Datos_equipo> ordenarClasificacion(Jornada j){
        ArrayList<Datos_equipo> ordenado = j.getClasificacion();
        Collections.sort(ordenado);
        return ordenado;
        
        
        /*int maximosPuntos = 0;
        ArrayList<Datos_equipo> aOrdenar = j.getClasificacion();
        
                for (Datos_equipo de : j.getClasificacion()) {
                    if (de.getPuntosClasificacion() > maximosPuntos) {
                        maximosPuntos = de.getPuntosClasificacion();
                    }
                }
                
                
                ArrayList<Datos_equipo> clasOrdenada = new ArrayList();
                
                //ArrayList<Datos_equipo> aOrdenar = lf.ordenarClasificacion(j);
                int max = maximosPuntos;

                

                for (int or = 0; or < aOrdenar.size(); or++) {

                    Datos_equipo de = aOrdenar.get(or);

                    if (de.getPuntosClasificacion() >= max) {
                        clasOrdenada.add(de);
                        aOrdenar.remove(or);
                        or = 0;
                    }

                    if (or == aOrdenar.size() - 1) {
                        max--;
                        or = 0;
                    }

                    if (aOrdenar.size() == 1) {
                        break;
                    }
                }
                return clasOrdenada;*/
    }

    public void guardarEnFicheroBinario() throws IOException {
        lf.guardarEnFicheroBinario();
        
    }

    public void leerFicheroBinario() throws ClassNotFoundException {
        lf.leerFicheroBinario();
        
    }

}
