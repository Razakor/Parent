package com.razakor.task.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.*;
import com.razakor.task.entities.*;

class Loader {
    private static Set<Trolleybuses> trolleybuses = new HashSet<>();
    private static Set<Stops> stops = new HashSet<>();
    private static Set<Hours> hours = new HashSet<>();
    private static Set<Minutes> minutes = new HashSet<>();
    private static Map<String, String> urls = new HashMap<>();
    private static Hours hour;
    private static Integer i = 1;

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
        urls.forEach((trolleybusNumber, url) -> {
            try {
                Document document = Jsoup.connect(url).get();
                Trolleybuses trolleybus = new Trolleybuses(trolleybusNumber, document.title());
                trolleybus.setStops(new HashSet<>());
                trolleybuses.add(trolleybus);
                loadStops(trolleybus, url, document);
                System.out.println("Trolleybus loaded");
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
            if(stops.contains(stop)) {
                for(Stops stp : stops) {
                    if (stp.equals(stop)) {
                        stop = stp;
                        break;
                    }
                }
            } else {
                stops.add(stop);
                stop.setTrolleybuses(new HashSet<>());
                stop.setHours(new HashSet<>());
            }
            trolleybus.getStops().add(stop);
            stop.getTrolleybuses().add(trolleybus);
            loadWorkDaysHours(trolleybus, stop, stopUrl);
            loadWeekendHours(trolleybus, stop, stopUrl);
        }
    }

    private static void loadWorkDaysHours(Trolleybuses trolleybus, Stops stop, String url) {
        try {
            Document stopDocument = Jsoup.connect(url).get();
            Element table = stopDocument.select("table").get(0);
            Elements rows = table.select("tr");

            for (Element row : rows) {
                Elements cols = row.select("td");
                if (cols.isEmpty()) continue;
                if (!cols.get(0).text().equals("")) {
                    String value = cols.get(0).text();
                    hour = new Hours(i, trolleybus.getNumber(), stop.getName(), value, true);
                    i++;
                    hour.setMinutes(new HashSet<>());
                    hour.setTrolleybus(trolleybus);
                    hour.setStop(stop);
                    stop.getHours().add(hour);
                    hours.add(hour);
                }
                setMinutes(cols);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void loadWeekendHours(Trolleybuses trolleybus, Stops stop, String url) {
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
                if (!cols.get(0).text().equals("")) {
                    String value = cols.get(0).text();
                    hour = new Hours(i, trolleybus.getNumber(), stop.getName(), value, false);
                    i++;
                    hour.setMinutes(new HashSet<>());
                    hour.setTrolleybus(trolleybus);
                    hour.setStop(stop);
                    stop.getHours().add(hour);
                    hours.add(hour);
                }
                setMinutes(cols);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void setMinutes(Elements cols) {
        for (int i = 1; i < cols.size(); i++) {
            if (!cols.get(i).text().equals("")) {
                String value = cols.get(i).text();
                Minutes minute = new Minutes(hour.getId(), value);
                minute.setHour(hour);
                minutes.add(minute);
            }
        }
    }

    static Set<Trolleybuses> getTrolleybuses() {
        return trolleybuses;
    }

    static Set<Stops> getStops() {
        return stops;
    }

    static Set<Hours> getHours() {
        return hours;
    }

    static Set<Minutes> getMinutes() {
        return minutes;
    }
}