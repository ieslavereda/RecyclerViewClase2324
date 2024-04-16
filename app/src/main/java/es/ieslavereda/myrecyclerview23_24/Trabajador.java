package es.ieslavereda.myrecyclerview23_24;

public class Trabajador {
    private int imagen;
    private String nombre;
    private String apellidos;
    private String oficio;

    public Trabajador(int imagen, String nombre, String apellidos, String oficio) {
        this.imagen = imagen;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.oficio = oficio;
    }

    public int getImagen() {
        return imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getOficio() {
        return oficio;
    }
}
