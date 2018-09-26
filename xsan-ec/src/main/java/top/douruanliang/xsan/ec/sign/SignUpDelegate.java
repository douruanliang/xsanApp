package top.douruanliang.xsan.ec.sign;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import top.douruanliang.xsan.core.delegate.XsanDelegate;
import top.douruanliang.xsan.ec.R;
import top.douruanliang.xsan.ec.R2;

public class SignUpDelegate extends XsanDelegate {
    @BindView(R2.id.edit_sign_up_name)
    TextInputEditText editSignUpName;
    @BindView(R2.id.edit_sign_up_email)
    TextInputEditText editSignUpEmail;
    @BindView(R2.id.edit_sign_up_phone)
    TextInputEditText editSignUpPhone;
    @BindView(R2.id.edit_sign_up_password)
    TextInputEditText editSignUpPassword;
    @BindView(R2.id.edit_sign_up_re_password)
    TextInputEditText editSignUpRePassword;
    @BindView(R2.id.btn_sign_up)
    AppCompatButton btnSignUp;
    @BindView(R2.id.tv_link_sign_in)
    AppCompatTextView tvLinkSignIn;
    Unbinder unbinder;

    @OnClick(R2.id.btn_sign_up)
    void onClickSignUp(){
        if (checkForm()){
     /*       RestClient.builder()
                    .url("sign_up")
                    .params("","")
                    .success(new ISuccess() {
                        @Override
                        public void onSuccess(String response) {

                        }
                    })
                    .build()
                    .post();*/

            Toast.makeText(getContext(),"success",Toast.LENGTH_LONG).show();
        }
    }
    @OnClick(R2.id.tv_link_sign_in)
    void onClickLink() {
        start(new SignInDelegate());
    }
    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_up;
    }

    @Override
    public void onBindView(@Nullable Bundle saveInstanceState, View view) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private boolean checkForm(){
        final String name = editSignUpName.getText().toString();
        final String email = editSignUpEmail.getText().toString();
        final String phone = editSignUpPhone.getText().toString();
        final String password = editSignUpPassword.getText().toString();
        final String rePassword = editSignUpRePassword.getText().toString();

        boolean isPass = true;

        if (name.isEmpty()){
            editSignUpName.setError("请输入姓名");
            isPass = false;
        }else{
            editSignUpName.setError(null);
        }

        if (email.isEmpty() || Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editSignUpEmail.setError("错误的邮箱各格式");
            isPass = false;
        }else{
            editSignUpEmail.setError(null);
        }

        if (phone.isEmpty()|| phone.length()!=11){
            editSignUpPhone.setError("手机号码错误");
            isPass = false;
        }else{
            editSignUpPhone.setError(null);
        }

        if (password.isEmpty()|| password.length()<6){
            editSignUpPassword.setError("请填写至少6位📖密码");
            isPass = false;
        }else{
            editSignUpPassword.setError(null);
        }

        if (rePassword.isEmpty()|| rePassword.length()<6 || !(rePassword.equals(password))){
            editSignUpRePassword.setError("密码验证错误");
            isPass = false;
        }else{
            editSignUpRePassword.setError(null);
        }

        return  isPass;
    }
}
