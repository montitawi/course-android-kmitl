package kmitl.lab09.montita58070114.moneyflow.Trask;


import android.os.AsyncTask;

import java.util.List;

import kmitl.lab09.montita58070114.moneyflow.Database.TransactionDB;
import kmitl.lab09.montita58070114.moneyflow.model.Transaction;

public class GetAmountTask extends AsyncTask<Void, Void, Void> {

    private TransactionDB transactionDB;
    private OnGetAmountListener listener;

    @Override
    protected Void doInBackground(Void... voids) {
        List<Transaction> transactions = transactionDB.getTransactionDAO().getSumAmount();
        if (transactions.isEmpty()){
            listener.onGetAmountFailed();
        }else{
            listener.onGetAmountSuccess(transactions);
        }
        return null;
    }

    private interface OnGetAmountListener {
        List<Transaction> onGetAmountSuccess(List<Transaction> transactions);
        void onGetAmountFailed();
    }
}
