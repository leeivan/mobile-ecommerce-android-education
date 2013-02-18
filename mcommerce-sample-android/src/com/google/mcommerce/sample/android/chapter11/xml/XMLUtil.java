package com.google.mcommerce.sample.android.chapter11.xml;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.util.Log;
import android.util.Xml;

import com.google.mcommerce.sample.android.chapter11.entity.User;

public class XMLUtil {
	private static final String TAG = "UserUtil";

	public static String getStringByUrl(String url) {
		StringBuilder builder = new StringBuilder();
		HttpClient client = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(url);
		try {
			HttpResponse response = client.execute(httpGet);
			StatusLine statusLine = response.getStatusLine();
			int statusCode = statusLine.getStatusCode();
			if (statusCode == 200) {
				HttpEntity entity = response.getEntity();
				InputStream content = entity.getContent();
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(content));
				String line;
				while ((line = reader.readLine()) != null) {
					builder.append(line);
				}
			} else {
				Log.e(TAG, "Failed to download file");
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return builder.toString();
	}

	public static User readSingleUserByDOM(String userXML) {
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
				user.setCity(userElement.getElementsByTagName("city").item(0)
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
		return user;
	}

	public static ArrayList<User> readMultiUserByDOM(String url) {
		ArrayList<User> users = new ArrayList<User>();
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(url);
			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName("user");
			for (int n = 0; n < nList.getLength(); n++) {
				Node nNode = nList.item(n);
				User user = new User();
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element userElement = (Element) nNode;
					user.setNick(userElement.getElementsByTagName("nick")
							.item(0).getFirstChild().getNodeValue());
					user.setCity(userElement.getElementsByTagName("city")
							.item(0).getFirstChild().getNodeValue());
					users.add(user);
				}
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
		return users;
	}

	public static ArrayList<User> readMultiUserBySAX(String url) {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		ArrayList<User> users = new ArrayList<User>();
		try {
			SAXParser saxParser = factory.newSAXParser();
			XMLReader xmlReader = saxParser.getXMLReader();
			XMLHandler handler = new XMLHandler();
			xmlReader.setContentHandler(handler);
			xmlReader.parse(new InputSource(new URL(url).openStream()));
			users = handler.getUsers();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return users;

	}

	public static ArrayList<User> readMultiUserByPULL(String url) {
		ArrayList<User> users = null;
		// XmlPullParser解析器
		XmlPullParser parser = Xml.newPullParser();
		try {
			parser.setInput(new StringReader(getStringByUrl(url)));
			int eventType = parser.getEventType();
			// 创建一个User对象
			User user = null;
			// 开始解析
			while (eventType != XmlPullParser.END_DOCUMENT) {
				switch (eventType) {
				case XmlPullParser.START_DOCUMENT:
					users = new ArrayList<User>();
					break;
				case XmlPullParser.START_TAG:
					// 判断当前元素名是否为user
					if ("user".equals(parser.getName())) {
						user = new User();
					} else if ("nick".equals(parser.getName())) {
						user.setNick(parser.nextText());
					} else if ("city".equals(parser.getName())) {
						user.setCity(parser.nextText());
					}
					break;
				case XmlPullParser.END_TAG:
					if ("user".equals(parser.getName()) && user != null) {
						users.add(user);
						user = null;
					}
					break;
				}
				eventType = parser.next();
			}
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return users;

	}
}
