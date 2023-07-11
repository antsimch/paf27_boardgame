package sg.edu.nus.iss.paf27_boardgame.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    
    private String c_id;

    private String user;

    private int rating;

    private String c_text;

    private int gid;
}
