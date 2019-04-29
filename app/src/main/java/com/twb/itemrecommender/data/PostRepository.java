package com.twb.itemrecommender.data;

import com.twb.itemrecommender.data.domain.Post;
import com.twb.itemrecommender.data.helper.DataWrapper;
import com.twb.itemrecommender.data.retrofit.PostRetrofitController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PostRepository {

    private final PostRetrofitController postRetrofitController;

    public PostRepository(PostRetrofitController postRetrofitController) {
        this.postRetrofitController = postRetrofitController;
    }

    public DataWrapper<List<Post>> getLiveFeedPostsNetwork(int page, int count, long organisationId) {
        Map<String, Integer> paginationParams = new HashMap<>();
        paginationParams.put("page", page);
        paginationParams.put("size", count);
        DataWrapper<List<Post>> listDataWrapper =
                postRetrofitController.getLiveFeedPosts(paginationParams);
//        if (listDataWrapper.isPresent()) {
//            List<Post> postList = listDataWrapper.getData();
//            postRoomController.save(organisationId, postList);
//        }
        return listDataWrapper;
    }

}
