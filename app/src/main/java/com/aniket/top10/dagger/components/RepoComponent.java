package com.aniket.top10.dagger.components;

import com.aniket.top10.base.BaseRepo;
import com.aniket.top10.news.repo.TopNewsRepo;

import dagger.Component;

@Component(dependencies = MyApplicationComponent.class)
public interface RepoComponent {
    void injectBaseRepo(BaseRepo baseRepo);
    void injectTopNewsRepo(TopNewsRepo topNewsRepo);
}
