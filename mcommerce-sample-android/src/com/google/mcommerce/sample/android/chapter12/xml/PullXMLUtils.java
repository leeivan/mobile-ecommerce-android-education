package com.google.mcommerce.sample.android.chapter12.xml;

/**
 * <?xml version='1.0' encoding='utf-8' standalone='yes' ?>  
 *<state>  
 *<view name="button">  
 *<text>send</text>  
 *<id>10</id>  
 *</view>  
 *
 *<view name="textview">  
 *<text>this is a demo!</text>  
 *<id>11</id>  
 *</view>  
 *</state>  
 */
import java.io.OutputStream;
import java.io.Writer;
import java.util.List;
import org.xmlpull.v1.XmlSerializer;


import android.util.Xml;

public class PullXMLUtils {

	/**
	 * 创建xml文件
	 * 
	 * @param items
	 *            存储view状态
	 * @param outStream
	 *            输出
	 * @throws Exception
	 */
	public static void createXML(List<ViewState> items, OutputStream outStream)
			throws Exception {
		XmlSerializer serializer = Xml.newSerializer();
		// 设置输出及编码
		serializer.setOutput(outStream, "utf-8");
		// 构建文档类似：<?xml version='1.0' encoding='utf-8' standalone='yes' ?>
		serializer.startDocument("utf-8", true);
		// 开始Tag
		serializer.startTag(null, "state");
		for (ViewState vs : items) { // 增强for循环
			serializer.startTag(null, "view");
			serializer.attribute(null, "name", vs.getName());
			serializer.startTag(null, "text");
			serializer.text(vs.getText());
			serializer.endTag(null, "text");
			serializer.startTag(null, "id");
			serializer.text(String.valueOf(vs.getId()));
			serializer.endTag(null, "id");
			serializer.endTag(null, "view");
		}
		// 结束Tag
		serializer.endTag(null, "state");
		// 结束文档
		serializer.endDocument();
		// 关闭流
		outStream.flush();
		outStream.close();
	}

	/**
	 * 创建xml文件，过载方法
	 * 
	 * @param items
	 *            存储view状态
	 * @param writer
	 *            字符流
	 * @throws Exception
	 */
	public static void createXML(List<ViewState> items, Writer writer)
			throws Exception {
		XmlSerializer serializer = Xml.newSerializer();
		serializer.setOutput(writer);
		serializer.startDocument("utf-8", true);
		serializer.startTag(null, "state");
		for (ViewState vs : items) {
			serializer.startTag(null, "view");
			serializer.attribute(null, "name", vs.getName());
			serializer.startTag(null, "text");
			serializer.text(vs.getText());
			serializer.endTag(null, "text");
			serializer.startTag(null, "id");
			serializer.text(String.valueOf(vs.getId()));
			serializer.endTag(null, "id");
			serializer.endTag(null, "view");
		}
		serializer.endTag(null, "state");
		serializer.endDocument();
		// 关闭流
		writer.flush();
		writer.close();
	}
}