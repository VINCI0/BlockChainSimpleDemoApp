package com.example.blockchainDemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    CoordinatorLayout layout;
    Blockchain chain;
    RecyclerView recyclerView;
    RecyclerViewAdapter adapter;
    Button validateChain,addNewBlock;
    TextView totalBlocks,latestBlock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        layout= findViewById(R.id.layout_transaction);
        totalBlocks=findViewById(R.id.totalBlocks);
        latestBlock=findViewById(R.id.latestBlock);

        chain=new Blockchain();
        Random random=new Random();

        int max=100;
        int min=1;
        String sender,receiver="";
        int numberOfInitialBlocks=4;

        for (int i = 0; i<numberOfInitialBlocks; ++i) {
            sender = Integer.toString(random.nextInt(max - min + min) + min);
            receiver = Integer.toString(random.nextInt(max - min + min) + min);
            chain.addBlock(sender, receiver, (float) random.nextFloat() * max);
        }


        adapter= new RecyclerViewAdapter(this,layout,chain);
        recyclerView=findViewById(R.id.blockRecycler);
        LinearLayoutManager lm=new LinearLayoutManager(this);
        lm.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView.setLayoutManager(lm);
        recyclerView.setAdapter(adapter);


        validateChain=findViewById(R.id.validateChain);

        totalBlocks.setText("Total Number of Blocks:"+chain.getLedger().size());
        latestBlock.setText("Latest Block Hash: "+chain.getLedger().getLast().getBlockHashString());

//        latestBlock.setText("Latest Block Hash:"+chain.getLedger().getLast().getPrevHashInString().length());

        validateChain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean check=chain.verifyBlockchain();
                if (check) {
                    Toast.makeText(MainActivity.this, ("Chain Verified. Not Corrupted"), Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(MainActivity.this, ("Chain Not Verified.sCorrupted"), Toast.LENGTH_LONG).show();

                }
            }
        });
        addNewBlock=findViewById(R.id.addBlock);
        addNewBlock.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Adding New Block", Toast.LENGTH_SHORT).show();

//                Fragment transactionFragment=new TransactionFragment();
//                getSupportFragmentManager().beginTransaction().replace(R.id.new_transaction_container,transactionFragment,"Transaction Fragment")
//                        .addToBackStack(null).commit();




//                findViewById(R.id.new_transaction_container).setForeground().set;
//                FragmentManager manager = getSupportFragmentManager();
//                FragmentTransaction transaction = manager.beginTransaction();
//                transaction.add(R.id.new_transaction_container,transactionFragment ,"Transaction Fragment");
//                transaction.addToBackStack(null);
//                transaction.commit();

//                getSupportFragmentManager().
                Random random=new Random();

                String sender=Integer.toString(random.nextInt(100 - 1 + 1) + 1);
                String receiver=Integer.toString(random.nextInt(100 - 1 + 1) + 1);

                chain.addBlock(sender,receiver, (float) random.nextFloat());
                adapter.notifyDataSetChanged();
                totalBlocks.setText("Total Number of Blocks:"+chain.getLedger().size());
                latestBlock.setText("Latest Block Hash: "+chain.getLedger().getLast().getBlockHashString());


            }
        });
    }
}