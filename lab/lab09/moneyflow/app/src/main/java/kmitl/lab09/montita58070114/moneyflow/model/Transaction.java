package kmitl.lab09.montita58070114.moneyflow.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

@Entity(tableName = "TRANSACTION")
public class Transaction implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "NOTE")
    private String note;

    @ColumnInfo(name = "AMOUNT")
    private int amount;

    @ColumnInfo(name = "TRANSACTION_TYPE")
    private String transactionType;

    public Transaction(String note, int amount, String transactionType) {
        this.note = note;
        this.amount = amount;
        this.transactionType = transactionType;
    }

    public static class TransactionExtraName {
        public static final String TRANSACTION_EXTRA_NAME = "transaction";
    }

    protected Transaction(Parcel in) {
        id = in.readInt();
        note = in.readString();
        amount = in.readInt();
        transactionType = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(note);
        dest.writeInt(amount);
        dest.writeString(transactionType);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Transaction> CREATOR = new Creator<Transaction>() {
        @Override
        public Transaction createFromParcel(Parcel in) {
            return new Transaction(in);
        }

        @Override
        public Transaction[] newArray(int size) {
            return new Transaction[size];
        }
    };

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

    public void updateInfo(Transaction transaction) {
        this.transactionType = transaction.getTransactionType();
        this.note = transaction.getNote();
        this.amount = transaction.getAmount();
    }

    @Override
    public String toString() {
        return String.format("Type: %s \n Note: %s \n Amount: %s", transactionType, note, amount);
    }
}
