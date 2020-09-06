package com.pierceecom.blog;

import java.util.List;
import java.util.ArrayList;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.DELETE;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;


@Path("posts")
public class PostResource {
    PostStorage ps = PostStorage.getInstance();

    @Context
    UriInfo uriInfo;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List getAllPosts() {
        return new ArrayList(PostStorage.getInstance().getAllPosts().values());
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response createPost(Post p) {
        if (p.getTitle() == null || p.getContent() == null || p.getId() == null || p.getId() == "" || p.getId() == " ") {
            return Response.status(Response.Status.METHOD_NOT_ALLOWED).entity("Invalid input").build();
        } else if (ps.getPostById(p.getId()) != null) {
            return Response.status(Response.Status.CONFLICT).entity("Post with id : "+p.getId()+ " already exist").build();
        }
        ps.createPost(p);
        return Response.status(Response.Status.CREATED).entity(p).header("Location", uriInfo.getAbsolutePath()+p.getId()).build();
    }

    @GET
    @Path("/{id}")
    public Response getPostById(@PathParam("id") String id) {
        Post p = ps.getPostById(id);
        if (p == null) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        return Response.status(Response.Status.OK).entity(p).build();
    }

    @PUT
    @Path("/{id}")
    public Response putPostById(@PathParam("id") String id, Post p) {
        if (p.getId() != null && !id.equals(p.getId())) {
            return Response.status(Response.Status.METHOD_NOT_ALLOWED).entity("Invalid input").build();
        } else if (p.getId() == "" || p.getId() == " " || p.getTitle() == null || p.getContent() == null) {
            return Response.status(Response.Status.METHOD_NOT_ALLOWED).entity("Invalid input").build();
        }
        Post p1 = ps.getPostById(id);
        if (p1 == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        // If id is not part of Post object, setting id to the Post object with the path param
        p1.setId(id);
        ps.createPost(p1);
        return Response.status(Response.Status.CREATED).entity(p1).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletePostById(@PathParam("id") String id) {
       if (ps.deletePostById(id) == null) {
           return Response.status(Response.Status.NOT_FOUND).entity("Post with id : "+id+" does not exist").build();
       }
        return Response.status(Response.Status.OK).entity("Post with id : "+id+" deleted successfully").build();
    }

}
