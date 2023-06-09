/*
Enrique Mesonero Ronco DNI:52417500V
 */
package modelo;



/**
 *
 * @author Enrique
 */
public class Datos_equipo implements Comparable<Datos_equipo>, java.io.Serializable{

    private String nombreEquipo;
    private int PJ;
    private int PG;
    private int PP;
    private int PF;
    private int PC;
    private int puntosClasificacion;

    public Datos_equipo() {
    }

    public Datos_equipo(String nombreEquipo, int PJ, int PG, int PP, int PF, int PC, int puntosClasificacion) {
        this.nombreEquipo = nombreEquipo;
        this.PJ = PJ;
        this.PG = PG;
        this.PP = PP;
        this.PF = PF;
        this.PC = PC;
        this.puntosClasificacion = puntosClasificacion;
    }

    public String getNombreEquipo() {
        return nombreEquipo;
    }

    public void setNombreEquipo(String nombreEquipo) {
        this.nombreEquipo = nombreEquipo;
    }

    public int getPJ() {
        return PJ;
    }

    public void setPJ(int PJ) {
        this.PJ = PJ;
    }

    public int getPG() {
        return PG;
    }

    public void setPG(int PG) {
        this.PG = PG;
    }

    public int getPP() {
        return PP;
    }

    public void setPP(int PP) {
        this.PP = PP;
    }

    public int getPF() {
        return PF;
    }

    public void setPF(int PF) {
        this.PF = PF;
    }

    public int getPC() {
        return PC;
    }

    public void setPC(int PC) {
        this.PC = PC;
    }

    public int getPuntosClasificacion() {
        return puntosClasificacion;
    }

    public void setPuntosClasificacion(int puntosClasificacion) {
        this.puntosClasificacion = puntosClasificacion;
    }

    @Override
    public int compareTo(Datos_equipo o) {
        if (o.getPuntosClasificacion() > puntosClasificacion) {
            return 1;
        } else if (o.getPuntosClasificacion() == puntosClasificacion) {
            return 0;
        } else {
            return -1;
        }
    }
}
