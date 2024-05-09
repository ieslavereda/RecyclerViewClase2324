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
import es.ieslavereda.myrecyclerview23_24.base.ImageDownloader;
import es.ieslavereda.myrecyclerview23_24.base.Parameters;
import es.ieslavereda.myrecyclerview23_24.model.Oficio;
import es.ieslavereda.myrecyclerview23_24.model.Usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private List<Usuario> itemsToShow;
    private List<Usuario> items;
    private List<Oficio> oficios;

    private Context context;
    private LayoutInflater layoutInflater;
    private int layout;
    private View.OnClickListener listener;

    public MyRecyclerViewAdapter(Context context, List<Usuario> trabajadors,List<Oficio> oficios) {
        super();
        this.oficios = oficios;
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

    public Usuario getTrabajador(int position) {
        return itemsToShow.get(position);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Usuario usuario = itemsToShow.get(position);
        Oficio oficio = getOficioById(usuario.getOficio_idOficio());

        holder.tvNombre.setText(usuario.getNombre() + " " + usuario.getApellidos());
        holder.tvOficio.setText(oficio.getDescripcion());
        ImageDownloader.downloadImage(context, Parameters.BASE_URL_IMAGE+oficio.getImageUrl(),holder.ivOficio,R.drawable.ic_launcher_foreground);

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

    public void removeUser(Usuario trabajador) {
        items.remove(trabajador);
        itemsToShow.remove(trabajador);
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

    private Oficio getOficioById(int id){
        Optional<Oficio> optional = oficios.stream()
                .filter(o->o.getIdOficio()==id)
                .findFirst();
        if(optional.isPresent())
            return optional.get();
        else return null;
    }
}
