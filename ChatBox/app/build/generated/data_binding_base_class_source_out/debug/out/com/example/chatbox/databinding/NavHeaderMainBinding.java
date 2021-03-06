// Generated by view binder compiler. Do not edit!
package com.example.chatbox.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.chatbox.R;
import de.hdodenhof.circleimageview.CircleImageView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class NavHeaderMainBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final CircleImageView imageView2;

  @NonNull
  public final LinearLayout navHeaderLayout;

  @NonNull
  public final TextView profileEmailPhone;

  @NonNull
  public final TextView profileUsername;

  private NavHeaderMainBinding(@NonNull LinearLayout rootView, @NonNull CircleImageView imageView2,
      @NonNull LinearLayout navHeaderLayout, @NonNull TextView profileEmailPhone,
      @NonNull TextView profileUsername) {
    this.rootView = rootView;
    this.imageView2 = imageView2;
    this.navHeaderLayout = navHeaderLayout;
    this.profileEmailPhone = profileEmailPhone;
    this.profileUsername = profileUsername;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static NavHeaderMainBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static NavHeaderMainBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.nav_header_main, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static NavHeaderMainBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.imageView2;
      CircleImageView imageView2 = ViewBindings.findChildViewById(rootView, id);
      if (imageView2 == null) {
        break missingId;
      }

      LinearLayout navHeaderLayout = (LinearLayout) rootView;

      id = R.id.profile_email_phone;
      TextView profileEmailPhone = ViewBindings.findChildViewById(rootView, id);
      if (profileEmailPhone == null) {
        break missingId;
      }

      id = R.id.profile_username;
      TextView profileUsername = ViewBindings.findChildViewById(rootView, id);
      if (profileUsername == null) {
        break missingId;
      }

      return new NavHeaderMainBinding((LinearLayout) rootView, imageView2, navHeaderLayout,
          profileEmailPhone, profileUsername);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
