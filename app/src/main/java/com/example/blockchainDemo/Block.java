package com.example.blockchainDemo;

import android.util.Base64;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

public class Block {
    private byte[] prevHash,hash=null;
    private int blockId;
    private Transaction transaction;
    String timeStamp;

    public Block(byte[] prevHash, int blockId,String sender,String receiver,float amount) {
        this.prevHash = prevHash;
        this.blockId = blockId;
        this.transaction=new Transaction(sender, receiver, amount);
        timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
        this.hash=calculateHash();
    }

    public Block() {

//        this.prevHash=new byte[0];
//        this.hash=new byte[0];
        this.transaction=null;
        this.blockId=0;
        timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
        this.hash=calculateHash();

    }

    public byte[] calculateHash(){

        MessageDigest digest = null;
        try {

            digest = MessageDigest.getInstance("SHA-256");
            String text="";
            if (prevHash!=null) {
                text = this.blockId + this.prevHash.toString()
                        + this.timeStamp + this.transaction.sender + this.transaction.receiver + this.transaction.amount;
            }
            else{
                text = this.blockId + this.timeStamp+"Start Block";

            }
            digest.update(text.getBytes());
            return digest.digest();
//                return digest.digest(text.getBytes("UTF-8"));

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return null;

    }
    public String calculateHashInString(){


//        byte [] localHash=calculateHash();
//        StringBuilder sb = new StringBuilder(localHash.length * 2);
//        for(byte b: localHash)
//            sb.append(String.format("%02x", b));
//        return sb.toString();

//        return new String(this.calculateHash(), StandardCharsets.UTF_8); // for UTF-8 encoding
        return Base64.encodeToString(this.calculateHash(),Base64.DEFAULT);

    }
    public String getBlockHashString(){
        // for getting the hash value stored in block in string format

//        StringBuilder sb = new StringBuilder(hash.length * 2);
//        for(byte b: hash)
//            sb.append(String.format("%02x", b));
//        return sb.toString();
//        return Arrays.toString(this.hash);


//        return String.format("%064x", new BigInteger(1, this.hash));

        return Base64.encodeToString(this.hash,Base64.DEFAULT);

    }
    @Override
    public String toString() {
        return "Block{" +
                "prevHash='" + prevHash + '\'' +
                ", hash='" + hash + '\'' +
                ", blockId=" + blockId +
                ", transaction=" + transaction +
                '}';
    }

    public byte[] getPrevHash() {
        return prevHash;
    }
    public String getPrevHashInString(){

                return Base64.encodeToString(this.prevHash,Base64.DEFAULT);
        //        return new String(this.prevHash, StandardCharsets.UTF_8); // for UTF-8 encoding
//        StringBuilder sb = new StringBuilder(prevHash.length * 2);
//        for(byte b: prevHash)
//            sb.append(String.format("%02x", b));
//        return sb.toString();




    }

    public byte[] getHash() {
        return hash;
    }

    public String getTimestamp() {
        return timeStamp;
    }

    public int getBlockId() {
        return blockId;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    class Transaction {
        String sender,receiver;
        float amount;

        @Override
        public String toString() {
            return "Transaction{" +
                    "sender='" + sender + '\'' +
                    ", receiver='" + receiver + '\'' +
                    ", amount=" + amount +
                    '}';
        }

        public Transaction(String sender, String receiver, float amount) {
            this.sender = sender;
            this.receiver = receiver;
            this.amount = amount;
        }

        public String getSender() {
            return sender;
        }

        public void setSender(String sender) {
            this.sender = sender;
        }

        public String getReceiver() {
            return receiver;
        }

        public void setReceiver(String receiver) {
            this.receiver = receiver;
        }

        public float getAmount() {
            return amount;
        }

        public void setAmount(float amount) {
            this.amount = amount;
        }
    }
}
