package com.example.housebuddy2.ui.wishlist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.housebuddy2.databinding.FragmentWishlistBinding;

public class WishlistFragment extends Fragment {

    private WishlistViewModel wishlistViewModel;
    private FragmentWishlistBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        wishlistViewModel =
                new ViewModelProvider(this).get(WishlistViewModel.class);

        binding = FragmentWishlistBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textWishlist;
        wishlistViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}