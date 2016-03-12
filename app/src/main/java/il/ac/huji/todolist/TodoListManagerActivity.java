package il.ac.huji.todolist;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class TodoListManagerActivity extends AppCompatActivity {
    ArrayList<String> items = new ArrayList<String>();
   // ListView lstTodoItems;
    MyArrayAdapter adapter;
    //private ArrayAdapter<String> itemsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_list_manager);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        adapter = new MyArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, items);
        ListView lstTodoItems = (ListView) findViewById(R.id.lstTodoItems);
        registerForContextMenu(lstTodoItems);
        //items = new ArrayList<>();
       // lstTodoItems.setAdapter(new MyArrayAdapter(this, items));
      /*  MyArrayAdapter itemsAdapter = new MyArrayAdapter<String>(this,
                android.R.layout.rowlayout, items);*/
        lstTodoItems.setAdapter(adapter);
        //items.add("First Item");
        //items.add("Second Item");
    }

    /*public class MyArrayAdapter extends ArrayAdapter<String> {
        private final Context context;
        private final String[] values;

        public MyArrayAdapter(Context context, String[] values) {
            super(context, -1, values);
            this.context = context;
            this.values = values;
        }

        public View getView(int position, View convertView, ViewGroup parent){
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View rowView = inflater.inflate(R.layout.rowlayout, parent, false);
            TextView textView = (TextView) rowView.findViewById(R.id.label);
            textView.setText(values[position]);
            if (position==2) {
                rowView.setBackgroundColor(Color.GREEN);
            }
            return rowView;
        }
    }*/


        @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuItemAdd:
                EditText edtNewItem = (EditText) findViewById(R.id.edtNewItem);
                String itemText = edtNewItem.getText().toString();

                /*synchronized (adapter) {
                    adapter.add(itemText);
                    adapter.notifyDataSetChanged();
                }*/
                adapter.add(itemText);
                edtNewItem.setText("");

                break;
            default:
                break;
        }

        return true;
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        String ConTitle = ((TextView) info.targetView).getText().toString();
        menu.setHeaderTitle(ConTitle);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
    }

    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.menuItemDelete:
                adapter.remove(adapter.getItem(info.position));
                Toast.makeText(this, "Item deleted", Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }


}