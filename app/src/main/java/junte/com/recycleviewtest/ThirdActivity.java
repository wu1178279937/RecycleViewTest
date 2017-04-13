package junte.com.recycleviewtest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import utils.ActivityManager;

public class ThirdActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
    }

    public void OnClick(View view){
         MyApplication myApplication=MyApplication.getmContext();
           Intent intent1=new Intent();
           intent1.setClassName(MyApplication.getmContext().getPackageName(),"ThirdActivity");
        if (ActivityManager.checkActivityExist(MyApplication.getmContext(),intent1)){
            Toast.makeText(this,"Hello",Toast.LENGTH_LONG).show();
        }else{
            Intent intent=new Intent(this,ThirdActivity.class);
            startActivity(intent);
        }

    }
}
