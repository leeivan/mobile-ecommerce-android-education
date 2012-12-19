package android.jbridge.client;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
/**
 * 
 * @author Majid Khosravi
 */
public class Shop extends Activity implements OnClickListener{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
                
        View addProductButton = findViewById(R.id.btnAddProduct);
        addProductButton.setOnClickListener(this);
        
        View productsButton = findViewById(R.id.products_button);
        productsButton.setOnClickListener(this);
        
        View aboutButton = findViewById(R.id.about_button);
        aboutButton.setOnClickListener(this);
        
        View settingButton = findViewById(R.id.setting_button);
        settingButton.setOnClickListener(this);
        
        View exitButton = findViewById(R.id.exit_button);
        exitButton.setOnClickListener(this);
        
        // Testing
        //RestEasyTest.test();
	
    }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.btnAddProduct:
			Intent addProductWindow = new Intent(this, AddProduct.class);
			startActivity(addProductWindow);
			break;
		case R.id.products_button:
			Intent productsWindow = new Intent(this, Products.class);
			startActivity(productsWindow);
			break;
		case R.id.about_button:
			Intent aboutWindow = new Intent(this, About.class);
			startActivity(aboutWindow);
			break;
		case R.id.setting_button:
			Intent settingWindow = new Intent(this, Settings.class);
			startActivity(settingWindow);
			break;
		case R.id.exit_button:
			finish();
			break;
		}
	}
}