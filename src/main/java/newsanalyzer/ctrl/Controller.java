package newsanalyzer.ctrl;

import newsapi.NewsApi;
import newsapi.NewsApiBuilder;
import newsapi.beans.Article;
import newsapi.beans.NewsReponse;
import newsapi.enums.Category;
import newsapi.enums.Country;
import newsapi.enums.Endpoint;
import newsapi.enums.Language;
import newsapi.expectationmodelling.NewsApiException;

import java.util.List;

public class Controller {

	public static final String APIKEY = "f4a146d29d134811b902355b9b6c558b";

	public void process(String title, Country country, Category category, Endpoint endpoint) {
		System.out.println("Start process");
		getData(title, country, category, endpoint);

		//TODO implement Error handling

		//TODO load the news based on the parameters

		//TODO implement methods for analysis

		/* Austausch mit Mohamad Arastu, Luka Grubisic, Enes Berk, Yassin Elwan und Emina Muminovic*/

		System.out.println("End process");
	}


	public Object getData(String title, Country country, Category category, Endpoint endpoint) {
		try {
			NewsApi newsApi = new NewsApiBuilder()
					.setApiKey(APIKEY)
					.setQ(title)
					.setEndPoint(endpoint)
					.setSourceCountry(country)
					.setSourceCategory(category)
					.createNewsApi();

			NewsReponse newsResponse = newsApi.getNews();
			if (newsResponse != null) {
				List<Article> articles = newsResponse.getArticles();
				articles.stream().forEach(article -> System.out.println(article.toString()));
				return articles;
			} else
				return new NewsApiException("Failed to receive data");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println("End process");

		return null;
	}
}