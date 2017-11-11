package kmitl.lab09.montita58070114.moneyflow;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import kmitl.lab09.montita58070114.moneyflow.Database.TransactionDB;
import kmitl.lab09.montita58070114.moneyflow.Trask.GetTransactionTask;
import kmitl.lab09.montita58070114.moneyflow.model.Transaction;

public class MainActivity extends AppCompatActivity implements GetTransactionTask.OnGetTransactionListener,
        ListView.OnItemClickListener {

    List<Transaction> transactions;
    ListView listView;
    TransactionDB transactionDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        initDB();
        new GetTransactionTask(transactionDB, this).execute();


    }

    private void initDB() {
        transactionDB = Room.databaseBuilder(this, TransactionDB.class, "TransactionDB")
                .fallbackToDestructiveMigration().build();
    }


    @Override
    public List<Transaction> onGetTransactionSuccess(List<Transaction> transactions) {
        this.transactions = transactions;

        ArrayAdapter<Transaction> adapter = new ArrayAdapter<>(MainActivity.this,
                android.R.layout.simple_list_item_1, transactions);
        listView.setAdapter(adapter);

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
                Intent intent = new Intent(MainActivity.this, AddTransactionActivity.class);
                startActivity(intent);
                return true;
            default:
                return false;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        Intent intent = new Intent(MainActivity.this, AddTransactionActivity.class);
        intent.putExtra("id", transactions.get(i).getId());
        startActivity(intent);
    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        new GetTransactionTask(transactionDB, this).execute();
//    }

}