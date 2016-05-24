package com.example.maratmamin.project1_rewrittenandrefactored;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class List_Details extends AppCompatActivity {
    final static String mListDetailsKey = "array";
    final static String mALALS = "ArrayListOfArrayLists";
    final static String mIntPos = "Position";
    final static int requestCode = -1;

    Button mAddButtonInListDetails, mRemoveButtonInListDetails, mBackButton;
    TextView mTaskName;
    ListView mItemListRelatedToSpecificTask;
    EditText mInputItemsIntoListDetailsActivity;

    String mTitleName;

//    ArrayList<Integer> mSelectedIndicesArrayList = new ArrayList<Integer>();
    ArrayList<String> mListOfItemsArrayList = new ArrayList<String>();
    ArrayAdapter<String> mListOfItemsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list__details);

        mAddButtonInListDetails = (Button) findViewById(R.id.add_button_details);
        mRemoveButtonInListDetails = (Button) findViewById(R.id.remove_button_details);
        mBackButton = (Button) findViewById(R.id.back_button);
        mTaskName = (TextView) findViewById(R.id.main_title_text_view);
        mItemListRelatedToSpecificTask = (ListView) findViewById(R.id.list_of_lists_details);

        mInputItemsIntoListDetailsActivity = (EditText) findViewById(R.id.list_details_edit_text);

//        mListOfItemsAdapter = new ArrayAdapter<String>(List_Details.this, android.R.layout.simple_list_item_1, mListOfItemsArrayList);
//        mItemListRelatedToSpecificTask.setAdapter(mListOfItemsAdapter);

//        if (getIntent().hasExtra(mListDetailsKey)) {
        mListOfItemsArrayList = getIntent().getStringArrayListExtra(mALALS);
//            mListOfItemsAdapter = new ArrayAdapter<String>(List_Details.this, android.R.layout.simple_list_item_1, mListOfItemsArrayList);
//            mItemListRelatedToSpecificTask.setAdapter(mListOfItemsAdapter);
        mTitleName = getIntent().getStringExtra(mListDetailsKey);
        mTaskName.setText(mTitleName);

            mListOfItemsAdapter = new ArrayAdapter<String>(List_Details.this, android.R.layout.simple_list_item_1, mListOfItemsArrayList);
            mItemListRelatedToSpecificTask.setAdapter(mListOfItemsAdapter);

//        }

        mAddButtonInListDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mInputItemsIntoListDetailsActivity.getText().toString().isEmpty()) {
                    // or (mInputItemsIntoListDetailsActivity.getText().length() == 0)
                    mInputItemsIntoListDetailsActivity.setError("Please enter task!");
                } else {
                    String input = mInputItemsIntoListDetailsActivity.getText().toString();
                    String input2 = input.substring(0,1).toUpperCase() + input.substring(1);
                    mListOfItemsArrayList.add(input2);
                    mInputItemsIntoListDetailsActivity.getText().clear();
//                    mListOfItemsAdapter = new ArrayAdapter<String>(List_Details.this, android.R.layout.simple_list_item_1, mListOfItemsArrayList);
//                    mItemListRelatedToSpecificTask.setAdapter(mListOfItemsAdapter);
                    mListOfItemsAdapter.notifyDataSetChanged();
//                    mInputItemsIntoListDetailsActivity.setText(" ");
                }
            }
        });

        mRemoveButtonInListDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListOfItemsArrayList.size() == 0){
                    Toast.makeText(List_Details.this, "There is nothing to remove.", Toast.LENGTH_LONG).show();
                } else {
                    mListOfItemsArrayList.remove(mListOfItemsArrayList.size() - 1);
//                    mListOfItemsAdapter = new ArrayAdapter<String>(List_Details.this, android.R.layout.simple_list_item_1, mListOfItemsArrayList);
//                    mItemListRelatedToSpecificTask.setAdapter(mListOfItemsAdapter);
                    mListOfItemsAdapter.notifyDataSetChanged();
                    mInputItemsIntoListDetailsActivity.getText().clear();
                }
            }
        });

        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent toMainListIntent = new Intent(List_Details.this, MainActivity.class);
//                Bundle bundle = new Bundle();
//                mListOfItemsArrayList.set(0, mTaskName.getText().toString());
//                bundle.putStringArrayList(mListDetailsKey, mListOfItemsArrayList);
//                //mListOfItemsArrayList or mListOfTaskNamesArrayList => or should they be consolidated???!!!
//                toMainListIntent.putExtras(bundle);
//                setResult(RESULT_OK,toMainListIntent);
//                finish();
                Intent returnIntent = new Intent();
                returnIntent.putExtra(mIntPos, getIntent().getIntExtra(mIntPos, requestCode));

                returnIntent.putStringArrayListExtra(MainActivity.mAllSubTasks, mListOfItemsArrayList);

                setResult(RESULT_OK, returnIntent);
                finish();
            }
        });

        AdapterView.OnItemLongClickListener onItemLongClickListener = new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("position", "onItemLongClick: " + position);
                mListOfItemsArrayList.remove(position);
                mListOfItemsAdapter.notifyDataSetChanged();
                mInputItemsIntoListDetailsActivity.getText().clear();
                return false;
            }
        };

        mItemListRelatedToSpecificTask.setOnItemLongClickListener(onItemLongClickListener);
    }

    @Override
    public void onBackPressed() {
        Intent returnIntent = new Intent();
        returnIntent.putExtra(mIntPos, getIntent().getIntExtra(mIntPos, requestCode));

        returnIntent.putStringArrayListExtra(MainActivity.mAllSubTasks, mListOfItemsArrayList);

        setResult(RESULT_OK, returnIntent);
        finish();
    }

}
