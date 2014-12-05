package net.bobmandude9889.World;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import net.bobmandude9889.Resource.ResourceLoader;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public abstract class OrthogonalMapLoader {

	public static OrthogonalMap load(String fileName) {
		File file = ResourceLoader.getFile(fileName);
		DocumentBuilderFactory fact = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		Document doc = null;
		try {
			builder = fact.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		try {
			doc = builder.parse(file);
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		doc.getDocumentElement().normalize();

		// Check map orientation
		NamedNodeMap attributes = doc.getDocumentElement().getAttributes();
		String orientation = attributes.getNamedItem("orientation").getNodeValue();
		if (!orientation.equals("orthogonal")) {
			System.err.println("The orientation " + orientation + " is not supported.");
		}

		NodeList tileSets = doc.getElementsByTagName("tileset");
		NodeList layerElements = doc.getElementsByTagName("layer");

		OrthogonalLayer[] layers = new OrthogonalLayer[tileSets.getLength()];

		int width = 0;
		int height = 0;
		int tileWidth = 0;
		int tileHeight = 0;

		for (int temp = 0; temp < tileSets.getLength(); temp++) {
			Node node = tileSets.item(temp);

			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element tileSetElement = (Element) node;
				Element imageElement = (Element) tileSetElement.getElementsByTagName("image").item(0);
				BufferedImage image = ResourceLoader.loadImage(imageElement.getAttribute("source"));
				HashMap<Integer, PropertySet> propertyList = new HashMap<Integer, PropertySet>();
				tileWidth = Integer.parseInt(tileSetElement.getAttribute("tilewidth"));
				tileHeight = Integer.parseInt(tileSetElement.getAttribute("tileheight"));
				NodeList tileNodes = tileSetElement.getElementsByTagName("tile");
				for (int i = 0; i < tileNodes.getLength(); i++) {
					Node tileNode = tileNodes.item(i);

					if (node.getNodeType() == Node.ELEMENT_NODE) {
						Element tileElement = (Element) tileNode;
						PropertySet propertySet = new PropertySet();
						Element properties = (Element) tileElement.getElementsByTagName("properties").item(0);
						NodeList propertyNodes = properties.getElementsByTagName("property");
						for (int j = 0; j < propertyNodes.getLength(); j++) {
							Node propertyNode = propertyNodes.item(j);

							if (propertyNode.getNodeType() == Node.ELEMENT_NODE) {
								Element propertyElement = (Element) propertyNode;
								propertySet.add(new Property(propertyElement.getAttribute("name"), propertyElement.getAttribute("value")));
							}
						}
						propertyList.put(i, propertySet);
					}
				}

				TileSet tileSet = new TileSet(tileWidth, tileHeight, image, propertyList, Integer.parseInt(tileSetElement.getAttribute("firstgid")));
				GlobalTileSet.addTileSet(tileSet);
				GlobalTileSet.addFirstGid(Integer.parseInt(tileSetElement.getAttribute("firstgid")));
			}
		}

		System.out.println(layerElements.getLength());
		for (int temp = 0; temp < layerElements.getLength(); temp++) {
			Node layerNode = layerElements.item(temp);

			if (layerNode.getNodeType() == Node.ELEMENT_NODE) {
				width = Integer.parseInt(doc.getDocumentElement().getAttribute("width"));
				height = Integer.parseInt(doc.getDocumentElement().getAttribute("height"));
				int[] tiles = new int[width * height];
				String[] split = ((Element) layerElements.item(temp)).getElementsByTagName("data").item(0).getTextContent().split(",");
				for (int i = 0; i < split.length; i++) {
					String s = split[i];
					s = s.replace("\n", "").replace("\r", "");
					tiles[i] = Integer.parseInt(s);

					OrthogonalLayer layer = new OrthogonalLayer(tiles, width, height);
					layers[temp] = layer;
				}
			}
		}
		
		OrthogonalMap map = new OrthogonalMap(width, height, tileWidth, tileHeight, layers);

		return map;
	}
}
