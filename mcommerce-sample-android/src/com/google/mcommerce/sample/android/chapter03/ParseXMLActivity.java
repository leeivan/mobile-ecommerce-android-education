package com.google.mcommerce.sample.android.chapter03;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import com.google.mcommerce.sample.android.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class ParseXMLActivity extends Activity {

	private TextView textViewSingleCity;
	private TextView textViewSingleNick;
	private TextView textViewMultiCity;
	private TextView textViewMultiNick;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.c03_parse_json_xml_layout);
		textViewSingleCity = (TextView) findViewById(R.id.c03_textView1);
		textViewSingleNick = (TextView) findViewById(R.id.c03_textView4);
		textViewMultiCity = (TextView) findViewById(R.id.c03_textView6);
		textViewMultiNick = (TextView) findViewById(R.id.c03_textView8);
		String url = "http://gw.api.taobao.com/router/rest?sign=05BD06221FC4BE98C0EAF71811A2EE3C&timestamp=2012-08-26+17%3A33%3A23&v=2.0&app_key=12129701&method=taobao.user.get&partner_id=top-apitools&format=xml&nick=andyy_tan&fields=user_id,uid,nick,sex,buyer_credit,seller_credit,location,created,last_visit,birthday,type,status,alipay_no,alipay_account,alipay_account,email,consumer_protection,alipay_bind";
	}

	private User readSingleUser(String userXML) {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		User user = new User();
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(userXML);
			doc.getDocumentElement().normalize();
			Node userNode = doc.getElementsByTagName("user").item(0);
			if (userNode.getNodeType() == Node.ELEMENT_NODE) {
				Element userElement = (Element) userNode;
				user.setNick(userElement.getElementsByTagName("nick").item(0)
						.getFirstChild().getNodeValue());
			}
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
