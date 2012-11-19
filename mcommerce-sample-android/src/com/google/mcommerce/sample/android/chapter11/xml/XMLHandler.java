package com.google.mcommerce.sample.android.chapter11.xml;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.google.mcommerce.sample.android.chapter11.entity.User;

public class XMLHandler extends DefaultHandler {

	ArrayList<User> users;
	boolean isUser;
	User user;
	boolean isNick;
	boolean isLocation;
	boolean isCity;

	public XMLHandler() {
		users = new ArrayList<User>();
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		// 设置属性值
		if (isNick) {
			user.setNick(new String(ch, start, length));
		} else if (isCity) {
			user.setCity(new String(ch, start, length));
		}

	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		// TODO Auto-generated method stub
		String tagName = localName.length() != 0 ? localName : qName;
		tagName = tagName.toLowerCase().trim();

		// 如果读取的是user标签结束，则把User对象添加进集合中
		if (tagName.equals("user")) {
			isUser = false;
			users.add(user);
		}
		// 然后读取其他节点
		if (isUser) {
			if (tagName.equals("nick")) {
				isNick = false;
			}
		}
		if (tagName.equals("location")) {
			isLocation = false;
		}
		if (isLocation) {
			if (tagName.equals("city")) {
				isCity = false;
			}
		}
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		// TODO Auto-generated method stub
		String tagName = localName.length() != 0 ? localName : qName;
		tagName = tagName.toLowerCase().trim();
		// 如果读取的是user标签开始，则实例化User对象
		if (tagName.equals("user")) {
			isUser = true;
			user = new User();
		}
		if (tagName.equals("location")) {
			isLocation = true;
		}
		// 然后读取其他节点
		if (isUser) {
			if (tagName.equals("nick")) {
				isNick = true;
			}
		}
		if (isLocation) {
			if (tagName.equals("city")) {
				isCity = true;
			}
		}

	}

	public ArrayList<User> getUsers() {
		return users;
	}
}