// Generated by view binder compiler. Do not edit!
package com.example.chatbox.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
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
import com.hbb20.CountryCodePicker;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivitySignUp2Binding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final AppCompatButton btnGetOTP;

  @NonNull
  public final AppCompatButton btnSignUp2;

  @NonNull
  public final ImageView btnVerify;

  @NonNull
  public final CountryCodePicker ccp;

  @NonNull
  public final EditText editTextOTP;

  @NonNull
  public final EditText editTextPhone;

  @NonNull
  public final ImageView imageView5;

  @NonNull
  public final RelativeLayout relativeLayout;

  @NonNull
  public final EditText signup2Name;

  @NonNull
  public final TextView signup2ToSignin;

  @NonNull
  public final TextView textView7;

  private ActivitySignUp2Binding(@NonNull LinearLayout rootView, @NonNull AppCompatButton btnGetOTP,
      @NonNull AppCompatButton btnSignUp2, @NonNull ImageView btnVerify,
      @NonNull CountryCodePicker ccp, @NonNull EditText editTextOTP,
      @NonNull EditText editTextPhone, @NonNull ImageView imageView5,
      @NonNull RelativeLayout relativeLayout, @NonNull EditText signup2Name,
      @NonNull TextView signup2ToSignin, @NonNull TextView textView7) {
    this.rootView = rootView;
    this.btnGetOTP = btnGetOTP;
    this.btnSignUp2 = btnSignUp2;
    this.btnVerify = btnVerify;
    this.ccp = ccp;
    this.editTextOTP = editTextOTP;
    this.editTextPhone = editTextPhone;
    this.imageView5 = imageView5;
    this.relativeLayout = relativeLayout;
    this.signup2Name = signup2Name;
    this.signup2ToSignin = signup2ToSignin;
    this.textView7 = textView7;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivitySignUp2Binding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivitySignUp2Binding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_sign_up2, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivitySignUp2Binding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btn_getOTP;
      AppCompatButton btnGetOTP = ViewBindings.findChildViewById(rootView, id);
      if (btnGetOTP == null) {
        break missingId;
      }

      id = R.id.btnSignUp2;
      AppCompatButton btnSignUp2 = ViewBindings.findChildViewById(rootView, id);
      if (btnSignUp2 == null) {
        break missingId;
      }

      id = R.id.btnVerify;
      ImageView btnVerify = ViewBindings.findChildViewById(rootView, id);
      if (btnVerify == null) {
        break missingId;
      }

      id = R.id.ccp;
      CountryCodePicker ccp = ViewBindings.findChildViewById(rootView, id);
      if (ccp == null) {
        break missingId;
      }

      id = R.id.editTextOTP;
      EditText editTextOTP = ViewBindings.findChildViewById(rootView, id);
      if (editTextOTP == null) {
        break missingId;
      }

      id = R.id.editTextPhone;
      EditText editTextPhone = ViewBindings.findChildViewById(rootView, id);
      if (editTextPhone == null) {
        break missingId;
      }

      id = R.id.imageView5;
      ImageView imageView5 = ViewBindings.findChildViewById(rootView, id);
      if (imageView5 == null) {
        break missingId;
      }

      id = R.id.relativeLayout;
      RelativeLayout relativeLayout = ViewBindings.findChildViewById(rootView, id);
      if (relativeLayout == null) {
        break missingId;
      }

      id = R.id.signup2_name;
      EditText signup2Name = ViewBindings.findChildViewById(rootView, id);
      if (signup2Name == null) {
        break missingId;
      }

      id = R.id.signup2_to_signin;
      TextView signup2ToSignin = ViewBindings.findChildViewById(rootView, id);
      if (signup2ToSignin == null) {
        break missingId;
      }

      id = R.id.textView7;
      TextView textView7 = ViewBindings.findChildViewById(rootView, id);
      if (textView7 == null) {
        break missingId;
      }

      return new ActivitySignUp2Binding((LinearLayout) rootView, btnGetOTP, btnSignUp2, btnVerify,
          ccp, editTextOTP, editTextPhone, imageView5, relativeLayout, signup2Name, signup2ToSignin,
          textView7);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
