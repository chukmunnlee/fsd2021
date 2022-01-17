package ibf2021.ssf.marvel.day19.services;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.logging.Logger;

import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import ibf2021.ssf.marvel.day19.Day19Application;
import ibf2021.ssf.marvel.day19.models.Marvel;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

@Service
public class MarvelService {

    private final Logger logger = Logger.getLogger(Day19Application.class.getName());

    public static final String MARVEL_PUBLIC_KEY="MARVEL_PUBLIC_KEY";
    public static final String MARVEL_PRIVATE_KEY="MARVEL_PRIVATE_KEY";
    public static final String API_MARVEL_CHARACTER = "https://gateway.marvel.com:443/v1/public/characters";

    private final String pubKey;
    private final String privKey;

    public MarvelService() {
        String v = System.getenv(MARVEL_PUBLIC_KEY);
        if (Objects.nonNull(v)) {
            pubKey = v;
            logger.info("Setting Marvel PUBLIC key");
        } else {
            pubKey = "not set";
            logger.info("Marvel PUBLIC key not set");
        }
        v = System.getenv(MARVEL_PRIVATE_KEY);
        if (Objects.nonNull(v)) {
            privKey = v;
            logger.info("Setting Marvel PRIVATE key");
        } else {
            privKey = "not set";
            logger.info("Marvel PRIVATE key not set");
        }
    }

    public List<Marvel> getCharacter(String name) {
        final String ts = UUID.randomUUID().toString().replace("-", "");
        final String hash = generateHash(ts);
        final String url = UriComponentsBuilder
            .fromUriString(API_MARVEL_CHARACTER)
            .queryParam("name", name.replace(" ", "+"))
            .queryParam("ts", ts)
            .queryParam("apikey", pubKey)
            .queryParam("hash", hash)
            .toUriString();

        final RequestEntity<Void> req = RequestEntity.get(url).build();
        final RestTemplate template = new RestTemplate();
        final ResponseEntity<String> resp = template.exchange(req, String.class);

        String result = resp.getBody();
        List<Marvel> marvelList = Collections.emptyList();

        try (InputStream is = new ByteArrayInputStream(result.getBytes())) {
            JsonReader reader = Json.createReader(is);
            JsonObject jo = reader.readObject();
            jo = jo.getJsonObject("data");
            JsonArray arr = jo.getJsonArray("results");
            marvelList = arr.stream()
                .map(v -> (JsonObject)v)
                .map(Marvel::create)
                .toList();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return marvelList;
    }

    private String generateHash(String hash) {
        final String toHash = "%s%s%s".formatted(hash, privKey, pubKey);
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException ex) { }
        md5.update(toHash.getBytes());
        byte[] digest = md5.digest();
        return (new BigInteger(1, digest)).toString(16);
    }
}
