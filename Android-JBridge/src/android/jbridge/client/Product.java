package android.jbridge.client;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources.NotFoundException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
/**
 * 
 * @author Majid Khosravi
 */
public class Product extends Activity implements OnClickListener{
	
	private static String TAG = "Products";
	private int id = 0;
	private String name = "";
	private double price = 0;
	private String picture = "";
	private int quantity = 0;
	private String description = "";

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.product);
		
		View delProduct = (Button) findViewById(R.id.btnDel);
		delProduct.setOnClickListener(this);
		
		View buyProduct = (Button) findViewById(R.id.btnBuy);
		buyProduct.setOnClickListener(this);

		
		Bundle extras = getIntent().getExtras();
		if(extras !=null)
		{
			id = extras.getInt("id");
		}

		JSONObject json =RestEasy.doGet(Settings.getServerAddress(getBaseContext()) +"/RestEasy/product/" + id);
		
		try
		{
			//JSONObject product = json.getJSONObject("product");
			if( json != null )
			{
				 name = json.getString("name");
				 price =  json.getDouble("price");
				 picture = json.getString("picture");
				 quantity = json.getInt("quantity"); 
				 description = json.getString("description");
 
				ImageView img = (ImageView) findViewById(R.id.imgProduct);
				img.setImageBitmap(this.getImageBitmap(Settings.getServerAddress(getBaseContext()) +"/products/" + picture));
		                  
		        TextView txtName = (TextView) findViewById(R.id.txtName);
            	txtName.setText(name + " (" + quantity + ")");

            	TextView txtPrice = (TextView) findViewById(R.id.txtPrice);
            	txtPrice.setText("£" + price);
            	
            	TextView txtDescription = (TextView) findViewById(R.id.txtDescription);
            	txtDescription.setText(description.replace("<br />", "\n"));

			}
		}
		catch (JSONException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		Button btnBuy  = (Button) findViewById(R.id.btnBuy);
		btnBuy.setOnClickListener(this);
	}
	
	private Bitmap getImageBitmap(String url) {
        Bitmap bm = null;
        try {
            URL aURL = new URL(url);
            URLConnection conn = aURL.openConnection();
            conn.connect();
            InputStream is = conn.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is);
            bm = BitmapFactory.decodeStream(bis);
            
            // resize the bitmap
            Matrix matrix = new Matrix();
            matrix.postScale(30, 30);
            
            bis.close();
            is.close();
       } catch (IOException e) {
           Log.e(TAG, "Error getting bitmap", e);
       }
       return bm;
    }

	@Override
	public void onClick(View v)
	{
		// TODO Auto-generated method stub
		switch(v.getId())
		{
		
		case R.id.btnDel:
			delProduct(id);
			break;
			
		case R.id.btnBuy:
			
			AlertDialog.Builder qtyDialog = new AlertDialog.Builder(this);
			qtyDialog.setTitle(getResources().getString(R.string.product_quantity));
			qtyDialog.setMessage(getResources().getString(R.string.quantity_dialog_text));

			// Set an EditText view to get user input 
			final EditText input = new EditText(this);
			input.setInputType(InputType.TYPE_CLASS_NUMBER);
			qtyDialog.setView(input);

			qtyDialog.setPositiveButton(getResources().getString(R.string.buy), new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
			  String value = input.getText().toString();
			  	int q = Integer.parseInt(value);
			  	buy(q);
			  }
			});

			qtyDialog.show();
			
			break;
		}
	} 
	
	// Delete a product by id
	private void delProduct(int id)
	{
		try
		{
			RestEasy.doDelete(Settings.getServerAddress(getBaseContext()) + "/RestEasy/product/" + id + "/delete");
			
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setMessage("Product removed successfully.")
			       .setCancelable(false)
			       .setNegativeButton("OK", new DialogInterface.OnClickListener() {
			           public void onClick(DialogInterface dialog, int id) {
			                Product.this.finish();
			                Intent productsWindow = new Intent(Product.this, Products.class);
			                startActivity(productsWindow);
			           }
			       });

			AlertDialog alert = builder.create();
			alert.show();
		}
		catch (ClientProtocolException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// Buy the product by given quantity
	private void buy(int newQty)
	{
		JSONObject json = new JSONObject();

		try
		{
			json.put("name", name);
			json.put("price", price);
			json.put("picture", picture);
			json.put("quantity", newQty);
			json.put("description", description);
			
			RestEasy.doPut(Settings.getServerAddress(getBaseContext()) + "/RestEasy/product/" + id + "/buy", json);
			
			
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setMessage("Product purchased successfully.")
			       .setCancelable(false)
			       .setNegativeButton("OK", new DialogInterface.OnClickListener() {
			           public void onClick(DialogInterface dialog, int id) {
			                Product.this.finish();
			                Intent productsWindow = new Intent(Product.this, Products.class);
			                startActivity(productsWindow);
			           }
			       });

			AlertDialog alert = builder.create();
			alert.show();
			
		}
		catch (JSONException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (ClientProtocolException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (NotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}