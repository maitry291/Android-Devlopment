// Generated by view binder compiler. Do not edit!
package com.example.chatbox.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.chatbox.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class BottomSheetLayoutBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final ImageView camera;

  @NonNull
  public final CardView cardView;

  @NonNull
  public final ImageView gallery;

  @NonNull
  public final TextView textView10;

  @NonNull
  public final TextView textView8;

  private BottomSheetLayoutBinding(@NonNull ConstraintLayout rootView, @NonNull ImageView camera,
      @NonNull CardView cardView, @NonNull ImageView gallery, @NonNull TextView textView10,
      @NonNull TextView textView8) {
    this.rootView = rootView;
    this.camera = camera;
    this.cardView = cardView;
    this.gallery = gallery;
    this.textView10 = textView10;
    this.textView8 = textView8;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static BottomSheetLayoutBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static BottomSheetLayoutBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.bottom_sheet_layout, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static BottomSheetLayoutBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.camera;
      ImageView camera = ViewBindings.findChildViewById(rootView, id);
      if (camera == null) {
        break missingId;
      }

      id = R.id.cardView;
      CardView cardView = ViewBindings.findChildViewById(rootView, id);
      if (cardView == null) {
        break missingId;
      }

      id = R.id.gallery;
      ImageView gallery = ViewBindings.findChildViewById(rootView, id);
      if (gallery == null) {
        break missingId;
      }

      id = R.id.textView10;
      TextView textView10 = ViewBindings.findChildViewById(rootView, id);
      if (textView10 == null) {
        break missingId;
      }

      id = R.id.textView8;
      TextView textView8 = ViewBindings.findChildViewById(rootView, id);
      if (textView8 == null) {
        break missingId;
      }

      return new BottomSheetLayoutBinding((ConstraintLayout) rootView, camera, cardView, gallery,
          textView10, textView8);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
