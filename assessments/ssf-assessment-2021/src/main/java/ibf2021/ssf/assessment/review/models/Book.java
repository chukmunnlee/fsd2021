package ibf2021.ssf.assessment.review.models;

import java.util.LinkedList;
import java.util.List;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import jakarta.json.JsonReader;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/*
 * http://openlibrary.org/search.json q=="harry potter" limit==10
 * 	key
 * 	title
 * https://openlibrary.org/works/OL82563W.json
 * 	key
 * 	title
 * 	description
 * 	excerpts[0].excerpt
 * 	first element in covers
 * https://covers.openlibrary.org/b/id/10521270-M.jpg
 */
public class Book {

	private String key;
	private String title;
	private String cover = "/no_cover.png";
	private String description = "";
	private String excerpt = "";
	private Boolean cached = false;

	public String getKey() { return key; }
	public void setKey(String key) { this.key = key; }

	public String getTitle() { return title; }
	public void setTitle(String title) { this.title = title; }

	public String getCover() { return cover; }
	public void setCover(String cover) { this.cover = cover; }

	public String getDescription() { return description; }
	public void setDescription(String description) { this.description = description; }

	public String getExcerpt() { return excerpt; }
	public void setExcerpt(String excerpt) { this.excerpt = excerpt; }

	public Boolean getCached() { return cached; }
	public void setCached(Boolean cached) { this.cached = cached; }

	@Override
	public String toString() {
		return toJson().toString();
	}

	public JsonObject toJson() {
		final JsonObjectBuilder objBuilder = Json.createObjectBuilder()
			.add("key", key)
			.add("title", title)
			.add("cover", cover)
			.add("description", description)
			.add("excerpt", excerpt);

		return objBuilder.build();
	}

	public static Book toBook(String json) throws IOException {

		try (InputStream is = new ByteArrayInputStream(json.getBytes())) {
			JsonReader reader = Json.createReader(is);
			JsonObject o = reader.readObject();
			Book book = new Book();

			book.setKey(getWorksKey(o.getString("key")));
			book.setTitle(o.getString("title"));
			book.setDescription(o.getString("description", ""));

			// hack to make it work with both source and cached version of JSON
			if (!isNull(o.get("excerpts"))) 
				book.setExcerpt(
						o.getJsonArray("excerpts").getJsonObject(0).getString("excerpt"));
			 else if (!isNull(o.get("excerpt")))
				book.setExcerpt(o.getString("excerpt"));

			if (!isNull(o.get("covers"))) {
				JsonArray arr = o.getJsonArray("covers");
				book.setCover("https://covers.openlibrary.org/b/id/%d-M.jpg".formatted(arr.getInt(0)));
			} else if (!isNull(o.get("cover")))
				book.setCover(o.getString("cover"));

			return book;
		}
	}

	public static List<Book> toBookList(String json) throws IOException {
		final List<Book> list = new LinkedList<>();

		try (InputStream is = new ByteArrayInputStream(json.getBytes())) {
			JsonReader reader = Json.createReader(is);
			JsonArray arr = reader.readObject().getJsonArray("docs");
			for (Integer i = 0; i < arr.size(); i++) {
				JsonObject o = arr.getJsonObject(i);
				Book book = new Book();
				book.setKey(getWorksKey(o.getString("key")));
				book.setTitle(o.getString("title"));
				list.add(book);
			}
		} 

		return list;
	}

	private static boolean isNull(Object obj) {
		return null == obj || obj.toString().trim().length() <= 0;
	}

	private static String getWorksKey(String s) {
		String[] t = s.split("/");
		return t[t.length - 1];
	}
}
