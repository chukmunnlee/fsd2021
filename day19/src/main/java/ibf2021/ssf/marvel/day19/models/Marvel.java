package ibf2021.ssf.marvel.day19.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;

import jakarta.json.JsonArray;
import jakarta.json.JsonObject;

public class Marvel {

    private String path;
    private String extension;
    private String name;
    private String details = "#";

    public String getName() { return this.name; }
    public void setName(String name) { this.name = name; }

    public String getPath() { return this.path; }
    public void setPath(String path) { this.path = path; }

    public String getExtension() { return this.extension; }
    public void setExtension(String extension) { this.extension = extension; }

    public String getImage() { return "%s.%s".formatted(this.path, this.extension); }
    public void setImage(String path) { }

    public String getDetails() { return this.details; }
    public void setDetails(String details) { this.details = details; }

    @Override
    public String toString() {
        return "name: %s, path: %s, extension: %s, details: %s"
                .formatted(name, path, extension, details);
    }

    public static Marvel create(JsonObject jo) {
        final Marvel marvel = new Marvel();
        marvel.setName(jo.getString("name"));
        final JsonObject thumbnail = jo.getJsonObject("thumbnail");
        marvel.setPath(thumbnail.getString("path"));
        marvel.setExtension(thumbnail.getString("extension"));
        final JsonArray urls = jo.getJsonArray("urls");
        // Get the details
        List<String> d = urls.stream()
            .map(v -> (JsonObject)v)
            .filter(v -> v.getString("type").equals("detail"))
            .map(v -> v.getString("url"))
            .toList();
        if (d.size() > 0)
            marvel.setDetails(d.get(0));
        return marvel;
    }
}
