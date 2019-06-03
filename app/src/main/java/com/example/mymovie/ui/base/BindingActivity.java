package com.example.mymovie.ui.base;

import android.os.Bundle;

import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

public abstract class BindingActivity<B extends ViewDataBinding, VM extends BaseViewModel>
        extends BaseActivity {

    private B binding;
    private VM viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind();
    }

    public void bind() {
        binding = DataBindingUtil.setContentView(this, getLayoutResources());
        this.viewModel = viewModel == null ? onCreate() : viewModel;
        binding.setVariable(getVariable(), viewModel);
    }

    public B getBinding() {
        return binding;
    }

    public abstract VM onCreate();

    public VM getViewModel() {
        return viewModel;
    }

    /**
     * Override for set binding variable
     *
     * @return variable id
     */
    public abstract @IdRes
    int getVariable();

    /**
     * Override for set layout resource
     *
     * @return layout resource id
     */
    public abstract @LayoutRes
    int getLayoutResources();
}