package com.example.maratmamin.project1_rewrittenandrefactored;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    final static int requestCode2 = 2;

    Button mAddButton;
    Button mRemoveButton;

    ListView mMainActivityListView;
    EditText mInputTask;

//    ArrayList<String> newArrayList = new ArrayList<String>();
    ArrayList<String> mListOfListTitles = new ArrayList<String>();
//    ArrayList<ArrayList<String>> mListOfArrayLists = new ArrayList<ArrayList<String>>();
    ArrayAdapter<String> mListOfListTitlesAdapter;

    int selectedMainIndex = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAddButton = (Button) findViewById(R.id.add_button_details);
        mRemoveButton = (Button) findViewById(R.id.remove_button);
        mMainActivityListView = (ListView) findViewById(R.id.list_of_lists);
        mInputTask = (EditText) findViewById(R.id.create_task);

        mListOfListTitlesAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, mListOfListTitles);
        mMainActivityListView.setAdapter(mListOfListTitlesAdapter);

        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mInputTask.getText().length() > 0) {
                    String addButtonGetTextFromMainActivityEditText = mInputTask.getText().toString();
                    mListOfListTitles.add(addButtonGetTextFromMainActivityEditText);
                    mMainActivityListView.setAdapter(mListOfListTitlesAdapter);
                    mListOfListTitlesAdapter.notifyDataSetChanged();
                    mInputTask.setText(" ");
                }
                else {
                    mInputTask.setError("Please Type In Your Task Here");
                }
            }
        });

        mRemoveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListOfListTitles.isEmpty()) {
                    Toast.makeText(MainActivity.this, "You have no tasks to remove.", Toast.LENGTH_SHORT).show();
                } else {
                    mListOfListTitles.remove(mListOfListTitles.size() - 1);
                    mListOfListTitlesAdapter.notifyDataSetChanged();
                    mInputTask.setText(" ");
                }
            }
        });

        AdapterView.OnItemClickListener mainActivityOnItemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent toListDetailsIntent = new Intent(MainActivity.this, List_Details.class);
                Bundle bundle = new Bundle();
                bundle.putStringArrayList(List_Details.mListDetailsKey, mListOfListTitles);
                toListDetailsIntent.putExtras(bundle);
//                mMainActivityListView.setAdapter(mListOfListTitlesAdapter);
//                mListOfListTitlesAdapter.notifyDataSetChanged();
                startActivityForResult(toListDetailsIntent, requestCode2);
            }
        };

        mMainActivityListView.setOnItemClickListener(mainActivityOnItemClickListener);
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (requestCode == requestCode2){
//            if (data.hasExtra(List_Details.mListDetailsKey)){
//                newArrayList = data.getStringArrayListExtra(List_Details.mListDetailsKey);
//                mListOfArrayLists.set(selectedMainIndex, newArrayList);
//                mListOfListTitles.set(selectedMainIndex, newArrayList.get(0).substring(12));
//                mMainActivityListView.setAdapter(mListOfListTitlesAdapter);
//                mListOfListTitlesAdapter.notifyDataSetChanged();
//                Toast.makeText(MainActivity.this, "List updated!", Toast.LENGTH_SHORT).show();
//            }
//        }
//        selectedMainIndex = 1000;
//    }
}
