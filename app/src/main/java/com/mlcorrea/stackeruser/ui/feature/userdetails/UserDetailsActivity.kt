package com.mlcorrea.stackeruser.ui.feature.userdetails

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.google.android.material.button.MaterialButton
import com.jakewharton.rxbinding2.view.RxView
import com.mlcorrea.stackeruser.R
import com.mlcorrea.stackeruser.domain.model.User
import com.mlcorrea.stackeruser.framework.extension.observe
import com.mlcorrea.stackeruser.framework.extension.parseToDateSting
import com.mlcorrea.stackeruser.ui.base.BaseActivity
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
import io.reactivex.android.schedulers.AndroidSchedulers
import org.koin.androidx.scope.currentScope
import java.util.concurrent.TimeUnit

class UserDetailsActivity : BaseActivity() {


    @BindView(R.id.ui_name)
    lateinit var uiName: TextView
    @BindView(R.id.ui_location)
    lateinit var uiLocation: TextView
    @BindView(R.id.ui_date)
    lateinit var uiDate: TextView
    @BindView(R.id.ui_follow_btn)
    lateinit var uiFollowBtn: MaterialButton
    @BindView(R.id.ui_block_btn)
    lateinit var uiBlockBtn: MaterialButton
    @BindView(R.id.ui_reputation)
    lateinit var uiReputation: TextView
    @BindView(R.id.ui_profile_photo)
    lateinit var uiImage: CircleImageView

    private val viewModel: UserDetailsViewModel by currentScope.inject()

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_details)
        ButterKnife.bind(this)

        viewModel.apply {
            observe(userData, ::handleUserChanges)
        }

        intent.extras?.let {
            val user = it.getSerializable(INTENT_EXTRA_USER) as? User
            user?.let { userdata ->
                viewModel.setUserData(userdata)
            }
        }

        RxView.clicks(uiBlockBtn)
            .throttleFirst(300, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                viewModel.onClickedBlock()
            }
        RxView.clicks(uiFollowBtn)
            .throttleFirst(300, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                viewModel.onClickedFollow()
            }
    }

    override fun onBackPressed() {
        val user = viewModel.userData.value
        if (user != null) {
            val intent = Intent().apply {
                putExtra(INTENT_RESULT_USER, user)
            }
            setResult(Activity.RESULT_OK, intent)
            finish()
        } else {
            super.onBackPressed()
        }
    }

    private fun handleUserChanges(user: User?) {
        val formatName = "%s (%s)"
        user?.apply {
            uiName.text = String.format(formatName, name, id.toString())
            uiLocation.text = location

            uiDate.text = creationDate.parseToDateSting()
            uiReputation.text = reputation.toString()
            uiFollowBtn.text = if (follow) getString(R.string.text_unfollow) else getString(R.string.text_follow)
            uiBlockBtn.text = if (block) getString(R.string.text_unblock) else getString(R.string.text_block)

            val image = profileImage
            if (image != null && image.isNotEmpty()) {
                Picasso.with(this@UserDetailsActivity)
                    .load(image)
                    .placeholder(R.color.colorPrimary)
                    .error(R.color.colorAccent)
                    .into(uiImage)
            } else {
                Picasso.with(this@UserDetailsActivity)
                    .load(R.drawable.ic_person_24dp)
                    .placeholder(R.color.colorPrimary)
                    .error(R.color.colorAccent)
                    .into(uiImage)
            }
        }
    }

    companion object {
        const val INTENT_RESULT_USER = "INTENT_RESULT_USER"
        private const val INTENT_EXTRA_USER = "INTENT_EXTRA_USER"

        @JvmStatic
        fun newIntent(context: Context, user: User): Intent {
            return Intent(context, UserDetailsActivity::class.java).apply {
                putExtra(INTENT_EXTRA_USER, user)
            }
        }
    }
}
