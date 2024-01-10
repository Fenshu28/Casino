/** **********************************************
 * Autores: Cristopher Alexis Zarate Valencia  *
 * Fecha de creación: 10 ene. 2024                *
 * Descripción: Clase para cambiar de panel.
 *********************************************** */
package caballitos.hilos;

import caballitos.Caballos;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class HiloCompCaballo extends Thread {

    private List<String> puestos;
    private String resultado = "";

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

        int i = 1;
        for (String puesto : puestos) {
            resultado += i + ".- " + puesto + "\n";
            i++;
        }

        if (("Caballo " + Caballos.apostar).equals(puestos.get(0))) {
            resultado += "\nGanaste!!";
        } else {
            resultado += "\nPerdiste!!";
        }

        JOptionPane.showMessageDialog(null, resultado);
    }

}
