/**
 * 
 */
package com.cityinfo.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.DoubleField;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.IntField;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopScoreDocCollector;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.cityinfo.bean.PlaceInformation;
import com.cityinfo.dao.PlaceDAO;
import com.cityinfo.dao.impl.PlaceDAOImpl;

/**
 * The simple search engine that provides user input search function
 * 
 * @author Brandon
 * @version 1.0 2014-05-10
 */
public class PlaceSearchService {

	private Directory directory;
	private Analyzer analyzer;
	private PlaceDAO placeDao;

	/**
	 * Construction for PlaceSearchService with given directory path
	 * 
	 * @param directoryPath
	 */
	public PlaceSearchService(String directoryPath) {
		try {
			directory = FSDirectory.open(new File(directoryPath));
			analyzer = new IKAnalyzer();
			placeDao = new PlaceDAOImpl();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Default construction for PlaceSearchService
	 */
	public PlaceSearchService() {
		this("G:/CISPlaceIndex");
	}

	/**
	 * Create indexes for the existed places
	 */
	public void createIndex() {
		IndexWriter writer = null;
		IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_47,
				analyzer);
		config.setOpenMode(OpenMode.CREATE);
		try {
			writer = new IndexWriter(directory, config);
			// writer.deleteAll();
			ArrayList<PlaceInformation> places = (ArrayList<PlaceInformation>) placeDao
					.getAllPlaces('0');
			for (PlaceInformation place : places) {
				Document doc = newDoc(place);
				writer.addDocument(doc);
			}
			// writer.commit();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * Append new place index
	 * 
	 * @param place
	 *            the place needs to be indexed
	 */
	public void addIndex(PlaceInformation place) {
		IndexWriter writer = null;
		IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_47,
				analyzer);
		config.setOpenMode(OpenMode.APPEND);
		try {
			writer = new IndexWriter(directory, config);
			Document doc = newDoc(place);
			writer.addDocument(doc);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * Add document index to the system
	 * 
	 * @param place
	 * @return TODO
	 * @throws IOException
	 */
	private Document newDoc(PlaceInformation place)
			throws IOException {
		Document doc = new Document();
		doc.add(new IntField("place_id", place.getPlaceId(), Field.Store.YES));
		doc.add(new StringField("place_name", place.getName(), Field.Store.YES));
		doc.add(new DoubleField("rating", place.getRating(), Field.Store.YES));
		doc.add(new DoubleField("price", place.getPrice(), Field.Store.YES));
		doc.add(new TextField("description", place.getDescription(),
				Field.Store.YES));
		doc.add(new TextField("address", place.getAddress(), Field.Store.YES));
		doc.add(new TextField("recommendation", place.getRecommendation(),
				Field.Store.YES));
		doc.add(new StringField("phone", place.getPhone(), Field.Store.YES));
		doc.add(new StringField("picture_address", place.getPicture_address(),
				Field.Store.YES));
		doc.add(new StringField("subcategory", place.getCategory().getName(),
				Field.Store.YES));
		doc.add(new StringField("category", place.getCategory().getCategory()
				.getName(), Field.Store.YES));
		doc.add(new StringField("location_name", place.getLocation()
				.getLocation_name(), Field.Store.YES));
		doc.add(new StringField("area",
				place.getLocation().getArea().getName(), Field.Store.YES));
		doc.add(new StringField("district", place.getLocation().getArea()
				.getDistrict().getName(), Field.Store.YES));
		return doc;
	}

	/**
	 * Respond to user's input text search
	 * 
	 * @param inputText
	 * @return a list including all satisfied places
	 */
	public List<PlaceInformation> search(String inputText) {
		ArrayList<PlaceInformation> places = new ArrayList<PlaceInformation>();
		int hitsPerPage = 25;
		String[] fields = { "place_name", "description", "address",
				"recommendation", "phone", "category", "location_name", "area",
				"district", "subcategory" };
		IndexReader reader = null;
		IndexSearcher searcher = null;
		try {
			reader = DirectoryReader.open(directory);
			searcher = new IndexSearcher(reader);
			MultiFieldQueryParser fieldQuery = new MultiFieldQueryParser(
					Version.LUCENE_47, fields, analyzer);
			Query query = fieldQuery.parse(inputText);
			TopScoreDocCollector collector = TopScoreDocCollector.create(
					hitsPerPage, true);
			searcher.search(query, collector);
			ScoreDoc[] hits = collector.topDocs().scoreDocs;
			System.out.println("Found " + hits.length + " hits.");
			for (ScoreDoc scoreDoc : hits) {
				Document document = searcher.doc(scoreDoc.doc);
				PlaceInformation place = placeDao.findPlaceById(Integer
						.parseInt(document.get("place_id")));
				places.add(place);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return places;
	}

	/**
	 * Delete index according to the given id
	 * 
	 * @param id
	 */
	public void delete(int id) {
		IndexWriter indexWriter = null;
		try {
			IndexWriterConfig indexWriterConfig = new IndexWriterConfig(
					Version.LUCENE_47, analyzer);
			indexWriter = new IndexWriter(directory, indexWriterConfig);
			Term term = new Term("place_id", String.valueOf(id));
			indexWriter.deleteDocuments(term);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (indexWriter != null) {
				try {
					indexWriter.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * Update the existed index of given id
	 * 
	 * @param id
	 * @param place
	 *            new Place Information
	 */
	public void update(int id, PlaceInformation place) {
		IndexWriter writer = null;
		try {
			IndexWriterConfig indexWriterConfig = new IndexWriterConfig(
					Version.LUCENE_47, analyzer);
			writer = new IndexWriter(directory, indexWriterConfig);
			Document document = newDoc(place);
			Term term = new Term("id", String.valueOf(id));
			writer.updateDocument(term, document);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
