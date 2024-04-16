package es.ieslavereda.myrecyclerview23_24;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private List<Trabajador> trabajadores;
    private RecyclerView recyclerView;

    private ImageButton ibGrid;
    private ImageButton ibLinear;

    private boolean isLinear;
    private MyRecyclerViewAdapter mrva;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        isLinear=true;

        trabajadores = new ArrayList<>(
                Arrays.asList(
                        new Trabajador(R.mipmap.ic_actor, "Carlos", "Garcia", "Actor"),
                        new Trabajador(R.mipmap.ic_albanil, "Pedro", "Martinez", "Albañil"),
                        new Trabajador(R.mipmap.ic_banquero, "Juan", "Lopez", "Banquero"),
                        new Trabajador(R.mipmap.ic_cocinero, "Manolo", "Lujan", "Cocinero"),
                        new Trabajador(R.mipmap.ic_estudiante, "Sergio", "Sanz", "Estudiante"),
                        new Trabajador(R.mipmap.ic_instagramer, "Luis", "Gimenez", "Instagramer"),
                        new Trabajador(R.mipmap.ic_pintor, "Tomas", "Sanchez", "Pintor"),
                        new Trabajador(R.mipmap.ic_policia, "Roberto", "Fernandez", "Policia"),
                        new Trabajador(R.mipmap.ic_politico_activo, "Martin", "Blasco", "Politico activo"),
                        new Trabajador(R.mipmap.ic_politico_retirado, "Ruben", "Diaz", "Politico retirado"),
                        new Trabajador(R.mipmap.ic_vendedor, "Rafa", "Garcia", "Teruel"),
                        new Trabajador(R.mipmap.ic_actor, "Carlos", "Garcia", "Actor"),
                        new Trabajador(R.mipmap.ic_albanil, "Pedro", "Martinez", "Albañil"),
                        new Trabajador(R.mipmap.ic_banquero, "Juan", "Lopez", "Banquero"),
                        new Trabajador(R.mipmap.ic_cocinero, "Manolo", "Lujan", "Cocinero"),
                        new Trabajador(R.mipmap.ic_estudiante, "Sergio", "Sanz", "Estudiante"),
                        new Trabajador(R.mipmap.ic_instagramer, "Luis", "Gimenez", "Instagramer"),
                        new Trabajador(R.mipmap.ic_pintor, "Tomas", "Sanchez", "Pintor"),
                        new Trabajador(R.mipmap.ic_policia, "Roberto", "Fernandez", "Policia"),
                        new Trabajador(R.mipmap.ic_politico_activo, "Martin", "Blasco", "Politico activo"),
                        new Trabajador(R.mipmap.ic_politico_retirado, "Ruben", "Diaz", "Politico retirado"),
                        new Trabajador(R.mipmap.ic_vendedor, "Rafa", "Garcia", "Teruel"),
                        new Trabajador(R.mipmap.ic_actor, "Carlos", "Garcia", "Actor"),
                        new Trabajador(R.mipmap.ic_albanil, "Pedro", "Martinez", "Albañil"),
                        new Trabajador(R.mipmap.ic_banquero, "Juan", "Lopez", "Banquero"),
                        new Trabajador(R.mipmap.ic_cocinero, "Manolo", "Lujan", "Cocinero"),
                        new Trabajador(R.mipmap.ic_estudiante, "Sergio", "Sanz", "Estudiante"),
                        new Trabajador(R.mipmap.ic_instagramer, "Luis", "Gimenez", "Instagramer"),
                        new Trabajador(R.mipmap.ic_pintor, "Tomas", "Sanchez", "Pintor"),
                        new Trabajador(R.mipmap.ic_policia, "Roberto", "Fernandez", "Policia"),
                        new Trabajador(R.mipmap.ic_politico_activo, "Martin", "Blasco", "Politico activo"),
                        new Trabajador(R.mipmap.ic_politico_retirado, "Ruben", "Diaz", "Politico retirado"),
                        new Trabajador(R.mipmap.ic_vendedor, "Rafa", "Garcia", "Teruel"),


                        new Trabajador(R.mipmap.ic_youtuber, "Jose", "Garcia", "Sanz")
                )
        );

        recyclerView = findViewById(R.id.recyclerView);
        ibGrid = findViewById(R.id.ibGrid);
        ibLinear = findViewById(R.id.ibLinear);

        ibGrid.setOnClickListener( view -> {
            isLinear=false;
            updateRecycler();
        });

        ibLinear.setOnClickListener( view -> {
            isLinear=true;
            updateRecycler();
        });


        mrva = new MyRecyclerViewAdapter(this,trabajadores);
        mrva.setLayout(R.layout.recycler_list_item);
        mrva.setListener(this);
        recyclerView.setAdapter(mrva);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



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
       Trabajador trabajador = mrva.getTrabajador(position);
        Toast.makeText(
                this,
                "Realizado clic sobre: " + trabajador.getNombre()+" "+trabajador.getApellidos(),
                Toast.LENGTH_LONG)
                .show();
    }
}