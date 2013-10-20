package com.example.gbox;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;





public class ProductFinderActivity extends Activity {
    ListView listView;
    ProgressDialog pd; 
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_list_view);
        
        
        
        class DownloadFilesTask extends AsyncTask<Void, Void, Void> {
            protected Void doInBackground(Void...voids ) {
            	Model.LoadModel();
				return null;
            }
            @Override
            protected void onProgressUpdate(Void...voids) {
                //setProgressPercent(progress[0]);
            }

            protected void onPostExecute(Void v) {
            	listView = (ListView) findViewById(R.id.product_listview);
                // find the number of items in the search result size from amazon
                String[] ids = new String[Model.Items.size()]; // change this
                
                for (int i= 0; i < ids.length; i++){
                    ids[i] = Integer.toString(i+1);
                }

                Log.i("ItemAdapter", "");
                ItemAdapter adapter = new ItemAdapter(getApplicationContext(), R.layout.list_row_item, ids);
                listView.setAdapter(adapter);
                
            }

        }
        new DownloadFilesTask().execute();
  
        

    }
}