/*
Enrique Mesonero Ronco DNI:52417500V
 */
package modelo;



/**
 *
 * @author Enrique
 */
public class Partido implements java.io.Serializable {
    private String equipoLocal;
    private String equipoVisitante;
    private int puntosLocal;
    private int puntosVisitante;
    private String[] fechaPartido = new String[3];
    private String[] horaPartido = new String[2];

    public Partido() {
    }

    Partido(String eqLocal, String eqVisit, String pLoc, String pVis, String fecha, String hora) {
        this.equipoLocal = eqLocal;
        this.equipoVisitante = eqVisit;
        this.puntosLocal = Integer.parseInt(pLoc);
        this.puntosVisitante = Integer.parseInt(pVis);
        String fechaP[] = fecha.split("/");
        this.fechaPartido = fechaP;
        String horaP[] = hora.split(":");
        this.horaPartido = horaP;
    }

    public String getEquipoLocal() {
        return equipoLocal;
    }

    public void setEquipoLocal(String equipoLocal) {
        this.equipoLocal = equipoLocal;
    }

    public String getEquipoVisitante() {
        return equipoVisitante;
    }

    public void setEquipoVisitante(String equipoVisitante) {
        this.equipoVisitante = equipoVisitante;
    }

    public int getPuntosLocal() {
        return puntosLocal;
    }

    public void setPuntosLocal(int puntosLocal) {
        this.puntosLocal = puntosLocal;
    }

    public int getPuntosVisitante() {
        return puntosVisitante;
    }

    public void setPuntosVisitante(int puntosVisitante) {
        this.puntosVisitante = puntosVisitante;
    }

    public String[] getFechaPartido() {
        return fechaPartido;
    }

    public void setFechaPartido(String[] fechaPartido) {
        this.fechaPartido = fechaPartido;
    }

    public String[] getHoraPartido() {
        return horaPartido;
    }

    public void setHoraPartido(String[] horaPartido) {
        this.horaPartido = horaPartido;
    }
    
    
}
