package cn.ucai.superwechat.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.hyphenate.easeui.domain.User;
import com.hyphenate.easeui.utils.EaseUserUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.ucai.superwechat.I;
import cn.ucai.superwechat.R;
import cn.ucai.superwechat.SuperWeChatHelper;
import cn.ucai.superwechat.utils.MFGT;

/**
 * Created by Administrator on 2016/11/10.
 */
public class FriendProfileActivity extends BaseActivity {
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.iv_profile_avatar)
    ImageView ivProfileAvatar;
    @BindView(R.id.tv_profile_nickname)
    TextView tvProfileNickname;
    @BindView(R.id.tv_profile_username)
    TextView tvProfileUsername;

    User user = null;
    @BindView(R.id.add_friend)
    Button addFriend;
    @BindView(R.id.send_video)
    Button sendVideo;
    @BindView(R.id.send_message)
    Button sendMessage;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frend_profile);
        ButterKnife.bind(this);
        user = (User) getIntent().getSerializableExtra(I.User.USER_NAME);
        if (user == null) {
            MFGT.finish(this);
            return;
        }
        initView();
    }

    private void initView() {
        imgBack.setVisibility(View.VISIBLE);
        txtTitle.setVisibility(View.VISIBLE);
        txtTitle.setText(getString(R.string.userinfo_txt_profile));
        setUserInfo();
        isFriend();
    }

    private void isFriend() {
        if (SuperWeChatHelper.getInstance().getAppContactList().containsKey(user.getMUserName())) {
            sendMessage.setVisibility(View.VISIBLE);
            sendVideo.setVisibility(View.VISIBLE);
        }else{
            addFriend.setVisibility(View.VISIBLE);
        }
    }

    private void setUserInfo() {
        EaseUserUtils.setAppUserAvatar(this, user.getMUserName(), ivProfileAvatar);
        EaseUserUtils.setAppUserNick(user.getMUserNick(), tvProfileNickname);
        EaseUserUtils.setAppUsernameWithNo(user.getMUserName(), tvProfileUsername);
    }

    @OnClick(R.id.img_back)
    public void onClick() {
        finish();
    }

    @OnClick({R.id.add_friend, R.id.send_video,R.id.send_message})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add_friend:
                MFGT.gotoAddFriendMsg(this,user.getMUserName());
                break;
            case R.id.send_video:
                break;
            case R.id.send_message:
                MFGT.gotoChat(this,user.getMUserName());
                break;
        }
    }
}
