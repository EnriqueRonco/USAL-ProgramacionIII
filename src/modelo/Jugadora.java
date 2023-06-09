/*
Enrique Mesonero Ronco DNI:52417500V
 */
package modelo;



/**
 *
 * @author Enrique
 */
public class Jugadora implements java.io.Serializable{

    private String nombre;
    private String posicion;
    private int Dorsal;
    private FechaNac nacimineto;
    private String nacionalidad;
    private float altura;

    public Jugadora() {
    }

    static Jugadora FactoryMethod(String s) {
        String ju[] = s.split("\t");

        if (ju.length == 5) {
            if (ju[2].equals("-")) {
                //String fechaNac[] = ju[3].split("\\(");
                FechaNac feNac = new FechaNac(ju[3]);
                Jugadora j = new Jugadora(ju[0], "Posicion desconocida", 00, feNac, ju[4], 0.0f);
                return j;
            } else {
                //String fechaNac[] = ju[3].split("\\(");
                FechaNac feNac = new FechaNac(ju[3]);
                Jugadora j = new Jugadora(ju[0], ju[1], Integer.parseInt(ju[2]), feNac, ju[4], 0.0f);
                return j;
            }
        } else {

            if (ju[2].equals("-")) {
                if (ju[5].equals("-")) {
                    //String fechaNac[] = ju[3].split("\\(");
                    FechaNac feNac = new FechaNac(ju[3]);
                    Jugadora j = new Jugadora(ju[0], "Posicion desconocida", 00, feNac, ju[4], 0.0f);
                    return j;
                } else {
                    //String fechaNac[] = ju[3].split("\\(");
                    FechaNac feNac = new FechaNac(ju[3]);
                    Jugadora j = new Jugadora(ju[0], "Posicion desconocida", 00, feNac, ju[4], Float.parseFloat(ju[5]));
                    return j;
                }
            } else {
                if (ju[5].equals("-")) {
                    //String fechaNac[] = ju[3].split("\\(");
                    FechaNac feNac = new FechaNac(ju[3]);
                    Jugadora j = new Jugadora(ju[0], ju[1], Integer.parseInt(ju[2]), feNac, ju[4], 0.0f);
                    return j;
                } else {
                    //String fechaNac[] = ju[3].split("\\(");
                    FechaNac feNac = new FechaNac(ju[3]);
                    Jugadora j = new Jugadora(ju[0], ju[1], Integer.parseInt(ju[2]), feNac, ju[4], Float.parseFloat(ju[5]));
                    return j;
                }
            }
        }
    }

    public Jugadora(String nombre, String posicion, int dorsal, FechaNac feNac, String nacionalidad, float altura) {
        this.nombre = nombre;
        this.posicion = posicion;
        this.Dorsal = dorsal;
        this.nacimineto = feNac;
        this.nacionalidad = nacionalidad;
        this.altura = altura;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public int getDorsal() {
        return Dorsal;
    }

    public void setDorsal(int Dorsal) {
        this.Dorsal = Dorsal;
    }

    public FechaNac getNacimineto() {
        return nacimineto;
    }

    public void setNacimineto(FechaNac nacimineto) {
        this.nacimineto = nacimineto;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public float getAltura() {
        return altura;
    }

    public void setAltura(float altura) {
        this.altura = altura;
    }

    

    public static class FechaNac implements java.io.Serializable{

        String[] fecha = new String[3];
        String ciudad;
        String provincia;

        public FechaNac() {
        }

        public FechaNac(String fechaNac) {

            String fechaVec[] = fechaNac.split("\\(");
            String fecha = fechaVec[0].substring(0, 10);

                if (fechaVec.length == 1) {
                    this.ciudad = "desconocido";
                    this.provincia = "desconocido";
                } else {
                    this.ciudad = fechaVec[0].substring(10);
                    this.provincia = fechaVec[1];
                }
            
                this.fecha = fecha.split("/");
            
        }

        public String cogerFecha() {
            String fe = "Dia " + fecha[0] + " Mes " + fecha[1] + " Anio " + fecha[2];
            return fe;
        }

        public void setFecha(String[] fecha) {
            this.fecha = fecha;
        }

        public String getCiudad() {
            return ciudad;
        }

        public void setCiudad(String ciudad) {
            this.ciudad = ciudad;
        }

        public String getProvincia() {
            return provincia;
        }

        public void setProvincia(String provincia) {
            this.provincia = provincia;
        }

    }

}
