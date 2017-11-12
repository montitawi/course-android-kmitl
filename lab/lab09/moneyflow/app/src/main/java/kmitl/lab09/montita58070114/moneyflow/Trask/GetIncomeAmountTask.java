package kmitl.lab09.montita58070114.moneyflow.Trask;


import android.os.AsyncTask;

import kmitl.lab09.montita58070114.moneyflow.Database.TransactionDB;

public class GetIncomeAmountTask extends AsyncTask<Void, Void, Void> {
    private TransactionDB transactionDB;
    private OnGetIncomeAmountListener listener;
    private int sumAmountIncome;

    public GetIncomeAmountTask(TransactionDB transactionDB, OnGetIncomeAmountListener listener) {
        this.transactionDB = transactionDB;
        this.listener = listener;
    }

    @Override
    protected Void doInBackground(Void... voids) {

        sumAmountIncome = transactionDB.getTransactionDAO().getAmountIncome();
        listener.onGetIncomeAmountSuccess(sumAmountIncome);

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        listener.onGetIncomeAmountSuccess(sumAmountIncome);
    }

    public interface OnGetIncomeAmountListener {
        void onGetIncomeAmountSuccess(int sumAmountIncome);
    }
}
