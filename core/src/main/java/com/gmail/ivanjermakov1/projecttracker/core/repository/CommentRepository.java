package com.gmail.ivanjermakov1.projecttracker.core.repository;

import com.gmail.ivanjermakov1.projecttracker.core.entity.Comment;
import com.gmail.ivanjermakov1.projecttracker.core.entity.Task;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommentRepository extends CrudRepository<Comment, Long> {

	List<Comment> findAllByTask(Task task);

}
