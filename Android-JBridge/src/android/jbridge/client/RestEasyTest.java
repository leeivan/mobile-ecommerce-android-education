package android.jbridge.client;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xml.sax.SAXException;

import android.util.Log;
public class RestEasyTest
{
	public RestEasyTest()
	{
	}
	
	public static void test()
	{
        // Testing The data size
		
		String jsonTag = "JSON";
		String xmlTag = "XML";
		    	
		// Testing JSON data size
		InputStream jsonInputStream = RestEasyTest.doGet("http://10.0.2.2:8080/JBridge/RestEasy/products", "json");
    	long startTime = System.currentTimeMillis();
		String strJSON = RestEasy.convertStreamToString(jsonInputStream);
    	long endTime = System.currentTimeMillis();
    	Log.i(jsonTag, "JSON loaded. JSON data size: " + strJSON.length() + " bytes in (" + (endTime - startTime) + "miliseconds");
    	
    	// Testing XML data size
		InputStream xmlInputStream = RestEasyTest.doGet("http://10.0.2.2:8080/JBridge/RestEasy/products", "xml");
    	 startTime = System.currentTimeMillis();
		String strXML = RestEasy.convertStreamToString(xmlInputStream);
    	 endTime = System.currentTimeMillis();
    	Log.i(xmlTag, "XML loaded. XML data size: " + strXML.length() + " bytes  in (" + (endTime - startTime) + "miliseconds");

    	
    	
 
    	// Testing JSON parsing time
		 jsonInputStream = RestEasyTest.doGet("http://10.0.2.2:8080/JBridge/RestEasy/products", "json");
    	 startTime = System.currentTimeMillis();
    	RestEasyTest.parseJSON(jsonInputStream);
    	 endTime = System.currentTimeMillis();
    	Log.v(jsonTag ,"Parsing JSON finished in: " + (endTime - startTime) + " miliseconds");
    	
    	// Testing XML parsing time	
    	 xmlInputStream = RestEasyTest.doGet("http://10.0.2.2:8080/JBridge/RestEasy/products", "xml");
    	 startTime = System.currentTimeMillis();
    	RestEasyTest.parseXML(xmlInputStream);
    	 endTime = System.currentTimeMillis();
    	Log.v(xmlTag ,"Parsing XML finished in: " + (endTime - startTime) + " miliseconds");
	
	}
	
	// Retrieve a resource from the resteasy web service
    public static InputStream doGet(String url, String type)
    {
    	InputStream is = null;
    	
        HttpClient httpclient = new DefaultHttpClient();
 
        // Prepare a request object
        HttpGet httpget = new HttpGet(url); 
        
        // Accept JSON
        httpget.addHeader("accept", "application/" + type);
 
        // Execute the request
        HttpResponse response;
        try {
            response = httpclient.execute(httpget);
 
            // Get the response entity
            HttpEntity entity = response.getEntity();
            
            // If response entity is not null 
            if (entity != null) {
 
                // get entity contents
                is = entity.getContent();
                
            }
             
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        // Return the inputStream
        return is;
    }
    
    
    public static List<HashMap<String,String>> parseJSON(InputStream is){
    	List<HashMap<String,String>> products = new ArrayList<HashMap<String,String>>();
    	try
		{
			JSONObject json = new JSONObject(RestEasy.convertStreamToString(is));
			JSONArray prdArray = json.getJSONArray("products");
			for(int i=0; i < prdArray.length(); i ++)
			{
				HashMap<String, String> product = new HashMap<String,String>();
				
				String id = ((JSONObject) prdArray.get(i)).getString("id");
				String name = ((JSONObject) prdArray.get(i)).getString("name");
				String price = ((JSONObject) prdArray.get(i)).getString("price");
				String picture = ((JSONObject) prdArray.get(i)).getString("picture");
				String description = ((JSONObject) prdArray.get(i)).getString("description");
				
				product.put("id", id);
				product.put("name", name);
				product.put("price",price);
				product.put("picture", picture);
				product.put("description", description);
				
				products.add(product);
			}
		}
		catch (JSONException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return products;
    }
    
    public static List<HashMap<String,String>> parseXML(InputStream is){
    	List<HashMap<String,String>> products = new ArrayList<HashMap<String,String>>();
    	
    	//get a factory
		SAXParserFactory spf = SAXParserFactory.newInstance();
		try {
		
			XMLParser xmlParser = new XMLParser();
			//get a new instance of parser
			SAXParser sp = spf.newSAXParser();
			
			//parse the file and also register this class for call backs
			sp.parse(is, xmlParser);
			
		}catch(SAXException se) {
			se.printStackTrace();
		}catch(ParserConfigurationException pce) {
			pce.printStackTrace();
		}catch (IOException ie) {
			ie.printStackTrace();
		}
    	
    	return products;
    }

}
