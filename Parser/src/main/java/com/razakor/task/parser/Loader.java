package com.razakor.task.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.*;
import com.razakor.task.documents.*;

class Loader {
    private static Set<Trolleybuses> trolleybuses = new HashSet<>();
    private static Map<String, String> urls = new HashMap<>();

    static {
        urls.put("1", "https://vn.rozklad.in.ua/home/schedule/48");
        urls.put("2", "https://vn.rozklad.in.ua/home/schedule/50");
        urls.put("3", "https://vn.rozklad.in.ua/home/schedule/74");
        urls.put("4", "https://vn.rozklad.in.ua/home/schedule/52");
        urls.put("5", "https://vn.rozklad.in.ua/home/schedule/69");
        urls.put("6", "https://vn.rozklad.in.ua/home/schedule/71");
        urls.put("6A", "https://vn.rozklad.in.ua/home/schedule/111");
        urls.put("7", "https://vn.rozklad.in.ua/home/schedule/65");
        urls.put("9", "https://vn.rozklad.in.ua/home/schedule/55");
        urls.put("10", "https://vn.rozklad.in.ua/home/schedule/64");
        urls.put("11", "https://vn.rozklad.in.ua/home/schedule/59");
        urls.put("12", "https://vn.rozklad.in.ua/home/schedule/57");
        urls.put("13", "https://vn.rozklad.in.ua/home/schedule/53");
        urls.put("14", "https://vn.rozklad.in.ua/home/schedule/109");
        urls.put("15", "https://vn.rozklad.in.ua/home/schedule/61");
    }

    static void  load() {
        urls.entrySet().parallelStream().forEach(url -> {
            try {
                Document document = Jsoup.connect(url.getValue()).get();
                Trolleybuses trolleybus = new Trolleybuses(url.getKey(), document.title());
                trolleybuses.add(trolleybus);
                loadStops(trolleybus, url.getValue(), document);
                System.out.println(trolleybus.getName());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private static void loadStops(Trolleybuses trolleybus, String url, Document document) {
        Element chosenRote = document.select("div.chosen-route.active").get(0);
        Element ul = chosenRote.select("ul").get(0);
        Elements li = ul.select("li");
        for (Element l : li) {
            String s = l.attr("onClick");
            s = s.substring(61, s.length() - 1);
            s = s.substring(s.indexOf(' ') + 1);
            String stopUrl = url + '/' + s;
            String name = l.select("p").text();
            Stops stop = new Stops(name);
            trolleybus.getStops().add(stop);
            loadWorkDayTimes(stop, stopUrl);
            loadWeekendTimes(stop, stopUrl);
        }
    }

    private static void loadWorkDayTimes(Stops stop, String url) {
        try {
            Document stopDocument = Jsoup.connect(url).get();
            Element table = stopDocument.select("table").get(0);
            Elements rows = table.select("tr");

            for (Element row : rows) {
                Elements cols = row.select("td");
                if (cols.isEmpty()) continue;
                setTimes(cols, stop, true);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void loadWeekendTimes(Stops stop, String url) {
        try {
            Document stopDocument = Jsoup.connect(url).get();
            Element table = stopDocument.select("table").get(0);
            Elements rows = table.select("tr");

            for (Element row : rows) {
                Elements cols = row.select("td");
                if (cols.isEmpty()) continue;
                if (cols.get(0).text().equals("Маршрут не працює в ці дні")) {
                    return;
                }
                setTimes(cols, stop, false);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void setTimes(Elements cols, Stops stop, boolean isWorkDay) {
        if (!cols.get(0).text().equals("")) {
            String value = cols.get(0).text();
            Hour hour = new Hour(value);
            hour.setMinutes(getMinutes(cols));

            hour.getTime().forEach(val -> {
                Times time = new Times(val, isWorkDay);
                stop.getTimes().add(time);
            });
        }
    }

    private static List<String> getMinutes(Elements cols) {
        List<String> minutes = new ArrayList<>();
        for (int i = 1; i < cols.size(); i++) {
            if (!cols.get(i).text().equals("")) {
                minutes.add(cols.get(i).text());
            }
        }
        return minutes;
    }


    static Set<Trolleybuses> getTrolleybuses() {
        return trolleybuses;
    }
}