package sg.edu.nus.iss.paf27_boardgame.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bson.Document;
import org.springframework.stereotype.Service;

import sg.edu.nus.iss.paf27_boardgame.model.BoardGame;
import sg.edu.nus.iss.paf27_boardgame.model.Comment;
import sg.edu.nus.iss.paf27_boardgame.repository.BoardGameRepository;
import sg.edu.nus.iss.paf27_boardgame.utility.Utility;

@Service
public class BoardGameService {

    private BoardGameRepository boardGameRepo;

    private Utility util;

    public BoardGameService(BoardGameRepository boardGameRepo, Utility util) {
        this.boardGameRepo = boardGameRepo;
        this.util = util;
    }
    
    public BoardGame findBoardGameById(int gid) {
        
        List<Document> docs = boardGameRepo.findBoardGameById(gid);

        if (docs.isEmpty())
            throw new IllegalArgumentException("invalid gid");

        BoardGame boardGame = util.documentToBoardGame(docs.get(0));

        return boardGame;
    }

    public BoardGame findBoardGame(String boardGameName) {
        
        List<Document> docs = boardGameRepo.findBoardGame(boardGameName);

        if (docs.isEmpty())
            throw new IllegalArgumentException("board game does not exists");

        BoardGame boardGame = util.documentToBoardGame(docs.get(0));

        System.out.println("\n\n" + "Before return to controller >>> " + boardGame + "\n\n");

        return boardGame;
    }

    public List<Comment> findComments(int gid) {

        List<Document> docs = boardGameRepo.findComments(gid);

        List<Comment> comments = new ArrayList<>();

        if (docs.isEmpty())
            return comments;        
        
        for (Document doc : docs) {
            Comment comment = util.documentToComment(doc);
            comments.add(comment);
        }

        return comments;
    }

    public void insertComment(Comment comment) {

        comment.setC_id(UUID.randomUUID().toString().substring(0, 8));
        Document document = util.commentToDocument(comment);
        boardGameRepo.insertComment(document);
    }
}
