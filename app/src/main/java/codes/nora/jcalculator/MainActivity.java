package codes.nora.jcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Calculator model;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.textView = findViewById(R.id.textView);

        if (savedInstanceState == null) {
            model = new Calculator();
        } else {
            model = new Calculator(savedInstanceState.getString("state"));
        }

        update();
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        savedInstanceState.putString("state", model.state);
    }

    private void update() {
        textView.setText(model.display());
    }

    public void onPressDigit1(View v) {
        model.push("1");
        update();
    }

    public void onPressDigit2(View v) {
        model.push("2");
        update();
    }

    public void onPressDigit3(View v) {
        model.push("3");
        update();
    }

    public void onPressDigit4(View v) {
        model.push("4");
        update();
    }

    public void onPressDigit5(View v) {
        model.push("5");
        update();
    }

    public void onPressDigit6(View v) {
        model.push("6");
        update();
    }

    public void onPressDigit7(View v) {
        model.push("7");
        update();
    }

    public void onPressDigit8(View v) {
        model.push("8");
        update();
    }

    public void onPressDigit9(View v) {
        model.push("9");
        update();
    }

    public void onPressDigit0(View v) {
        model.push("0");
        update();
    }

    public void onPressOpAdd(View v) {
        model.push("+");
        update();
    }

    public void onPressOpSubtract(View v) {
        model.push("-");
        update();
    }

    public void onPressOpMultiply(View v) {
        model.push("x");
        update();
    }

    public void onPressOpDivide(View v) {
        model.push("/");
        update();
    }

    public void onPressEquals(View v) {
        model.evaluate();
        update();
    }

    public void onPressClear(View v) {
        model.clear();
        update();
    }
}
