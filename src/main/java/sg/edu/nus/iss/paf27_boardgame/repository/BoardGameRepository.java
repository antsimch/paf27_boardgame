package sg.edu.nus.iss.paf27_boardgame.repository;

import java.util.List;

import org.bson.Document;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class BoardGameRepository {
    
    private MongoTemplate template;

    public static final String C_BOARDGAME = "boardgame";

    public static final String C_COMMENT = "comment";

    public static final String F_BOARD_GAME_NAME = "name";

    public BoardGameRepository(MongoTemplate template) {
        this.template = template;
    }

    public List<Document> findBoardGameById(int gid) {

        Criteria criteria = Criteria.where("gid").is(gid);

        Query query = Query.query(criteria);

        List<Document> docs = template.find(query, Document.class, C_BOARDGAME);

        return docs;
    }

    public List<Document> findBoardGame(String boardGameName) {

        System.out.printf("\n\n" + "repository >>> " + boardGameName + "\n\n");

        Criteria criteria = Criteria.where("name").regex(boardGameName, "i");

        Query query = Query.query(criteria);

        List<Document> docs = template.find(query, Document.class, C_BOARDGAME);

        System.out.println("\n\n" + "Before return to service >>> " + docs.get(0).toString() + "\n\n");

        return docs;
    }

    public List<Document> findComments(int gid) {

        Criteria criteria = Criteria.where("gid").is(gid);

        Query query = Query.query(criteria).with(Sort.by(Sort.Direction.DESC, "rating")).limit(5);

        return template.find(query, Document.class, C_COMMENT);
    }

    public void insertComment(Document document) {
        
        Document newDoc = template.insert(document, C_COMMENT);
        
        System.out.println("\n\n" + "Object Id for comment >>>" + newDoc.get("_id") + "\n\n");
    }
}
