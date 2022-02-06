package com.geektech.newsapi.ui.news_list;

import android.util.Log;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.geektech.newsapi.base.BaseFragment;
import com.geektech.newsapi.common.Resource;
import com.geektech.newsapi.data.models.com.example.MainResponse;
import com.geektech.newsapi.databinding.FragmentNewsListBinding;

import dagger.hilt.android.AndroidEntryPoint;


@AndroidEntryPoint
public class NewsListFragment extends BaseFragment<FragmentNewsListBinding> {

    private NewsListViewModel viewModel;

    @Override
    protected FragmentNewsListBinding bind() {
        return FragmentNewsListBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void initValues() {

viewModel = new ViewModelProvider(requireActivity()).get(NewsListViewModel.class);

    }

    @Override
    protected void setupObservers() {
viewModel.newsLiveData.observe(getViewLifecycleOwner(), new Observer<Resource<MainResponse>>() {
    @Override
    public void onChanged(Resource<MainResponse> mainResponseResource) {
        Log.d("TAG", "onChanged: ");
    }
});
    }

    @Override
    protected void setupListeners() {

    }

    @Override
    protected void setupViews() {

    }

    @Override
    protected void callRequests() {
    viewModel.getTopNews();
    }
}
