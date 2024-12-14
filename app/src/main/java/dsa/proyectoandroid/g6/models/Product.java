package dsa.proyectoandroid.g6.models;

public class Product {
    private String id;
    private String name;
    private Double precio;

    //Constructors
    public Product() {
    }

    public Product(String id, String name, Double precio) {
        this.id = id;
        this.name = name;
        this.precio = precio;
    }

    //Getters & Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }
}
