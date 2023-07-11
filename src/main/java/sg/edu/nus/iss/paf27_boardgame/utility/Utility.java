package sg.edu.nus.iss.paf27_boardgame.utility;

import org.bson.Document;
import org.springframework.stereotype.Component;

import sg.edu.nus.iss.paf27_boardgame.model.BoardGame;
import sg.edu.nus.iss.paf27_boardgame.model.Comment;

@Component
public class Utility {
    
    public BoardGame documentToBoardGame(Document document) {

        return new BoardGame(
                document.getInteger("gid"), 
                document.getString("name"), 
                document.getInteger("year"), 
                document.getInteger("ranking"), 
                document.getInteger("users_rated"), 
                document.getString("url"), 
                document.getString("image"));
    }

    public Comment documentToComment(Document document) {

        return new Comment(
                document.getString("c_id"),
                document.getString("user"),
                document.getInteger("rating"),
                document.getString("c_text"),
                document.getInteger("gid")
        );
    }

    public Document commentToDocument(Comment comment) {
        
            Document document = new Document();

            document.put("c_id", comment.getC_id());
            document.put("user", comment.getUser());
            document.put("rating", comment.getRating());
            document.put("c_text", comment.getC_text());
            document.put("gid", comment.getGid());

            return document;
    }
}
