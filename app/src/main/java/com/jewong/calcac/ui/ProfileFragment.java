package com.jewong.calcac.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavBackStackEntry;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.jewong.calcac.R;
import com.jewong.calcac.common.BaseFragment;
import com.jewong.calcac.data.stringdef.Gender;
import com.jewong.calcac.data.stringdef.SystemOfMeasurement;
import com.jewong.calcac.databinding.FragmentProfileBinding;

import java.util.Arrays;
import java.util.List;

public class ProfileFragment extends BaseFragment<FragmentProfileBinding> {

    private ProfileViewModel mProfileViewModel;

    final static List<String> DIET_LIST = Arrays.asList(SystemOfMeasurement.METRIC, SystemOfMeasurement.IMPERIAL);
    final static List<String> WEIGHT_GOAL_LIST = Arrays.asList(Gender.MALE, Gender.FEMALE);

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        NavController navController = NavHostFragment.findNavController(this);
        NavBackStackEntry backStackEntry = navController.getBackStackEntry(R.id.navigation);
        mProfileViewModel = new ViewModelProvider(backStackEntry).get(ProfileViewModel.class);
        View root = inflater.inflate(R.layout.fragment_profile, container, false);
        FragmentProfileBinding binding = FragmentProfileBinding.bind(root);
        binding.setLifecycleOwner(this);
        binding.setMProfileFormViewModel(mProfileViewModel);
        mDataBinding = binding;
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();
    }

    private void initViews() {
        mDataBinding.dietInput.setItems(DIET_LIST, getContext());
        mDataBinding.weightGoalInput.setItems(WEIGHT_GOAL_LIST, getContext());
    }

}
