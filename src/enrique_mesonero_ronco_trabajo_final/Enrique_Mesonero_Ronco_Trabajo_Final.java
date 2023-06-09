/*
Enrique Mesonero Ronco DNI:52417500V
 */
package enrique_mesonero_ronco_trabajo_final;

import java.io.IOException;
import vista.Vista;

/**
 *
 * @author Enrique Mesonero Ronco
 */
public class Enrique_Mesonero_Ronco_Trabajo_Final {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     * @throws java.lang.ClassNotFoundException
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Vista v = new Vista();
        v.runMenu(
            "1.Gestion de temporada\n"+
            "2.Gestion de jugadoras\n"+
            "3.Gestion de jornada\n"+
            "4.Visualizacion de resultados\n"+
            "5.Almacenamiento de resultados\n"+
            "q.Salir de la aplicacion\n"
            );
    }

}
