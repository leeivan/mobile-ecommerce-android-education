package android.jbridge.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class XMLParser  extends DefaultHandler
{
	private List<HashMap<String,String>> products;
	private HashMap<String,String> tmpProduct;
	private String tempVal;
	
	public XMLParser()
	{
		products = new ArrayList<HashMap<String,String>>();
	}
	
	//Event Handlers
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		
		//reset
		if(qName.equalsIgnoreCase("product")) {
			//create a new instance of employee
			tmpProduct = new HashMap<String,String>();
			tmpProduct.put("id", attributes.getValue("id"));
		}		
	}
	
	public void characters(char[] ch, int start, int length) throws SAXException {
		tempVal = new String(ch,start,length);
	}
	
	public void endElement(String uri, String localName, String qName) throws SAXException {

		if(qName.equalsIgnoreCase("product")) {
			//add it to the list
			products.add(tmpProduct);	
		}else{
			tmpProduct.put(qName,tempVal);			
		}
		
	}
}