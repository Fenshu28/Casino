/** **********************************************
 * Autores: Cristopher Alexis Zarate Valencia  *
 * Fecha de creación: 10 ene. 2024                *
 * Descripción: Clase para cambiar de panel.
 *********************************************** */
package caballitos.hilos;

import caballitos.Caballos;
import java.util.ArrayList;
import java.util.List;

public class HiloCompCaballo extends Thread {

    private List<String> puestos;

    public HiloCompCaballo() {
        puestos = new ArrayList<>();
    }

    @Override
    public void run() {
        while (true) {
            int i = 1;
            for (HiloCaballo caballo : Caballos.caballos) {
                if (caballo.getValue() >= 100
                        && !puestos.contains("Caballo " + i)) {
                    puestos.add("Caballo " + i);
                }
                i++;
            }
            if (puestos.size() >= 4) {
                break;
            }
        }

        System.out.println("Primer lugar " + puestos.get(0));
        System.out.println("Segundo lugar " + puestos.get(1));
        System.out.println("Tercer  lugar " + puestos.get(2));

        if (("Caballo " + Caballos.apostar).equals(puestos.get(0))) {
            System.out.println("Ganaste!!");
        } else {
            System.out.println("Perdiste!!");
        }
    }

}
