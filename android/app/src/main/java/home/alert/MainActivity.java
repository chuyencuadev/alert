package home.alert;

import android.media.AudioManager;
import android.media.ToneGenerator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import home.alert.lib.Setting;

public class MainActivity extends AppCompatActivity implements OnLoopjCompleted {
    MyLoopjTask myLoopjTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myLoopjTask = new MyLoopjTask(this, this);
    }

    @Override
    public void taskStart() {
        Toast.makeText(getApplicationContext(),
                "Đang gọi...",
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void taskFailed() {
        Toast.makeText(getApplicationContext(),
                "Không gọi được",
                Toast.LENGTH_SHORT).show();

        ToneGenerator toneG = new ToneGenerator(AudioManager.STREAM_ALARM, 100);
        toneG.startTone(ToneGenerator.TONE_CDMA_ALERT_CALL_GUARD, 200);
    }

    @Override
    public void taskSuccess(String results) {
        Toast.makeText(getApplicationContext(),
                "Đã nghe",
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void taskCompleted() {

    }

    public void command(final View view) {
        String apiUrl = String.format(Setting.COMMAND_API, view.getTag().toString());
        myLoopjTask.executeLoopjCall(apiUrl);
    }
}
