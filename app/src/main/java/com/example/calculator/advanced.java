package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.style.RelativeSizeSpan;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class advanced extends AppCompatActivity {

    Button zero,one,two,three,four,five,six,seven,eight,nine,plus, minus,multiply,divide,equals,back,C,CE;
    Button negate,point,square,root,reverse,percent,sin,cos,tan,ctan,log,ln;
    TextView textfield,mem;
    Boolean reset = false;
    Toast m;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced);
        zero = (Button) findViewById(R.id.zero);
        one = (Button) findViewById(R.id.one);
        two = (Button) findViewById(R.id.two);
        three = (Button) findViewById(R.id.three);
        four = (Button) findViewById(R.id.four);
        five = (Button) findViewById(R.id.five);
        six = (Button) findViewById(R.id.six);
        seven = (Button) findViewById(R.id.seven);
        eight = (Button) findViewById(R.id.eight);
        nine = (Button) findViewById(R.id.nine);
        C = (Button) findViewById(R.id.C);
        minus = (Button) findViewById(R.id.minus);
        plus = (Button) findViewById(R.id.plus);
        CE = (Button) findViewById(R.id.CE);
        multiply = (Button) findViewById(R.id.multiply);
        divide = (Button) findViewById(R.id.divide);
        back = (Button) findViewById(R.id.backspace);
        equals = (Button) findViewById(R.id.equal);
        negate = (Button) findViewById(R.id.opposite);
        point = (Button) findViewById(R.id.dot);
        root = (Button) findViewById(R.id.sqrt);
        square = (Button) findViewById(R.id.xsquared);
        reverse = (Button) findViewById(R.id.oneperx);
        percent = (Button) findViewById(R.id.percent);
        sin = (Button) findViewById(R.id.sin);
        cos = (Button) findViewById(R.id.cos);
        tan = (Button) findViewById(R.id.tan);
        ctan = (Button) findViewById(R.id.ctan);
        log = (Button) findViewById(R.id.log);
        ln =  (Button) findViewById(R.id.ln);
        textfield = (TextView)findViewById(R.id.mainfield);
        mem = (TextView)findViewById(R.id.memoryop);
        m = Toast.makeText(getApplicationContext(),"", Toast.LENGTH_LONG);

        zero.setOnClickListener(numerical);
        one.setOnClickListener(numerical);
        two.setOnClickListener(numerical);
        three.setOnClickListener(numerical);
        four.setOnClickListener(numerical);
        five.setOnClickListener(numerical);
        six.setOnClickListener(numerical);
        seven.setOnClickListener(numerical);
        eight.setOnClickListener(numerical);
        nine.setOnClickListener(numerical);
        C.setOnClickListener(numerical);
        CE.setOnClickListener(numerical);
        back.setOnClickListener(numerical);
        negate.setOnClickListener(numerical);
        point.setOnClickListener(numerical);

        sin.setOnClickListener(advanced);
        cos.setOnClickListener(advanced);
        tan.setOnClickListener(advanced);
        ctan.setOnClickListener(advanced);
        ln.setOnClickListener(advanced);
        log.setOnClickListener(advanced);
        percent.setOnClickListener(advanced);
        square.setOnClickListener(advanced);
        root.setOnClickListener(advanced);
        reverse.setOnClickListener(advanced);

        minus.setOnClickListener(operation);
        plus.setOnClickListener(operation);
        multiply.setOnClickListener(operation);
        divide.setOnClickListener(operation);
        equals.setOnClickListener(operation);



    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString("mem", mem.getText().toString());
        savedInstanceState.putString("text", textfield.getText().toString());
        savedInstanceState.putBoolean("reset",reset);
    }
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mem.setText(savedInstanceState.getString("mem"));
        textfield.setText(savedInstanceState.getString("text"));
        reset=savedInstanceState.getBoolean("reset");
    }
    private OnClickListener advanced = new OnClickListener() {
        @SuppressLint("SetTextI18n")
        @Override
        public void onClick(View v) {
            if(infinityCheck()){
                textfield.setText("0");
            }
            switch (v.getId()){
                case R.id.percent:
                    try {
                        double tem = Double.parseDouble(mem.getText().toString().substring(0, mem.getText().toString().length() - 1));
                        tem = tem * (Double.parseDouble(textfield.getText().toString()) / 100);
                        textfield.setText(Double.toString(tem));
                        reset = true;
                    }catch (Exception e){
                        toastMessage("Invalid argument");
                    }
                    break;
                case R.id.oneperx:
                    if(textfield.getText().toString().equals("0")){
                        //komunikat o błedzie
                        toastMessage("Invalid operation: Dividing by zero");
                    }else {
                        mem.setText("");
                        textfield.setText(Double.toString(
                                1 / Double.parseDouble(textfield.getText().toString())
                        ));
                        reset=true;
                    }
                    break;
                case R.id.sqrt:
                    if(Double.parseDouble(textfield.getText().toString())<0){
                        //komunikat o błędzie
                        toastMessage("Invalid operation: root of negative number");
                    }else{
                        mem.setText("sqrt("+textfield.getText().toString()+")=");
                        textfield.setText(Double.toString(
                                round(Math.sqrt(Double.parseDouble(textfield.getText().toString())))));
                        reset=true;
                    }
                    break;
                case R.id.xsquared:
                    mem.setText(textfield.getText().toString()+"^2=");
                    textfield.setText(Double.toString(
                            round(Math.pow(
                                    Double.parseDouble(textfield.getText().toString())
                                    ,2
                            ))));
                    reset=true;
                    break;
                case R.id.sin:
                    mem.setText("sin("+textfield.getText().toString()+")=");
                    textfield.setText(Double.toString(
                            round(Math.sin(Double.parseDouble(textfield.getText().toString())))
                    ));
                    reset=true;
                    break;
                case R.id.cos:
                    mem.setText("cos("+textfield.getText().toString()+")=");
                    textfield.setText(Double.toString(
                            round(Math.cos(Double.parseDouble(textfield.getText().toString())))
                    ));
                    reset=true;
                    break;
                case R.id.tan:
                    double temp = Double.parseDouble(textfield.getText().toString());
                    if( temp!=0 && temp % 90 == 0){
                        toastMessage("Invalid operation: wrong argument");
                    }else{
                        mem.setText("tg("+textfield.getText().toString()+")=");
                        textfield.setText(Double.toString(
                                round(Math.tan(Double.parseDouble(textfield.getText().toString())))
                        ));
                        reset=true;
                    }
                    break;
                case R.id.ctan:
                    double tmp = Double.parseDouble(textfield.getText().toString());
                    if( tmp==0 || tmp % 180 == 0){
                        toastMessage("Invalid operation: wrong argument");
                    }else{
                        mem.setText("ctg("+textfield.getText().toString()+")=");
                        textfield.setText(Double.toString(
                                round(1.0/Math.tan(Double.parseDouble(textfield.getText().toString())))
                        ));
                        reset=true;
                    }
                    break;
                case R.id.log:
                    if( Double.parseDouble(textfield.getText().toString()) <= 0){
                        toastMessage("Invalid operation: wrong argument");
                    }
                    else{
                        mem.setText("log("+textfield.getText().toString()+")=");
                        textfield.setText(Double.toString(
                                round(Math.log10(Double.parseDouble(textfield.getText().toString())))
                        ));
                        reset=true;
                    }
                    break;
                case R.id.ln:
                    if( Double.parseDouble(textfield.getText().toString()) <= 0){
                        toastMessage("Invalid operation: wrong argument");
                    }
                    else{
                        mem.setText("ln("+textfield.getText().toString()+")=");
                        textfield.setText(Double.toString(
                                round(Math.log(Double.parseDouble(textfield.getText().toString())))
                        ));
                        reset=true;
                    }
                    break;
            }
        }
    };
    private OnClickListener numerical = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if(infinityCheck()){
                textfield.setText("0");
            }
            switch (v.getId()){
                case R.id.backspace:
                    if(textfield.getText().toString().equals("0"));
                    else{
                        textfield.setText(textfield.getText().toString().
                                substring(0, textfield.getText().toString().length() - 1));
                    }
                    if(textfield.getText().toString().isEmpty())
                        textfield.setText("0");
                    reset=false;
                    break;
                case R.id.opposite:
                    if(textfield.getText().toString().equals("0"));
                    else{
                        if(!mem.getText().toString().isEmpty()) {
                            if (mem.getText().toString().charAt(mem.getText().toString().length() - 1) == '=') {
                                mem.setText("");
                            }
                        }
                        if(textfield.getText().toString().contains(".")) {
                            textfield.setText(
                                    Double.toString(
                                            Double.parseDouble(
                                                    textfield.getText().toString()) * (-1))
                            );
                        }else{
                            textfield.setText(
                                    Integer.toString(
                                            Integer.parseInt(
                                                    textfield.getText().toString())*(-1))
                            );
                        }
                    }
                    reset=false;
                    break;
                case R.id.dot:
                    if(textfield.getText().toString().contains("."));
                    else{
                        textfield.setText(textfield.getText().toString()+".");
                    }
                    reset=false;
                    break;
                case R.id.CE:
                    textfield.setText("0");
                    reset=false;
                    break;
            }
            if (reset){
                textfield.setText("0");
                mem.setText("");
                reset=false;
            }
            switch (v.getId()) {
                case R.id.zero:
                    setText('0');
                    break;
                case R.id.one:
                    setText('1');
                    break;
                case R.id.two:
                    setText('2');
                    break;
                case R.id.three:
                    setText('3');
                    break;
                case R.id.four:
                    setText('4');
                    break;
                case R.id.five:
                    setText('5');
                    break;
                case R.id.six:
                    setText('6');
                    break;
                case R.id.seven:
                    setText('7');
                    break;
                case R.id.eight:
                    setText('8');
                    break;
                case R.id.nine:
                    setText('9');
                    break;
                case R.id.C:
                    textfield.setText("0");
                    mem.setText("");
                    break;
            }
        }
    };
    private void setText(char a){
        if(textfield.getText().toString().equals("0")){
            textfield.setText(Character.toString(a));
        }
        else{
            textfield.setText(textfield.getText().toString()+Character.toString(a));
        }
    }
    private OnClickListener operation = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if(infinityCheck()){
                textfield.setText("0");
            }
            //obliczenia
            reset=false;
            String memory = mem.getText().toString();
            String result="Error";
            double first=0,second=0;
            if(!memory.isEmpty()){
                if (memory.charAt(memory.length()-1) == '=') {
                    switch (v.getId()) {
                        case R.id.plus:
                            mem.setText(textfield.getText().toString() + "+");
                            textfield.setText("0");
                            break;
                        case R.id.minus:
                            mem.setText(textfield.getText().toString() + "-");
                            textfield.setText("0");
                            break;
                        case R.id.multiply:
                            mem.setText(textfield.getText().toString() + "*");
                            textfield.setText("0");
                            break;
                        case R.id.divide:
                            mem.setText(textfield.getText().toString() + "/");
                            textfield.setText("0");
                            break;
                        case R.id.equal:
                            mem.setText(textfield.getText().toString() + "=");
                            reset=true;
                            break;
                    }
                    return;
                }
                char oper = memory.charAt(memory.length()-1);
                first = Double.parseDouble(memory.substring(0,memory.length()-1));
                second = Double.parseDouble(textfield.getText().toString());
                switch (oper){
                    case '+':
                        if(mem.getText().toString().contains(".")||textfield.getText().toString().contains(".")){
                            result =Double.toString(first+second);
                        }else{
                            result = Integer.toString((int)first+(int)second);
                        }
                        break;
                    case '-':
                        if(mem.getText().toString().contains(".")||textfield.getText().toString().contains(".")){
                            result = Double.toString(first-second);
                        }else{
                            result = Integer.toString((int)first-(int)second);
                        }
                        break;
                    case '*':
                        if(mem.getText().toString().contains(".")||textfield.getText().toString().contains(".")){
                            result = Double.toString(first*second);
                        }else{
                            result = Integer.toString((int)first*(int)second);
                        }
                        break;
                    case '/':
                        if(second==0){
                            //obsłużyć błąd
                            toastMessage("Invalid operation: Dividing by zero");
                            return;
                        }
                        result = Double.toString(first/second);
                        break;
                }
            }
            //koniec obliczeń
            switch (v.getId()){
                case R.id.plus:
                    if(mem.getText().toString().isEmpty()){
                        mem.setText(textfield.getText().toString()+"+");
                    }else{
                        mem.setText(result + "+");
                    }
                    textfield.setText("0");
                    break;
                case R.id.minus:
                    if(mem.getText().toString().isEmpty()){
                        mem.setText(textfield.getText().toString()+"-");
                    }else{
                        mem.setText(result + "-");
                    }
                    textfield.setText("0");
                    break;
                case R.id.multiply:
                    if(mem.getText().toString().isEmpty()){
                        mem.setText(textfield.getText().toString()+"*");
                    }else{
                        mem.setText(result + "*");
                    }
                    textfield.setText("0");
                    break;
                case R.id.divide:
                    if(mem.getText().toString().isEmpty()){
                        mem.setText(textfield.getText().toString()+"/");
                    }else{
                        mem.setText(result + "/");
                    }
                    textfield.setText("0");
                    break;
                case R.id.equal:
                    if(mem.getText().toString().isEmpty()){
                        mem.setText(textfield.getText().toString()+"=");
                    }else {
                        if (mem.getText().toString().contains(".") || textfield.getText().toString().contains(".")) {
                            mem.setText(mem.getText().toString()+Double.toString(second)+"=");
                        } else {
                            mem.setText(mem.getText().toString()+Integer.toString((int)second)+"=");
                        }
                        textfield.setText(result);
                        reset=true;
                        break;
                    }

            }
        }
    };
    private boolean infinityCheck(){
        if(!textfield.getText().toString().isEmpty()){
            if(textfield.getText().toString().charAt(textfield.getText().toString().length()-1)=='y') return true;
        }
        return false;
    }
    private double round(double number){
        return (double)Math.round(number*10000)/10000.0;
    }
    private void toastMessage(String text) {
        m.cancel();
        SpannableStringBuilder biggerText = new SpannableStringBuilder(text);
        biggerText.setSpan(new RelativeSizeSpan(1.35f), 0, text.length(), 0);
        m = Toast.makeText(getApplicationContext(), biggerText, Toast.LENGTH_LONG);
        m.show();
    }

}