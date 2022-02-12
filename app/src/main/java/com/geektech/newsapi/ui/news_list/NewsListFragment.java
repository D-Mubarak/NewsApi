package com.geektech.newsapi.ui.news_list;

import android.util.Log;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.geektech.newsapi.MainActivity;
import com.geektech.newsapi.base.BaseFragment;
import com.geektech.newsapi.common.OnSearchListenner;
import com.geektech.newsapi.common.Resource;
import com.geektech.newsapi.common.Status;
import com.geektech.newsapi.data.models.com.example.MainResponse;
import com.geektech.newsapi.databinding.FragmentNewsListBinding;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import dagger.hilt.android.AndroidEntryPoint;


@AndroidEntryPoint
public class NewsListFragment extends BaseFragment<FragmentNewsListBinding> implements OnSearchListenner {

    private NewsListViewModel viewModel;
    private NewsAdapter adapter;
    private MainActivity activity;

    @Override
    protected FragmentNewsListBinding bind() {
        return FragmentNewsListBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void initValues() {
        activity = (MainActivity) requireActivity();
adapter = new NewsAdapter();
viewModel = new ViewModelProvider(requireActivity()).get(NewsListViewModel.class);

    }

    @Override
    protected void setupObservers() {
viewModel.newsLiveData.observe(getViewLifecycleOwner(), new Observer<Resource<MainResponse>>() {
    @Override
    public void onChanged(Resource<MainResponse> resource) {
        if (resource.status == Status.SUCCESS){
            adapter.setNewslist(resource.data.getArticles());

        }else{
            Snackbar.make(viewBinding.getRoot(), resource.msg, BaseTransientBottomBar.LENGTH_LONG);

        }
    }
});
    }

    @Override
    protected void setupListeners() {
activity.setListenner(this);
    }

    @Override
    protected void setupViews() {
viewBinding.recycler.setAdapter(adapter);
    }

    @Override
    protected void callRequests() {
    viewModel.getTopNews();
    }

    @Override
    public void onSearchSubmit(String s) {
        viewModel.getNewsByKeyWord(s);

    }

    @Override
    public void onSearchText(String s) {
        if (s.isEmpty()){
            viewModel.getTopNews();
        }
        viewModel.getNewsByKeyWord(s);
    }
}
