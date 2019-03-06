package com.techtalentsouth.techtalentblog.Controller;

import com.techtalentsouth.techtalentblog.BlogPost;
import com.techtalentsouth.techtalentblog.Interface.BlogPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class BlogPostController {

    @Autowired
    private BlogPostRepository blogPostRepository;

    @GetMapping(value="/")
    public String index(Model model) {
        model.addAttribute("posts", blogPostRepository.findAll());
        return "blogpost/index";
    }

    @PostMapping(value = "/")
    public String addNewBlogPost(BlogPost blogPost, Model model) {
        blogPostRepository.save(new BlogPost(blogPost.getTitle(), blogPost.getAuthor(), blogPost.getBlogEntry()));
        model.addAttribute("title", blogPost.getTitle());
        model.addAttribute("author", blogPost.getAuthor());
        model.addAttribute("blogEntry", blogPost.getBlogEntry());
        return "blogpost/result";
    }

    @PostMapping(value = "/blog_posts/new")
    public String create(BlogPost blogPost, Model model) {
        blogPostRepository.save(blogPost);
        model.addAttribute("title", blogPost.getTitle());
        model.addAttribute("author", blogPost.getAuthor());
        model.addAttribute("blogEntry", blogPost.getBlogEntry());
        return "blogpost/result";
    }

    @GetMapping(value = "/blog_posts/new")
    public String newBlog (Model model) {
        model.addAttribute("blogPost", new BlogPost());
        return "blogpost/new";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id, Model model) {
        BlogPost user = blogPostRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid blog Id:" + id));
        blogPostRepository.delete(user);
        model.addAttribute("posts", blogPostRepository.findAll());
        return "blogpost/index";
    }

}
