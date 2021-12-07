package com.sample;

import com.sample.model.Tasker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@DataJpaTest
public class TaskerRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private TaskerRepository repository;

    @BeforeEach
    void initUseCase() {
        entityManager.persist(new Tasker("Learn React", LocalDate.now()));
        entityManager.persist(new Tasker("Profit", LocalDate.now()));
    }

    @Test
    public void testFindById() {
        Optional<Tasker> tasker = repository.findById("Learn React");

        Assert.notNull(tasker.get());
        Assert.isTrue("Learn React".equals(tasker.get().getName()));
    }

    @Test
    public void testFindAll() {
        List<Tasker> taskers = repository.findAll();

        Assert.notEmpty(taskers);
        Assert.isTrue(taskers.size() == 2);
    }
}
