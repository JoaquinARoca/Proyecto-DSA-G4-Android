package dsa.proyectoandroid.g6;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import dsa.proyectoandroid.g6.models.Message;

public class MessageAdapter extends BaseAdapter {
    private Context context;
    private List<Message> messages;

    // Constructor
    public MessageAdapter(Context context, List<Message> messages) {
        this.context = context;
        this.messages = messages;
    }

    @Override
    public int getCount() {
        return messages.size();  // Devuelve la cantidad de mensajes en la lista
    }

    @Override
    public Object getItem(int position) {
        return messages.get(position);  // Devuelve el mensaje en la posición especificada
    }

    @Override
    public long getItemId(int position) {
        return position;  // Devuelve la ID del ítem (en este caso, simplemente la posición)
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Si la vista es nula, infla una nueva vista directamente
        if (convertView == null) {
            convertView = new TextView(context);
            ((TextView) convertView).setTextSize(16);  // Tamaño de texto por defecto
        }

        // Obtén el mensaje en la posición correspondiente
        Message message = messages.get(position);

        // Configura el TextView para mostrar el mensaje
        TextView textViewMessage = (TextView) convertView;
        textViewMessage.setText(message.getMessage());

        return convertView;
    }
}