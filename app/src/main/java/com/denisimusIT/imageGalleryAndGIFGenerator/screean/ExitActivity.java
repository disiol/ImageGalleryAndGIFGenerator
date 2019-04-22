package com.denisimusIT.imageGalleryAndGIFGenerator.screean;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.denisimusIT.imageGalleryAndGIFGenerator.R;
import static com.denisimusIT.imageGalleryAndGIFGenerator.util.Constants.LOG_TAG;

public class ExitActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView exitAndSaveSessionTextView, exitAndLogOutTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exit);

        exitAndSaveSessionTextView = findViewById(R.id.exit_and_save_session_textView);
        exitAndLogOutTextView = findViewById(R.id.exit_and_log_out_textView);

        exitAndSaveSessionTextView.setOnClickListener(this);
        exitAndLogOutTextView.setOnClickListener(this);


    }

//TODO


    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.exit_and_save_session_textView:
                exitAndSaveSession();

                break;
            case R.id.exit_and_log_out_textView:
                Log.d(LOG_TAG, "imageViewAvatar click");
                exitAndLogOut();
                break;

        }

    }


    private void exitAndSaveSession() {
        moveTaskToBack(true);
        super.onBackPressed();


    }

    private void exitAndLogOut() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
           this.finishAffinity();
        }else{
            this.finish();
        }
    }

}
