/*
Enrique Mesonero Ronco DNI:52417500V
 */
package modelo;


import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Enrique
 */
public class Jornada implements java.io.Serializable{

    private int numeroJornada;
    private String fecha;
    private ArrayList<Partido> partidos = new ArrayList();
    private ArrayList<Datos_equipo> clasificacion = new ArrayList();

    public Jornada() {
    }

    static Jornada FactoryMethod(String s) {
        
        ArrayList<String[]> datosPartido = new ArrayList();
        String atribJorn[] = s.split("\\+");
        String partidosJorn[] = atribJorn[2].split("#");
        

        for (int i = 0; i < partidosJorn.length; i++) {
            String d[] = partidosJorn[i].split("\\$");
            datosPartido.add(d);
        }
        
        Jornada jor = new Jornada(atribJorn[0], atribJorn[1], datosPartido);
        return jor;

    }

    private Jornada(String numeroJornada, String fecha, List<String[]> datosPartido) {
        this.numeroJornada = Integer.parseInt(numeroJornada);
        this.fecha = fecha;
        for (String[] s : datosPartido) {

            Partido p = new Partido(s[0], s[1], "0", "0", s[2], s[3]);
            Datos_equipo d = new Datos_equipo(s[0], 0, 0, 0, 0, 0, 0);
            Datos_equipo d2 = new Datos_equipo(s[1], 0, 0, 0, 0, 0, 0);
            partidos.add(p);
            clasificacion.add(d);
            clasificacion.add(d2);
        }
    }

    public int getNumeroJorada() {
        return numeroJornada;
    }

    public void setNumeroJorada(int numeroJorada) {
        this.numeroJornada = numeroJorada;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public ArrayList<Partido> getPartidos() {
        return partidos;
    }

    public void setPartidos(ArrayList<Partido> partidos) {
        this.partidos = partidos;
    }

    public ArrayList<Datos_equipo> getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(ArrayList<Datos_equipo> clasificacion) {
        this.clasificacion = clasificacion;
    }

    static String transformarNumeroAPalabra(int numero) {

        String numeroADevolver = "";

        if (numero <= 15) {
            String numerosHastaQuince[] = {
                "cero",
                "uno",
                "dos",
                "tres",
                "cuatro",
                "cinco",
                "seis",
                "siete",
                "ocho",
                "nueve",
                "diez",
                "once",
                "doce",
                "trece",
                "catorce",
                "quince"
            };

            numeroADevolver = numerosHastaQuince[numero];
        } else {
            String numerosDecenas[] = {
                "dieci",
                "veinti",
                "treintai",
                "cuarentai",
                "cincuentai",
                "sesentai",
                "setentai",
                "ochentai",
                "noventai"
            };

            String numerosUnidades[] = {
                "uno",
                "dos",
                "tres",
                "cuatro",
                "cinco",
                "seis",
                "siete",
                "ocho",
                "nueve"
            };

            if (numero >= 16 && numero < 20) {
                int unidades = (numero - 10);
                numeroADevolver = numerosDecenas[0] + numerosUnidades[unidades - 1];
            }

            if (numero >= 20 && numero < 30) {
                int unidades = (numero - 20);
                if (unidades == 0) {
                    numeroADevolver = "veinte";
                } else {
                    numeroADevolver = numerosDecenas[1] + numerosUnidades[unidades - 1];
                }
            }

            if (numero >= 30 && numero < 40) {
                int unidades = (numero - 30);
                if (unidades == 0) {
                    numeroADevolver = "treinta";
                } else {
                    numeroADevolver = numerosDecenas[2] + numerosUnidades[unidades - 1];
                }
            }
            
            if (numero >= 40 && numero < 50) {
                int unidades = (numero - 40);
                if (unidades == 0) {
                    numeroADevolver = "cuarenta";
                } else {
                    numeroADevolver = numerosDecenas[3] + numerosUnidades[unidades - 1];
                }
            }
            
            if (numero >= 50 && numero < 60) {
                int unidades = (numero - 50);
                if (unidades == 0) {
                    numeroADevolver = "cincuenta";
                } else {
                    numeroADevolver = numerosDecenas[4] + numerosUnidades[unidades - 1];
                }
            }
            
            if (numero >= 60 && numero < 70) {
                int unidades = (numero - 60);
                if (unidades == 0) {
                    numeroADevolver = "sesenta";
                } else {
                    numeroADevolver = numerosDecenas[5] + numerosUnidades[unidades - 1];
                }
            }
            
            if (numero >= 70 && numero < 80) {
                int unidades = (numero - 70);
                if (unidades == 0) {
                    numeroADevolver = "setenta";
                } else {
                    numeroADevolver = numerosDecenas[6] + numerosUnidades[unidades - 1];
                }
            }
            
            if (numero >= 80 && numero < 90) {
                int unidades = (numero - 80);
                if (unidades == 0) {
                    numeroADevolver = "ochenta";
                } else {
                    numeroADevolver = numerosDecenas[7] + numerosUnidades[unidades - 1];
                }
            }
            
            if (numero >= 90 && numero < 1000) {
                int unidades = (numero - 90);
                if (unidades == 0) {
                    numeroADevolver = "noventa";
                } else {
                    numeroADevolver = numerosDecenas[8] + numerosUnidades[unidades - 1];
                }
            }

        }
        return numeroADevolver;
    }

}
