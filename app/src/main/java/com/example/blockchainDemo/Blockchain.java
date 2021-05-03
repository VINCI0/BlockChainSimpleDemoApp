package com.example.blockchainDemo;

import android.util.Log;

import java.util.LinkedList;

public class Blockchain {
    LinkedList<Block> ledger;

    public Blockchain() {
        this.ledger = new LinkedList<Block>();
        this.ledger.add(new Block());
    }

    public boolean addBlock(String sender,String receiver,float amount){
        this.ledger.add(new Block(this.ledger.getLast().getHash(),ledger.size(),sender,receiver,amount));
        return true;
    }

    public LinkedList<Block> getLedger() {
        return ledger;
    }

    public boolean verifyBlockchain(){

        for (int i=1;i<ledger.size();++i){

            if (this.ledger.get(i).getPrevHashInString().equals(this.ledger.get(i-1).getBlockHashString())){

//                if (this.ledger.get(i).getHash().toString().equals(this.ledger.get(i).calculateHash().toString()))
                if (this.ledger.get(i).getBlockHashString().equals(this.ledger.get(i).calculateHashInString()))
                {
                    continue;
                }
                else{
                    Log.d("Verify","Block Hash not equal"+i);
//                    Log.d("Verify","hash Stored:"+this.ledger.get(i).getHash().toString());
//                    Log.d("Verify","hash Calculated:"+this.ledger.get(i).calculateHash().toString());
//

//                    should be false here
                    return false;
//                    return true;
                }
            }
            else
            {
                Log.d("VerifyF","PrevHash not equal"+i);

                return false;
            }
        }

            return true;
    }
}
