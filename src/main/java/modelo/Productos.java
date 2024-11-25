package modelo;

public class Productos {
    //defino olas variables de lo productos
    private int idProductos;
    private String nombre;
    private String categoria;
    private double precio;

    //implementamos un constructor vacio
    public Productos() {

    }
    // implemebntamios un copnstructor con todos los atribnutos


    public Productos(int idProductos, String nombre, String categoria, double precio) {
        this.idProductos = idProductos;
        this.nombre = nombre;
        this.categoria = categoria;
        this.precio = precio;
    }

    public int getIdProductos() {
        return idProductos;
    }

    public void setIdProductos(int idProductos) {
        this.idProductos = idProductos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
