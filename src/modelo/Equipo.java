/*
Enrique Mesonero Ronco DNI:52417500V
 */
package modelo;


import java.text.Normalizer;
import java.util.ArrayList;

/**
 *
 * @author Enrique
 */
public class Equipo implements Comparable<Equipo>, java.io.Serializable {

    
    private String nombreEquipo;
    private String direccion;
    private int telefono;
    private String web;
    private String email;
    private ArrayList<Jugadora> jugadoras= new ArrayList();

    public Equipo() {
    }
    
    static Equipo FactoryMethod(String se) {
        String equipo[] = se.split("#");
        Equipo e = new Equipo(equipo[0], equipo[1], Integer.parseInt(equipo[2]), equipo[3], equipo[4]);
        return e;
    }
    
    public Equipo(String nombreEquipo, String direccion, int telefono, String web, String email) {
        this.nombreEquipo = nombreEquipo;
        this.direccion = direccion;
        this.telefono = telefono;
        this.web = web;
        this.email = email;
        this.jugadoras = null;
    }

    public void anadirJugadoras(ArrayList<Jugadora> jugadoras) {
        this.jugadoras=jugadoras;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombreEquipo() {
        return nombreEquipo;
    }

    public void setNombreEquipo(String nombreEquipo) {
        this.nombreEquipo = nombreEquipo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public ArrayList<Jugadora> getJugadoras() {
        return jugadoras;
    }

    public void setJugadoras(ArrayList<Jugadora> jugadoras) {
        this.jugadoras = jugadoras;
    }

    public String getNombreEquipoSinAcentos() {
        String texto = Normalizer.normalize(nombreEquipo, Normalizer.Form.NFD);
        texto = texto.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
        return texto.toUpperCase();
    }


    @Override
    public int compareTo(Equipo o) {
        if(o.getTelefono()>telefono){
            return -1;
        }else if(o.getTelefono()==telefono){
            return 0;
        }else{
            return 1;
        }
    }

    
    
    
    
}
