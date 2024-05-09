package es.ieslavereda.myrecyclerview23_24;

import static es.ieslavereda.myrecyclerview23_24.MyRecyclerViewAdapter.SORT.*;
import static es.ieslavereda.myrecyclerview23_24.MyRecyclerViewAdapter.*;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import es.ieslavereda.myrecyclerview23_24.API.Connector;
import es.ieslavereda.myrecyclerview23_24.base.BaseActivity;
import es.ieslavereda.myrecyclerview23_24.base.CallInterface;
import es.ieslavereda.myrecyclerview23_24.model.Oficio;
import es.ieslavereda.myrecyclerview23_24.model.Usuario;

public class MainActivity extends BaseActivity implements View.OnClickListener, CallInterface {

    private List<Usuario> trabajadores;
    private List<Oficio> oficios;
    private RecyclerView recyclerView;
    private ImageButton ibGrid;
    private ImageButton ibLinear;
    private ImageButton ibSort;

    private SORT ordenacion;

    private boolean isLinear;
    private MyRecyclerViewAdapter mrva;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        isLinear=true;

        ordenacion = NORMAL;

        recyclerView = findViewById(R.id.recyclerView);
        ibGrid = findViewById(R.id.ibGrid);
        ibLinear = findViewById(R.id.ibLinear);
        ibSort = findViewById(R.id.ibSort);

        ibSort.setOnClickListener(
                view -> {
                    ordenacion = ordenacion.next();
                    mrva.sort(ordenacion);
                    switch (ordenacion){
                        case DES:ibSort.setImageDrawable(getDrawable(android.R.drawable.arrow_down_float));
                            break;
                        case ASC:ibSort.setImageDrawable(getDrawable(android.R.drawable.arrow_up_float));
                            break;
                        default:ibSort.setImageDrawable(getDrawable(android.R.drawable.checkbox_off_background));

                    }
                }
        );

        ibGrid.setOnClickListener( view -> {
            isLinear=false;
            updateRecycler();
        });

        ibLinear.setOnClickListener( view -> {
            isLinear=true;
            updateRecycler();
        });

        executeCall(this);



    }

    private void updateRecycler() {

        if(isLinear){
            mrva.setLayout(R.layout.recycler_list_item);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }else{
            mrva.setLayout(R.layout.recycler_grid_item);
            recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        }

        recyclerView.getRecycledViewPool().clear();
    }

    @Override
    public void onClick(View view) {
       int position = recyclerView.getChildAdapterPosition(view);
       Usuario usuario = mrva.getTrabajador(position);
        Toast.makeText(
                this,
                "Realizado clic sobre: " + usuario.getNombre()+" "+usuario.getApellidos(),
                Toast.LENGTH_LONG)
                .show();

        executeCall(new CallInterface() {

            private int rows;

            @Override
            public void doInBackground() {

                rows = Connector.getConector().delete(Integer.class,"usuarios/"+usuario.getIdUsuario());

            }

            @Override
            public void doInUI() {
                if(rows==1)
                    mrva.removeUser(usuario);
            }
        });
    }

    @Override
    public void doInBackground() {

        trabajadores = Connector.getConector().getAsList(Usuario.class,"usuarios");
        oficios = Connector.getConector().getAsList(Oficio.class,"oficios");

    }

    @Override
    public void doInUI() {
        mrva = new MyRecyclerViewAdapter(this,trabajadores,oficios);
        mrva.setLayout(R.layout.recycler_list_item);
        mrva.setListener(this);
        recyclerView.setAdapter(mrva);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}