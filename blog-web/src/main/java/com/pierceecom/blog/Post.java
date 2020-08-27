package com.pierceecom.blog;
/***
 *   Post class has the details of id, Title and Content.
 */
/
public class Post {

    private String id;
    private String title;
    private String content;

    public Post() {
    }

    public Post(String id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    /***
     * @return the postId
     */
    /
    public String getId() {
        return this.id;
    }

    /***
     * @set/momdify the postId
     */
    public void setId(String id) {
        this.id = id;
    }

    /***
     * @return the post title.
     */
    /
    public String getTitle() {
        return this.title;
    }

    /***
     * @set/modify the post title.
     */
    /
    public void setTitle(String title) {
        this.title = title;
    }

    /***
     * @return the post content
     */
    /
    public String getContent() {
        return this.content;
    }

    /***
     * @set/modify the post content.
     */
    /
    public void setContent(String content) {
        this.content = content;
    }
}