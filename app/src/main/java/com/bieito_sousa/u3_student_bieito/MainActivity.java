package com.bieito_sousa.u3_student_bieito;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button button ;
    private TextView textView ;
    private CheckBox checkBox ;
    private EditText editText ;
    private RadioButton radioButton_red ;
    private RadioButton radioButton_blue ;
    private Spinner spinner_prv;
    private Chronometer chronometer;
    private Switch switch_Chr;
    private int temp;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        defValues();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        /*
        final Button button = findViewById(R.id.button_id);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(checkBox.isChecked()){
                    textView.setText("");
                }else {
                    textView.setText(editText.getText());
                }
            }
        });
        */



    }
    /*
    Define layaut values
    */
    protected void defValues(){
        this.button = findViewById(R.id.button_id);
        this.textView = findViewById(R.id.text_view_id);
        this.checkBox = findViewById(R.id.checkbox_id);
        this.editText = findViewById(R.id.plain_text_input);
        this.radioButton_red = findViewById(R.id.radio_red);
        this.radioButton_blue = findViewById(R.id.radio_blue);
       /*
        this.spinner_prv=findViewById(R.id.);
        this.chronometer=findViewById(R.id.);
        this.switch_Chr=findViewById(R.id.);
        this.temp=findViewById(R.id.);
        this.imageView=findViewById(R.id.);
        */
    }

    /*
     button actions :
        checkBox is true ->  replace text with the text of editText
        checkBox is false -> replace text with the text of ""
     */

    public void onButtonClicked(View view){
        if(checkBox.isChecked()){
            textView.setText("");
        }else {
            textView.setText(editText.getText());
        }
    }


    /*
     radio button actions :
        button red checked -> put the text in red
        button blue checked -> put the text in blue
     */
    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();
        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_red:
                if (checked)
                    radioButton_blue.setChecked(false);
                    textView.setTextColor(ContextCompat.getColor(this,R.color.red));
                    break;
            case R.id.radio_blue:
                if (checked)
                    radioButton_red.setChecked(false);
                    textView.setTextColor(ContextCompat.getColor(this,R.color.blue));
                    break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}