package net.tfobz.rssreader;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

/**
 * RSS Reader fuer RSS Feeds. Die Items werden in einer ArrayList abgespeichert
 * 
 * @author Daniel Lechner
 *
 */
public class RSSReader {

	/**
	 * URL für Internet
	 */
	private String url;

	/**
	 * Channel
	 */
	private Channel channel;

	/**
	 * ArrayList der Items
	 */
	ArrayList<Item> items;

	public static void main(String[] args) {
		try {
			RSSReader reader = new RSSReader("http://provinz.bz.it/wetter/rss.asp");
			ArrayList<Item> itmes = reader.parseURL();
			System.out.println(reader.getChannel().toString());
			System.out.println();
			for (Item item : itmes) {
				System.out.println(item.toString());
				System.out.println();
			}
		} catch (XMLStreamException | IOException e) {
			System.out.println("URL is not valid");
		}
	}

	/**
	 * RSS Reader mit Internet URL
	 * 
	 * @param url URL
	 * @throws MalformedURLException
	 */
	public RSSReader(String url) throws MalformedURLException {
		new URL(url);
		this.url = url;
	}

	/**
	 * Parsed die URL Items in einer ArrayList und setzt den Channel
	 * 
	 * @return Items in einer ArrayList
	 * @throws XMLStreamException
	 * @throws IOException
	 */
	public ArrayList<Item> parseURL() throws XMLStreamException, IOException {
		if (this.url != null) {
			this.channel = new Channel();
			items = new ArrayList<Item>();

			XMLInputFactory factory = XMLInputFactory.newInstance();
			URL urltemp = new URL(this.url);
			InputStream in = urltemp.openStream();
			XMLStreamReader parser = factory.createXMLStreamReader(in);

			String characters = "";
			boolean isItem = false;
			Item itemp = new Item();
			while (parser.hasNext()) {

				int elementType = parser.next();
				switch (elementType) {
					case XMLStreamConstants.START_DOCUMENT: {
						break;
					}
					case XMLStreamConstants.END_DOCUMENT: {
						channel.setItems(items);
						parser.close();
						break;
					}
					case XMLStreamConstants.NAMESPACE: {
						break;
					}
					case XMLStreamConstants.START_ELEMENT: {
						if (parser.getLocalName().equals("item")) {
							isItem = true;
						}
						break;
					}
					case XMLStreamConstants.END_ELEMENT: {
						if (isItem) {
							characters = characters.replaceAll("\n", "").trim();
							String attr = parser.getLocalName().toString();
							switch (attr) {
								case "title": {
									itemp.setTitle(characters);
									break;
								}
								case "author": {
									itemp.setAuthor(characters);
									break;
								}
								case "pubDate": {
									itemp.setPubDate(characters);
									break;
								}
								case "link": {
									itemp.setLink(characters);
									break;
								}
								case "description": {
									itemp.setDescription(characters);
									break;
								}
							}
						} else {
							String att = parser.getLocalName().toString();
							characters = characters.replaceAll("\n", "").trim();
							switch (att) {
								case "url": {
									channel.setUrl(characters);
									break;
								}
								case "language": {
									channel.setLanguage(characters);
									break;
								}
								case "link": {
									channel.setLink(characters);
									break;
								}
								case "title": {
									channel.setTitle(characters);
									break;
								}
								case "description": {
									channel.setDescription(characters);
									break;
								}
								case "copyright": {
									channel.setCopyright(characters);
									break;
								}
							}
						}
						characters = "";
						if (parser.getLocalName().equals("item")) {
							isItem = false;
							items.add(itemp);
							itemp = new Item();
						}
						break;
					}
					case XMLStreamConstants.CHARACTERS: {
						if (!parser.isWhiteSpace() && parser.getText() != null && parser.getText().length() > 0)
							characters += parser.getText();
						break;
					}
				}
			}
			return items;
		} else
			throw new NullPointerException("URL cannot be null");
	}

	/**
	 * return the channel
	 * 
	 * @return channel
	 */
	public Channel getChannel() {
		return this.channel;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}
}