package com.trendyol.bootcamp.spotifyapishows.repository;

import com.querydsl.core.types.Predicate;
import com.trendyol.bootcamp.spotifyapishows.contract.response.ShowResponse;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.domain.Pageable;

import javax.inject.Singleton;
import java.util.List;

@Singleton      //aynı anda değiştirmeye çalışma sorunları
public interface ShowRepository extends PagingAndSortingRepository<ShowResponse, Integer>, QueryDslPredicateExecutor<ShowResponse> {
    List<ShowResponse> findAll(Predicate predicate, Pageable paging);
}
