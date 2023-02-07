package ru.netology.repository;

import ru.netology.exception.NotFoundException;
import ru.netology.model.Post;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

// Stub
public class PostRepository {
    private List<Post> list = new CopyOnWriteArrayList<>();
    private AtomicLong longID = new AtomicLong(1);

    public List<Post> all() {
        return list;
    }

    public Optional<Post> getById(long id) {
        return list.stream()
                .filter(post -> id == post.getId())
                .findAny();
    }

    public Post save(Post post) {
        if (post.getId() == 0) {
            Post newPost = new Post(longID.get(), post.getContent());
            longID.incrementAndGet();
            list.add(newPost);
            return newPost;
        } else {
            if (list.size() == 0) {
                try {
                    throw new NotFoundException();
                } catch (NotFoundException e) {
                    e.printStackTrace();
                }
            }
            for (Post post1 : list) {
                if (post1.getId() == post.getId()) {
                    post1.setContent(post1.getContent());
                    return post1;
                } else {
                    try {
                        throw new NotFoundException();
                    } catch (NotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return null;
    }

    public void removeById(long id) {
        list.removeIf(post -> post.getId() == id);
    }
}
