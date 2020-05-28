/*
    Get list of files -
    loop through files -
    deserialize xml file -
    get restaurant name -
    store names in array -
    display names on recyclerview ?
    on touch:
    loop through files
    if file name matches touched name, delete file
    DONE YAY
 */

package d.diablo.calcmysushi;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class Delete extends AppCompatActivity implements MyRecyclerViewAdapter.ItemClickListener {

    MyRecyclerViewAdapter adapter;
    List<File> fileList = new ArrayList<>();
    List<String> nameList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        this.getSupportActionBar().setTitle("Delete a restaurant");

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
                        case "delete":
                            if (file.getName().equals(name)){
                                file.delete();
                            }
                    }
                    //use file.delete(); here to clear dir
                }
            }
        }
    }

    @Override
    public void onItemClick(View view, int position) {
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setMessage("Clicking yes will delete this restaurant!")
                .setTitle("Are you sure?")
                .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        traverse(new File(getFilesDir().getPath()), "delete",
                                nameList.get(position) + ".xml");
                        Toast.makeText(Delete.this, "Deleted " + nameList.get(position),
                                Toast.LENGTH_SHORT).show();
                        nameList.remove(position);
                        adapter.notifyItemRemoved(position);

                    }
                })
                .setNegativeButton("no", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //do nothing
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
