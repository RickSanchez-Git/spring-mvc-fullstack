package spring.controllers;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import spring.dao.PostDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import spring.models.Post;

import javax.validation.Valid;

@Controller
@RequestMapping("/posts")
public class PostsController {
    private PostDAO postDAO;

    @Autowired
    public PostsController(PostDAO postDAO) {
        this.postDAO = postDAO;
    }

    //Getting posts
    @GetMapping()
    public String getPosts(Model model) {
        model.addAttribute("posts", postDAO.getPosts());
        return "posts/get-posts";
    }

    @GetMapping("/{id}")
    public String getPostById(@PathVariable("id") int id, Model model) {
        model.addAttribute("post", postDAO.getPostById(id));
        return "posts/get-post";
    }

    //Creation new post
    @GetMapping("/new")
    public String newPost(@ModelAttribute("post") Post post) {
        return "posts/new";
    }

    @PostMapping()
    public String createPost(@ModelAttribute("post") @Valid Post post,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "posts/new";
        }
        postDAO.savePost(post);
        return "redirect:/posts";
    }

    //Editing posts
    @GetMapping("/{id}/edit")
    public String editPost(@PathVariable("id") int id, Model model) {
        model.addAttribute("post", postDAO.getPostById(id));
        return "posts/edit";
    }

    @PatchMapping("/{id}")
    public String updatePost(@ModelAttribute("post") @Valid Post post,
                             BindingResult bindingResult,
                             @PathVariable("id") int id) {
        if (bindingResult.hasErrors()) {
            return "posts/edit";
        }
        postDAO.updatePost(id, post);
        return "redirect:/posts";
    }

    //Deleting posts
    @DeleteMapping("/{id}")
    public String deletePost(@PathVariable("id") int id) {
        postDAO.deletePost(id);
        return "redirect:/posts";
    }
}
