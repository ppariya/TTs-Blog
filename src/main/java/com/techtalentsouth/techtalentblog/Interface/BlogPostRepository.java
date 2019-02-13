package com.techtalentsouth.techtalentblog.Interface;
import com.techtalentsouth.techtalentblog.BlogPost;
import org.springframework.data.repository.CrudRepository;

public interface BlogPostRepository extends CrudRepository<BlogPost, Long>{

}