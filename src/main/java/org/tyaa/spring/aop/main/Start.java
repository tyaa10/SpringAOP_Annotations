package org.tyaa.spring.aop.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.tyaa.spring.aop.objects.FileManager;

import java.util.Map;

public class Start {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
		FileManager fileManager = (FileManager) context.getBean("fileManager");
		Map<String, Integer> integerMap =
			// fileManager.getExtensionCount("c:\\Windows\\System32");
			fileManager.getExtensionCount("/home/yurii/Документы/книги");
		System.out.println(integerMap);
		//fileManager.getExtensionCount("c:\\Windows\\");
		//fileManager.getExtensionCount("c:\\Windows\\system32\\drivers");
	}
}
