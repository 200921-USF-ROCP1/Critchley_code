package com.revature.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.classes.DemoClass;
import com.revature.classes.JsonClass;
import com.revature.classes.XmlClass;

public class App {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//xmlMethod();
		
		//jsonMethod();
		
		DemoClass demoClass = new DemoClass();
		demoClass.setI(10);
		demoClass.setS("hello from demo class");
		demoClass.setB(false);
		
		
		// marshal to xml as string
		System.out.println("marshalToXml");
		String marshalToXmlString = marshalToXml(demoClass);
		System.out.println(marshalToXmlString);
		
		
		// unmarshal from xml as string
		System.out.println("");
		System.out.println("marshal from xml");
		DemoClass demo = unmarshalFromXml(marshalToXmlString);
		System.out.println(demo.getI() + " " + demo.getS());
		
		
		// marshal from json as string
		System.out.println("");
		System.out.println("marshal to json");
		String marshalToJsonString = marshalToJson(demoClass);
		System.out.println(marshalToJsonString);
		
		
		// unmarshal from json as string
		System.out.println("");
		System.out.println("marshal from json");
		DemoClass jsonDemo = unmarshalFromJson(marshalToJsonString);
		System.out.println(demo.getI() + " " + jsonDemo.getS());
		
		
		// unmarshal from xml as BufferedReader
		BufferedReader xmlData = new BufferedReader(new StringReader("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\r\n"
				+ "<DemoClass>\r\n"
				+ "    <b>true</b>\r\n"
				+ "    <i>10</i>\r\n"
				+ "    <s>Hello</s>\r\n"
				+ "</DemoClass>"));
		DemoClass xmlBrDemo = unmarshalFromXml(xmlData);
		System.out.println("");
		System.out.println("unmarshal from xml as BufferedReader");
		System.out.println(xmlBrDemo.getI() + " " + xmlBrDemo.getS());
		
		
		// unmarshal from json as BufferedReader
		BufferedReader jsonData = new BufferedReader(new StringReader("{\"i\":56,\"s\":\"Hi there!\",\"b\":false}"));
		System.out.println("");
		System.out.println("unmarshal from json as BufferedReader");
		DemoClass brDemo = unmarshalFromJson(jsonData);
		System.out.println(brDemo.getI() + " " + brDemo.getS());
		
	}
	
	public static void xmlMethod() {
		XmlClass xml = new XmlClass(), empty;
		xml.setI(10);
		xml.setS("Hello");
		xml.setB(true);
		try {
			JAXBContext context = JAXBContext.newInstance(XmlClass.class);
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			marshaller.marshal(xml, System.out);
			
			// Unmarshalling 
			Unmarshaller unmarshaller = context.createUnmarshaller();
			empty = (XmlClass) unmarshaller.unmarshal(new StringReader("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>"
				+ "<XmlClass>"
				+   "<b>true</b>"
				+    "<i>10</i>"
				+    "<s>Hello</s>"
				+"</XmlClass>"));
			
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void jsonMethod() {
		JsonClass json = new JsonClass(), empty;
		json.setI(10);
		json.setS("Hello Json");
		json.setB(false);
		
		try {
			ObjectMapper mapper = new ObjectMapper();
			
			String s = mapper.writeValueAsString(json);
			System.out.println(s);
			
			empty = mapper.readValue(s, JsonClass.class);
			System.out.println(empty.getI() + " " + empty.getS());
			
			
			
		} catch (Exception e) {
			
		}
		
		
	}
	
	public static String marshalToXml(DemoClass demoClass) {
		
		String ret = null;
		JAXBContext context;
		try {
			context = JAXBContext.newInstance(DemoClass.class);
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			
			StringWriter sw = new StringWriter();
			
			marshaller.marshal(demoClass, sw);
			ret = sw.toString();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return ret;

	}
	
	public static DemoClass unmarshalFromXml(String xml) {
		JAXBContext context;
		DemoClass empty;
		try {
			context = JAXBContext.newInstance(DemoClass.class);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			empty = (DemoClass) unmarshaller.unmarshal(new StringReader(xml));
			return empty;
			
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
	public static String marshalToJson(DemoClass demoClass) {
		
		try {
			ObjectMapper om = new ObjectMapper();
			String s = om.writeValueAsString(demoClass);
			return s;
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
	public static DemoClass unmarshalFromJson(String json) {
		DemoClass empty;
		
		
		try {
			ObjectMapper mapper = new ObjectMapper();
			empty = mapper.readValue(json, DemoClass.class);
			return empty;
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	public static DemoClass unmarshalFromXml(BufferedReader br) {
		JAXBContext context;
		DemoClass empty;
		try {
			context = JAXBContext.newInstance(DemoClass.class);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			empty = (DemoClass) unmarshaller.unmarshal(br);
			return empty;
			
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
	public static DemoClass unmarshalFromJson(BufferedReader br) {
		DemoClass empty;
		
		try {
			ObjectMapper mapper = new ObjectMapper();
			empty = mapper.readValue(br, DemoClass.class);
			return empty;
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}

}
