package kmitl.lab09.montita58070114.moneyflow;

import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import kmitl.lab09.montita58070114.moneyflow.Database.TransactionDB;
import kmitl.lab09.montita58070114.moneyflow.Trask.DeleteTransactionTask;
import kmitl.lab09.montita58070114.moneyflow.Trask.InsertTransactionTask;
import kmitl.lab09.montita58070114.moneyflow.Trask.UpdateTransactionTask;
import kmitl.lab09.montita58070114.moneyflow.model.Transaction;

import static kmitl.lab09.montita58070114.moneyflow.model.Transaction.TransactionExtraName.TRANSACTION_EXTRA_NAME;

public class TransactionActivity extends AppCompatActivity implements View.OnClickListener {
    private RadioGroup radioType;
    private EditText etNote, etAmount;
    private Button btnSave, btnDelete;
    private Transaction transaction;
    private TransactionDB transactionDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);
        radioType = findViewById(R.id.radioType);
        etNote = findViewById(R.id.etNote);
        etAmount = findViewById(R.id.etAmount);
        btnSave = findViewById(R.id.btnSave);
        btnDelete = findViewById(R.id.btnDelete);

        btnSave.setOnClickListener(this);
        btnDelete.setOnClickListener(this);

        transaction = getIntent().getParcelableExtra(TRANSACTION_EXTRA_NAME);

        initDB();
        setupInfo();


    }

    private void initDB() {
        transactionDB = Room.databaseBuilder(this, TransactionDB.class, "TransactionDB")
                .fallbackToDestructiveMigration().build();
    }

    private void setupInfo() {

        if (transaction != null) {
            if (transaction.getTransactionType().equals(R.string.income)) {
                radioType.check(R.id.income);
            } else {
                radioType.check(R.id.expense);
            }
            etNote.setText(transaction.getNote());
            etAmount.setText(String.valueOf(transaction.getAmount()));
            btnDelete.setVisibility(View.VISIBLE);
        } else {
            btnDelete.setVisibility(View.GONE);
        }
    }

    private void save() {
        String transactionType = "";
        String note;
        int amount;

        switch (radioType.getCheckedRadioButtonId()) {
            case R.id.income:
                transactionType = "income";
                break;
            case R.id.expense:
                transactionType = "expense";
                break;
        }

        note = etNote.getText().toString();

        try {
            amount = Integer.parseInt(etAmount.getText().toString());
        } catch (IllegalArgumentException ignore) {
            Toast.makeText(this, "Invalid input", Toast.LENGTH_SHORT).show();
            return;
        }

        Transaction transaction = new Transaction(note, amount, transactionType);

        if (this.transaction != null) {
            this.transaction.updateInfo(transaction);
            new UpdateTransactionTask(transactionDB, this.transaction).execute();

        } else {
            new InsertTransactionTask(transactionDB, transaction).execute();

        }
    }

    private void delete() {
        new DeleteTransactionTask(transactionDB, transaction).execute();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnDelete:
                delete();
                finish();
                break;
            case R.id.btnSave:
                save();
                finish();
                break;
        }

    }

}
