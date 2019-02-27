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
            Double workingValue = null;
            if (!Double.isNaN(savedInstanceState.getDouble("workingValue"))) {
                workingValue = savedInstanceState.getDouble("workingValue");
            }
            model = new Calculator(
                        savedInstanceState.getDouble("storedValue"),
                        savedInstanceState.getInt("storedOperation"),
                        workingValue);
        }

        update();
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        savedInstanceState.putDouble("storedValue", model.storedValue);
        savedInstanceState.putInt("storedOperation", model.storedOperation);
        if (model.workingValue != null) {
            savedInstanceState.putDouble("workingValue", model.workingValue);
        } else {
            savedInstanceState.putDouble("workingValue", Float.NaN);
        }
    }

    private void update() {
        textView.setText(model.display());
    }

    public void onPressDigit1(View v) {
        model.pushDigit(1);
        update();
    }

    public void onPressDigit2(View v) {
        model.pushDigit(2);
        update();
    }

    public void onPressDigit3(View v) {
        model.pushDigit(3);
        update();
    }

    public void onPressDigit4(View v) {
        model.pushDigit(4);
        update();
    }

    public void onPressDigit5(View v) {
        model.pushDigit(5);
        update();
    }

    public void onPressDigit6(View v) {
        model.pushDigit(6);
        update();
    }

    public void onPressDigit7(View v) {
        model.pushDigit(7);
        update();
    }

    public void onPressDigit8(View v) {
        model.pushDigit(8);
        update();
    }

    public void onPressDigit9(View v) {
        model.pushDigit(9);
        update();
    }

    public void onPressDigit0(View v) {
        model.pushDigit(0);
        update();
    }

    public void onPressOpAdd(View v) {
        model.pushOperation(Calculator.OP_ADD);
        update();
    }

    public void onPressOpSubtract(View v) {
        model.pushOperation(Calculator.OP_SUBTRACT);
        update();
    }

    public void onPressOpMultiply(View v) {
        model.pushOperation(Calculator.OP_MUTLIPLY);
        update();
    }

    public void onPressOpDivide(View v) {
        model.pushOperation(Calculator.OP_DIVIDE);
        update();
    }

    public void onPressOpMod(View v) {
        model.pushOperation(Calculator.OP_MODULO);
        update();
    }

    public void onPressOpExponent(View v) {
        model.pushOperation(Calculator.OP_EXPONENT);
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
