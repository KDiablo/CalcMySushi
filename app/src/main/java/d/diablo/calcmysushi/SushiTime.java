package d.diablo.calcmysushi;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.VectorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.NumberFormat;

public class SushiTime extends AppCompatActivity {

    Restaurant r = null;
    double total = 0;

    ImageView plate1 = null;
    ImageView plate2 = null;
    ImageView plate3 = null;
    ImageView plate4 = null;
    ImageView plate5 = null;
    ImageView plate6 = null;

    TextView p1Cost = null;
    TextView p2Cost = null;
    TextView p3Cost = null;
    TextView p4Cost = null;
    TextView p5Cost = null;
    TextView p6Cost = null;

    NumberFormat formatter = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sushi_time);

        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        r = (Restaurant) bundle.getSerializable("RESTAURANT");
        formatter = NumberFormat.getCurrencyInstance();

        getSupportActionBar().setTitle(r.get_name());


        plate1 = (ImageView)findViewById(R.id.plate1);
        plate2 = (ImageView)findViewById(R.id.plate2);
        plate3 = (ImageView)findViewById(R.id.plate3);
        plate4 = (ImageView)findViewById(R.id.plate4);
        plate5 = (ImageView)findViewById(R.id.plate5);
        plate6 = (ImageView)findViewById(R.id.plate6);

        p1Cost = (TextView)findViewById(R.id.p1Cost);
        p2Cost = (TextView)findViewById(R.id.p2Cost);
        p3Cost = (TextView)findViewById(R.id.p3Cost);
        p4Cost = (TextView)findViewById(R.id.p4Cost);
        p5Cost = (TextView)findViewById(R.id.p5Cost);
        p6Cost = (TextView)findViewById(R.id.p6Cost);

        plate1.setColorFilter(r.get_color1());
        plate2.setColorFilter(r.get_color2());
        plate3.setColorFilter(r.get_color3());
        plate4.setColorFilter(r.get_color4());
        plate5.setColorFilter(r.get_color5());
        plate6.setColorFilter(r.get_color6());

        p1Cost.setText(formatter.format(r.get_price1()));
        p2Cost.setText(formatter.format(r.get_price2()));
        p3Cost.setText(formatter.format(r.get_price3()));
        p4Cost.setText(formatter.format(r.get_price4()));
        p5Cost.setText(formatter.format(r.get_price5()));
        p6Cost.setText(formatter.format(r.get_price6()));

    }

    private void addToTotal(double cost){
        if (total + cost >= 0){
            total += cost;
            TextView totalBox = (TextView)findViewById(R.id.Total);
            String moneyString = formatter.format(total);
            System.out.println(moneyString);
            String price = "Total: " + moneyString;
            totalBox.setText(price);
        }

    }

    public void sub1(View v){
        addToTotal(r.get_price1() * -1);
    }
    public void sub2(View v){
        addToTotal(r.get_price2() * -1);
    }
    public void sub3(View v){
        addToTotal(r.get_price3() * -1);
    }
    public void sub4(View v){
        addToTotal(r.get_price4() * -1);
    }
    public void sub5(View v){
        addToTotal(r.get_price5() * -1);
    }
    public void sub6(View v){
        addToTotal(r.get_price6() * -1);
    }



    public void add1(View v){
        addToTotal(r.get_price1());
    }
    public void add2(View v){
        addToTotal(r.get_price2());
    }
    public void add3(View v){
        addToTotal(r.get_price3());
    }
    public void add4(View v){
        addToTotal(r.get_price4());
    }
    public void add5(View v){
        addToTotal(r.get_price5());
    }
    public void add6(View v){
        addToTotal(r.get_price6());
    }

    public void addon(View v){
        TextView addon = (TextView)findViewById(R.id.editText);
        addToTotal(Double.parseDouble(addon.getText().toString()));
    }
}
