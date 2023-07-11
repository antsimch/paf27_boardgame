package sg.edu.nus.iss.paf27_boardgame.model;

public record BoardGame(
        int gid, 
        String name, 
        int year, 
        int ranking, 
        int users_rated, 
        String url, 
        String image) {
}
