package Bussines;

/**
 * Clase que representa una revisión de un producto.
 */
@SuppressWarnings("SpellCheckingInspection")
public class Review {

    private String qualityStars;
    private String commentary;

    /**
     * Constructor que inicializa una revisión con las estrellas de calidad y el comentario proporcionados.
     *
     * @param qualityStars Estrellas de calidad de la revisión.
     * @param commentary   Comentario de la revisión.
     */
    public Review(String qualityStars, String commentary) {
        this.qualityStars = qualityStars;
        this.commentary = commentary;
    }

    /**
     * Obtiene la cantidad de estrellas de calidad de la revisión.
     *
     * @return Cantidad de estrellas de calidad.
     */
    public int getStars(){
        return qualityStars.length();
    }

    /**
     * Obtiene el comentario de la revisión.
     *
     * @return Comentario de la revisión.
     */
    public String getCommentary(){
        return commentary;
    }
}
