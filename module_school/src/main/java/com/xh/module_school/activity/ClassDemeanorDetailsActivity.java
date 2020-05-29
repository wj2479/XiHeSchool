package com.xh.module_school.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.xh.module.base.BackActivity;
import com.xh.module.base.entity.ClassDemeanor;
import com.xh.module_school.R;
import com.xh.module_school.R2;
import com.zzhoujay.richtext.RichText;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 校园风采详情
 */
public class ClassDemeanorDetailsActivity extends BackActivity {
    public static final String DEMEANOR = "demeanor";

    @BindView(R2.id.contentTv)
    TextView contentTv;

    ClassDemeanor demeanor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_demeanor_details);

        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        if (getIntent().hasExtra(DEMEANOR)) {
            demeanor = getIntent().getParcelableExtra(DEMEANOR);
            setTitle(demeanor.getTitle());
            RichText.fromHtml(demeanor.getContent()).into(contentTv);
        }

    }
}
