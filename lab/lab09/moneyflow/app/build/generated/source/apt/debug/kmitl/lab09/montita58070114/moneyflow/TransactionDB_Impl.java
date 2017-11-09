package kmitl.lab09.montita58070114.moneyflow;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.db.SupportSQLiteOpenHelper.Callback;
import android.arch.persistence.db.SupportSQLiteOpenHelper.Configuration;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.RoomOpenHelper;
import android.arch.persistence.room.RoomOpenHelper.Delegate;
import android.arch.persistence.room.util.TableInfo;
import android.arch.persistence.room.util.TableInfo.Column;
import android.arch.persistence.room.util.TableInfo.ForeignKey;
import android.arch.persistence.room.util.TableInfo.Index;
import java.lang.IllegalStateException;
import java.lang.Override;
import java.lang.String;
import java.util.HashMap;
import java.util.HashSet;

public class TransactionDB_Impl extends TransactionDB {
  private volatile TransactionInfoDAO _transactionInfoDAO;

  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `TRANSACTION_INFO` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `TRANSACTION_TYPE` TEXT, `NOTE` TEXT, `AMOUT` INTEGER NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, \"3651dfae497d831d0bb707bb3c8d4514\")");
      }

      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `TRANSACTION_INFO`");
      }

      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      protected void validateMigration(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsTRANSACTIONINFO = new HashMap<String, TableInfo.Column>(4);
        _columnsTRANSACTIONINFO.put("id", new TableInfo.Column("id", "INTEGER", true, 1));
        _columnsTRANSACTIONINFO.put("TRANSACTION_TYPE", new TableInfo.Column("TRANSACTION_TYPE", "TEXT", false, 0));
        _columnsTRANSACTIONINFO.put("NOTE", new TableInfo.Column("NOTE", "TEXT", false, 0));
        _columnsTRANSACTIONINFO.put("AMOUT", new TableInfo.Column("AMOUT", "INTEGER", true, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysTRANSACTIONINFO = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesTRANSACTIONINFO = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoTRANSACTIONINFO = new TableInfo("TRANSACTION_INFO", _columnsTRANSACTIONINFO, _foreignKeysTRANSACTIONINFO, _indicesTRANSACTIONINFO);
        final TableInfo _existingTRANSACTIONINFO = TableInfo.read(_db, "TRANSACTION_INFO");
        if (! _infoTRANSACTIONINFO.equals(_existingTRANSACTIONINFO)) {
          throw new IllegalStateException("Migration didn't properly handle TRANSACTION_INFO(kmitl.lab09.montita58070114.moneyflow.TransactionInfo).\n"
                  + " Expected:\n" + _infoTRANSACTIONINFO + "\n"
                  + " Found:\n" + _existingTRANSACTIONINFO);
        }
      }
    }, "3651dfae497d831d0bb707bb3c8d4514");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    return new InvalidationTracker(this, "TRANSACTION_INFO");
  }

  @Override
  public TransactionInfoDAO getTransactionInfoDAO() {
    if (_transactionInfoDAO != null) {
      return _transactionInfoDAO;
    } else {
      synchronized(this) {
        if(_transactionInfoDAO == null) {
          _transactionInfoDAO = new TransactionInfoDAO_Impl(this);
        }
        return _transactionInfoDAO;
      }
    }
  }
}
