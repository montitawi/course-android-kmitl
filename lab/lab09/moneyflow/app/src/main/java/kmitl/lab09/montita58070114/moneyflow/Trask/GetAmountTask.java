package kmitl.lab09.montita58070114.moneyflow.Trask;


import android.os.AsyncTask;

import kmitl.lab09.montita58070114.moneyflow.Database.TransactionDB;

public class GetAmountTask extends AsyncTask<Void, Void, Void> {

    private TransactionDB transactionDB;
    private OnGetAmountListener listener;
    private int sumAmount;

    public GetAmountTask(TransactionDB transactionDB, OnGetAmountListener listener) {
        this.transactionDB = transactionDB;
        this.listener = listener;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        sumAmount = transactionDB.getTransactionDAO().getSumAmount();
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        listener.onGetAmountSuccess(sumAmount);
    }

    public interface OnGetAmountListener {
        void onGetAmountSuccess(int sumAmount);
    }
}
