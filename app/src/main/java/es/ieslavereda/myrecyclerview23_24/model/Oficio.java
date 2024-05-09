package es.ieslavereda.myrecyclerview23_24.model;

public class Oficio {

    private int idOficio;
    private String descripcion;
    private String imageUrl;

    public Oficio() {
    }

    public Oficio(int idOficio, String descripcion, String imageUrl) {
        this.idOficio = idOficio;
        this.descripcion = descripcion;
        this.imageUrl = imageUrl;
    }

    public int getIdOficio() {
        return idOficio;
    }

    public void setIdOficio(int idOficio) {
        this.idOficio = idOficio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
