/** **********************************************
 * Autores: Cristopher Alexis Zarate Valencia  *
 * Fecha de creación: 30 dic. 2023     *
 * Descripción: Clase para
 *********************************************** */
package controller;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import javax.swing.ImageIcon;

public class ImagenController {

    private static ImageWriteParam iwp;

    // Autor: Cristopher Alexis Zarate Valencia
    /**
     * Método que carga una imagen desde un directorio y la guarda en un arreglo
     * de bytes.
     *
     * @param path Directorio de la imagen.
     * @return El arreglo de bytes con la imagen cargada.
     * @throws Exception de tipo {@link IOException} al intentar abrir un flujo
     * de archivo.
     */
    private static byte[] loadImagen(String path) throws Exception {
        File imagenFile = new File(path);
        byte[] imagenBytes = new byte[(int) imagenFile.length()];

        try (FileInputStream fis = new FileInputStream(imagenFile)) {
            fis.read(imagenBytes);
        }

        return imagenBytes;
    }

    // Autor: Cristopher Alexis Zarate Valencia
    /**
     * Método para configurar los parametros de compresion.
     *
     * @param writer El escritor de la imagen.
     */
    private static void configParametros(ImageWriter writer) {
        iwp = writer.getDefaultWriteParam();
        iwp.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
        iwp.setCompressionQuality(0.2f); // Ajusta la calidad        
    }

    // Autor: Cristopher Alexis Zarate Valencia
    /**
     * Método que carga una imagen desde un directorio y la guarda optimizada en
     * un arreglo de {@link byte}.
     *
     * @param path El directorio de la imagen.
     * @return El arreglo de bytes con la imagen cargaga ya optimizada.
     * @throws Exception Exception de tipo {@link IOException} al intentar abrir
     * un flujo de archivo.
     */
    private static byte[] loadImagenOptimizada(String path) throws Exception {
        // Leer la imagen original
        BufferedImage imagenOriginal = ImageIO.read(new File(path));

        // Configurar el parámetro de compresión
        Iterator<ImageWriter> iter = ImageIO.getImageWritersByFormatName(
                path.substring(path.lastIndexOf('.') + 1));
        ImageWriter writer = iter.next();

        configParametros(writer);

        // Crear un flujo de bytes en lugar de un arreglo de salida
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try (ImageOutputStream salida = ImageIO.createImageOutputStream(byteArrayOutputStream)) {
            writer.setOutput(salida);
            // Escribir la imagen optimizada
            writer.write(null, new IIOImage(imagenOriginal, null, null), iwp);
        } catch (Exception e) {
            return null;
        } finally {
            // Cerrar los flujos
            writer.dispose();
        }

        return byteArrayOutputStream.toByteArray();
    }

    // Autor: Cristopher Alexis Zarate Valencia
    /**
     * Método para cargar una imgen desde un directorio y guardarla en un objeto
     * {@link Image}.
     *
     * @param path Directorio de la imagen.
     * @param isOptimizada Bandera que indica si se usara la compresion de la
     * imagen.
     * @param size Arreglo con el tamaño que se desea redimencionar la imagen.
     * @return Devuleve un objeto {@link Image} con la imagen redimencionada,
     * cargada y optimizada si así se indicó.
     * @throws Exception Exception Exception de tipo {@link IOException} al
     * intentar abrir un flujo de archivo.
     */
    public static Image loadImagen(String path, boolean isOptimizada, int... size) throws Exception {
        return new ImageIcon(
                (isOptimizada ? loadImagenOptimizada(path) : loadImagen(path))).getImage().getScaledInstance(
                (size.length > 0 ? size[0] : 150),
                (size.length > 1 ? size[1] : 150),
                4);
    }

    // Autor: Cristopher Alexis Zarate Valencia
    /**
     * Método para cargar una imagen desde un arreglo de bytes y guardarlo en un
     * objeto {@link Image}.
     *
     * @param img El arreglo de bytes.
     * @param size Un arreglo con el ancho y el alto de la imagen.
     * @return Devuelve un objeto {@link Image} con la imagen redimencionada.
     * @throws Exception Exception Exception de tipo {@link IOException} al
     * intentar abrir un flujo de archivo.
     */
    public static Image loadImagen(byte[] img, int... size) throws Exception {
        return new ImageIcon(img).getImage().getScaledInstance(
                (size.length > 0 ? size[0] : 150),
                (size.length > 1 ? size[1] : 150),
                4);
    }

    public static byte[] loadImagen(String path, boolean isOptimizada) throws Exception {
        return (isOptimizada ? loadImagenOptimizada(path) : loadImagen(path));
    }

    /**
     * Convierte un objeto {@link Image} en un {@link byte[]}
     *
     * @param image La imagen que debe convertir, es de tipo {@link Image}.
     * @return El {@link bytes[]} con la imagen cargada.
     * @throws IOException
     */
    public static byte[] imageToBytes(Image image) throws IOException {
        BufferedImage bufferedImage = ImageToBuffered(image);

        byte[] bytes;
        // Escribir la imagen en formato PNG en el flujo de salida
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            // Escribir la imagen en formato PNG en el flujo de salida
            ImageIO.write(bufferedImage, "png", baos);
            // Obtener el array de bytes resultante
            bytes = baos.toByteArray();
            // Cerrar el flujo de salida
        }

        return bytes;
    }

    private static BufferedImage ImageToBuffered(Image image) {
        // Crear una BufferedImage para dibujar la imagen
        BufferedImage bufferedImage = new BufferedImage(
                image.getWidth(null),
                image.getHeight(null),
                BufferedImage.TYPE_INT_RGB);

        // Dibujar la imagen en la BufferedImage
        bufferedImage.getGraphics().drawImage(image, 0, 0, null);

        return bufferedImage;
    }
}
