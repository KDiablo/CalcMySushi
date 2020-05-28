package d.diablo.calcmysushi;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.math.BigDecimal;
import java.nio.Buffer;

import top.defaults.colorpicker.ColorPickerPopup;

public class AddPlace extends AppCompatActivity {

    Restaurant r = new Restaurant();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_place);
        this.getSupportActionBar().setTitle("Add a new restaurant");
    }

    public void editFirstColor(View view){
        Button b = (Button) findViewById(R.id.btnEditFirstPlate);
        editColor(view, b,1);
    }
    public void editSecondColor(View view){
        Button b = (Button) findViewById(R.id.btnEditSecondPlate);
        editColor(view, b, 2);
    }
    public void editThirdColor(View view){
        Button b = (Button) findViewById(R.id.btnEditThirdPlate);
        editColor(view, b, 3);
    }
    public void editFourthColor(View view){
        Button b = (Button) findViewById(R.id.btnEditFourthPlate);
        editColor(view, b, 4);
    }
    public void editFifthColor(View view){
        Button b = (Button) findViewById(R.id.bthEditFifthPlate);
        editColor(view, b, 5);
    }
    public void editSixthColor(View view){
        Button b = (Button) findViewById(R.id.btnEditSixthPlate);
        editColor(view, b, 6);
    }
    public void finalizePlace(View view){

        if (r.get_color1() == 0 || r.get_color2() == 0 || r.get_color3() == 0
                || r.get_color4() == 0 || r.get_color5() == 0 || r.get_color6() == 0){

            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            builder.setMessage("Please choose a color for each plate, even if not needed.")
                    .setTitle("Alert");

            AlertDialog dialog = builder.create();
            dialog.show();
            return;
        }
        EditText txtName = (EditText)findViewById(R.id.txtName);
        String name = txtName.getText().toString().toLowerCase();
        r.set_name(name);

        EditText txtP1 = (EditText)findViewById(R.id.txtFirstPlatePrice);
        double p1 = Double.parseDouble(txtP1.getText().toString());
        r.set_price1(p1);

        EditText txtP2 = (EditText)findViewById(R.id.txtSecondPlatePrice);
        double p2 = Double.parseDouble(txtP2.getText().toString());
        r.set_price2(p2);

        EditText txtP3 = (EditText)findViewById(R.id.txtThirdPlatePrice);
        double p3 = Double.parseDouble(txtP3.getText().toString());
        r.set_price3(p3);

        EditText txtP4 = (EditText)findViewById(R.id.txtFourthPlatePrice);
        double p4 = Double.parseDouble(txtP4.getText().toString());
        r.set_price4(p4);

        EditText txtP5 = (EditText)findViewById(R.id.txtFifthPlatePrice);
        double p5 = Double.parseDouble(txtP5.getText().toString());
        r.set_price5(p5);

        EditText txtP6 = (EditText)findViewById(R.id.txtSixthPlatePrice);
        double p6 = Double.parseDouble(txtP6.getText().toString());
        r.set_price6(p6);

        //save object to file
        //WriteObjectFile write = new WriteObjectFile(this);
        //write.writeObject(r, r.get_name().toLowerCase());
        Serializer serializer = new Persister();

        try {
            File result = new File(getFilesDir().getPath() + "/" + r.get_name() + ".xml");
            if (!result.exists())
                serializer.write(r, result);
            else{
                AlertDialog.Builder builder= new AlertDialog.Builder(this);
                builder.setMessage("This Restaurant already exists.")
                        .setTitle("Alert");

                AlertDialog dialog = builder.create();
                dialog.show();
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        Intent intent = new Intent(this, Menu.class);
        startActivity(intent);

    }

    public void editColor(final View view, final Button b, final int buttonId){
        new ColorPickerPopup.Builder(this)
                .initialColor(Color.RED) // Set initial color
                .enableBrightness(true) // Enable brightness slider or not
                .enableAlpha(false) // Enable alpha slider or not
                .okTitle("Choose")
                .cancelTitle("Cancel")
                .showIndicator(true)
                .showValue(true)
                .build()
                .show(view, new ColorPickerPopup.ColorPickerObserver() {
                    @Override
                    public void onColorPicked(int color) {
                        b.setBackgroundColor(color);
                        switch (buttonId){
                            case 1: r.set_color1(color);
                            case 2: r.set_color2(color);
                            case 3: r.set_color3(color);
                            case 4: r.set_color4(color);
                            case 5: r.set_color5(color);
                            case 6: r.set_color6(color);
                        }
                    }

                    @Override
                    public void onColor(int color, boolean fromUser) {
                    }
                });//end popupBuilder
    }//end editcolor
}
