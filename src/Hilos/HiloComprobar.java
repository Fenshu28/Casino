/** **********************************************
 * Autores: Cristopher Alexis Zarate Valencia  *
 * Fecha de creación: 8 ene. 2024                *
 * Descripción: Clase para cambiar de panel.
 *********************************************** */
package Hilos;

import casino.Casino;
import javax.swing.JOptionPane;

public class HiloComprobar extends Thread {

    private Casino casino;

    public HiloComprobar(Casino casino) {
        this.casino = casino;
    }

    @Override
    public void run() {
        do {
        } while (casino.h1.isAlive()
                || casino.h2.isAlive()
                || casino.h3.isAlive());

        if (!casino.comprobar()) {
            casino.setMonedas(casino.getMonedas() - 10);
            JOptionPane.showMessageDialog(null, "Perdiste");

        } else {
            casino.setMonedas(casino.getMonedas() + 10);
            JOptionPane.showMessageDialog(null, "Ganaste!!");
        }

        casino.getLbMoneda().setText("Monedas: " + casino.getMonedas());
    }

}
