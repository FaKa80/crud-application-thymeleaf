package com.example.springboot_with_thymeleaf;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PostController {
    @Autowired
    private PostService service;

    @RequestMapping("/")
    public String viewHomePage(Model model) {
        List<Post> listPosts = service.listAll();
        model.addAttribute("listPosts", listPosts);

        return "index";
    }

    @RequestMapping("/new")
    public String showNewProductPage(Model model) {
        Post post = new Post();
        model.addAttribute("post", post);

        return "new_post";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String savePost(@ModelAttribute("post") Post post) {
        service.save(post);

        return "redirect:/";
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView showEditPostPage(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("edit_post");
        Post post = service.get(id);
        mav.addObject("post", post);

        return mav;
    }

    @RequestMapping("/delete/{id}")
    public String deletePost(@PathVariable(name = "id") int id) {
        service.delete(id);
        return "redirect:/";
    }
}
