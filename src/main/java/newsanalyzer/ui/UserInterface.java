package newsanalyzer.ui;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import newsanalyzer.ctrl.Controller;
import newsapi.NewsApi;
import newsapi.NewsApiBuilder;
import newsapi.enums.Category;
import newsapi.enums.Country;
import newsapi.enums.Endpoint;

import static newsanalyzer.ctrl.Controller.APIKEY;

public class UserInterface {

    private Controller ctrl = new Controller();

    public void getHealthTopics() {

        ctrl.process("Corona", Country.at, Category.health, Endpoint.TOP_HEADLINES);
    }

    public void getSportsTopics() {
        ctrl.process("Fußball", Country.at, Category.sports, Endpoint.TOP_HEADLINES);
    }

    public void getScienceTopics() {
        ctrl.process("Gravitation", Country.at, Category.science, Endpoint.TOP_HEADLINES);
    }

    public void getDataForCustomInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose a title:");
        String topic = scanner.nextLine();

        ctrl.process(topic, Country.us, Category.business, Endpoint.TOP_HEADLINES);
    }


    public void start() {
        NewsApi newsApi = new NewsApiBuilder()
                .setApiKey(APIKEY)
                .setQ("corona")
                .setEndPoint(Endpoint.TOP_HEADLINES)
                .setSourceCountry(Country.at)
                .setSourceCategory(Category.health)
                .createNewsApi();

        Menu<Runnable> menu = new Menu<>("User Interfacx");
        menu.setTitel("Wählen Sie aus:");
        menu.insert("a", "Corona", this::getHealthTopics);
        menu.insert("b", "Sport", this::getSportsTopics);
        menu.insert("c", "Science", this::getScienceTopics);
        menu.insert("d", "Topic of your wishes:", this::getDataForCustomInput);
        menu.insert("q", "Quit", null);
        Runnable choice;
        while ((choice = menu.exec()) != null) {
            choice.run();
        }
        System.out.println("Program finished");
    }


    protected String readLine() {
        String value = "\0";
        BufferedReader inReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            value = inReader.readLine();
        } catch (IOException ignored) {
        }
        return value.trim();
    }

    protected Double readDouble(int lowerlimit, int upperlimit) {
        Double number = null;
        while (number == null) {
            String str = this.readLine();
            try {
                number = Double.parseDouble(str);
            } catch (NumberFormatException e) {
                number = null;
                System.out.println("Please enter a valid number:");
                continue;
            }
            if (number < lowerlimit) {
                System.out.println("Please enter a higher number:");
                number = null;
            } else if (number > upperlimit) {
                System.out.println("Please enter a lower number:");
                number = null;
            }
        }
        return number;
    }
}
