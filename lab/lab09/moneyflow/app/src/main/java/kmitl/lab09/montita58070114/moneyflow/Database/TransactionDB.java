package kmitl.lab09.montita58070114.moneyflow.Database;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import kmitl.lab09.montita58070114.moneyflow.DAO.TransactionDAO;
import kmitl.lab09.montita58070114.moneyflow.model.Transaction;

@Database(entities = {Transaction.class}, version = 1)
public abstract class TransactionDB extends RoomDatabase{
    public abstract TransactionDAO getTransactionDAO();

}
