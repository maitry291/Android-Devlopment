// Generated by view binder compiler. Do not edit!
package com.example.chatbox.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.chatbox.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import de.hdodenhof.circleimageview.CircleImageView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityProfilePhotoBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final AppCompatButton button;

  @NonNull
  public final FloatingActionButton floatingActionButton;

  @NonNull
  public final LinearLayout linearLayout;

  @NonNull
  public final CircleImageView preview;

  @NonNull
  public final TextView textView;

  @NonNull
  public final ImageView userFemale;

  @NonNull
  public final ImageView userMale;

  private ActivityProfilePhotoBinding(@NonNull RelativeLayout rootView,
      @NonNull AppCompatButton button, @NonNull FloatingActionButton floatingActionButton,
      @NonNull LinearLayout linearLayout, @NonNull CircleImageView preview,
      @NonNull TextView textView, @NonNull ImageView userFemale, @NonNull ImageView userMale) {
    this.rootView = rootView;
    this.button = button;
    this.floatingActionButton = floatingActionButton;
    this.linearLayout = linearLayout;
    this.preview = preview;
    this.textView = textView;
    this.userFemale = userFemale;
    this.userMale = userMale;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityProfilePhotoBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityProfilePhotoBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_profile_photo, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityProfilePhotoBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.button;
      AppCompatButton button = ViewBindings.findChildViewById(rootView, id);
      if (button == null) {
        break missingId;
      }

      id = R.id.floatingActionButton;
      FloatingActionButton floatingActionButton = ViewBindings.findChildViewById(rootView, id);
      if (floatingActionButton == null) {
        break missingId;
      }

      id = R.id.linearLayout;
      LinearLayout linearLayout = ViewBindings.findChildViewById(rootView, id);
      if (linearLayout == null) {
        break missingId;
      }

      id = R.id.preview;
      CircleImageView preview = ViewBindings.findChildViewById(rootView, id);
      if (preview == null) {
        break missingId;
      }

      id = R.id.textView;
      TextView textView = ViewBindings.findChildViewById(rootView, id);
      if (textView == null) {
        break missingId;
      }

      id = R.id.user_female;
      ImageView userFemale = ViewBindings.findChildViewById(rootView, id);
      if (userFemale == null) {
        break missingId;
      }

      id = R.id.user_male;
      ImageView userMale = ViewBindings.findChildViewById(rootView, id);
      if (userMale == null) {
        break missingId;
      }

      return new ActivityProfilePhotoBinding((RelativeLayout) rootView, button,
          floatingActionButton, linearLayout, preview, textView, userFemale, userMale);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}