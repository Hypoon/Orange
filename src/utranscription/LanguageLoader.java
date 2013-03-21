package utranscription;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class LanguageLoader {
	private Map<String, File> langIndex;
	private Language selLang;
	
	public LanguageLoader() {
		langIndex = new HashMap<String, File>();
		File langDir = new File("languages");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			File[] fileList = langDir.listFiles(new FilenameFilter() {
				public boolean accept(File dir, String name) {
					if(name.endsWith(".xml")){
						return true;
					}
					return false;
				}
			});
			for(File f : fileList) {
				Document dom = dBuilder.parse(f);
				Element docEle = dom.getDocumentElement();
				langIndex.put(docEle.getAttribute("name"), f);
			}	
		} catch (ParserConfigurationException e) {
			System.err.println("There is a serious configuration error.");
			e.printStackTrace();
		} catch (SAXException e) {
			System.err.println("A parse error has occurred.");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("An IO error has occurred.");
			e.printStackTrace();
		}
	}
	
	public Set<String> getAvailableLangs() {
		return langIndex.keySet();
	}
	
	public void setLang(String lang) {
		if(langIndex.containsKey(lang)) {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			File f = langIndex.get(lang);
			try {
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				Document dom = dBuilder.parse(f);
				Element docEle = dom.getDocumentElement();
				selLang = new Language(docEle.getAttribute("name"));
			} catch (ParserConfigurationException e) {
				System.err.println("There is a serious configuration error.");
				e.printStackTrace();
			} catch (SAXException e) {
				System.err.println("A parse error has occurred.");
				e.printStackTrace();
			} catch (IOException e) {
				System.err.println("An IO error has occurred.");
				e.printStackTrace();
			}
		}
	}
	
	public Language getLang() {
		return selLang;
	}
}
