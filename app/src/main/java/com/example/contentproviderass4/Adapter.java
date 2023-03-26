package com.example.contentproviderass4;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.contentproviderass4.databinding.ItemCustomBinding;
import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.itemViewHolder> {

    ArrayList<Contacts> contents;
    Listener listener;

    public Adapter( ArrayList < Contacts > contents , Listener listener ) {
        this.contents = contents;
        this.listener = listener;
    }

    @NonNull
    @Override
    public itemViewHolder onCreateViewHolder
            (@NonNull ViewGroup parent, int viewType) {
        ItemCustomBinding binding= ItemCustomBinding.inflate
                (LayoutInflater.from
                (parent.getContext()),parent,false);

        return new itemViewHolder( binding );
    }

    @Override
    public void onBindViewHolder(@NonNull itemViewHolder holder, int position) {
        int pos = position;
        Contacts content = contents.get(pos);

        holder.phone.setText(content.getPhoto());
   //     holder.details.setText(content.getDetails());
  //      holder.email.setText(content.getEmail());
        holder.name.setText(content.getName());

        holder.itemView.setOnClickListener(
                view -> listener.getPos( holder.getAdapterPosition() ) );

//       holder.imageView.setImageURI( Uri.parse(content.getPhoto()));
//        Bitmap bitmap = null;
//        if (!content.getPhoto().equals( "" ) && content.getPhoto()!=null){
//           bitmap = BitmapFactory.decodeFile( content.getPhoto() );
//           if ( bitmap != null ){
//               holder.imageView.setImageBitmap( bitmap );
//           }else {
//              // bitmap =BitmapFactory.decodeResource( holder );
//               holder.imageView.setImageBitmap( null );
//           }
//        }else {
//            //bitmap=BitmapFactory.decodeResource(  )
//            holder.imageView.setImageBitmap( null );
//
//        }

    }

    @Override
    public int getItemCount() {
        return contents.size();
    }

    public static class itemViewHolder extends RecyclerView.ViewHolder {

        TextView phone , details,name,email;
        ImageView imageView;


        public itemViewHolder(@NonNull ItemCustomBinding binding) {
            super(binding.getRoot());

            phone=binding.tvPhone;
//            details=binding.tvDetiles;
            name=binding.tvName;
//            email=binding.tvEmail;
            imageView=binding.imageView2;


        }
    }
}
