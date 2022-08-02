package spring.dao;

import org.springframework.jdbc.core.RowMapper;
import spring.models.Post;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PostMapper implements RowMapper<Post> {
    @Override
    public Post mapRow(ResultSet resultSet, int i) throws SQLException {
        Post post = new Post();

        post.setPostText(resultSet.getString("post"));
        post.setId(resultSet.getInt("id"));
        post.setTime(resultSet.getTimestamp("timestamp"));

        return post;
    }
}
