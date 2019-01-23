package com.example.udevAndroidTp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.udevAndroidTp.R;
import com.example.udevAndroidTp.classes.Client;

public class ClientAdapter extends ArrayAdapter<Client> {

    private Context ctx;
    private int resource;
    private Client[] clients;

    public ClientAdapter(Context context, int resource, Client[] clients) {
        super(context, resource, clients);
        this.ctx = ctx;
        this.resource = resource;
        this.clients = clients;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(
                            R.layout.client_cell,
                            parent,
                            false
                    );
        }

        ClientHolder clientHolder = new ClientHolder();

        clientHolder.nameClientCellTextView = convertView.findViewById(R.id.nameClientCellTextView);
        clientHolder.firstNameClientCellTextView = convertView.findViewById(R.id.firstNameClientCellTextView);

        Client client = getItem(position);

        clientHolder.firstNameClientCellTextView.setText(client.getFirstName());
        clientHolder.nameClientCellTextView.setText(client.getName().toUpperCase());

        return convertView;
    }

    private class ClientHolder {
        TextView nameClientCellTextView = null;
        TextView firstNameClientCellTextView = null;
    }
}
