package com.android.listpopwindow;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.android.listpoplibrary.ListPopWindowManager;
import com.android.listpoplibrary.model.PopWindowType;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CommonPopActivity extends AppCompatActivity {

    @BindView(R.id.btn_string_common)
    Button btnStringCommon;
    private PopupWindow popupWindow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_pop);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_string_common)
    public void onViewClicked() {
        TextView tv=new TextView(this);
        tv.setText("测试");
        tv.setGravity(Gravity.CENTER);
        tv.setPadding(10,30,10,30);
        tv.setBackgroundColor(Color.WHITE);
        popupWindow= ListPopWindowManager.getInStance().showCommonPopWindow(tv,btnStringCommon,this,true);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
                Toast.makeText(CommonPopActivity.this, "测试", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (popupWindow.isShowing()){
            popupWindow.dismiss();
        }
    }
}
