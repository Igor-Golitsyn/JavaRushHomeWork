package com.javarush.test.level28.lesson15.big01.model;
import com.javarush.test.level28.lesson15.big01.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by golit on 17.02.2016.
 */
public class MoikrugStrategy implements Strategy {
    private static final String URL_FORMAT = "https://moikrug.ru/vacancies?page=%s&q=java+%s";
    @Override
    public List<Vacancy> getVacancies(String searchString) {
        int i = 1;
        List<Vacancy> vacancyList = new ArrayList<>();
        while (true) {
            Document document = null;
            try {
                document = getDocument(searchString, i);
            }
            catch (IOException e) {
            }
            Elements elements = document.getElementsByClass("job");
            if (elements.size() == 0) break;
            for (Element element : elements) {
                Vacancy vacancy = new Vacancy();
                vacancy.setCity(element.getElementsByClass("location").text());
                element.getElementsByClass("date").remove();
                vacancy.setCompanyName(element.getElementsByClass("company_name").text());
                vacancy.setSalary(element.getElementsByClass("salary").text());
                vacancy.setSiteName(document.title());
                vacancy.setTitle(element.getElementsByClass("title").text());
                vacancy.setUrl(element.getElementsByClass("title").first().getElementsByAttribute("href").first().absUrl("href"));
                vacancyList.add(vacancy);
            }
            i++;
        }
        return vacancyList;
    }
    protected Document getDocument(String searchString, int page) throws IOException {
        String pageString = page == 1 ? "" : String.valueOf(page);
        String url = String.format(URL_FORMAT, pageString, searchString);
        Document document = null;
        try {
            document = Jsoup.connect(url).userAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/48.0.2564.109 Safari/537.36").referrer("http://www.google.com").get();
        }
        catch (IOException e) {
        }
        return document;
    }
}
