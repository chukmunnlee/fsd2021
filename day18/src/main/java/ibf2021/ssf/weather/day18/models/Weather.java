package ibf2021.ssf.weather.day18.models;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class Weather {

    private String cityName;
    private String main;
    private String description;
    private String icon;
    private Float temperature;
    private Float latitude;
    private Float longitude;

    public String getCityName() { return this.cityName; }
    public void setCityName(String cityName) { this.cityName = cityName; }

    public String getMain() { return main; }
    public void setMain(String main) { this.main = main; }

    public String getDescription() { return this.description; }
    public void setDescription(String description) { this.description = description; }

    public String getIcon() { return this.icon; }
    public void setIcon(String icon) { 
        this.icon = "http://openweathermap.org/img/wn/%s@2x.png".formatted(icon);
    }

    public Float getTemperature() { return this.temperature; }
    public void setTemperature(Float temperature) { this.temperature = temperature; }

    public Float getLatitude() { return this.latitude; }
    public void setLatitude(Float latitude) { this.latitude = latitude; }

    public Float getLongitude() { return this.longitude; }
    public void setLongitude(Float longitude) { this.longitude = longitude; }

    public static Weather create(JsonObject o) {
        final Weather w = new Weather();
        w.setMain(o.getString("main"));
        w.setDescription(o.getString("description"));
        w.setIcon(o.getString("icon"));
        return w;
    }

    public JsonObject toJson() {
        return Json.createObjectBuilder()
            .add("cityName", cityName)
            .add("main", main)
            .add("description", description)
            .add("icon", icon)
            .add("temperature", temperature)
            .build();
    }
    
}
