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

import java.util.List;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyViewHolder>{

    private List<Trabajador> trabajadors;
    private Context context;
    private LayoutInflater layoutInflater;
    private int layout;
    private View.OnClickListener listener;


    public MyRecyclerViewAdapter(Context context, List<Trabajador> trabajadors) {
        super();
        this.trabajadors = trabajadors;
        this.context = context;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(layout,parent,false);
        view.setOnClickListener(listener);
        return new MyViewHolder(view);
    }
    public void setLayout(int layout) {
        this.layout = layout;
    }

    public void setListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    public Trabajador getTrabajador(int position){
        return trabajadors.get(position);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Trabajador trabajador = trabajadors.get(position);

        holder.tvNombre.setText(trabajador.getNombre()+" " + trabajador.getApellidos());
        holder.tvOficio.setText(trabajador.getOficio());
        holder.ivOficio.setImageResource(trabajador.getImagen());


    }

    @Override
    public int getItemCount() {
        return trabajadors.size();
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
