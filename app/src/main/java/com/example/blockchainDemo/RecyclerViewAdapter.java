package com.example.blockchainDemo;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    CoordinatorLayout layout;
    Context context;
    Blockchain chain;
    Dialog transactionDialog;
    TextView dialog_sender_tv;
    TextView dialog_receiver_tv;
    TextView dialog_amount_tv;

    public RecyclerViewAdapter(Context context, CoordinatorLayout layout,Blockchain chain) {
        this.context = context;
        this.chain = chain;
        this.layout=layout;

    }

    public void setChain(Blockchain chain) {
        this.chain = chain;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v=LayoutInflater.from(context).inflate(R.layout.block_layout, parent, false);
        final BlockHolder bHolder=  new BlockHolder(v);
        transactionDialog= new Dialog(context);
        transactionDialog.setContentView(R.layout.dialog_transaction);

        bHolder.mainItemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(context, "Show Transaction", Toast.LENGTH_SHORT).show();
                dialog_sender_tv= transactionDialog.findViewById(R.id.tvSender);
                dialog_receiver_tv= transactionDialog.findViewById(R.id.tvReceiver);
                dialog_amount_tv= transactionDialog.findViewById(R.id.tvAmount);
                if (bHolder.getAdapterPosition()==0){

                    dialog_sender_tv.setText("No Sender");
                    dialog_receiver_tv.setText("No Receiver");
                    dialog_amount_tv.setText("Null");


                }
                else {
                    dialog_sender_tv.setText("Sender:" + chain.getLedger().get(bHolder.getAdapterPosition()).getTransaction().getSender());
                    dialog_receiver_tv.setText("Receiver:" + chain.getLedger().get(bHolder.getAdapterPosition()).getTransaction().getReceiver());
                    dialog_amount_tv.setText("Amount:" + chain.getLedger().get(bHolder.getAdapterPosition()).getTransaction().getAmount());
                }
                transactionDialog.show();

                }
        });

        return bHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ((BlockHolder) holder).bind(position);
    }

    @Override
    public int getItemCount() {
        return chain.getLedger().size();
    }

    private class BlockHolder extends RecyclerView.ViewHolder {

        ConstraintLayout mainItemLayout;
        Dialog transactionDialog;
        TextView blockId;
        TextView blockHash;
        TextView prevHash;
        TextView timestamp;
        Button viewTransaction;

        BlockHolder(final View itemView) {
            super(itemView);
            mainItemLayout= itemView.findViewById(R.id.block_item_layout);
            blockId=itemView.findViewById(R.id.block_id);
            blockHash=itemView.findViewById(R.id.block_hash);
            prevHash=itemView.findViewById(R.id.block_prev_hash);
            timestamp=itemView.findViewById(R.id.block_timestamp);
//            viewTransaction=itemView.findViewById(R.id.btnViewTransaction);
        }


        void bind(int position) {

            Block block = chain.getLedger().get(position);
            blockId.setText("Block Id:"+Integer.toString(block.getBlockId()));
//            blockHash.setText("Block Hash:"+block.getHash().toString());
            blockHash.setText(block.getBlockHashString());

            if (block.getPrevHash()!=null) {
//                prevHash.setText("Prev Hash:" + block.getPrevHash().toString());
                prevHash.setText(block.getPrevHashInString());
            }

            else {
                prevHash.setText("Null");
            }

            timestamp.setText("Time:"+block.getTimestamp());
        }

    }


}
