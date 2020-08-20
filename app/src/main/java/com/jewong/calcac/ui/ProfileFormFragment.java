package com.jewong.calcac.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.jewong.calcac.R;
import com.jewong.calcac.common.BaseFragment;
import com.jewong.calcac.data.stringdef.Gender;
import com.jewong.calcac.data.stringdef.SystemOfMeasurement;
import com.jewong.calcac.databinding.FragmentProfileFormBinding;

import java.util.Arrays;
import java.util.List;

import static androidx.navigation.fragment.NavHostFragment.findNavController;

public class ProfileFormFragment extends BaseFragment<FragmentProfileFormBinding> {

    private ProfileFormViewModel mProfileFormViewModel;

    final static List<String> GENDER_LIST = Arrays.asList(Gender.MALE, Gender.FEMALE);
    final static List<String> SYSTEM_LIST = Arrays.asList(SystemOfMeasurement.METRIC, SystemOfMeasurement.IMPERIAL);

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mProfileFormViewModel = new ViewModelProvider(this).get(ProfileFormViewModel.class);
        View root = inflater.inflate(R.layout.fragment_profile_form, container, false);
        FragmentProfileFormBinding binding = FragmentProfileFormBinding.bind(root);
        binding.setLifecycleOwner(this);
        binding.setMProfileFormViewModel(mProfileFormViewModel);
        mDataBinding = binding;
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();
        observeUser();
    }

    private void initViews() {
        mDataBinding.genderInput.setItems(GENDER_LIST, getContext());
        mDataBinding.systemInput.setItems(SYSTEM_LIST, getContext());
        mDataBinding.submitButton.setOnClickListener(v -> {
            hideSoftKeyBoard();
            mDataBinding.progressBar.setVisibility(View.VISIBLE);
            mProfileFormViewModel.saveUserAndClearCache();
        });
    }

    private void observeUser() {
        mProfileFormViewModel.mUser.observe(getViewLifecycleOwner(), user -> {
            if (user != null) {
                findNavController(this).navigate(R.id.action_form_to_profile);
            } else {
                mDataBinding.progressBar.setVisibility(View.GONE);
            }
        });
    }

}