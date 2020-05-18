package com.xh.module_school.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.xh.module.base.BackActivity;
import com.xh.module.base.entity.SchoolInformation;
import com.xh.module_school.R;
import com.xh.module_school.R2;
import com.zzhoujay.richtext.RichText;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 资讯详情
 */
public class SchoolInfomationDetailsActivity extends BackActivity {

    public static final String INFOMATION = "infomation";

    @BindView(R2.id.contentTv)
    TextView contentTv;

    SchoolInformation information;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_infomation_details);

        ButterKnife.bind(this);

        initData();
    }

    private void initData() {
        if (getIntent().hasExtra(INFOMATION)) {
            information = getIntent().getParcelableExtra(INFOMATION);
            setTitle(information.getTitle());
            RichText.fromHtml(information.getContent()).into(contentTv);
        }

    }
}
