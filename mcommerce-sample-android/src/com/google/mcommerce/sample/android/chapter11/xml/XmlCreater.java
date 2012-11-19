package com.google.mcommerce.sample.android.chapter11.xml;

import java.io.File;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * 创建xml工具类 DOM方式
 */
public class XmlCreater {

	private Document doc = null;// 新创建的DOM
	private Element root = null;// 根节点
	private String encoding = "UTF-8";// 默认为UTF-8

	public XmlCreater() {
		init();
	}

	private void init() {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			doc = builder.newDocument();// 新建DOM
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 创建xml的根节点
	 * 
	 * @param rootTagName
	 * @return
	 */
	public Element createRootElement(String rootTagName) {
		if (doc.getDocumentElement() == null) {
			root = doc.createElement(rootTagName);
			doc.appendChild(root);
			return root;
		}
		return doc.getDocumentElement();
	}

	/**
	 * 创建一个名为tagName,值为value的节点
	 * 
	 * @param tagName
	 * @param value
	 * @return
	 */
	public Element createElement(String tagName, String value) {
		Document doc = root.getOwnerDocument();
		Element child = doc.createElement(tagName);
		child.setTextContent(value);
		return child;
	}

	/**
	 * 创建一个名为tagName的空节点。
	 * 
	 * @param tagName
	 * @return
	 */
	public Element createElement(String tagName) {
		Document doc = root.getOwnerDocument();
		Element child = doc.createElement(tagName);
		return child;
	}

	/**
	 * 传入文件地址路径，生成对应的xml文件
	 * 
	 * @param path
	 */
	public void buildXmlFile(String path) {
		TransformerFactory tfactory = TransformerFactory.newInstance();
		try {
			Transformer transformer = tfactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(path));
			transformer.setOutputProperty("encoding", this.encoding);
			transformer.transform(source, result);
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 返回xml对应的String字符串
	 * 
	 * @return
	 */
	public String xmlToString() {
		String xmlString = "";
		TransformerFactory tfactory = TransformerFactory.newInstance();
		try {
			Transformer transformer = tfactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StringWriter writer = new StringWriter();
			StreamResult result = new StreamResult(writer);
			transformer.setOutputProperty("encoding", this.encoding);
			transformer.transform(source, result);
			xmlString = writer.getBuffer().toString();
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}
		return xmlString;
	}

	/**
	 * @return 返回 doc。
	 */
	public Document getDoc() {
		return doc;
	}

	/**
	 * 设置当前xml的编码格式
	 * 
	 * @param encoding
	 */
	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

}
