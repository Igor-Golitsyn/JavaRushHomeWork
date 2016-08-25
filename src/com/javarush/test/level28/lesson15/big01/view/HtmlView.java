package com.javarush.test.level28.lesson15.big01.view;
import com.javarush.test.level28.lesson15.big01.Controller;
import com.javarush.test.level28.lesson15.big01.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by golit on 12.02.2016.
 */
public class HtmlView implements View {
    private final String filePath = "./src/" + String.valueOf(this.getClass().getPackage()).replace('.', '/').substring(8) + "/vacancies.html";
    private Controller controller;
    @Override
    public void update(List<Vacancy> vacancies) {
        try {
            updateFile(getUpdatedFileContent(vacancies));
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Some exception occurred");
        }
    }
    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }
    public void userCitySelectEmulationMethod() {
        controller.onCitySelect("Odessa");
    }
    private String getUpdatedFileContent(List<Vacancy> vacancyList) throws Exception {
        Document document = getDocument();
        Element elementTemplate = document.getElementsByClass("template").get(0);
        Element copyTemplate = elementTemplate.clone();
        copyTemplate = copyTemplate.removeClass("template");
        copyTemplate.removeAttr("style");
        document.select("tr[class=\"vacancy\"]").remove();
        for (Vacancy vacancy : vacancyList) {
            Element element = copyTemplate.clone();
            element.getElementsByTag("a").attr("href", vacancy.getUrl()).append(vacancy.getTitle());
            element.getElementsByClass("city").first().text(vacancy.getCity());
            element.getElementsByClass("companyName").first().text(vacancy.getCompanyName());
            element.getElementsByClass("salary").first().text(vacancy.getSalary());
            elementTemplate.before(element.outerHtml());
        }
        return document.outerHtml();
    }
    private void updateFile(String data) throws IOException {
        FileWriter fileWriter = new FileWriter(new File(filePath));
        fileWriter.write(data);
        fileWriter.flush();
        fileWriter.close();
    }
    protected Document getDocument() throws IOException {
        return Jsoup.parse(new File(filePath), "UTF-8");
    }
}
