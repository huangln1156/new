package com.keeplive.my.retomedesktop;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.keeplive.my.retomedesktop.wiget.VerifyCodeView;

public class AboutActivity extends AppCompatActivity implements VerifyCodeView.InputCompleteListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        VerifyCodeView  verifyCodeView = findViewById(R.id.verify_code_view);
        verifyCodeView.setInputCompleteListener(this);
    }
    @Override
    public void inputComplete() {

    }
}
