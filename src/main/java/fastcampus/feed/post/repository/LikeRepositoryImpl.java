package fastcampus.feed.post.repository;

import fastcampus.feed.post.domain.Post;
import fastcampus.feed.post.domain.comment.Comment;
import fastcampus.feed.post.repository.entity.comment.CommentEntity;
import fastcampus.feed.post.repository.entity.like.LikeEntity;
import fastcampus.feed.post.repository.entity.like.LikeIdEntity;
import fastcampus.feed.post.repository.entity.like.LikeTarget;
import fastcampus.feed.post.repository.entity.post.PostEntity;
import fastcampus.feed.post.repository.interfaces.LikeRepository;
import fastcampus.feed.post.repository.jpa.JpaCommentRepository;
import fastcampus.feed.post.repository.jpa.JpaLikeRepository;
import fastcampus.feed.post.repository.jpa.JpaPostRepository;
import fastcampus.feed.user.domain.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class LikeRepositoryImpl implements LikeRepository {

    @PersistenceContext
    private EntityManager em; //likeEntity는 항상 select 쿼리를 보낸다. 이런 무의미한 조회 쿼리를 줄이기 위해 바로 persist를 적용

    private final JpaLikeRepository jpaLikeRepository;
    private final JpaPostRepository jpaPostRepository;
    private final JpaCommentRepository jpaCommentRepository;

    @Override
    public boolean isAlreadyLiked(User user, Post post) {
        LikeIdEntity likeIdEntity = new LikeIdEntity(user.getId(), post.getId(), LikeTarget.POST);
        return jpaLikeRepository.existsById(likeIdEntity);
    }

    @Override
    public void save(User user, Post post) {
        LikeEntity likeEntity = new LikeEntity(post, user);
        em.persist(likeEntity);
        //jpaLikeRepository.save(likeEntity); //이렇게 하면 select 쿼리가 먼저 나가서 무의미한 조회 쿼리가 발생
        jpaPostRepository.save(new PostEntity(post));
    }

    @Override
    public void delete(User user, Post post) {
        LikeIdEntity likeIdEntity = new LikeIdEntity(user.getId(), post.getId(), LikeTarget.POST);
        jpaPostRepository.save(new PostEntity(post));
        jpaLikeRepository.deleteById(likeIdEntity);
    }

    @Override
    public boolean isAlreadyLiked(User user, Comment comment) {
        LikeIdEntity likeIdEntity = new LikeIdEntity(user.getId(), comment.getId(), LikeTarget.COMMENT);
        return jpaLikeRepository.existsById(likeIdEntity);
    }

    @Override
    public void save(User user, Comment comment) {
        LikeEntity likeEntity = new LikeEntity(comment, user);
        em.persist(likeEntity);
        jpaCommentRepository.save(new CommentEntity(comment));
    }

    @Override
    public void delete(User user, Comment comment) {
        LikeIdEntity likeIdEntity = new LikeIdEntity(user.getId(), comment.getId(), LikeTarget.COMMENT);
        jpaCommentRepository.save(new CommentEntity(comment));
        jpaLikeRepository.deleteById(likeIdEntity);
    }
}
