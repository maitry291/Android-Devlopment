// Generated by view binder compiler. Do not edit!
package com.example.chatbox.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.Guideline;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.chatbox.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class SampleSentBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final Guideline guideline2;

  @NonNull
  public final Guideline guideline3;

  @NonNull
  public final TextView sentMsg;

  @NonNull
  public final TextView sentTime;

  private SampleSentBinding(@NonNull RelativeLayout rootView, @NonNull Guideline guideline2,
      @NonNull Guideline guideline3, @NonNull TextView sentMsg, @NonNull TextView sentTime) {
    this.rootView = rootView;
    this.guideline2 = guideline2;
    this.guideline3 = guideline3;
    this.sentMsg = sentMsg;
    this.sentTime = sentTime;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static SampleSentBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static SampleSentBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.sample_sent, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static SampleSentBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.guideline2;
      Guideline guideline2 = ViewBindings.findChildViewById(rootView, id);
      if (guideline2 == null) {
        break missingId;
      }

      id = R.id.guideline3;
      Guideline guideline3 = ViewBindings.findChildViewById(rootView, id);
      if (guideline3 == null) {
        break missingId;
      }

      id = R.id.sent_msg;
      TextView sentMsg = ViewBindings.findChildViewById(rootView, id);
      if (sentMsg == null) {
        break missingId;
      }

      id = R.id.sent_time;
      TextView sentTime = ViewBindings.findChildViewById(rootView, id);
      if (sentTime == null) {
        break missingId;
      }

      return new SampleSentBinding((RelativeLayout) rootView, guideline2, guideline3, sentMsg,
          sentTime);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}