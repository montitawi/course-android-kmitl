package kmitl.lab09.montita58070114.moneyflow.Trask;


import android.os.AsyncTask;

import java.util.List;

import kmitl.lab09.montita58070114.moneyflow.model.Transaction;
import kmitl.lab09.montita58070114.moneyflow.Database.TransactionDB;

public class GetTransactionTask extends AsyncTask<Void, Void, Void>{
    private TransactionDB transactionDB;
    private OnGetTransactionListener listener;
    private List<Transaction> transactions;

    public GetTransactionTask(TransactionDB transactionDB, OnGetTransactionListener listener) {
        this.transactionDB = transactionDB;
        this.listener = listener;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        transactions = transactionDB.getTransactionDAO().getAllTransaction();
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        if (transactions.isEmpty()){
            listener.onGetTransactionListenerFailed();
        }else{
            listener.onGetTransactionSuccess(transactions);
        }
    }

    public interface OnGetTransactionListener{
        List<Transaction> onGetTransactionSuccess(List<Transaction> transactions);
        void onGetTransactionListenerFailed();
    }
}
