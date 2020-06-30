package com.keeplive.my.retomedesktop.privacy;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.keeplive.my.retomedesktop.R;
import com.keeplive.my.retomedesktop.wiget.KeyboardNum;
import com.keeplive.my.retomedesktop.wiget.SelfDialog;

public class MainActivity extends AppCompatActivity {

    private String SP_PRIVACY = "sp_privacy";
    private String SP_VERSION_CODE = "sp_version_code";
    private boolean isCheckPrivacy = false;
    private long versionCode;
    private long currentVersionCode;
    private KeyboardNum mMKeyboardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mains);

        Button btn_reset = findViewById(R.id.btn_reset);
        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SPUtil.put(MainActivity.this, SP_VERSION_CODE, 0L);
                SPUtil.put(MainActivity.this, SP_PRIVACY, false);
                finish();
            }
        });

        check();
    }

    /**
     * 显示隐私政策或跳转到其他界面
     */
    private void check() {

        //先判断是否显示了隐私政策
        versionCode = (long) SPUtil.get(MainActivity.this, SP_VERSION_CODE, 0L);
        isCheckPrivacy = (boolean) SPUtil.get(MainActivity.this, SP_PRIVACY, false);

        if (!isCheckPrivacy || versionCode != currentVersionCode) {
            showPrivacy();
        } else {
            Toast.makeText(MainActivity.this, getString(R.string.confirmed), Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 显示用户协议和隐私政策
     */
    private void showPrivacy() {

        final PrivacyDialog dialog = new PrivacyDialog(MainActivity.this);
        TextView tv_privacy_tips = dialog.findViewById(R.id.tv_privacy_tips);
        TextView btn_exit = dialog.findViewById(R.id.btn_exit);
        TextView btn_enter = dialog.findViewById(R.id.btn_enter);
        dialog.show();

        String string = getResources().getString(R.string.privacy_tips);
        String key1 = getResources().getString(R.string.privacy_tips_key1);
        String key2 = getResources().getString(R.string.privacy_tips_key2);
        int index1 = string.indexOf(key1);
        int index2 = string.indexOf(key2);

        //需要显示的字串
        SpannableString spannedString = new SpannableString(string);
        //设置点击字体颜色
        ForegroundColorSpan colorSpan1 = new ForegroundColorSpan(getResources().getColor(R.color.colorBlue));
        spannedString.setSpan(colorSpan1, index1, index1 + key1.length()+key2.length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
      //
        //
        // ForegroundColorSpan colorSpan2 = new ForegroundColorSpan(getResources().getColor(R.color.colorBlue));
       // spannedString.setSpan(colorSpan2, index2, index2 + key2.length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        //设置点击字体大小
        AbsoluteSizeSpan sizeSpan1 = new AbsoluteSizeSpan(18, true);
        spannedString.setSpan(sizeSpan1, index1, index1 + key1.length()+key2.length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
      //  AbsoluteSizeSpan sizeSpan2 = new AbsoluteSizeSpan(18, true);
       // spannedString.setSpan(sizeSpan2, index2, index2 + key2.length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        //设置点击事件
        ClickableSpan clickableSpan1 = new ClickableSpan() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TermsActivity.class);
                startActivity(intent);
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                //点击事件去掉下划线

                ds.setUnderlineText(false);
            }
        };
        spannedString.setSpan(clickableSpan1, index1, index1 + key1.length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);

        ClickableSpan clickableSpan2 = new ClickableSpan() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PrivacyPolicyActivity.class);
                startActivity(intent);
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                //点击事件去掉下划线
                ds.setUnderlineText(false);
            }
        };
        spannedString.setSpan(clickableSpan2, index2, index2 + key2.length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
      //  int SPAN_EXCLUSIVE_EXCLUSIVE = 33; //在Span前后输入的字符都不应用Span效果
       // int SPAN_EXCLUSIVE_INCLUSIVE = 34; //在Span前面输入的字符不应用Span效果，后面输入的字符应用Span效果
       // int SPAN_INCLUSIVE_EXCLUSIVE = 17; //在Span前面输入的字符应用Span效果，后面输入的字符不应用Span效果
       // int SPAN_INCLUSIVE_INCLUSIVE = 18; //在Span前后输入的字符都应用Span效果


        //设置点击后的颜色为透明，否则会一直出现高亮
        tv_privacy_tips.setHighlightColor(Color.TRANSPARENT);
        //开始响应点击事件
        tv_privacy_tips.setMovementMethod(LinkMovementMethod.getInstance());

        tv_privacy_tips.setText(spannedString);

        //设置弹框宽度占屏幕的80%
        WindowManager m = getWindowManager();
        Display defaultDisplay = m.getDefaultDisplay();
        final WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
        params.width = (int) (defaultDisplay.getWidth() * 0.80);
        dialog.getWindow().setAttributes(params);

        btn_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                SPUtil.put(MainActivity.this, SP_VERSION_CODE, currentVersionCode);
                SPUtil.put(MainActivity.this, SP_PRIVACY, false);
                finish();
            }
        });

        btn_enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                SPUtil.put(MainActivity.this, SP_VERSION_CODE, currentVersionCode);
                SPUtil.put(MainActivity.this, SP_PRIVACY, true);

                Toast.makeText(MainActivity.this, getString(R.string.confirmed), Toast.LENGTH_SHORT).show();
            }
        });

    }
    public void showDialog() {
        View contentView = getLayoutInflater().inflate(R.layout.public_dialog1, null);
         mMKeyboardView = contentView.findViewById(R.id.keyboard_view);
        SelfDialog   toDialog = new SelfDialog(this, R.style.MessageDialog);
        toDialog.setContentView(contentView);
        new KeyboardNum(this, contentView, et_qqcp_edittext_hint);
        toDialog.setOnclickOutsideListener(new SelfDialog.OnClickOutsideListener() {
            @Override
            public void onOutsideClick() {
                if (mMKeyboardView != null && mMKeyboardView.getVisibility() == View.VISIBLE) {
                    mMKeyboardView.setVisibility(View.GONE);
                }
            }
        });
        toDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
            }
        });
        Window window = toDialog.getWindow();
        if (window != null) {
            window.getDecorView().setPadding(0, 0, 0, 0);
            WindowManager.LayoutParams layoutParams = window.getAttributes();
            layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
            layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
            window.setAttributes(layoutParams);
            // 设置Dialog布局位于屏幕底部
            window.setGravity(Gravity.BOTTOM);
            layoutParams.y = 16;
            window.setAttributes(layoutParams);
        }
        toDialog.show();

    }
//                <EditText
//    android:id="@+id/et_qqcp_edittext"
//    android:layout_width="match_parent"
//    android:layout_height="@dimen/dp45"
//    android:background="@drawable/qqcp_windowd_maincolor_shape"
//    android:digits="1234567890"
//    android:hint="@string/qqcp_edittext_hint"
//    android:inputType="number"
//    android:lines="1"
//    android:maxLength="10"
//    android:paddingStart="@dimen/dp10"
//    android:textColor="@color/hint"
//    android:textColorHint="@color/login_exittext_notfocus_textcolor"
//    android:textCursorDrawable="@drawable/color_cursor"
//    android:textSize="@dimen/dp15"
//    android:textStyle="normal"
//    android:typeface="normal" />normal

}
