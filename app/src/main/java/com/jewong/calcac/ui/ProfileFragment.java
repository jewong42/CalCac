package com.jewong.calcac.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.ViewModelProvider;


import com.jewong.calcac.R;
import com.jewong.calcac.common.BaseFragment;
import com.jewong.calcac.data.stringdef.Gender;
import com.jewong.calcac.data.stringdef.SystemOfMeasurement;
import com.jewong.calcac.databinding.FragmentProfileBinding;

import java.util.Arrays;
import java.util.List;

import static androidx.navigation.fragment.NavHostFragment.findNavController;

public class ProfileFragment extends BaseFragment<FragmentProfileBinding> {

    private ProfileViewModel mProfileViewModel;

    final static List<String> DIET_LIST = Arrays.asList(SystemOfMeasurement.METRIC, SystemOfMeasurement.IMPERIAL);
    final static List<String> WEIGHT_GOAL_LIST = Arrays.asList(Gender.MALE, Gender.FEMALE);

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mProfileViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);
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
        observeUser();
    }

    private void initViews() {
        mDataBinding.dietInput.setItems(DIET_LIST, getContext());
        mDataBinding.weightGoalInput.setItems(WEIGHT_GOAL_LIST, getContext());
        mDataBinding.toolbar.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()) {
                case R.id.item_reset_account:
                    showResetDialog();
                    break;
                case R.id.item_backup_account:
                    backupProfile();
                    break;
            }
            return true;
        });
    }

    private void backupProfile() {
    }

    @SuppressWarnings("CodeBlock2Expr")
    private void showResetDialog() {
        if (getActivity() == null) return;
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.reset_profile)
                .setMessage(R.string.reset_confirmation)
                .setPositiveButton(R.string.yes, (dialog, id) -> {
                    dialog.dismiss();
                    mDataBinding.progressBar.setVisibility(View.VISIBLE);
                    mProfileViewModel.deleteProfile();
                })
                .setNegativeButton(R.string.no, (dialog, id) -> {
                    dialog.dismiss();
                })
                .create()
                .show();
    }

    private void observeUser() {
        mProfileViewModel.mUser.observe(getViewLifecycleOwner(), user -> {
            if (user == null) {
                findNavController(this).navigate(R.id.action_profile_to_form);
            } else {
                mDataBinding.progressBar.setVisibility(View.GONE);
            }
        });
    }

}
