/*
Enrique Mesonero Ronco DNI:52417500V
 */
package modelo;

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
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Enrique
 */
public class LigaFem{

    private String Temporada;
    private ArrayList<Jornada> jornadas = new ArrayList();
    private ArrayList<Equipo> equipos = new ArrayList();

    public LigaFem() {
    }

    public String getTemporada() {
        return Temporada;
    }

    public void setTemporada(String Temporada) {
        this.Temporada = Temporada;
    }

    public ArrayList<Jornada> getJornadas() {
        return jornadas;
    }

    public void setJornadas(ArrayList<Jornada> jornadas) {
        this.jornadas = jornadas;
    }

    public ArrayList<Equipo> getEquipos() {
        return equipos;
    }

    public void setEquipos(ArrayList<Equipo> equipos) {
        this.equipos = equipos;
    }

    public ArrayList<ArrayList> getJugadoras() {
        ArrayList<ArrayList> jugadoras = new ArrayList();
        for (Equipo e : equipos) {
            jugadoras.add(e.getJugadoras());
        }
        return jugadoras;
    }

    public void cargarPartidos() throws IOException {
        String ruta = System.getProperty("user.home")
                + File.separator + "Desktop"
                + File.separator + "LigFemBal"
                + File.separator + "datosjornadas.txt";

        File f = new File(ruta);
        List<String> datos = null;
        datos = Files.readAllLines(f.toPath());

        for (String s : datos) {
            jornadas.add(Jornada.FactoryMethod(s));
        }
    }

    public void cargarEquipos() throws IOException {
        String ruta = System.getProperty("user.home")
                + File.separator + "Desktop"
                + File.separator + "LigFemBal"
                + File.separator + "datosequipos.txt";

        File fe = new File(ruta);
        List<String> datosEq = null;
        datosEq = Files.readAllLines(fe.toPath());

        for (String d : datosEq) {
            equipos.add(Equipo.FactoryMethod(d));
        }
    }

    public void cargarJugadoras() throws IOException {
        for (Equipo equipJu : equipos) {
            String ruta = System.getProperty("user.home")
                    + File.separator + "Desktop"
                    + File.separator + "LigFemBal"
                    + File.separator + "jugadoras"
                    + File.separator + equipJu.getNombreEquipoSinAcentos() + ".txt";

            File de = new File(ruta);
            List<String> datosEqJu = null;
            datosEqJu = Files.readAllLines(de.toPath());

            ArrayList<Jugadora> jugadoras = new ArrayList();
            for (String s : datosEqJu) {
                jugadoras.add(Jugadora.FactoryMethod(s));
            }
            equipJu.anadirJugadoras(jugadoras);
        }
    }

    public void cargarJornada(int opcJornada) throws IOException {

        Jornada j = jornadas.get(opcJornada - 1);
        String numero = Jornada.transformarNumeroAPalabra(opcJornada);
        System.out.println(numero);

        String ruta = System.getProperty("user.home")
                + File.separator + "Desktop"
                + File.separator + "LigFemBal"
                + File.separator + "resul_jornadas"
                + File.separator + numero + ".txt";

        File jorn = new File(ruta);
        List<String> datos = null;
        datos = Files.readAllLines(jorn.toPath());

        int i = 0;
        for (String s : datos) {

            String datosJornadaPuntos[] = s.split("=");
            int ploc = Integer.parseInt(datosJornadaPuntos[2]);
            int pvis = Integer.parseInt(datosJornadaPuntos[3]);

            j.getPartidos().get(i).setPuntosLocal(ploc);
            j.getPartidos().get(i).setPuntosVisitante(pvis);
            i++;

        }

        System.out.println("Guardado" + j.getClasificacion().size());
        for (Datos_equipo de : j.getClasificacion()) {
            int k = 0;
            for (Partido p : j.getPartidos()) {
                String nombreEquipoLocal = j.getPartidos().get(k).getEquipoLocal();
                String nombreEquipoVisitante = j.getPartidos().get(k).getEquipoVisitante();
                int pEqLoc = j.getPartidos().get(k).getPuntosLocal();
                int pEqVis = j.getPartidos().get(k).getPuntosVisitante();

                String nombEquipo = de.getNombreEquipo();

                if (nombEquipo.equals(nombreEquipoLocal)) {
                    de.setPJ(de.getPJ() + 1);
                    if (pEqLoc > pEqVis) {
                        de.setPG(de.getPG() + 1);
                    } else {
                        de.setPP(de.getPP() + 1);
                    }
                    de.setPF(de.getPF() + pEqLoc);
                    de.setPC(de.getPC() + pEqVis);
                    de.setPuntosClasificacion((2 * de.getPG()) + de.getPP());
                }

                if (nombEquipo.equals(nombreEquipoVisitante)) {
                    de.setPJ(de.getPJ() + 1);
                    if (pEqVis > pEqLoc) {
                        de.setPG(de.getPG() + 1);
                    } else {
                        de.setPP(de.getPP() + 1);
                    }
                    de.setPF(de.getPF() + pEqVis);
                    de.setPC(de.getPC() + pEqLoc);
                    de.setPuntosClasificacion((2 * de.getPG()) + de.getPP());
                }
                k++;
            }
        }
        if (opcJornada != 1) {
            Jornada jAnterior = jornadas.get(opcJornada - 2);

            for (Datos_equipo datosEquipo : j.getClasificacion()) {
                for (Datos_equipo datosAnterior : jAnterior.getClasificacion()) {
                    if (datosEquipo.getNombreEquipo().equals(datosAnterior.getNombreEquipo())) {
                        datosEquipo.setPJ(datosAnterior.getPJ() + datosEquipo.getPJ());
                        datosEquipo.setPG(datosAnterior.getPG() + datosEquipo.getPG());
                        datosEquipo.setPP(datosAnterior.getPP() + datosEquipo.getPP());
                        datosEquipo.setPF(datosAnterior.getPF() + datosEquipo.getPF());
                        datosEquipo.setPC(datosAnterior.getPC() + datosEquipo.getPC());
                        datosEquipo.setPuntosClasificacion(datosAnterior.getPuntosClasificacion() + datosEquipo.getPuntosClasificacion());

                    }
                }
            }
        }
    }

    public void clasificacionJornadaHTML(int numJornada) throws FileNotFoundException {
        Jornada jornada = getJornadas().get(numJornada - 1);
        ArrayList<Datos_equipo> clasificacion = ordenarClasificacion(jornada);

        String ruta = System.getProperty("user.home")
                + File.separator + "Desktop"
                + File.separator + "LigFemBal"
                + File.separator + "fichsalida"
                + File.separator + "ich_html_" + numJornada + ".html";

        PrintWriter wr = new PrintWriter(ruta);

        wr.printf("<!DOCTYPE html>%n"
                + "<HTML>%n"
                + "<HEAD%n>"
                + "<meta charset=\"UTF-8\">%n"
                + "<H1>CLASIFICACION</H1>%n"
                + "</HEAD>%n"
                + "<BODY>"
        );

        wr.printf("<TABLE BORDER=1>%n");

        wr.printf("<tr>"
                + "<th>Puesto</th>"
                + "<th>EQUIPO</th>"
                + "<th>PJ</th>"
                + "<th>PG</th>"
                + "<th>PP</th>"
                + "<th>PF</th>"
                + "<th>PC</th>"
                + "<th>PTS</th>"
                + "</tr>"
        );

        int i = 1;
        for (Datos_equipo e : clasificacion) {

            wr.printf("<tr>");
            wr.println(
                    "<td>" + i + "</td>"
                    + "<td>" + e.getNombreEquipo() + "</td>"
                    + "<td>" + e.getPJ() + "</td>"
                    + "<td>" + e.getPG() + "</td>"
                    + "<td>" + e.getPP() + "</td>"
                    + "<td>" + e.getPF() + "</td>"
                    + "<td>" + e.getPC() + "</td>"
                    + "<td>" + e.getPuntosClasificacion() + "</td>"
            );
            wr.printf("</tr>");
            i++;
        }
        wr.printf("</TABLE>%n</BODY>%n</HTML>%n");

        wr.close();
    }

    public ArrayList<Datos_equipo> ordenarClasificacion(Jornada j) {
        ArrayList<Datos_equipo> aOrdenar = j.getClasificacion();
        Collections.sort(aOrdenar);
        return aOrdenar;
    }

    public void guardarEnFicheroBinario() throws FileNotFoundException, IOException {
        String ruta = System.getProperty("user.home")
                + File.separator + "Desktop"
                + File.separator + "LigFemBal"
                + File.separator + "binarios"
                + File.separator + "archivoBinario.bin";

        try {
            FileOutputStream fos = new FileOutputStream(ruta);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(Temporada);
            oos.writeObject(jornadas);
            oos.writeObject(equipos);
            
            oos.close();
        } catch (FileNotFoundException ex) {
            err.printf("\n--No se ha encontrado el archivo-\n");
        } catch (IOException ex) {
            err.printf("\n--Error de I/O--\n" + ex);
        }
    }

    public void leerFicheroBinario() throws ClassNotFoundException {
        String ruta = System.getProperty("user.home")
                + File.separator + "Desktop"
                + File.separator + "LigFemBal"
                + File.separator + "binarios"
                + File.separator + "archivoBinario.bin";

        try {
            FileInputStream fis = new FileInputStream(ruta);
            ObjectInputStream ois = new ObjectInputStream(fis);

            this.Temporada = (String) ois.readObject();
            this.jornadas = (ArrayList<Jornada>) ois.readObject();
            this.equipos = (ArrayList<Equipo>) ois.readObject();        
            ois.close();
            
        } catch (FileNotFoundException ex) {
            err.printf("\n--No se ha encontrado el archivo-\n");
        } catch (IOException ex) {
            err.printf("\n--Error de I/O--\n" + ex);
        } catch (ClassNotFoundException ex) {
            err.printf("\n--No se ha encontrado la clase--\n" + ex);
        }
    }
}
