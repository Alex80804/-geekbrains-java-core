package lesson7hw;

import java.io.IOException;
import java.text.ParseException;

public interface WeatherForecast {
    public void getWeatherForecast(int option, String cityID) throws IOException, ParseException;
}
