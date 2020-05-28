package d.diablo.calcmysushi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void addPlace(View view){
        //open AddPlace activity
        Intent intent = new Intent(this, AddPlace.class);
        startActivity(intent);
    }

    public void deletePlace(View view){
        Intent intent = new Intent(this, Delete.class);
        startActivity(intent);
    }

    public void sushiGo(View view){
        Intent intent = new Intent(this, ChooseRestaurant.class);
        startActivity(intent);
    }


}
