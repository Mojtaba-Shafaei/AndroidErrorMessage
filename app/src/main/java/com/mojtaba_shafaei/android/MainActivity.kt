package com.mojtaba_shafaei.android

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_close.setOnClickListener { em.hide() }

        btnShowInternetError.setOnClickListener { em.showInternetError() }
        btnShowError.setOnClickListener { em.showError(getString(R.string.error_String)) }
        btnShowNoDataError.setOnClickListener { em.showNoData() }
        btnShowLoading.setOnClickListener { em.showLoading() }
        btnShowListLoading.setOnClickListener { em.showListLoading() }
    }
}
