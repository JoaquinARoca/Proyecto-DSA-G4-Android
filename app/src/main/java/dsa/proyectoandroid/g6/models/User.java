package dsa.proyectoandroid.g6.models;

public class User {
    private String id;
    private String nombre;
    private String contraseña;
    private Integer saldo;
    private String perfil;

    public User() {
    }

    public User(String id, String name, String passwd) {
        this.id = id;
        this.nombre = name;
        this.contraseña = passwd;
    }

    public User(String passwd, String name) {
        this.contraseña = passwd;
        this.nombre = name;
    }

    public User(String id, String nombre, String contraseña, Integer saldo, String perfil) {
        this.nombre = nombre;
        this.id = id;
        this.contraseña = contraseña;
        this.saldo = saldo;
        this.perfil = perfil;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return nombre;
    }
    public void setName(String name) {
        this.nombre = name;
    }

    public String getPasswd() {
        return contraseña;
    }
    public void setPasswd(String passwd) {
        this.contraseña = passwd;
    }

    public Integer getSaldo() {
        return saldo;
    }
    public void setSaldo(Integer saldo) {
        this.saldo = saldo;
    }

    public String getPerfil() {
        return perfil;
    }
    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }
}
