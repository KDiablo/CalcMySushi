package d.diablo.calcmysushi;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.sql.StatementEventListener;

public class ChooseRestaurant extends AppCompatActivity implements MyRecyclerViewAdapter.ItemClickListener {

    MyRecyclerViewAdapter adapter;
    List<File> fileList = new ArrayList<>();
    List<String> nameList = new ArrayList<>();
    File selectedFile = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_restaurant);
        this.getSupportActionBar().setTitle("Choose a restaurant!");

        String name = null;
        File folder = new File(getFilesDir().getPath());
        traverse(folder, "add", null);
        Restaurant r = null;

        for (File f : fileList){
            Serializer serializer = new Persister();
            File source = f;
            try {
                r = serializer.read(Restaurant.class, source);
                name = r.get_name();
                nameList.add(name);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyRecyclerViewAdapter(this, nameList);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
    }

    public void traverse (File dir, String arg, String name) {
        if (dir.exists()) {
            File[] files = dir.listFiles();
            for (int i = 0; i < files.length; ++i) {
                File file = files[i];
                if (file.isDirectory()) {
                    traverse(file, arg, name);
                } else {
                    switch(arg){
                        case "add":
                            fileList.add(file);
                        case "choose":
                            if (file.getName().equals(name)){
                                selectedFile = file;
                            }
                    }
                }
            }
        }
    }

    @Override
    public void onItemClick(View view, int position) {
        traverse(new File(getFilesDir().getPath()), "choose", nameList.get(position) + ".xml");
        Restaurant selectedRestaurant = null;
        Serializer serializer = new Persister();
        File source = selectedFile;
        try {
            selectedRestaurant = serializer.read(Restaurant.class, source);
            Intent intent = new Intent(this, SushiTime.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("RESTAURANT", selectedRestaurant);
            intent.putExtras(bundle);
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
