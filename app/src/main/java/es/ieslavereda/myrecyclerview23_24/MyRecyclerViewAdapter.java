package es.ieslavereda.myrecyclerview23_24;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import es.ieslavereda.myrecyclerview23_24.MyRecyclerViewAdapter.MyViewHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private List<Trabajador> itemsToShow;
    private List<Trabajador> items;

    private Context context;
    private LayoutInflater layoutInflater;
    private int layout;
    private View.OnClickListener listener;

    public MyRecyclerViewAdapter(Context context, List<Trabajador> trabajadors) {
        super();
        this.itemsToShow = trabajadors;
        this.items = new ArrayList<>(trabajadors);
        this.context = context;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(layout, parent, false);
        view.setOnClickListener(listener);
        return new MyViewHolder(view);
    }

    public void setLayout(int layout) {
        this.layout = layout;
    }

    public void setListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    public Trabajador getTrabajador(int position) {
        return itemsToShow.get(position);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Trabajador trabajador = itemsToShow.get(position);

        holder.tvNombre.setText(trabajador.getNombre() + " " + trabajador.getApellidos());
        holder.tvOficio.setText(trabajador.getOficio());
        holder.ivOficio.setImageResource(trabajador.getImagen());

    }

    @Override
    public int getItemCount() {
        return itemsToShow.size();
    }

    public void sort(SORT ordenacion) {

        switch (ordenacion) {
            case ASC:
                itemsToShow = items.stream()
                        .sorted((t1, t2) ->
                                (t1.getApellidos() + t1.getNombre()).compareToIgnoreCase(t2.getApellidos() + t2.getNombre())
                        )
                        .collect(Collectors.toList());
                break;
            case DES:
                itemsToShow = items.stream()
                        .sorted((t1, t2) ->
                                (t2.getApellidos() + t2.getNombre()).compareToIgnoreCase(t1.getApellidos() + t1.getNombre())
                        )
                        .collect(Collectors.toList());
                break;
            default:itemsToShow = items;

        }
        notifyDataSetChanged();

    }


    enum SORT {
        ASC, DES, NORMAL;

        public SORT next() {
            if (this.equals(NORMAL))
                return ASC;
            if (this.equals(ASC))
                return DES;
            return NORMAL;
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView ivOficio;
        TextView tvNombre;
        TextView tvOficio;
        ConstraintLayout constraintLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ivOficio = itemView.findViewById(R.id.ivOficio);
            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvOficio = itemView.findViewById(R.id.tvOficio);
            constraintLayout = itemView.findViewById(R.id.constraintLayout);
        }
    }
}
