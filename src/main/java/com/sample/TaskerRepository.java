package com.sample;

import com.sample.model.Tasker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskerRepository extends JpaRepository<Tasker, String> {
}
