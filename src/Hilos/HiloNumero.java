/** **********************************************
 * Autores: Cristopher Alexis Zarate Valencia  *
 * Fecha de creación: 8 ene. 2024                *
 * Descripción: Clase para cambiar de panel.
 *********************************************** */
package Hilos;

import java.awt.Color;
import java.awt.Image;
import java.util.HashMap;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.plaf.BorderUIResource;

public class HiloNumero extends Thread {

    private HashMap<Integer, Image> lista;
    private JLabel etiqueta;

    private int sleepTime = 300;
    
    private int result;

    public HiloNumero(HashMap<Integer, Image> lista, JLabel etiqueta) {
        this.lista = lista;
        this.etiqueta = etiqueta;
    }

    public int getResult() {
        return result;
    }    

    @Override
    public void run() {
        int maxTime = new Random().nextInt(50);
        
        try {
            int i = 0;
            
            etiqueta.setBorder(null);
            do {
                result = new Random().nextInt(lista.size());
                etiqueta.setIcon(new ImageIcon(lista.get(result)));
                Thread.sleep(sleepTime);
                i ++;
            } while (i < maxTime);
            
            etiqueta.setBorder(new BorderUIResource.LineBorderUIResource(Color.yellow));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
