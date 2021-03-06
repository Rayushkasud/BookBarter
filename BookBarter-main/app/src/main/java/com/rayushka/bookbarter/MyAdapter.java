package com.rayushka.bookbarter;


import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyAdapter extends FirebaseRecyclerAdapter<model,MyAdapter.myviewholder>
{
    public MyAdapter(@NonNull FirebaseRecyclerOptions<model> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull final myviewholder holder, final int position, @NonNull final model model) {
        DatabaseReference ref;
        final String[] ownerPin = new String[1];
        ref = FirebaseDatabase.getInstance().getReference("Books").child(getRef(position).getKey());
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ownerPin[0] = snapshot.child("ownerpincode").getValue().toString();
                holder.ownerpincode.setText("Pin Code: " + ownerPin[0]);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        holder.name.setText(model.getName());
        holder.author.setText("Author: " + model.getAuthor());
//        holder.email.setText(model.getEmail());
        holder.genre.setText("Genre: " + model.getGenre());
//
      //  holder.ownerpincode.setText("Pin Code: " + ownerPin[0]);
        Glide.with(holder.img.getContext()).load("https://images.unsplash.com/photo-1476275466078-4007374efbbe?ixid=MnwxMjA3fDB8MHxzZWFyY2h8Mjh8fGJvb2t8ZW58MHx8MHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=500&q=60").into(holder.img);

//        holder.updatebutton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                final DialogPlus dialogPlus=DialogPlus.newDialog(holder.name.getContext())
//                        .setContentHolder(new ViewHolder(R.layout.dialogcontent))
//                        .setExpanded(true,1100)
//                        .create();
//
//                View myview=dialogPlus.getHolderView();
//                final EditText purl=myview.findViewById(R.id.uimgurl);
//                final EditText name=myview.findViewById(R.id.uname);
//                final EditText course=myview.findViewById(R.id.ucourse);
//                final EditText email=myview.findViewById(R.id.uemail);
//                Button submit=myview.findViewById(R.id.usubmit);
//
//                purl.setText(model.getPurl());
//                name.setText(model.getName());
//                course.setText(model.getCourse());
//                email.setText(model.getEmail());
//
//                dialogPlus.show();
//
//                submit.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Map<String,Object> map=new HashMap<>();
//                        map.put("purl",purl.getText().toString());
//                        map.put("name",name.getText().toString());
//                        map.put("email",email.getText().toString());
//                        map.put("course",course.getText().toString());
//
//                        FirebaseDatabase.getInstance().getReference().child("students")
//                                .child(getRef(position).getKey()).updateChildren(map)
//                                .addOnSuccessListener(new OnSuccessListener<Void>() {
//                                    @Override
//                                    public void onSuccess(Void aVoid) {
//                                        dialogPlus.dismiss();
//                                    }
//                                })
//                                .addOnFailureListener(new OnFailureListener() {
//                                    @Override
//                                    public void onFailure(@NonNull Exception e) {
//                                        dialogPlus.dismiss();
//                                    }
//                                });
//                    }
//                });
//
//
//            }
//        });
//
//
//        holder.deletebutton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                AlertDialog.Builder builder=new AlertDialog.Builder(holder.img.getContext());
//                builder.setTitle("DELETE");
//                builder.setMessage("Do you want to delete this book entry?");
//
//                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        FirebaseDatabase.getInstance().getReference().child("students")
//                                .child(getRef(position).getKey()).removeValue();
//                    }
//                });
//
//                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//
//                    }
//                });
//
//                builder.show();
//            }
//        });
        holder.reqbutton.setOnClickListener(new View.OnClickListener() {
           // DatabaseReference ref;

            @Override
            public void onClick(View v) {

//                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//                String SUBJECT = "BORROW REQUEST VIA BOOK BARTER APP FOR ";
//
//
//                // String  emailsend =  FirebaseDatabase.getInstance().getReference("students").child("email").toString();
//                ref.addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(DataSnapshot dataSnapshot) {
//                        String emailsend = dataSnapshot.child("email").getValue(String.class);
//                        String bookName =  dataSnapshot.child("course").getValue(String.class);
//                        //do what you want with the likes
//                        Intent intent = new Intent(Intent.ACTION_SENDTO);
//                        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
//                        intent.putExtra(Intent.EXTRA_EMAIL,  new String[]{emailsend});
//                        intent.putExtra(Intent.EXTRA_SUBJECT, SUBJECT+bookName);
//                        // if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
//                        v.getContext().startActivity(intent);
//                    }
//
//                    @Override
//                    public void onCancelled(DatabaseError databaseError) {
//
//                    }
//                });

                Intent intent = new Intent(v.getContext(), BorrowLendActivity.class);
                //set flag
                //Start activity
                intent.putExtra("uid", getRef(position).getKey().toString());
                v.getContext().startActivity(intent);


            }


        });
    }



    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerowlayout,parent,false);
        return new myviewholder(view);
    }

    class myviewholder extends RecyclerView.ViewHolder
    {
        CircleImageView img;
        TextView name,author,email,genre,ownerpincode;
        Button deletebutton, updatebutton, reqbutton ;
        DatabaseReference ref;
        String ownerPin ;
        //public EditText title , description , location;
        public myviewholder(@NonNull View itemView)
        {
            super(itemView);
            img=(CircleImageView)itemView.findViewById(R.id.img1);
            name=(TextView)itemView.findViewById(R.id.nametext);
            author=(TextView)itemView.findViewById(R.id.authortext);
//            email=(TextView)itemView.findViewById(R.id.emailtext);
            genre=(TextView)itemView.findViewById(R.id.genre);
            ownerpincode=(TextView)itemView.findViewById(R.id.pintext);

            updatebutton= (Button) itemView.findViewById(R.id.updatebutton);
            deletebutton= (Button) itemView.findViewById(R.id.deletebutton);
            reqbutton = (Button) itemView.findViewById(R.id.reqbutton);


        }
    }
}

