package sample.application.calculator;

import java.text.DecimalFormat;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CalculatorActivity extends Activity {
	
	private String strTemp;

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
