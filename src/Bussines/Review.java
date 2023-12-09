package Bussines;

public class Review {

    private String qualityStars;
    private String commentary;

    public Review(String qualityStars, String commentary) {
        this.qualityStars = qualityStars;
        this.commentary = commentary;
    }

    public int getStars(){
        return 0;
    }

    public String getCommentary(){
        return commentary;
    }
}
