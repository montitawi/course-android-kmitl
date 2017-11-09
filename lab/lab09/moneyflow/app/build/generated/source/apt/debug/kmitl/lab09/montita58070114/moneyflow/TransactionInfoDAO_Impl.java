package kmitl.lab09.montita58070114.moneyflow;

import android.arch.persistence.db.SupportSQLiteStatement;
import android.arch.persistence.room.EntityDeletionOrUpdateAdapter;
import android.arch.persistence.room.EntityInsertionAdapter;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.RoomSQLiteQuery;
import android.database.Cursor;
import java.lang.Override;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;

public class TransactionInfoDAO_Impl implements TransactionInfoDAO {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfTransactionInfo;

  private final EntityDeletionOrUpdateAdapter __deletionAdapterOfTransactionInfo;

  private final EntityDeletionOrUpdateAdapter __updateAdapterOfTransactionInfo;

  public TransactionInfoDAO_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfTransactionInfo = new EntityInsertionAdapter<TransactionInfo>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `TRANSACTION_INFO`(`id`,`TRANSACTION_TYPE`,`NOTE`,`AMOUT`) VALUES (nullif(?, 0),?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, TransactionInfo value) {
        stmt.bindLong(1, value.getId());
        if (value.getTransactionType() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getTransactionType());
        }
        if (value.getNote() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getNote());
        }
        stmt.bindLong(4, value.getAmount());
      }
    };
    this.__deletionAdapterOfTransactionInfo = new EntityDeletionOrUpdateAdapter<TransactionInfo>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `TRANSACTION_INFO` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, TransactionInfo value) {
        stmt.bindLong(1, value.getId());
      }
    };
    this.__updateAdapterOfTransactionInfo = new EntityDeletionOrUpdateAdapter<TransactionInfo>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `TRANSACTION_INFO` SET `id` = ?,`TRANSACTION_TYPE` = ?,`NOTE` = ?,`AMOUT` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, TransactionInfo value) {
        stmt.bindLong(1, value.getId());
        if (value.getTransactionType() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getTransactionType());
        }
        if (value.getNote() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getNote());
        }
        stmt.bindLong(4, value.getAmount());
        stmt.bindLong(5, value.getId());
      }
    };
  }

  @Override
  public void insert(TransactionInfo transaction) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfTransactionInfo.insert(transaction);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(TransactionInfo transactionInfo) {
    __db.beginTransaction();
    try {
      __deletionAdapterOfTransactionInfo.handle(transactionInfo);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(TransactionInfo transactionInfo) {
    __db.beginTransaction();
    try {
      __updateAdapterOfTransactionInfo.handle(transactionInfo);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<TransactionInfo> getAllTransaction() {
    final String _sql = "SELECT * FROM TRANSACTION_INFO";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
      final int _cursorIndexOfTransactionType = _cursor.getColumnIndexOrThrow("TRANSACTION_TYPE");
      final int _cursorIndexOfNote = _cursor.getColumnIndexOrThrow("NOTE");
      final int _cursorIndexOfAmount = _cursor.getColumnIndexOrThrow("AMOUT");
      final List<TransactionInfo> _result = new ArrayList<TransactionInfo>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final TransactionInfo _item;
        _item = new TransactionInfo();
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _item.setId(_tmpId);
        final String _tmpTransactionType;
        _tmpTransactionType = _cursor.getString(_cursorIndexOfTransactionType);
        _item.setTransactionType(_tmpTransactionType);
        final String _tmpNote;
        _tmpNote = _cursor.getString(_cursorIndexOfNote);
        _item.setNote(_tmpNote);
        final int _tmpAmount;
        _tmpAmount = _cursor.getInt(_cursorIndexOfAmount);
        _item.setAmount(_tmpAmount);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
