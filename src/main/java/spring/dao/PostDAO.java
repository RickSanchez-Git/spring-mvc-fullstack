package spring.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import spring.models.Post;

import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class PostDAO {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public PostDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<Post> getPosts() {
        return jdbcTemplate.query("SELECT * FROM posts", new PostMapper());
    }

    public Post getPostById(int id) {
        return jdbcTemplate.query("SELECT * FROM posts WHERE id=?", new Object[]{id}, new PostMapper())
                .stream().findAny().orElse(null);
    }

    public void savePost(Post post) {
        jdbcTemplate.update("INSERT INTO posts VALUES(?, ?)",
                post.getPostText(), new Date());
    }

    public void updatePost(int id, Post updatedPost) {
        jdbcTemplate.update("UPDATE posts SET post=?, timestamp =? WHERE id=?",
                updatedPost.getPostText(), new Date(), id);
    }

    public void deletePost(int id) {
        jdbcTemplate.update("DELETE FROM posts WHERE id=?", id);
    }
}
