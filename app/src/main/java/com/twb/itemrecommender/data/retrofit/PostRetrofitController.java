package com.twb.itemrecommender.data.retrofit;

import com.twb.itemrecommender.data.domain.Post;
import com.twb.itemrecommender.data.helper.DataWrapper;
import com.twb.itemrecommender.data.retrofit.api.ErrorParser;
import com.twb.itemrecommender.data.retrofit.dao.PostService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Response;

public class PostRetrofitController {

    private final PostService postService;

    public PostRetrofitController(PostService postService) {
        this.postService = postService;
    }

    public DataWrapper<List<Post>> getLiveFeedPosts(Map<String, Integer> paginationParams) {
        Call<List<Post>> postCall = postService.getLiveFeedPosts(paginationParams);
        List<Post> postList = new ArrayList<>();
        DataWrapper<List<Post>> listDataWrapper = new DataWrapper<>();
        try {
            Response<List<Post>> postResponse = postCall.execute();
            if (postResponse.isSuccessful()) {
                List<Post> responseBody = postResponse.body();
                if (responseBody != null) {
                    postList.addAll(responseBody);
                    listDataWrapper.setData(postList);
                    return listDataWrapper;
                }
            }
            ErrorParser.parse(postResponse);
        } catch (Throwable e) {
            e.printStackTrace();
            listDataWrapper.setError(e);
        }
        return listDataWrapper;
    }
}
