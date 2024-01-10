/** **********************************************
 * Autores: Cristopher Alexis Zarate Valencia  *
 * Fecha de creación: 10 ene. 2024                *
 * Descripción: Clase para cambiar de panel.
 *********************************************** */
package caballitos.hilos;

import java.util.Random;
import javax.swing.JProgressBar;

public class HiloCaballo extends Thread {

    private JProgressBar cab;
    private int value = 0;

    public HiloCaballo(JProgressBar cab) {
        this.cab = cab;
    }

    public int getValue() {
        return value;
    }

    @Override
    public void run() {
        do {
            try {
                value += new Random().nextInt(1, 6);
                cab.setValue(value);
                sleep(500);
            } catch (InterruptedException ex) {
                System.out.println(ex.getMessage());
            }
        } while (value <= 100);
    }

}
