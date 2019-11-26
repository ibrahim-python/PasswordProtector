package com.example.passwordprotector;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.passwordprotector.database1.PassWord;

import java.util.List;

import static android.support.v7.widget.DividerItemDecoration.VERTICAL;

public class PassWordActivity extends AppCompatActivity {

    private PassWordViewModel mPasswordViewModel;
    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;
    public static final int UPDATE_ACTIVITY_REQUEST_CODE = 2;

    public static final int EDIT_WORD_REQUEST = 2;
    private RecyclerView mRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passwords);
        mRecyclerView = findViewById(R.id.recyclerview);
        final PassWordListAdapter adapter = new PassWordListAdapter();
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        mPasswordViewModel = new ViewModelProviders().of(this).get(PassWordViewModel.class);
        mPasswordViewModel.getAllPassWords().observe(this, new Observer<List<PassWord>>() {
            @Override
            public void onChanged(@Nullable final List<PassWord> words) {
                // Update the cached copy of the words in the adapter.
                adapter.setWords(words);
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PassWordActivity.this, PassWordEditActivity.class);
                startActivityForResult(intent, NEW_WORD_ACTIVITY_REQUEST_CODE);
            }
        });

        DividerItemDecoration decoration = new DividerItemDecoration(getApplicationContext(), VERTICAL);
        mRecyclerView.addItemDecoration(decoration);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(final RecyclerView.ViewHolder viewHolder, int swipeDir) {
                mPasswordViewModel.delete(adapter.getPassWordAt(viewHolder.getAdapterPosition()));
            }
        }).attachToRecyclerView(mRecyclerView);

        adapter.setOnItemClickListener(new PassWordListAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(PassWord word) {
                Intent intent = new Intent(PassWordActivity.this, PassWordEditActivity.class);
                intent.putExtra(PassWordEditActivity.EXTRA_ID, word.getId());
                intent.putExtra(PassWordEditActivity.EXTRA_PASSWORD, word.getPassword());
                intent.putExtra(PassWordEditActivity.EXTRA_USERNAME, word.getUsername());
                intent.putExtra(PassWordEditActivity.EXTRA_WEBSITE, word.getWebsite());


                startActivityForResult(intent, EDIT_WORD_REQUEST);
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            String username =data.getStringExtra(PassWordEditActivity.EXTRA_USERNAME);
            byte[] password =data.getByteArrayExtra(PassWordEditActivity.EXTRA_PASSWORD);
            String website =data.getStringExtra(PassWordEditActivity.EXTRA_WEBSITE);

            PassWord word = new PassWord(username,password,website);

            mPasswordViewModel.insert(word);
        }
        else if (requestCode == UPDATE_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            int id = data.getIntExtra(PassWordEditActivity.EXTRA_ID, -1);

            if (id == -1) {
                Toast.makeText(this, "Note can't be updated", Toast.LENGTH_SHORT).show();
                return;
            }

            String username =data.getStringExtra(PassWordEditActivity.EXTRA_USERNAME);
            byte[] password =data.getByteArrayExtra(PassWordEditActivity.EXTRA_PASSWORD);
            String website =data.getStringExtra(PassWordEditActivity.EXTRA_WEBSITE);

            PassWord word = new PassWord(username,password,website);
            word.setId(id);

            mPasswordViewModel.update(word);

            Toast.makeText(this, "Note updated", Toast.LENGTH_SHORT).show();
        }

        else {
            Toast.makeText(
                    getApplicationContext(),
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG).show();
        }
    }

}