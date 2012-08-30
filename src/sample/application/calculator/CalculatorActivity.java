package sample.application.calculator;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class CalculatorActivity extends Activity {
	
	private String strTemp;
	private String strResult;
	private Integer operator;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_calculator, menu);
        return true;
    }
    
    public void numKeyOnClick(View v) {
    	String strInKey = (String) ((Button)v).getText();
    	
    	if(strInKey.equals(".")){
    		if(this.strTemp.length() == 0){
    			this.strTemp = "0.";
    		}else{
    			if(strTemp.indexOf(".") == -1){
    				this.strTemp = strTemp + ".";
    			}
    		}
    	}else{
    		this.strTemp = this.strTemp + strInKey;
    	}
    	
    	this.showNumber(this.strTemp);
    }
    
    public void showNumber(String strNum){
    	DecimalFormat form = new DecimalFormat("#,##0");
    	String strDecimal = "";
    	String strInt = "";
    	String fText = "";
    	
    	if (strNum.length() > 0){
    		int decimalPoint = strNum.indexOf(".");
    		if (decimalPoint > -1){
    			strDecimal = strNum.substring(decimalPoint);
    			strInt = strNum.substring(0, decimalPoint);
    		}else{
    			strInt = strNum;
    		}
    		fText = form.format(Double.parseDouble(strInt))+strDecimal;
    	}else fText = "0";
    	
    	((TextView)this.findViewById(R.id.displayPanel)).setText(fText);
    }
    
    public void functionKeyOnClick(View v){
    	switch (v.getId()) {
		case R.id.keypadAC:
			break;

		default:
			break;
		}
    }
    
    public void operatorKeyOnClick(View v){
    	if(this.operator != 0){
    		if(this.strTemp.length() > 0){
    			this.showNumber(this.strResult);
    		}
    	}else{
    		if(this.strTemp.length() > 0){
    			this.strResult = this.strTemp;
    		}
    	}
    	
    	this.strTemp = "";
    	
    	if(v.getId() == R.id.keypadEq){
    		this.operator = 0;
    	}else{
    		this.operator = v.getId();
    	}
    }
    
    private String doCalc(){
    	BigDecimal bd1 = new BigDecimal(this.strResult);
    	BigDecimal bd2 = new BigDecimal(this.strTemp);
    	BigDecimal result = BigDecimal.ZERO;
    	switch (this.operator) {
		case R.id.keypadAdd:
			result = bd1.add(bd2);
			break;
		case R.id.keypadSub:
			result = bd1.subtract(bd2);
			break;
		case R.id.keypadMulti:
			result = bd1.multiply(bd2);
			break;
		case R.id.keypadDiv:
			if (!bd2.equals(BigDecimal.ZERO)) {
				result = bd1.divide(bd2, 12, 3);
			}else{
				Toast toast = Toast.makeText(this,  R.string.toast_div_by_zero,  1000);
				toast.show();
			}
			break;
		}
    	
    	if(result.toString().indexOf(".") >= 0){
    		return result.toString().replaceAll("¥¥.0+$|0+$", "");
    	}
    	return result.toString();
    }
    
//    public void numKeyOnClick(View v) {
//    	String strInKey = (String) ((Button)v).getText();
//    	TextView tv = (TextView)this.findViewById(R.id.displayPanel);
//    	Log.d("display:", tv.getText().toString());
//    	tv.setText(strInKey);
//    	
//		if (strInKey.equals(".")) {
//
//		} else {
//			strTemp = strTemp + strInKey;
//		}
//    	
//    }

    
}
