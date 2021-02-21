package com.bieito_sousa.u3_student_bieito;

import android.content.Context;
import android.os.Bundle;
import android.os.SystemClock;
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
    Layout separador
    [https://stackoverflow.com/questions/5049852/android-drawing-separator-divider-line-in-layout]
     */


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
        this.chronometer=findViewById(R.id.chronometer_id);
        this.switch_Chr=findViewById(R.id.switch_id);
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
        chronometerOperations();
    }

    /*
    Button
    [https://developer.android.com/reference/android/widget/Button]
     button actions :
        checkBox is true ->  replace text with the text of editText
        checkBox is false -> replace text with the text of ""
     */
    /*
    Editable text to string
    [https://stackoverflow.com/questions/2216201/editable-text-to-string]
     */

    public void onButtonClicked(View view){
        if(checkBox.isChecked()){
            textView.setText("");
        }else {
            textView.setText(ucFirst((String) editText.getText().toString()));
        }
    }
    /*
        poner en mayúsculas el primer carácter de una cadena en Android
        [https://es.stackoverflow.com/questions/10393/c%C3%B3mo-poner-en-may%C3%BAsculas-el-primer-car%C3%A1cter-de-una-cadena-en-android/10395]
         */
    public static String ucFirst(String str) {
        if (str == null || str.isEmpty()) {
            return "";
        } else {
            return str.substring(0, 1).toUpperCase() + str.substring(1);
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
                    textView.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.red));
                    break;
            case R.id.radio_blue:
                if (checked)
                    radioButton_red.setChecked(false);
                    textView.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.blue));
                    break;
        }
    }
    /*
    Spinner
    [https://developer.android.com/reference/android/widget/Spinner]
    */
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
    /*
    Toast
    [https://developer.android.com/guide/topics/ui/notifiers/toasts]
     */

    public void opSpinner(int pos, long id) {
       // Log.i(TAG, "POS ["+pos + "] ID ["+ id+"]");
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
        final int rotation = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getRotation();
        switch (rotation) {
            case Surface.ROTATION_0:
            case Surface.ROTATION_180:
                return "vertical";
            case Surface.ROTATION_90:
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
        if (item.getItemId() == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    /*
    Chronometer
    [https://developer.android.com/reference/android/widget/Chronometer]
        =   getOnChronometerTickListener   => Chronometer click Event  management
        =   Chronometer.getBase            => Return the base time in milliseconds
        =   chronometer.setBase            => Put the base time in milliseconds
        =   chronometer.start()            => Start chronometer
        =   chronometer.stop()             => Stop chronometer
     Switch
     [https://developer.android.com/reference/android/widget/Switch]
     extend CompoundButton [https://developer.android.com/reference/android/widget/CompoundButton]
        =   CompoundButton.isChecked() -> [true| false]
     Time
     [https://developer.android.com/reference/android/os/SystemClock]
        =   SystemClock-                  = > system time management
        =   SystemClock.elapsedRealtime() = > take system time in milliseconds
        =   milliseconds to second        = > [ milliseconds/1000 = second ]

    */
     public void chronometerOperations(){
        this.chronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer_var) { //  when chronometer 15 seg
                if (((int)(SystemClock.elapsedRealtime() - chronometer_var.getBase())/1000) == temp){
                    finish(); // Activity destroy
                }}
        });
        this.switch_Chr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (switch_Chr.isChecked()){// reload chronometer
                    temp = 15; // chronometer timer 15 seg
                    chronometer.setBase(SystemClock.elapsedRealtime());
                    chronometer.start();
                    Toast.makeText(getApplicationContext(), getString(R.string.text_start), Toast.LENGTH_LONG).show();
                }else{ // stop chronometer
                    chronometer.stop();
                    Toast.makeText(getApplicationContext(), getString(R.string.text_stop), Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}