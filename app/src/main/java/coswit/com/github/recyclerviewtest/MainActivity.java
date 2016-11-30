package coswit.com.github.recyclerviewtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btn_recylerview)
    Button btnRecylerview;
    @BindView(R.id.btn_handler)
    Button btnHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }


    @OnClick({R.id.btn_recylerview, R.id.btn_handler})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_recylerview:
                startActivity(new Intent(getBaseContext(), RecylerViewAct.class));
                break;
            case R.id.btn_handler:
                break;
        }
    }
}
