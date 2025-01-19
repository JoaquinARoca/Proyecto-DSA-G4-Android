package dsa.proyectoandroid.g6.models;

public class Message {
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message; // Muestra el mensaje directamente
    }
}