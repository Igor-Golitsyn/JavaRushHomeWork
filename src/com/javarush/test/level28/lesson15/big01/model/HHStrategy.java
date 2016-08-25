package com.javarush.test.level28.lesson15.big01.model;
import com.javarush.test.level28.lesson15.big01.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by golit on 08.02.2016.
 */
public class HHStrategy implements Strategy {
    private static final String URL_FORMAT = "http://hh.ua/search/vacancy?text=java+%s&page=%d";
    @Override
    public List<Vacancy> getVacancies(String searchString) {
        int i = 0;
        List<Vacancy> vacancyList = new ArrayList<>();
        List<Element> elementList = new ArrayList<>();
        while (true) {
            Document document = null;
            try {
                document = getDocument(searchString, i);
            }
            catch (IOException e) {
            }
            List<Element> elements = document.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy");
            if (elements.size() == 0) break;
            elementList.addAll(elements);
            for (Element element : elementList) {
                Vacancy vacancy = new Vacancy();
                vacancy.setCity(getText(element, "data-qa", "vacancy-serp__vacancy-address"));
                vacancy.setCompanyName(getText(element, "data-qa", "vacancy-serp__vacancy-employer"));
                vacancy.setSalary(getText(element, "data-qa", "vacancy-serp__vacancy-compensation"));
                vacancy.setSiteName(document.title());
                vacancy.setTitle(getText(element, "data-qa", "vacancy-serp__vacancy-title"));
                vacancy.setUrl(getUrl(element, "data-qa", "vacancy-serp__vacancy-title", "href"));
                vacancyList.add(vacancy);
            }
            i++;
        }
        return vacancyList;
    }
    protected Document getDocument(String searchString, int page) throws IOException {
        String url = String.format(URL_FORMAT, searchString, page);
        Document document = null;
        try {
            document = Jsoup.connect(url).userAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/48.0.2564.109 Safari/537.36").referrer("http://www.google.com").get();
            document.html();
        }
        catch (IOException e) {
        }
        return document;
    }
    private String getText(Element element, String atribute, String value) {
        List<Element> elements = element.getElementsByAttributeValue(atribute, value);
        if (elements.isEmpty()) return new String();
        else return elements.get(0).text();
    }
    private String getUrl(Element element, String atribute, String value, String href) {
        List<Element> elements = element.getElementsByAttributeValue(atribute, value);
        if (elements.isEmpty()) return new String();
        else return elements.get(0).absUrl(href);
    }
}
