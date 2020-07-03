package org.tyaa.spring.aop.objects;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.stereotype.Component;

@Component
public class FileManager {

	public Set<String> getExtensionList(String folder) throws IOException {
		File dir = new File(folder);
		Set<String> extList = new TreeSet<>();
		for (File file : dir.listFiles()) {
			String fileName = file.getName();
			int i = fileName.lastIndexOf(".");
			if (file.isFile() && i != -1) {
				extList.add(fileName.substring(i + 1).toLowerCase());
			}
		}
		return extList;
	}

	public Map<String, Integer> getExtensionCount(String folder) throws IOException {
		File dir = new File(folder);
		Map<String, Integer> map = new HashMap<>();
		for (String ext : getExtensionList(folder)) {
			FilenameFilter filter = new CustomFileFilter(ext);
			map.put(ext, dir.listFiles(filter).length);
		}
		return map;
	}
}
