package cart;

public class Book {
	private String id;
	private String name;
	private String author;
	private String description;
	private int price;
	private String url;
	
	public Book(String id, String name, String author, String description, int price, String url) {
		this.id = id;
		this.name = name;
		this.author = author;
		this.description = description;
		this.price = price;
		this.url = url;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
}
