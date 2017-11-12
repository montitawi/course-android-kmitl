package kmitl.lab09.montita58070114.moneyflow.Trask;

import android.os.AsyncTask;

import kmitl.lab09.montita58070114.moneyflow.Database.TransactionDB;
import kmitl.lab09.montita58070114.moneyflow.model.Transaction;


public class DeleteTransactionTask extends AsyncTask<Void, Void, Void> {
    private TransactionDB transactionDB;
    private Transaction transaction;

    public DeleteTransactionTask(TransactionDB transactionDB, Transaction transaction) {
        this.transactionDB = transactionDB;
        this.transaction = transaction;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        transactionDB.getTransactionDAO().deleteTransaction(transaction);
        return null;
    }
}
