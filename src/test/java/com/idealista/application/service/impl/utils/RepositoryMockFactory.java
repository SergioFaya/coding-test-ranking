package com.idealista.application.service.impl.utils;

import com.idealista.application.model.Ad;
import com.idealista.application.repository.AdRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public class RepositoryMockFactory {

    public static AdRepository createAdRepositoryMock(List<Ad> findAll){
        return new AdRepository() {
            @Override
            public List<Ad> findAll() { return findAll; }
            @Override
            public List<Ad> findAll(Sort sort) { return null; }
            @Override
            public Page<Ad> findAll(Pageable pageable) { return null; }
            @Override
            public List<Ad> findAllById(Iterable<Long> iterable) { return null; }
            @Override
            public long count() { return 0; }
            @Override
            public void deleteById(Long aLong) { }
            @Override
            public void delete(Ad ad) { }
            @Override
            public void deleteAll(Iterable<? extends Ad> iterable) { }
            @Override
            public void deleteAll() { }
            @Override
            public <S extends Ad> S save(S s) { return null; }
            @Override
            public <S extends Ad> List<S> saveAll(Iterable<S> iterable) { return null; }
            @Override
            public Optional<Ad> findById(Long aLong) { return Optional.empty(); }
            @Override
            public boolean existsById(Long aLong) { return false; }
            @Override
            public void flush() { }
            @Override
            public <S extends Ad> S saveAndFlush(S s) { return null; }
            @Override
            public void deleteInBatch(Iterable<Ad> iterable) { }
            @Override
            public void deleteAllInBatch() { }
            @Override
            public Ad getOne(Long aLong) { return null; }
            @Override
            public <S extends Ad> Optional<S> findOne(Example<S> example) { return Optional.empty(); }
            @Override
            public <S extends Ad> List<S> findAll(Example<S> example) { return null; }
            @Override
            public <S extends Ad> List<S> findAll(Example<S> example, Sort sort) { return null; }
            @Override
            public <S extends Ad> Page<S> findAll(Example<S> example, Pageable pageable) { return null; }
            @Override
            public <S extends Ad> long count(Example<S> example) { return 0; }
            @Override
            public <S extends Ad> boolean exists(Example<S> example) { return false; }
        };
    }

}
