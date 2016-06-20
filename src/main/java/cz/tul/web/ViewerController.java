package cz.tul.web;

import cz.tul.domain.Image;
import cz.tul.service.CommentService;
import cz.tul.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(path = "/mvc")
public class ViewerController {

    @Autowired
    private ImageService imageService;

    @Autowired
    private CommentService commentService;

    @RequestMapping("/viewer")
    public String loadImage(@RequestParam(name = "page", required = false, defaultValue = "0") int pageId, Model model) {
        final int pageSize = 1;
        final Sort pageSort = new Sort("creation");

        if (pageId < 0) {
            pageId = 0;
        }
        Page<Image> page = imageService.getPage(new PageRequest(pageId, pageSize, pageSort));
        if (pageId >= page.getTotalPages()) {
            pageId = page.getTotalPages() - 1;
            page = imageService.getPage(new PageRequest(pageId, pageSize, pageSort));
        }

        model.addAttribute("page", page);
        model.addAttribute("image", page.getContent().get(0));
        return "viewer";
    }

    @RequestMapping("/likeImage")
    public String likeImage(@RequestParam("image") String imageId, @RequestParam(name = "page", required = false, defaultValue = "0") int pageId) {
        imageService.like(imageId);
        return "redirect:./viewer?page=" + pageId;
    }

    @RequestMapping("/dislikeImage")
    public String dislikeImage(@RequestParam("image") String imageId, @RequestParam(name = "page", required = false, defaultValue = "0") int pageId) {
        imageService.dislike(imageId);
        return "redirect:./viewer?page=" + pageId;
    }

    @RequestMapping("/likeComment")
    public String likeComment(@RequestParam("comment") String commentId, @RequestParam(name = "page", required = false, defaultValue = "0") int pageId) {
        commentService.like(commentId);
        return "redirect:./viewer?page=" + pageId;
    }

    @RequestMapping("/dislikeComment")
    public String dislikeComment(@RequestParam("comment") String commentId, @RequestParam(name = "page", required = false, defaultValue = "0") int pageId) {
        commentService.dislike(commentId);
        return "redirect:./viewer?page=" + pageId;
    }

}
