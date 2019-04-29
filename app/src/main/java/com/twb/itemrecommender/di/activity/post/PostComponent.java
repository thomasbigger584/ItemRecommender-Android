package com.twb.itemrecommender.di.activity.post;


import dagger.Component;

@PostScope
@Component(modules = {PostModule.class}, dependencies = {})
public interface PostComponent {

}
