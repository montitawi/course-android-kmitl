package kmitl.lab09.montita58070114.moneyflow.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "TRANSACTION")
public class Transaction {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "NOTE")
    private String note;

    @ColumnInfo(name = "AMOUNT")
    private int amount;

    @ColumnInfo(name = "TRANSACTION_TYPE")
    private String transactionType;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }
    @Override
    public String toString() {
        return String.format("Type: %s \n note: %s \n amount: %s", transactionType, note, amount);
    }
}
