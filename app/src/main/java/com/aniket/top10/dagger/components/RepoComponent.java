package com.aniket.top10.dagger.components;

import com.aniket.top10.base.ITopNewsRepo;
import com.aniket.top10.news.repo.TopNewsRepo;

import dagger.Component;

@Component(dependencies = MyApplicationComponent.class)
public interface RepoComponent {
    void injectBaseRepo(ITopNewsRepo baseRepo);
    void injectTopNewsRepo(TopNewsRepo topNewsRepo);
}
