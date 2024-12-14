package dsa.proyectoandroid.g6.models;

public class SavedPreferences {
    private static SavedPreferences instance;
    private User my_user;


    private SavedPreferences() {}

    public static SavedPreferences getInstance(){
        if(instance==null)
            instance = new SavedPreferences();
        return instance;
    }

    public SavedPreferences(User my_user) {
        this.my_user = my_user;
    }

    public User getMy_user() {
        return my_user;
    }

    public void setMy_user(User my_user) {
        this.my_user = my_user;
    }
}
