package ibf2021.paf.day34.controllers;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import ibf2021.paf.day34.models.Post;
import ibf2021.paf.day34.repositories.PostRepository;

@Controller
@RequestMapping(path="/post")
public class PostController {

    @Autowired
    private PostRepository postRepo;

    @GetMapping(path="/{postId}")
    public String getPostById(@PathVariable Integer postId, Model model) {

        Optional<Post> opt = postRepo.getPostById(postId);
        // We are not handling 404

        model.addAttribute("post", opt.get());

        return "post";
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String postPost(
            @RequestParam MultipartFile image,
            @RequestPart String comment, @RequestPart String poster, Model model) {

        String imageName = image.getOriginalFilename();
        long imageSize = image.getSize();
        String imageType = image.getContentType();
        byte[] buff = new byte[0];

        try {
            buff = image.getBytes();
        } catch (IOException ex) { ex.printStackTrace(); }

        System.out.printf("Image: fileName=%s, size=%d (%d), type=%s\n"
                , imageName, imageSize, buff.length, imageType);
        System.out.printf("Others: comment=%s, poster=%s\n", comment, poster);

        Post post = new Post();
        post.setComment(comment);
        post.setPoster(poster);
        post.setImageType(imageType);
        post.setImage(buff);

        Integer updateCount = postRepo.insertPost(post);
        model.addAttribute("updateCount", updateCount);

        return "result";
    }
}
