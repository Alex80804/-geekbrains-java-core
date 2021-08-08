package lesson7hw;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;

public class AccuweatherForecastModel implements WeatherForecast {
    // apiKey = "QfRAWNPVcQG5PvxrVGy1KHRHB1GLLH2u";
    public final String apiKey = "4IE3E0H7RIW6xId5Yefwqw1CwoAhlJQz";

    Connection addConnection = null;
    Connection getDataConnection = null;

    @Override
    public void getWeatherForecast(int option, String cityName) throws IOException, ParseException, ClassNotFoundException, SQLException {

        Class.forName("org.sqlite.JDBC");
        addConnection = DriverManager.getConnection("jdbc:sqlite:accuweather.db");

        Statement addDataStatement = addConnection.createStatement();

        ObjectMapper forecastObjectMapper = new ObjectMapper();

        String cityID = getCityIDByName(cityName);
        if (cityID.equals("(empty)")) return;

        OkHttpClient forecastClient = new OkHttpClient.Builder().build();

        switch (option) {
            case 1: { // Прогноз на 1 день
                HttpUrl oneDayForecastURL = new HttpUrl.Builder()
                        .scheme("http")
                        .host("dataservice.accuweather.com")
                        .addPathSegments("forecasts/v1/daily/1day/" + cityID)
                        .addQueryParameter("apikey", apiKey)
                        .addQueryParameter("metric", "true")
                        .build();

                Request oneDayForecastRequest = new Request.Builder()
                        .url(oneDayForecastURL)
                        .get()
                        .build();

                Response oneDayForecastResponse = forecastClient.newCall(oneDayForecastRequest).execute();

                String oneDayForecastResponseBody = oneDayForecastResponse.body().string();


                if (!oneDayForecastResponseBody.equals("[]")) {
                    addDataStatement.executeUpdate("delete from forecast");

                    DailyForecast oneDayForecast = forecastObjectMapper.readValue(oneDayForecastResponseBody, DailyForecast.class);
                    ArrayList<DailyForecasts> dailyForecasts = new ArrayList<>(oneDayForecast.getDailyForecasts());
                    System.out.println("Прогноз погоды на " + dailyForecasts.get(0).getDate().substring(0, 10) + " в городе " + cityName);
                    System.out.println();
                    System.out.println("Максимальная температура " +
                            dailyForecasts.get(0).getTemperatureObject().getMaximumObject().getValue() +
                            dailyForecasts.get(0).getTemperatureObject().getMaximumObject().getUnit() + ", " +
                            "минимальная температура " +
                            dailyForecasts.get(0).getTemperatureObject().getMinimumObject().getValue() +
                            dailyForecasts.get(0).getTemperatureObject().getMinimumObject().getUnit()
                    );
                    System.out.println();
                    System.out.println("Основное событие на предстоящие 5 дней: "
                            + oneDayForecast.getHeadline().getText()
                            + " (" + oneDayForecast.getHeadline().getEffectiveDate().substring(0, 10) + ")");

                    addDataStatement.executeUpdate("insert into forecast(city_name, first_date, forecast_date, min_temperature, max_temperature, headline) " +
                            "values ('" + cityName + "', '" +
                            dailyForecasts.get(0).getDate().substring(0, 10) + "', '" +
                            dailyForecasts.get(0).getDate().substring(0, 10) + "', '" +
                            dailyForecasts.get(0).getTemperatureObject().getMinimumObject().getValue() +
                            dailyForecasts.get(0).getTemperatureObject().getMinimumObject().getUnit() + "', '" +
                            dailyForecasts.get(0).getTemperatureObject().getMaximumObject().getValue() +
                            dailyForecasts.get(0).getTemperatureObject().getMaximumObject().getUnit() + "', '" +
                            oneDayForecast.getHeadline().getText() + "')");
                }
                break;
            }
            case 2: {  // Прогноз на 5 дней

                HttpUrl fiveDaysForecastURL = new HttpUrl.Builder()
                        .scheme("http")
                        .host("dataservice.accuweather.com")
                        .addPathSegments("forecasts/v1/daily/5day/" + cityID)
                        .addQueryParameter("apikey", apiKey)
                        .addQueryParameter("metric", "true")
                        .build();

                Request fiveDaysForecastRequest = new Request.Builder()
                        .url(fiveDaysForecastURL)
                        .get()
                        .build();

                Response fiveDaysForecastResponse = forecastClient.newCall(fiveDaysForecastRequest).execute();

                String fiveDaysForecastResponseBody = fiveDaysForecastResponse.body().string();

                if (!fiveDaysForecastResponseBody.equals("[]")) {
                    addDataStatement.executeUpdate("delete from forecast");

                    DailyForecast fiveDaysForecast = forecastObjectMapper.readValue(fiveDaysForecastResponseBody, DailyForecast.class);
                    ArrayList<DailyForecasts> dailyForecasts = new ArrayList<>(fiveDaysForecast.getDailyForecasts());
                    System.out.println("Прогноз погоды на 5 дней с " + dailyForecasts.get(0).getDate().substring(0, 10) + " в городе " + cityName);
                    System.out.println();

                    Iterator<DailyForecasts> iter = dailyForecasts.iterator();
                    while (iter.hasNext()) {
                        DailyForecasts dailyForecastIter = iter.next();
                        System.out.println("Дата: " + dailyForecastIter.getDate().substring(0, 10) + "; " +
                                "максимальная температура " +
                                dailyForecastIter.getTemperatureObject().getMaximumObject().getValue() +
                                dailyForecastIter.getTemperatureObject().getMaximumObject().getUnit() + ", " +
                                "минимальная температура " +
                                dailyForecastIter.getTemperatureObject().getMinimumObject().getValue() +
                                dailyForecastIter.getTemperatureObject().getMinimumObject().getUnit()
                        );

                        addDataStatement.executeUpdate("insert into forecast(city_name, first_date, forecast_date, min_temperature, max_temperature, headline) " +
                                "values ('" + cityName + "', '" +
                                dailyForecasts.get(0).getDate().substring(0, 10) + "', '" +
                                dailyForecastIter.getDate().substring(0, 10) + "', '" +
                                dailyForecastIter.getTemperatureObject().getMinimumObject().getValue() +
                                dailyForecastIter.getTemperatureObject().getMinimumObject().getUnit() + "', '" +
                                dailyForecastIter.getTemperatureObject().getMaximumObject().getValue() +
                                dailyForecastIter.getTemperatureObject().getMaximumObject().getUnit() + "', '" +
                                fiveDaysForecast.getHeadline().getText() + "')");
                    }

                    System.out.println();
                    System.out.println("Основное событие на предстоящие 5 дней: "
                            + fiveDaysForecast.getHeadline().getText()
                            + " (" + fiveDaysForecast.getHeadline().getEffectiveDate().substring(0, 10) + ")");
                }
                break;
            }
        }
    }

    private String getCityIDByName(String cityName) throws IOException {
        String cityID = "(empty)";

        OkHttpClient citySearchClient = new OkHttpClient.Builder()
                .build();

        HttpUrl citySearchURL = new HttpUrl.Builder()
                .scheme("http")
                .host("dataservice.accuweather.com")
                .addPathSegments("locations/v1/cities/search")
                .query("apikey=" + apiKey + "&q=" + cityName)
                .build();

        Request citySearchRequest = new Request.Builder()
                .url(citySearchURL)
                .get()
                .build();

        Response citySearchResponse = citySearchClient.newCall(citySearchRequest).execute();

        String citySearchResponseBody = citySearchResponse.body().string();

        ObjectMapper citySearchObjectMapper = new ObjectMapper();

        if (!citySearchResponseBody.equals("[]")) {
            cityID = citySearchObjectMapper.readTree(citySearchResponseBody).get(0).at("/Key").asText();
        }
        return cityID;
    }

    public void printDataFromDB() throws ClassNotFoundException, SQLException {
        String lastHeadline = "";
        Class.forName("org.sqlite.JDBC");
        getDataConnection = DriverManager.getConnection("jdbc:sqlite:accuweather.db");
        Statement getDataStatement = getDataConnection.createStatement();

        ResultSet forecastResults = getDataStatement.executeQuery("select * from forecast");
        while (forecastResults.next()) {
            if (forecastResults.isFirst()) {
                System.out.println("Прогноз погоды с " + forecastResults.getString("first_date") +
                        " в городе " + forecastResults.getString("city_name"));
                System.out.println();
            }
            System.out.println("Дата: " + forecastResults.getString("forecast_date") + "; " +
                    "максимальная температура " + forecastResults.getString("max_temperature") + ", " +
                    "минимальная температура " + forecastResults.getString("min_temperature"));
            lastHeadline = forecastResults.getString("headline");
        }
        System.out.println();
        System.out.println("Основное событие на предстоящие 5 дней: "
                + lastHeadline);

    }
}
