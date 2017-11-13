package com.android.listpopwindow;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.btn_string)
    Button btnString;
    @BindView(R.id.btn_string_image)
    Button btnStringImage;
    @BindView(R.id.btn_string_common)
    Button btnStringCommon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_string, R.id.btn_string_image,R.id.btn_string_common})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_string:
                startActivity(new Intent(this,StringActivity.class));
                break;
            case R.id.btn_string_image:
                startActivity(new Intent(this,StringImageActivity.class));
                break;
            case R.id.btn_string_common:
                startActivity(new Intent(this,CommonPopActivity.class));
                break;
        }
    }
}
