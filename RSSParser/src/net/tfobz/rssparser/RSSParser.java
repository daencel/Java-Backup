package net.tfobz.rssparser;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * https://www.heise.de/rss/heise-top-atom.xml
 * 
 * @author Daniel Lechner
 *
 */
public class RSSParser {

	/**
	 * Document Builder fuer den Parser
	 */
	private DocumentBuilder documentbuilder;

	public static void main(String[] args) {
		RSSParser parser = null;
		try {
			parser = new RSSParser();
			URL url = new URL("https://www.heise.de/rss/heise-top-atom.xml");
			ArrayList<Entry> entries = parser.parse(url.openStream());
			for (Entry entry : entries) {
				System.out.println(entry.toString());
			}
		} catch (ParserConfigurationException | SAXException | IOException e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * Konstruktor des Parser
	 * 
	 * @throws ParserConfigurationException wenn der Document Builder nicht gestetzt
	 *                                      werden kann
	 */
	public RSSParser() throws ParserConfigurationException {
		documentbuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
	}

	/**
	 * Parsed den RSS Feed des uebergebenen Input Streams und gibt die Entries in
	 * eine ArrayList zurueck
	 * 
	 * @param stream Input Stream mit RSS Feed
	 * @throws SAXException
	 * @throws IOException
	 */
	public ArrayList<Entry> parse(InputStream stream) throws SAXException, IOException {
		Document document = documentbuilder.parse(stream);
		Element root = document.getDocumentElement();

		NodeList tempEntries = root.getElementsByTagName("entry");
		ArrayList<Entry> entries = new ArrayList<Entry>();

		for (int i = 0; i < tempEntries.getLength(); i++) {
			Element tempElement = (Element) tempEntries.item(i);
			Entry tempEntry = new Entry();
			tempEntry.setTitle(((Element) tempElement.getElementsByTagName("title").item(0)).getTextContent());
			tempEntry.setId(((Element) tempElement.getElementsByTagName("id").item(0)).getTextContent());
			tempEntry.setUpdated(((Element) tempElement.getElementsByTagName("updated").item(0)).getTextContent());
			tempEntry.setPublished(((Element) tempElement.getElementsByTagName("published").item(0)).getTextContent());
			tempEntry.setLink(((Element) tempElement.getElementsByTagName("link").item(0)).getAttribute("href"));
			tempEntry.setSummary(((Element) tempElement.getElementsByTagName("summary").item(0)).getTextContent());
			tempEntry.setContent(((Element) tempElement.getElementsByTagName("content").item(0)).getTextContent());
			entries.add(tempEntry);
		}
		return entries;
	}
}
