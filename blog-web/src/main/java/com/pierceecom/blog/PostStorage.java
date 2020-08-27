package com.pierceecom.blog;

/***
 *  PostStorage adds, edits and deletes the post details.
 */

import java.util.Map;
import java.util.HashMap;

public class PostStorage {

    private static PostStorage postStorage = null;
    private HashMap<String, Post> posts = new HashMap<String, Post>();

    public static PostStorage getInstance() {
        if (postStorage == null) {
            postStorage = new PostStorage();
        }
        return postStorage;
    }

    /*** Get all available posts.
     * @return Map which has key as string and value as post object.
     */
    public Map getAllPosts() {
        return posts;
    }

    /***
     * Create new post.
     * @param post p
     */
    public void createPost(Post p) {
        posts.put(p.getId(), p);
    }

    /***
     * Get Post object
     * @param String id from post object
     * @return post id
     */
    public Post getPostById(String id) {
        return posts.get(id);
    }

    /***
     * Delete post object
     * @param String id from post object
     */
    public Post deletePostById(String id) {
        return posts.remove(id);
    }

}
