package ru.netology.repository;

import ru.netology.exception.NotFoundException;
import ru.netology.model.Post;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

// Stub
public class PostRepository {
    private ConcurrentMap<Long, Post> map = new ConcurrentHashMap<>();
    private AtomicLong longID = new AtomicLong(0);

    public List<Post> all() {
        return new ArrayList<>(map.values());
    }

    public Optional<Post> getById(long id) {
        return Optional.of(map.get(id));
    }

    public Post save(Post post) {
     if(post.getId()!= 0 && map.containsKey(post.getId())){
         map.replace(post.getId(), post);
        }else if (post.getId() == 0){
         long newID = longID.incrementAndGet();
         post.setId(newID);
         map.put(newID,post);
        }
     return post;
    }

    public void removeById(long id) {
        if(map.containsKey(id)){
            map.remove(id);
        }else {
            System.out.println("Нет такого ID");
        }
    }
}
