package junte.com.recycleviewtest;

import android.app.LoaderManager;
import android.content.Intent;

import android.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import utils.FileLoader;

public class FlexboxActivity extends AppCompatActivity  implements LoaderManager.LoaderCallbacks<String>{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flexbox);
        getLoaderManager().initLoader(0,null,this);
    }

    public void OnClick(View view){
        Intent intent=new Intent(this,ThirdActivity.class);
        startActivity(intent);
    }


    @Override
    public Loader<String> onCreateLoader(int id, Bundle args) {
        return new FileLoader(this);
    }

    @Override
    public void onLoadFinished(android.content.Loader<String> loader, String data) {

    }

    @Override
    public void onLoaderReset(android.content.Loader<String> loader) {

    }
}
