package com.bieito_sousa.u3_student_bieito;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Display;
import android.view.Surface;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity  {
    private static final String TAG = "MyActivity";
    private Button button ;
    private TextView textView ;
    private CheckBox checkBox ;
    private EditText editText ;
    private RadioButton radioButton_red ;
    private RadioButton radioButton_blue ;
    private Spinner spinner_prv;
    private String[] province_number;
    private String[] province_name;
    private String[] province_autonomy;
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
        eventOperations();
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
        this.spinner_prv=findViewById(R.id.spinner_id);
        createSpinnerAdapter();
        this.province_number = getResources().getStringArray(R.array.province_number);
        this.province_name = getResources().getStringArray(R.array.province_name);
        this.province_autonomy = getResources().getStringArray(R.array.province_autonomy);
        /*
        this.chronometer=findViewById(R.id.chronometer_id);
        this.switch_Chr=findViewById(R.id.switch_Chr_id);
        this.temp=findViewById(R.id.);
        */
    }

    /*
    [https://developer.android.com/guide/topics/ui/controls/spinner]
     */
    protected void createSpinnerAdapter(){
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter
                .createFromResource(this,
                        R.array.province_name, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner_prv.setAdapter(adapter);
    };

    protected void eventOperations(){
        onSpinnerClicked();
        onImageViewClicked();
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

    public void onSpinnerClicked() {
        spinner_prv.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                opSpinner(position , id);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    public void opSpinner(int pos, long id) {
        Log.i(TAG, "POS ["+pos + "] ID ["+ id+"]");
        if (this.province_autonomy[pos].contains("Galicia")) {
            Toast.makeText(getApplicationContext(), getString(R.string.text_toast_gal), Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(getApplicationContext(), getString(R.string.text_toast_no_gal),Toast.LENGTH_LONG).show();
        }
    }

    public void onImageViewClicked(){
        if (getRotation(this).contains("vertical")){
            ImageView imageView = findViewById(R.id.imagenView_id);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String texto = (String)imageView.getTag();
                    Toast.makeText(getApplicationContext(), (String)imageView.getTag(), Toast.LENGTH_LONG).show();
                }
            });
        }else if (getRotation(this).contains("horizontal")){

        }
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }

    /*
    [https://es.stackoverflow.com/questions/51511/c%C3%B3mo-detectar-la-orientacionhorizontal-o-vertical-en-android-de-la-pantalla-e]
     */
    public String getRotation(Context context){
        final Display display = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        switch (display.getRotation()) {
            case 0:
            case 180:
                return "vertical";
            case 90:
            default:
                return "horizontal";
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