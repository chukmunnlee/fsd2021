package ibf2021.paf.day34.controllers;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.UUID;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import jakarta.json.Json;
import jakarta.json.JsonObject;

@RestController
@RequestMapping("/post/s3")
public class PostS3RestController {

	@Autowired
	private AmazonS3 s3;

	@PostMapping
	public ResponseEntity<String> post(
			@RequestParam MultipartFile image,
			@RequestPart String comments, @RequestPart String poster) {

		JsonObject result;
		String contentType = image.getContentType();
		byte[] buff = null;

		try {
			buff = image.getBytes();
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		System.out.printf(">> contentType: %s, buff: %d\n", contentType, buff.length);
		System.out.printf(">> comments: %s, poster: %s\n", comments, poster);

		String uuid = UUID.randomUUID().toString().substring(0, 8);
		ObjectMetadata metadata = new ObjectMetadata();
		metadata.setContentType(image.getContentType());
		metadata.setContentLength(buff.length);

		try {
			PutObjectRequest putReq = new PutObjectRequest("ibf2021-paf", uuid, 
					image.getInputStream(), metadata);
			putReq.setCannedAcl(CannedAccessControlList.PublicRead);
			s3.putObject(putReq);
			result = Json.createObjectBuilder()
				.add("objId", uuid)
				.build();
			return ResponseEntity.ok(result.toString());
		} catch (IOException ex) {
			ex.printStackTrace();
			result = Json.createObjectBuilder()
				.add("error", ex.getMessage())
				.build();
			return ResponseEntity.status(500).body(result.toString());
		}

    }
    
}
