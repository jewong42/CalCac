package com.jewong.calcac.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.ViewModelProvider;

import com.jewong.calcac.R;
import com.jewong.calcac.common.BaseFragment;
import com.jewong.calcac.data.stringdef.Diet;
import com.jewong.calcac.data.stringdef.Goal;
import com.jewong.calcac.databinding.FragmentProfileBinding;

import java.util.Arrays;
import java.util.List;

import static androidx.navigation.fragment.NavHostFragment.findNavController;

public class ProfileFragment extends BaseFragment<FragmentProfileBinding> {

    private ProfileViewModel mProfileViewModel;
    private View.OnFocusChangeListener mHideSoftKeyBoardFL = (v, hasFocus) -> {
        if (!hasFocus) {
            hideSoftKeyBoard(v);
            mProfileViewModel.updateWeight();
        }
    };
    private TextView.OnEditorActionListener mClearFocusEAL = (v, actionId, event) -> {
        if (actionId == EditorInfo.IME_ACTION_DONE) mDataBinding.weightInput.clearFocus();
        return false;
    };

    final static List<String> DIET_LIST = Arrays.asList(Diet.TRADITIONAL, Diet.PALEO, Diet.LOW_CARB, Diet.KETO);
    final static List<String> WEIGHT_GOAL_LIST = Arrays.asList(Goal.WEIGHT_LOSS, Goal.WEIGHT_GAIN, Goal.MAINTENANCE);

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
        observeUser();
        initViews();
        initObservers();
    }

    private void initObservers() {
        mProfileViewModel.mDietInput.observe(getViewLifecycleOwner(), s -> mProfileViewModel.updateDiet());
        mProfileViewModel.mGoalInput.observe(getViewLifecycleOwner(), s -> mProfileViewModel.updateGoal());
    }

    private void initViews() {
        mDataBinding.dietInput.setItems(DIET_LIST, getContext());
        mDataBinding.weightGoalInput.setItems(WEIGHT_GOAL_LIST, getContext());
        mDataBinding.weightInput.setOnFocusChangeListener(mHideSoftKeyBoardFL);
        mDataBinding.weightInput.setOnEditorActionListener(mClearFocusEAL);
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

    private void observeUser() {
        mProfileViewModel.mUser.observe(getViewLifecycleOwner(), user -> {
            if (user == null) {
                findNavController(this).navigate(R.id.action_profile_to_form);
            } else {
                mDataBinding.progressBar.setVisibility(View.GONE);
                mProfileViewModel.updateData();
            }
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

}
