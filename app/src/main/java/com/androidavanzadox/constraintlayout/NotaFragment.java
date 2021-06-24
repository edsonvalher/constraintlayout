package com.androidavanzadox.constraintlayout;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Items.
 */
public class NotaFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 2;

    //1 DECLARAR LISTA DE NOTAS
    private List<Nota> notalist;
    private MyNotaRecyclerViewAdapter notasAdapter;
    private NotasInteractionListener mListener;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public NotaFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static NotaFragment newInstance(int columnCount) {
        NotaFragment fragment = new NotaFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public void onAttach(@NonNull @NotNull Context context) {
        super.onAttach(context);
        if(context instanceof NotasInteractionListener){
            mListener = (NotasInteractionListener)context;
        }else{
            throw new RuntimeException(context.toString() + "must implement NotasInteractionListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_nota_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;

            //detectando orientacion


            if(getResources().getConfiguration().orientation== Configuration.ORIENTATION_PORTRAIT){
                Log.i("ORIENTATION","PORTRAIT!");
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                Log.i("ORIENTATION","LANDSCAPE!");
                //CON EL StaggeredGridLayoutManager se auto ajusta en las celdas

                //averiguar el tamano de la pantalla para definir las columnas

                DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
                //pixeles por pulgada en el ancho
                float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
                //calculando el número de columnas
                int ncolumnas = (int)(dpWidth/180);

                //distribucion por orientacion

                recyclerView.setLayoutManager(new StaggeredGridLayoutManager( ncolumnas,StaggeredGridLayoutManager.VERTICAL));
            }
            //3 agregar informacion

            notalist = new ArrayList<>();
            notalist.add(new Nota("Lista de compra","Comprar pan tostado",true, android.R.color.holo_blue_bright));
            notalist.add(new Nota("Recordar","Carro parqueado afuera, el cual solo puede estar hasta las 3 de la tarde despues de la hora estará cobrando",false, android.R.color.holo_green_light));
            notalist.add(new Nota("Cumpleaños (fiesta)","No olvidar las velas",true, android.R.color.holo_orange_light));
            //2 MODIFICAR ADAPTADOR CONSTRUCTOR
            notasAdapter =new MyNotaRecyclerViewAdapter(notalist, mListener);
            recyclerView.setAdapter(notasAdapter);
        }
        return view;
    }
}