package kmitl.lab09.montita58070114.moneyflow;

import android.arch.persistence.room.Room;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import kmitl.lab09.montita58070114.moneyflow.Database.TransactionDB;
import kmitl.lab09.montita58070114.moneyflow.Trask.DeleteTransactionTask;
import kmitl.lab09.montita58070114.moneyflow.Trask.InsertTransactionTask;
import kmitl.lab09.montita58070114.moneyflow.Trask.UpdateTransactionTask;
import kmitl.lab09.montita58070114.moneyflow.model.Transaction;

public class AddTransactionActivity extends AppCompatActivity implements View.OnClickListener,
        RadioGroup.OnCheckedChangeListener{
    private RadioGroup radioType;
    private EditText etNote, etAmount;
    private Button btnSave, btnDelete;
    private String transactionType, note;
    private int amount;
    private Transaction transaction;
    private TransactionDB transactionDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_transaction);
        radioType = findViewById(R.id.radioType);
        etNote = findViewById(R.id.etNote);
        etAmount = findViewById(R.id.etAmount);
        btnSave = findViewById(R.id.btnSave);
        btnDelete = findViewById(R.id.btnDelete);

        btnSave.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        radioType.setOnCheckedChangeListener(this);


        transactionDB = Room.databaseBuilder(this, TransactionDB.class, "TransactionDB")
                .fallbackToDestructiveMigration().build();

        new InsertTransactionTask(transactionDB,transaction).execute();
        new DeleteTransactionTask(transactionDB,transaction).execute();
        new UpdateTransactionTask(transactionDB,transaction).execute();


    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case (R.id.btnDelete):
                Bundle bundle = getIntent().getExtras();
                int id = bundle.getInt("id");
                transaction.setId(id);
                finish();

                break;
            case (R.id.btnSave):
                note = String.valueOf(etNote.getText());
                amount = Integer.valueOf(String.valueOf(etAmount.getText()));
                transaction.setAmount(amount);
                transaction.setNote(note);
                finish();
                break;
        }

    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (radioType.getCheckedRadioButtonId()){
            case (R.id.income):
                transactionType = "income";
                transaction.setTransactionType(transactionType);
                break;
            case (R.id.expense):
                transactionType = "expense";
                transaction.setTransactionType(transactionType);
                break;
        }

    }
}
