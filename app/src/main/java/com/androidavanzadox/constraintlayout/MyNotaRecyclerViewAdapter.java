package com.androidavanzadox.constraintlayout;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidavanzadox.constraintlayout.placeholder.PlaceholderContent.PlaceholderItem;
import com.androidavanzadox.constraintlayout.databinding.FragmentItemNotaBinding;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link PlaceholderItem}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyNotaRecyclerViewAdapter extends RecyclerView.Adapter<MyNotaRecyclerViewAdapter.ViewHolder> {

    private final List<Nota> mValues;
    private final NotasInteractionListener mListener;

    public MyNotaRecyclerViewAdapter(List<Nota> items, NotasInteractionListener mListener) {
        mValues = items;
        this.mListener = mListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(FragmentItemNotaBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.txtViewTitulo.setText(mValues.get(position).getTitulo());
        holder.txtViewContenido.setText(mValues.get(position).getContenido());

        if(holder.mItem.getFavorita()){
            holder.imvFavorita.setImageResource(R.drawable.ic_baseline_star_24);
        }

        //con esto manda a llamar el click favorito por medio de la interfaz
        holder.imvFavorita.setOnClickListener((v)->{
            if(null!=mListener){
                mListener.favoritaNotaClick(holder.mItem);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView txtViewTitulo;
        public final  TextView txtViewContenido;
        public final ImageView imvFavorita;
        public Nota mItem;

        public ViewHolder(FragmentItemNotaBinding binding) {
            super(binding.getRoot());
            txtViewTitulo = binding.textViewTitulo;
            txtViewContenido = binding.textViewContenido;
            imvFavorita = binding.imageViewFavorita;

        }

        @Override
        public String toString() {
            return super.toString() + " '" + txtViewTitulo.getText() + "'";
        }
    }
}