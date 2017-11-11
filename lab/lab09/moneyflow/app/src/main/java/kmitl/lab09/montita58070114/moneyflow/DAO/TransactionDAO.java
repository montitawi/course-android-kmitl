package kmitl.lab09.montita58070114.moneyflow.DAO;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import kmitl.lab09.montita58070114.moneyflow.model.Transaction;

@Dao
public interface TransactionDAO {

    @Insert
    void insertTransaction(Transaction transaction);

    @Query("SELECT * FROM `TRANSACTION`")
    List<Transaction> getAllTransaction();


    @Update
    void updateTransaction(Transaction transaction);

    @Delete
    void deleteTransaction(Transaction transaction);


}
