package sg.edu.nus.iss.paf27_boardgame.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import sg.edu.nus.iss.paf27_boardgame.model.BoardGame;
import sg.edu.nus.iss.paf27_boardgame.model.Comment;
import sg.edu.nus.iss.paf27_boardgame.service.BoardGameService;

@Controller
@RequestMapping(path = "/api")
public class BoardGameController {

    private BoardGameService boardGameService;

    public BoardGameController(BoardGameService boardGameService) {
        this.boardGameService = boardGameService;
    }
    
    @GetMapping(path = "/form")
    public String getSearchForm(HttpSession session) {
        session.invalidate();
        return "boardGameSearchForm";
    }

    @GetMapping(path = "/search")
    public String getBoardGame(@RequestParam String boardGameName, Model model, HttpSession session) {
        
        System.out.printf("\n\n" + "controller >>> " + boardGameName + "\n\n");

        BoardGame boardGame = boardGameService.findBoardGame(boardGameName);

        System.out.println("\n\n" + "Before return to view >>> " + boardGame + "\n\n");

        List<Comment> comments = boardGameService.findComments(boardGame.gid());

        System.out.println("\n\n" + "List of comments >>>" + comments + "\n\n");

        session.setAttribute("gid", boardGame.gid());
        model.addAttribute("boardgame", boardGame); 
        model.addAttribute("comments", comments);
        model.addAttribute("comment", new Comment());
        return "details";
    }

    @PostMapping(path = "/comment")
    public String saveComment(Comment comment, Model model, HttpSession session) {
        
        comment.setGid((Integer) session.getAttribute("gid"));
        boardGameService.insertComment(comment);

        model.addAttribute("boardgame", boardGameService.findBoardGameById(comment.getGid())); 
        model.addAttribute("comments", boardGameService.findComments(comment.getGid()));
        model.addAttribute("comment", new Comment());
        return "details";
    }
}
