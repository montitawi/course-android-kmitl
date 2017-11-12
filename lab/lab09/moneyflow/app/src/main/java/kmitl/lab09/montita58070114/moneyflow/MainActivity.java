package kmitl.lab09.montita58070114.moneyflow;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import kmitl.lab09.montita58070114.moneyflow.Database.TransactionDB;
import kmitl.lab09.montita58070114.moneyflow.Trask.GetAmountTask;
import kmitl.lab09.montita58070114.moneyflow.Trask.GetIncomeAmountTask;
import kmitl.lab09.montita58070114.moneyflow.Trask.GetTransactionTask;
import kmitl.lab09.montita58070114.moneyflow.model.Transaction;

public class MainActivity extends AppCompatActivity implements GetTransactionTask.OnGetTransactionListener,
        GetAmountTask.OnGetAmountListener, GetIncomeAmountTask.OnGetIncomeAmountListener, ListView.OnItemClickListener {

    private List<Transaction> transactions;
    private ListView transactionListView;
    private TextView amountText;
    private TransactionDB transactionDB;
    private int totalAmount;
    private int sumIncome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        transactionListView = findViewById(R.id.transactionListView);
        transactionListView.setOnItemClickListener(this);
        amountText = findViewById(R.id.amountText);
        initDB();


    }

    @Override
    protected void onResume() {
        super.onResume();
        getTransaction();

    }

    private void initDB() {
        transactionDB = Room.databaseBuilder(this, TransactionDB.class, "TransactionDB")
                .fallbackToDestructiveMigration().build();
    }

    private void getTransaction() {
        new GetTransactionTask(transactionDB, this).execute();
        new GetAmountTask(transactionDB, this).execute();
        new GetIncomeAmountTask(transactionDB, this).execute();

    }


    @Override
    public List<Transaction> onGetTransactionSuccess(List<Transaction> transactions) {
        this.transactions = transactions;
        updateTransactionView(transactions);

        ArrayAdapter<Transaction> adapter = new ArrayAdapter<>(MainActivity.this,
                android.R.layout.simple_list_item_1, transactions);
        transactionListView.setAdapter(adapter);


        return null;
    }

    @Override
    public void onGetTransactionListenerFailed() {


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.btnAdd:
                Intent intent = new Intent(MainActivity.this, TransactionActivity.class);
                startActivity(intent);
                return true;
            default:
                return false;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        Intent intent = new Intent(MainActivity.this, TransactionActivity.class);
        intent.putExtra("id", transactions.get(i).getId());
        startActivity(intent);
    }

    private void updateTransactionView(List<Transaction> transactions) {
        if (transactions.size() == 0) {
            transactionListView.setVisibility(View.GONE);
        } else {
            transactionListView.setVisibility(View.VISIBLE);

        }
    }


    @Override
    public void onGetAmountSuccess(int sumAmount) {
        totalAmount = sumAmount;
        amountText.setText(String.valueOf(sumAmount));
        updateMoney(totalAmount, sumIncome);

    }

    @Override
    public void onGetIncomeAmountSuccess(int sumAmountIncome) {
        sumIncome = sumAmountIncome;

    }

    private void updateMoney(int totalAmount, int sumIncome) {

        if ((float) totalAmount / sumIncome <= 0.25) {
            amountText.setTextColor(ContextCompat.getColor(this, android.R.color.holo_red_light));
        } else if ((float) totalAmount / sumIncome <= 0.5) {
            amountText.setTextColor(ContextCompat.getColor(this, android.R.color.holo_orange_light));
        } else {
            amountText.setTextColor(ContextCompat.getColor(this, android.R.color.holo_green_light));
        }

    }
}