package com.example.debtsapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class AddNoteActivity extends AppCompatActivity {
    public static final String EXTRA_ID = "id";
    public static final String EXTRA_NAME = "name";
    public static final String EXTRA_AMOUNT = "amount";
    public static final String EXTRA_DESCRIPTION = "description";

    private EditText editTextName;
    private EditText editTextDescription;
    private EditText editTextAmount;
    private int amount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        editTextName = findViewById(R.id.edit_text_name);
        editTextDescription = findViewById(R.id.edit_text_description);
        editTextAmount = findViewById(R.id.edit_text_amount);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        Intent intent = getIntent();

        if(intent.hasExtra(EXTRA_ID)) {
            setTitle("Edit Note");
            editTextName.setText(intent.getStringExtra(EXTRA_NAME));
            editTextDescription.setText(intent.getStringExtra(EXTRA_DESCRIPTION));
            int getAmount = intent.getIntExtra(EXTRA_AMOUNT,1);
            editTextAmount.setText(String.valueOf(getAmount));
        }else {
            setTitle("Add Note");
        }
    }
    private void saveNote() {
         String name = editTextName.getText().toString();
         String description = editTextDescription.getText().toString();

        // to check wether the amount field is empty or not.. we can not do it normal way because it is a number edit text
        if(!TextUtils.isEmpty(editTextAmount.getText().toString().trim())) {
             amount = Integer.parseInt(editTextAmount.getText().toString());
        }
        if (name.trim().isEmpty() || description.trim().isEmpty()|| editTextAmount.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please insert a name, an amount and a description", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent data = new Intent();
//        final AlertDialog.Builder builer = new AlertDialog.Builder(AddNoteActivity.this);
//        builer.setTitle("Attention!")
//                .setMessage("Do you really want to change "+name+"'s debts to "+amount+"?")
//                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//
//                    }
//                })
//                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        data.putExtra(EXTRA_NAME, name);
//                        data.putExtra(EXTRA_DESCRIPTION, description);
//                        data.putExtra(EXTRA_AMOUNT, amount);
//                    }
//
//                });
//        AlertDialog dialog = builer.create();
//        dialog.show();
        data.putExtra(EXTRA_NAME, name);
        data.putExtra(EXTRA_DESCRIPTION, description);
        data.putExtra(EXTRA_AMOUNT, amount);

        int id = getIntent().getIntExtra(EXTRA_ID,-1);
        if(id != -1) {
            data.putExtra(EXTRA_ID,id);
        }
        setResult(RESULT_OK, data);
        finish();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_note_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_note:
                saveNote();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}